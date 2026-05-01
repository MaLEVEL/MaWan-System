-- Initial and sample data for Donor Management System.
-- The statements are idempotent so the application can run this file on every startup.

-- =====================================================
-- Roles
-- =====================================================
INSERT INTO roles (name, description) VALUES
('ROLE_ADMIN', 'System Administrator'),
('ROLE_DOCTOR', 'Medical Doctor'),
('ROLE_LAB_TECH', 'Laboratory Technician'),
('ROLE_REGISTRAR', 'Registration Staff'),
('ROLE_REPORT_VIEWER', 'Report Viewer'),
('ROLE_DONOR', 'Donor self-service user')
ON DUPLICATE KEY UPDATE description = VALUES(description);

-- =====================================================
-- Users
-- Password for non-admin seeded accounts: password
-- The DataInitializer resets admin to admin123 after SQL initialization.
-- =====================================================
INSERT INTO users (username, password_hash, full_name, email, enabled, donor_id) VALUES
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'System Administrator', 'admin@example.com', true, NULL),
('doctor_wang', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Wang Doctor', 'doctor.wang@example.com', true, NULL),
('lab_zhang', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Zhang Lab Technician', 'lab.zhang@example.com', true, NULL),
('registrar_liu', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Liu Registrar', 'registrar.liu@example.com', true, NULL),
('viewer_chen', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Chen Report Viewer', 'viewer.chen@example.com', true, NULL),
('donor_demo', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Demo Donor', 'donor.demo@example.com', true, NULL)
ON DUPLICATE KEY UPDATE
  password_hash = VALUES(password_hash),
  full_name = VALUES(full_name),
  email = VALUES(email),
  enabled = VALUES(enabled);

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u JOIN roles r ON r.name = 'ROLE_ADMIN'
WHERE u.username = 'admin'
  AND NOT EXISTS (
    SELECT 1 FROM user_roles ur WHERE ur.user_id = u.id AND ur.role_id = r.id
  );

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u JOIN roles r ON r.name = 'ROLE_DOCTOR'
WHERE u.username = 'doctor_wang'
  AND NOT EXISTS (
    SELECT 1 FROM user_roles ur WHERE ur.user_id = u.id AND ur.role_id = r.id
  );

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u JOIN roles r ON r.name = 'ROLE_LAB_TECH'
WHERE u.username = 'lab_zhang'
  AND NOT EXISTS (
    SELECT 1 FROM user_roles ur WHERE ur.user_id = u.id AND ur.role_id = r.id
  );

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u JOIN roles r ON r.name = 'ROLE_REGISTRAR'
WHERE u.username = 'registrar_liu'
  AND NOT EXISTS (
    SELECT 1 FROM user_roles ur WHERE ur.user_id = u.id AND ur.role_id = r.id
  );

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u JOIN roles r ON r.name = 'ROLE_REPORT_VIEWER'
WHERE u.username = 'viewer_chen'
  AND NOT EXISTS (
    SELECT 1 FROM user_roles ur WHERE ur.user_id = u.id AND ur.role_id = r.id
  );

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u JOIN roles r ON r.name = 'ROLE_DONOR'
WHERE u.username = 'donor_demo'
  AND NOT EXISTS (
    SELECT 1 FROM user_roles ur WHERE ur.user_id = u.id AND ur.role_id = r.id
  );

-- =====================================================
-- Donors
-- =====================================================
INSERT INTO donors (first_name, last_name, date_of_birth, gender, passport_number, phone, email, address, blood_type, hla_type, active) VALUES
('Qiang', 'Zhang', '1985-03-15', 'MALE', '10001001', '13800138001', 'zhang.qiang@example.com', 'Beijing Chaoyang District', 'I_POSITIVE', 'A*02:01', true),
('Wei', 'Li', '1990-07-22', 'MALE', '10001002', '13800138002', 'li.wei@example.com', 'Shanghai Pudong New Area', 'II_POSITIVE', 'B*07:02', true),
('Ming', 'Wang', '1988-11-08', 'MALE', '10001003', '13800138003', 'wang.ming@example.com', 'Guangzhou Tianhe District', 'III_NEGATIVE', 'A*24:02', true),
('Fang', 'Lin', '1993-05-28', 'FEMALE', '10001004', '13900139001', 'lin.fang@example.com', 'Beijing Haidian District', 'I_POSITIVE', 'A*02:01', true),
('Na', 'Zheng', '1996-10-15', 'FEMALE', '10001005', '13900139002', 'zheng.na@example.com', 'Shanghai Jing''an District', 'II_NEGATIVE', 'B*07:02', true),
('Lei', 'Chen', '1987-09-12', 'MALE', '10001006', '13800138006', 'chen.lei@example.com', 'Hangzhou Xihu District', 'IV_POSITIVE', 'B*15:01', true),
('Min', 'Sun', '1986-03-08', 'FEMALE', '10001007', '13900139003', 'sun.min@example.com', 'Guangzhou Yuexiu District', 'III_POSITIVE', 'A*24:02', true),
('Jun', 'Huang', '1991-12-05', 'MALE', '10001008', '13800138008', 'huang.jun@example.com', 'Nanjing Gulou District', 'IV_NEGATIVE', 'B*08:01', false)
ON DUPLICATE KEY UPDATE
  first_name = VALUES(first_name),
  last_name = VALUES(last_name),
  date_of_birth = VALUES(date_of_birth),
  gender = VALUES(gender),
  phone = VALUES(phone),
  email = VALUES(email),
  address = VALUES(address),
  blood_type = VALUES(blood_type),
  hla_type = VALUES(hla_type),
  active = VALUES(active);

UPDATE users
SET donor_id = (SELECT id FROM donors WHERE passport_number = '10001001')
WHERE username = 'donor_demo';

-- =====================================================
-- Medical checks
-- =====================================================
INSERT INTO medical_checks (donor_id, check_date, hemoglobin, systolic_pressure, diastolic_pressure, conclusion)
SELECT d.id, '2026-04-01 09:00:00', 145.5, 120, 80, 'FIT'
FROM donors d
WHERE d.passport_number = '10001001'
  AND NOT EXISTS (SELECT 1 FROM medical_checks mc WHERE mc.donor_id = d.id AND mc.check_date = '2026-04-01 09:00:00');

INSERT INTO medical_checks (donor_id, check_date, hemoglobin, systolic_pressure, diastolic_pressure, conclusion)
SELECT d.id, '2026-04-02 10:30:00', 152.0, 118, 75, 'FIT'
FROM donors d
WHERE d.passport_number = '10001002'
  AND NOT EXISTS (SELECT 1 FROM medical_checks mc WHERE mc.donor_id = d.id AND mc.check_date = '2026-04-02 10:30:00');

INSERT INTO medical_checks (donor_id, check_date, hemoglobin, systolic_pressure, diastolic_pressure, conclusion)
SELECT d.id, '2026-04-03 14:00:00', 138.5, 125, 82, 'FIT'
FROM donors d
WHERE d.passport_number = '10001003'
  AND NOT EXISTS (SELECT 1 FROM medical_checks mc WHERE mc.donor_id = d.id AND mc.check_date = '2026-04-03 14:00:00');

INSERT INTO medical_checks (donor_id, check_date, hemoglobin, systolic_pressure, diastolic_pressure, conclusion)
SELECT d.id, '2026-04-04 09:30:00', 128.5, 110, 70, 'FIT'
FROM donors d
WHERE d.passport_number = '10001004'
  AND NOT EXISTS (SELECT 1 FROM medical_checks mc WHERE mc.donor_id = d.id AND mc.check_date = '2026-04-04 09:30:00');

INSERT INTO medical_checks (donor_id, check_date, hemoglobin, systolic_pressure, diastolic_pressure, conclusion)
SELECT d.id, '2026-04-05 11:00:00', 108.0, 145, 95, 'UNFIT'
FROM donors d
WHERE d.passport_number = '10001005'
  AND NOT EXISTS (SELECT 1 FROM medical_checks mc WHERE mc.donor_id = d.id AND mc.check_date = '2026-04-05 11:00:00');

INSERT INTO medical_checks (donor_id, check_date, hemoglobin, systolic_pressure, diastolic_pressure, conclusion)
SELECT d.id, '2026-04-06 15:00:00', 118.0, 136, 88, 'NEEDS_RECHECK'
FROM donors d
WHERE d.passport_number = '10001006'
  AND NOT EXISTS (SELECT 1 FROM medical_checks mc WHERE mc.donor_id = d.id AND mc.check_date = '2026-04-06 15:00:00');

INSERT INTO medical_checks (donor_id, check_date, hemoglobin, systolic_pressure, diastolic_pressure, conclusion)
SELECT d.id, '2026-04-07 09:45:00', 132.0, 116, 74, 'FIT'
FROM donors d
WHERE d.passport_number = '10001007'
  AND NOT EXISTS (SELECT 1 FROM medical_checks mc WHERE mc.donor_id = d.id AND mc.check_date = '2026-04-07 09:45:00');

INSERT INTO medical_checks (donor_id, check_date, hemoglobin, systolic_pressure, diastolic_pressure, conclusion)
SELECT d.id, '2026-04-08 13:30:00', 122.5, 130, 85, 'NEEDS_RECHECK'
FROM donors d
WHERE d.passport_number = '10001008'
  AND NOT EXISTS (SELECT 1 FROM medical_checks mc WHERE mc.donor_id = d.id AND mc.check_date = '2026-04-08 13:30:00');

-- =====================================================
-- Donations
-- =====================================================
INSERT INTO donations (donor_id, type, planned_at, performed_at, status, volume_ml, notes, pre_check_id)
SELECT d.id, 'BLOOD', '2026-04-10 09:00:00', '2026-04-10 09:40:00', 'COMPLETED', 400, 'Completed without adverse reaction', mc.id
FROM donors d
JOIN medical_checks mc ON mc.donor_id = d.id AND mc.check_date = '2026-04-01 09:00:00'
WHERE d.passport_number = '10001001'
  AND NOT EXISTS (SELECT 1 FROM donations dn WHERE dn.donor_id = d.id AND dn.planned_at = '2026-04-10 09:00:00');

INSERT INTO donations (donor_id, type, planned_at, performed_at, status, volume_ml, notes, pre_check_id)
SELECT d.id, 'BLOOD', '2026-04-11 10:00:00', '2026-04-11 10:35:00', 'COMPLETED', 350, 'Mild dizziness resolved after rest', mc.id
FROM donors d
JOIN medical_checks mc ON mc.donor_id = d.id AND mc.check_date = '2026-04-02 10:30:00'
WHERE d.passport_number = '10001002'
  AND NOT EXISTS (SELECT 1 FROM donations dn WHERE dn.donor_id = d.id AND dn.planned_at = '2026-04-11 10:00:00');

INSERT INTO donations (donor_id, type, planned_at, performed_at, status, volume_ml, notes, pre_check_id)
SELECT d.id, 'BONE_MARROW', '2026-04-12 08:00:00', '2026-04-12 12:10:00', 'COMPLETED', 160, 'Bone marrow collection completed', mc.id
FROM donors d
JOIN medical_checks mc ON mc.donor_id = d.id AND mc.check_date = '2026-04-03 14:00:00'
WHERE d.passport_number = '10001003'
  AND NOT EXISTS (SELECT 1 FROM donations dn WHERE dn.donor_id = d.id AND dn.planned_at = '2026-04-12 08:00:00');

INSERT INTO donations (donor_id, type, planned_at, performed_at, status, volume_ml, notes, pre_check_id)
SELECT d.id, 'BLOOD', '2026-04-29 09:00:00', NULL, 'PLANNED', NULL, 'Planned regular whole blood donation', mc.id
FROM donors d
JOIN medical_checks mc ON mc.donor_id = d.id AND mc.check_date = '2026-04-04 09:30:00'
WHERE d.passport_number = '10001004'
  AND NOT EXISTS (SELECT 1 FROM donations dn WHERE dn.donor_id = d.id AND dn.planned_at = '2026-04-29 09:00:00');

INSERT INTO donations (donor_id, type, planned_at, performed_at, status, volume_ml, notes, pre_check_id)
SELECT d.id, 'BLOOD', '2026-04-16 11:00:00', NULL, 'REJECTED', NULL, 'Rejected because the medical check result was unfit', mc.id
FROM donors d
JOIN medical_checks mc ON mc.donor_id = d.id AND mc.check_date = '2026-04-05 11:00:00'
WHERE d.passport_number = '10001005'
  AND NOT EXISTS (SELECT 1 FROM donations dn WHERE dn.donor_id = d.id AND dn.planned_at = '2026-04-16 11:00:00');

INSERT INTO donations (donor_id, type, planned_at, performed_at, status, volume_ml, notes, pre_check_id)
SELECT d.id, 'BLOOD', '2026-04-18 15:00:00', NULL, 'CANCELLED', NULL, 'Cancelled by donor before collection', mc.id
FROM donors d
JOIN medical_checks mc ON mc.donor_id = d.id AND mc.check_date = '2026-04-06 15:00:00'
WHERE d.passport_number = '10001006'
  AND NOT EXISTS (SELECT 1 FROM donations dn WHERE dn.donor_id = d.id AND dn.planned_at = '2026-04-18 15:00:00');

-- =====================================================
-- Inventory
-- =====================================================
INSERT INTO inventory_items (type, donation_id, blood_type, collected_at, expiry_date, status, storage_location, comment)
SELECT 'WHOLE_BLOOD', dn.id, 'I_POSITIVE', '2026-04-10', '2026-05-22', 'IN_STOCK', 'A-01-01', 'Whole blood unit from donation 10001001'
FROM donations dn
JOIN donors d ON d.id = dn.donor_id
WHERE d.passport_number = '10001001' AND dn.planned_at = '2026-04-10 09:00:00'
  AND NOT EXISTS (SELECT 1 FROM inventory_items ii WHERE ii.donation_id = dn.id AND ii.type = 'WHOLE_BLOOD' AND ii.storage_location = 'A-01-01');

INSERT INTO inventory_items (type, donation_id, blood_type, collected_at, expiry_date, status, storage_location, comment)
SELECT 'PLASMA', dn.id, 'I_POSITIVE', '2026-04-10', '2027-04-10', 'RESERVED', 'B-01-01', 'Plasma reserved for scheduled surgery'
FROM donations dn
JOIN donors d ON d.id = dn.donor_id
WHERE d.passport_number = '10001001' AND dn.planned_at = '2026-04-10 09:00:00'
  AND NOT EXISTS (SELECT 1 FROM inventory_items ii WHERE ii.donation_id = dn.id AND ii.type = 'PLASMA' AND ii.storage_location = 'B-01-01');

INSERT INTO inventory_items (type, donation_id, blood_type, collected_at, expiry_date, status, storage_location, comment)
SELECT 'PLATELETS', dn.id, 'II_POSITIVE', '2026-04-11', '2026-04-16', 'EXPIRED', 'C-01-01', 'Platelets kept for expiry status testing'
FROM donations dn
JOIN donors d ON d.id = dn.donor_id
WHERE d.passport_number = '10001002' AND dn.planned_at = '2026-04-11 10:00:00'
  AND NOT EXISTS (SELECT 1 FROM inventory_items ii WHERE ii.donation_id = dn.id AND ii.type = 'PLATELETS' AND ii.storage_location = 'C-01-01');

INSERT INTO inventory_items (type, donation_id, blood_type, collected_at, expiry_date, status, storage_location, comment)
SELECT 'WHOLE_BLOOD', dn.id, 'II_POSITIVE', '2026-04-11', '2026-05-23', 'USED', 'A-01-02', 'Used whole blood unit'
FROM donations dn
JOIN donors d ON d.id = dn.donor_id
WHERE d.passport_number = '10001002' AND dn.planned_at = '2026-04-11 10:00:00'
  AND NOT EXISTS (SELECT 1 FROM inventory_items ii WHERE ii.donation_id = dn.id AND ii.type = 'WHOLE_BLOOD' AND ii.storage_location = 'A-01-02');

INSERT INTO inventory_items (type, donation_id, blood_type, collected_at, expiry_date, status, storage_location, comment)
SELECT 'BONE_MARROW_SAMPLE', dn.id, 'III_NEGATIVE', '2026-04-12', '2026-04-19', 'IN_STOCK', 'D-01-01', 'Bone marrow sample stored at low temperature'
FROM donations dn
JOIN donors d ON d.id = dn.donor_id
WHERE d.passport_number = '10001003' AND dn.planned_at = '2026-04-12 08:00:00'
  AND NOT EXISTS (SELECT 1 FROM inventory_items ii WHERE ii.donation_id = dn.id AND ii.type = 'BONE_MARROW_SAMPLE' AND ii.storage_location = 'D-01-01');

INSERT INTO inventory_items (type, donation_id, blood_type, collected_at, expiry_date, status, storage_location, comment)
SELECT 'BONE_MARROW_SAMPLE', dn.id, 'III_NEGATIVE', '2026-04-12', '2026-04-19', 'DISCARDED', 'D-01-02', 'Quality control failed'
FROM donations dn
JOIN donors d ON d.id = dn.donor_id
WHERE d.passport_number = '10001003' AND dn.planned_at = '2026-04-12 08:00:00'
  AND NOT EXISTS (SELECT 1 FROM inventory_items ii WHERE ii.donation_id = dn.id AND ii.type = 'BONE_MARROW_SAMPLE' AND ii.storage_location = 'D-01-02');

-- =====================================================
-- Appointments
-- =====================================================
INSERT INTO appointments (donor_id, type, start_time, end_time, status, location, doctor_name)
SELECT d.id, 'BLOOD', '2026-04-29 09:00:00', '2026-04-29 10:00:00', 'PLANNED', 'Collection Room A', 'Dr. Wang'
FROM donors d
WHERE d.passport_number = '10001004'
  AND NOT EXISTS (SELECT 1 FROM appointments ap WHERE ap.donor_id = d.id AND ap.start_time = '2026-04-29 09:00:00');

INSERT INTO appointments (donor_id, type, start_time, end_time, status, location, doctor_name)
SELECT d.id, 'BLOOD', '2026-04-30 10:30:00', '2026-04-30 11:30:00', 'CONFIRMED', 'Collection Room B', 'Dr. Wang'
FROM donors d
WHERE d.passport_number = '10001007'
  AND NOT EXISTS (SELECT 1 FROM appointments ap WHERE ap.donor_id = d.id AND ap.start_time = '2026-04-30 10:30:00');

INSERT INTO appointments (donor_id, type, start_time, end_time, status, location, doctor_name)
SELECT d.id, 'BONE_MARROW', '2026-05-02 08:00:00', '2026-05-02 12:00:00', 'PLANNED', 'Bone Marrow Collection Room', 'Dr. Li'
FROM donors d
WHERE d.passport_number = '10001003'
  AND NOT EXISTS (SELECT 1 FROM appointments ap WHERE ap.donor_id = d.id AND ap.start_time = '2026-05-02 08:00:00');

INSERT INTO appointments (donor_id, type, start_time, end_time, status, location, doctor_name)
SELECT d.id, 'BLOOD', '2026-04-20 14:00:00', '2026-04-20 15:00:00', 'CANCELLED', 'Collection Room A', 'Dr. Wang'
FROM donors d
WHERE d.passport_number = '10001006'
  AND NOT EXISTS (SELECT 1 FROM appointments ap WHERE ap.donor_id = d.id AND ap.start_time = '2026-04-20 14:00:00');

INSERT INTO appointments (donor_id, type, start_time, end_time, status, location, doctor_name)
SELECT d.id, 'BLOOD', '2026-04-22 15:00:00', '2026-04-22 16:00:00', 'NO_SHOW', 'Collection Room B', 'Dr. Li'
FROM donors d
WHERE d.passport_number = '10001008'
  AND NOT EXISTS (SELECT 1 FROM appointments ap WHERE ap.donor_id = d.id AND ap.start_time = '2026-04-22 15:00:00');
