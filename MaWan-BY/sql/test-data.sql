-- =====================================================
-- 测试数据脚本 - 血液与骨髓捐献管理系统
-- 执行前请确保已运行 data.sql 初始化基础数据
-- =====================================================

-- =====================================================
-- 1. 添加更多用户
-- =====================================================
INSERT INTO users (username, password_hash, full_name, email, enabled) VALUES
('doctor_wang', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Wang Doctor', 'wang@hospital.com', true),
('doctor_li', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Li Doctor', 'li@hospital.com', true),
('lab_zhang', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Zhang LabTech', 'zhang@hospital.com', true),
('registrar_liu', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Liu Registrar', 'liu@hospital.com', true),
('viewer_chen', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Chen Viewer', 'chen@hospital.com', true)
ON DUPLICATE KEY UPDATE full_name=VALUES(full_name);

-- 分配角色
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'doctor_wang' AND r.name = 'ROLE_DOCTOR'
ON DUPLICATE KEY UPDATE user_id=VALUES(user_id);

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'doctor_li' AND r.name = 'ROLE_DOCTOR'
ON DUPLICATE KEY UPDATE user_id=VALUES(user_id);

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'lab_zhang' AND r.name = 'ROLE_LAB_TECH'
ON DUPLICATE KEY UPDATE user_id=VALUES(user_id);

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'registrar_liu' AND r.name = 'ROLE_REGISTRAR'
ON DUPLICATE KEY UPDATE user_id=VALUES(user_id);

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'viewer_chen' AND r.name = 'ROLE_REPORT_VIEWER'
ON DUPLICATE KEY UPDATE user_id=VALUES(user_id);

-- =====================================================
-- 2. 添加捐献者数据 (20人，各种血型、性别)
-- =====================================================
INSERT INTO donors (first_name, last_name, date_of_birth, gender, passport_number, phone, email, address, blood_type, hla_type, active) VALUES
-- 男性捐献者
('志强', '张', '1985-03-15', 'MALE', '10001001', '13800138001', 'zhangzq@email.com', '北京市朝阳区建国路88号', 'I_POSITIVE', 'A*02:01', true),
('伟', '李', '1990-07-22', 'MALE', '10001002', '13800138002', 'liwei@email.com', '上海市浦东新区陆家嘴环路100号', 'II_POSITIVE', 'B*07:02', true),
('明', '王', '1988-11-08', 'MALE', '10001003', '13800138003', 'wangming@email.com', '广州市天河区体育西路50号', 'III_NEGATIVE', 'A*24:02', true),
('强', '刘', '1992-04-30', 'MALE', '10001004', '13800138004', 'liuqiang@email.com', '深圳市南山区科技园路200号', 'IV_POSITIVE', 'B*15:01', true),
('磊', '陈', '1987-09-12', 'MALE', '10001005', '13800138005', 'chenlei@email.com', '杭州市西湖区文三路168号', 'I_NEGATIVE', 'A*11:01', true),
('涛', '杨', '1995-01-25', 'MALE', '10001006', '13800138006', 'yangtao@email.com', '成都市武侯区人民南路四段', 'II_NEGATIVE', 'B*40:01', true),
('鹏', '赵', '1983-06-18', 'MALE', '10001007', '13800138007', 'zhaopeng@email.com', '武汉市江汉区解放大道688号', 'III_POSITIVE', 'A*03:01', true),
('军', '黄', '1991-12-05', 'MALE', '10001008', '13800138008', 'huangjun@email.com', '南京市鼓楼区中山北路100号', 'IV_NEGATIVE', 'B*08:01', true),
('勇', '周', '1989-08-20', 'MALE', '10001009', '13800138009', 'zhouyong@email.com', '西安市雁塔区高新路60号', 'I_POSITIVE', 'A*01:01', true),
('杰', '吴', '1994-02-14', 'MALE', '10001010', '13800138010', 'wujie@email.com', '重庆市渝中区解放碑步行街', 'II_POSITIVE', 'B*44:02', true),

-- 女性捐献者
('芳', '林', '1993-05-28', 'FEMALE', '10001011', '13900139001', 'linfang@email.com', '北京市海淀区中关村大街1号', 'I_POSITIVE', 'A*02:01', true),
('娜', '郑', '1996-10-15', 'FEMALE', '10001012', '13900139002', 'zhengna@email.com', '上海市静安区南京西路1000号', 'II_NEGATIVE', 'B*07:02', true),
('敏', '孙', '1986-03-08', 'FEMALE', '10001013', '13900139003', 'sunmin@email.com', '广州市越秀区北京路200号', 'III_POSITIVE', 'A*24:02', true),
('静', '胡', '1991-07-19', 'FEMALE', '10001014', '13900139004', 'hujing@email.com', '深圳市福田区华强北路500号', 'IV_POSITIVE', 'B*15:01', true),
('丽', '高', '1988-12-25', 'FEMALE', '10001015', '13900139005', 'gaoli@email.com', '杭州市上城区延安路300号', 'I_NEGATIVE', 'A*11:01', true),
('婷', '马', '1994-04-10', 'FEMALE', '10001016', '13900139006', 'mating@email.com', '成都市锦江区春熙路100号', 'II_POSITIVE', 'B*40:01', true),
('雪', '罗', '1990-09-03', 'FEMALE', '10001017', '13900139007', 'luoxue@email.com', '武汉市武昌区中南路88号', 'III_NEGATIVE', 'A*03:01', true),
('梅', '谢', '1985-11-30', 'FEMALE', '10001018', '13900139008', 'xiemei@email.com', '南京市秦淮区夫子庙50号', 'IV_NEGATIVE', 'B*08:01', true),
('燕', '韩', '1992-06-22', 'FEMALE', '10001019', '13900139009', 'hanyan@email.com', '西安市碑林区南大街200号', 'I_POSITIVE', 'A*01:01', true),
('玲', '唐', '1997-01-08', 'FEMALE', '10001020', '13900139010', 'tangling@email.com', '重庆市江北区观音桥步行街', 'II_POSITIVE', 'B*44:02', true)
ON DUPLICATE KEY UPDATE first_name=VALUES(first_name);

-- =====================================================
-- 3. 添加体检记录 (多样化结论)
-- =====================================================
INSERT INTO medical_checks (donor_id, check_date, hemoglobin, systolic_pressure, diastolic_pressure, conclusion) VALUES
-- 合格的体检记录
((SELECT id FROM donors WHERE passport_number='10001001'), '2026-01-10 09:00:00', 145.5, 120, 80, 'FIT'),
((SELECT id FROM donors WHERE passport_number='10001002'), '2026-01-11 10:30:00', 152.0, 118, 75, 'FIT'),
((SELECT id FROM donors WHERE passport_number='10001003'), '2026-01-12 14:00:00', 138.5, 125, 82, 'FIT'),
((SELECT id FROM donors WHERE passport_number='10001004'), '2026-01-13 09:30:00', 160.0, 115, 70, 'FIT'),
((SELECT id FROM donors WHERE passport_number='10001005'), '2026-01-14 11:00:00', 142.0, 122, 78, 'FIT'),
((SELECT id FROM donors WHERE passport_number='10001011'), '2026-01-15 09:00:00', 128.5, 110, 70, 'FIT'),
((SELECT id FROM donors WHERE passport_number='10001012'), '2026-01-16 10:00:00', 132.0, 108, 68, 'FIT'),
((SELECT id FROM donors WHERE passport_number='10001013'), '2026-01-17 14:30:00', 125.5, 115, 75, 'FIT'),
((SELECT id FROM donors WHERE passport_number='10001014'), '2026-01-18 09:00:00', 130.0, 112, 72, 'FIT'),
((SELECT id FROM donors WHERE passport_number='10001015'), '2026-01-19 11:30:00', 135.5, 118, 76, 'FIT'),

-- 不合格的体检记录
((SELECT id FROM donors WHERE passport_number='10001006'), '2026-01-20 09:00:00', 105.0, 145, 95, 'UNFIT'),
((SELECT id FROM donors WHERE passport_number='10001007'), '2026-01-21 10:00:00', 98.5, 150, 100, 'UNFIT'),
((SELECT id FROM donors WHERE passport_number='10001016'), '2026-01-22 14:00:00', 102.0, 90, 55, 'UNFIT'),

-- 需要复查的体检记录
((SELECT id FROM donors WHERE passport_number='10001008'), '2026-01-23 09:30:00', 118.0, 135, 88, 'NEEDS_RECHECK'),
((SELECT id FROM donors WHERE passport_number='10001009'), '2026-01-24 11:00:00', 122.5, 138, 90, 'NEEDS_RECHECK'),
((SELECT id FROM donors WHERE passport_number='10001017'), '2026-01-25 10:30:00', 115.0, 132, 85, 'NEEDS_RECHECK'),

-- 最近的体检记录
((SELECT id FROM donors WHERE passport_number='10001010'), '2026-03-10 09:00:00', 148.0, 120, 78, 'FIT'),
((SELECT id FROM donors WHERE passport_number='10001018'), '2026-03-11 10:30:00', 126.5, 115, 72, 'FIT'),
((SELECT id FROM donors WHERE passport_number='10001019'), '2026-03-12 14:00:00', 133.0, 118, 75, 'FIT'),
((SELECT id FROM donors WHERE passport_number='10001020'), '2026-03-13 09:30:00', 129.5, 110, 70, 'FIT');

-- =====================================================
-- 4. 添加捐献记录 (各种状态和类型)
-- =====================================================
INSERT INTO donations (donor_id, type, planned_at, performed_at, status, volume_ml, notes, pre_check_id) VALUES
-- 已完成的血液捐献
((SELECT id FROM donors WHERE passport_number='10001001'), 'BLOOD', '2026-01-15 09:00:00', '2026-01-15 09:45:00', 'COMPLETED', 400, '捐献顺利，无不良反应', (SELECT id FROM medical_checks WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001001') LIMIT 1)),
((SELECT id FROM donors WHERE passport_number='10001002'), 'BLOOD', '2026-01-16 10:00:00', '2026-01-16 10:40:00', 'COMPLETED', 350, '轻微头晕，休息后恢复', (SELECT id FROM medical_checks WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001002') LIMIT 1)),
((SELECT id FROM donors WHERE passport_number='10001003'), 'BLOOD', '2026-01-17 14:00:00', '2026-01-17 14:35:00', 'COMPLETED', 400, '一切正常', (SELECT id FROM medical_checks WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001003') LIMIT 1)),
((SELECT id FROM donors WHERE passport_number='10001011'), 'BLOOD', '2026-01-20 09:00:00', '2026-01-20 09:30:00', 'COMPLETED', 300, '首次捐献，表现良好', (SELECT id FROM medical_checks WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001011') LIMIT 1)),
((SELECT id FROM donors WHERE passport_number='10001012'), 'BLOOD', '2026-01-21 10:30:00', '2026-01-21 11:00:00', 'COMPLETED', 350, '捐献顺利', (SELECT id FROM medical_checks WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001012') LIMIT 1)),

-- 已完成的骨髓捐献
((SELECT id FROM donors WHERE passport_number='10001004'), 'BONE_MARROW', '2026-02-01 08:00:00', '2026-02-01 12:00:00', 'COMPLETED', 150, '骨髓采集成功，供者状态良好', (SELECT id FROM medical_checks WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001004') LIMIT 1)),
((SELECT id FROM donors WHERE passport_number='10001005'), 'BONE_MARROW', '2026-02-10 08:30:00', '2026-02-10 11:30:00', 'COMPLETED', 180, '采集顺利，已安排后续随访', (SELECT id FROM medical_checks WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001005') LIMIT 1)),
((SELECT id FROM donors WHERE passport_number='10001013'), 'BONE_MARROW', '2026-02-15 09:00:00', '2026-02-15 13:00:00', 'COMPLETED', 160, '配型成功，捐献完成', (SELECT id FROM medical_checks WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001013') LIMIT 1)),

-- 计划中的捐献
((SELECT id FROM donors WHERE passport_number='10001010'), 'BLOOD', '2026-03-20 09:00:00', NULL, 'PLANNED', NULL, '预约血液捐献', (SELECT id FROM medical_checks WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001010') LIMIT 1)),
((SELECT id FROM donors WHERE passport_number='10001018'), 'BLOOD', '2026-03-21 10:00:00', NULL, 'PLANNED', NULL, '定期捐献', (SELECT id FROM medical_checks WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001018') LIMIT 1)),
((SELECT id FROM donors WHERE passport_number='10001019'), 'BONE_MARROW', '2026-03-25 08:00:00', NULL, 'PLANNED', NULL, '骨髓配型成功，等待采集', (SELECT id FROM medical_checks WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001019') LIMIT 1)),

-- 已取消的捐献
((SELECT id FROM donors WHERE passport_number='10001014'), 'BLOOD', '2026-02-20 09:00:00', NULL, 'CANCELLED', NULL, '捐献者临时有事取消', (SELECT id FROM medical_checks WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001014') LIMIT 1)),

-- 被拒绝的捐献
((SELECT id FROM donors WHERE passport_number='10001015'), 'BLOOD', '2026-02-25 10:00:00', NULL, 'REJECTED', NULL, '体检不合格，暂缓捐献', (SELECT id FROM medical_checks WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001015') LIMIT 1));

-- =====================================================
-- 5. 添加库存数据 (各种血型和状态)
-- =====================================================
INSERT INTO inventory_items (type, donation_id, blood_type, blood_group, rhesus_factor, collected_at, expiry_date, status, storage_location, comment) VALUES
-- 在库的全血
('WHOLE_BLOOD', (SELECT id FROM donations WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001001') AND status='COMPLETED' LIMIT 1), 'I_POSITIVE', 'I', 'POSITIVE', '2026-01-15', '2026-02-26', 'IN_STOCK', 'A-01-1', 'I+ whole blood'),
('WHOLE_BLOOD', (SELECT id FROM donations WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001002') AND status='COMPLETED' LIMIT 1), 'II_POSITIVE', 'II', 'POSITIVE', '2026-01-16', '2026-02-27', 'IN_STOCK', 'A-01-2', 'II+ whole blood'),
('WHOLE_BLOOD', (SELECT id FROM donations WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001003') AND status='COMPLETED' LIMIT 1), 'III_NEGATIVE', 'III', 'NEGATIVE', '2026-01-17', '2026-02-28', 'IN_STOCK', 'A-02-1', 'III- whole blood rare'),

-- 在库的血浆
('PLASMA', (SELECT id FROM donations WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001011') AND status='COMPLETED' LIMIT 1), 'I_POSITIVE', 'I', 'POSITIVE', '2026-01-20', '2027-01-20', 'IN_STOCK', 'B-01-1', 'I+ plasma frozen'),
('PLASMA', (SELECT id FROM donations WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001012') AND status='COMPLETED' LIMIT 1), 'II_NEGATIVE', 'II', 'NEGATIVE', '2026-01-21', '2027-01-21', 'IN_STOCK', 'B-01-2', 'II- plasma'),

-- 在库的血小板
('PLATELETS', (SELECT id FROM donations WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001001') AND status='COMPLETED' LIMIT 1), 'I_POSITIVE', 'I', 'POSITIVE', '2026-01-15', '2026-01-20', 'IN_STOCK', 'C-01', 'I+ platelets'),
('PLATELETS', (SELECT id FROM donations WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001002') AND status='COMPLETED' LIMIT 1), 'II_POSITIVE', 'II', 'POSITIVE', '2026-01-16', '2026-01-21', 'IN_STOCK', 'C-01', 'II+ platelets'),

-- 在库的骨髓样本
('BONE_MARROW_SAMPLE', (SELECT id FROM donations WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001004') AND type='BONE_MARROW' LIMIT 1), 'IV_POSITIVE', 'IV', 'POSITIVE', '2026-02-01', '2026-02-08', 'IN_STOCK', 'D-01', 'IV+ bone marrow -80C'),
('BONE_MARROW_SAMPLE', (SELECT id FROM donations WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001005') AND type='BONE_MARROW' LIMIT 1), 'I_NEGATIVE', 'I', 'NEGATIVE', '2026-02-10', '2026-02-17', 'IN_STOCK', 'D-01', 'I- bone marrow'),

-- 已预留的库存
('WHOLE_BLOOD', (SELECT id FROM donations WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001003') AND status='COMPLETED' LIMIT 1), 'III_NEGATIVE', 'III', 'NEGATIVE', '2026-01-17', '2026-02-28', 'RESERVED', 'A-02-2', 'Reserved for ER'),
('PLASMA', (SELECT id FROM donations WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001011') AND status='COMPLETED' LIMIT 1), 'I_POSITIVE', 'I', 'POSITIVE', '2026-01-20', '2027-01-20', 'RESERVED', 'B-02-1', 'Reserved for surgery'),

-- 已使用的库存
('WHOLE_BLOOD', (SELECT id FROM donations WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001001') AND status='COMPLETED' LIMIT 1), 'I_POSITIVE', 'I', 'POSITIVE', '2026-01-15', '2026-02-26', 'USED', 'A-01-1', 'Used for transfusion'),
('PLATELETS', (SELECT id FROM donations WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001002') AND status='COMPLETED' LIMIT 1), 'II_POSITIVE', 'II', 'POSITIVE', '2026-01-16', '2026-01-21', 'USED', 'C-01', 'Used for patient'),

-- 已过期的库存
('PLATELETS', (SELECT id FROM donations WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001001') AND status='COMPLETED' LIMIT 1), 'I_POSITIVE', 'I', 'POSITIVE', '2026-01-15', '2026-01-20', 'EXPIRED', 'C-01', 'Expired'),

-- 已销毁的库存
('WHOLE_BLOOD', (SELECT id FROM donations WHERE donor_id=(SELECT id FROM donors WHERE passport_number='10001002') AND status='COMPLETED' LIMIT 1), 'II_POSITIVE', 'II', 'POSITIVE', '2026-01-16', '2026-02-27', 'DISCARDED', 'A-01-2', 'QC failed discarded');

-- =====================================================
-- 6. 添加预约数据 (各种状态)
-- =====================================================
INSERT INTO appointments (donor_id, type, start_time, end_time, status, location, doctor_name) VALUES
-- 计划中的预约
((SELECT id FROM donors WHERE passport_number='10001010'), 'BLOOD', '2026-03-20 09:00:00', '2026-03-20 10:00:00', 'PLANNED', '采血室A', '王医生'),
((SELECT id FROM donors WHERE passport_number='10001018'), 'BLOOD', '2026-03-21 10:00:00', '2026-03-21 11:00:00', 'PLANNED', '采血室B', '李医生'),
((SELECT id FROM donors WHERE passport_number='10001019'), 'BONE_MARROW', '2026-03-25 08:00:00', '2026-03-25 12:00:00', 'PLANNED', '骨髓采集室', '王医生'),
((SELECT id FROM donors WHERE passport_number='10001020'), 'BLOOD', '2026-03-22 14:00:00', '2026-03-22 15:00:00', 'PLANNED', '采血室A', '李医生'),

-- 已确认的预约
((SELECT id FROM donors WHERE passport_number='10001001'), 'BLOOD', '2026-03-18 09:00:00', '2026-03-18 10:00:00', 'CONFIRMED', '采血室A', '王医生'),
((SELECT id FROM donors WHERE passport_number='10001002'), 'BLOOD', '2026-03-18 10:30:00', '2026-03-18 11:30:00', 'CONFIRMED', '采血室B', '李医生'),
((SELECT id FROM donors WHERE passport_number='10001011'), 'BLOOD', '2026-03-19 09:00:00', '2026-03-19 10:00:00', 'CONFIRMED', '采血室A', '王医生'),

-- 已取消的预约
((SELECT id FROM donors WHERE passport_number='10001006'), 'BLOOD', '2026-03-15 09:00:00', '2026-03-15 10:00:00', 'CANCELLED', '采血室A', '王医生'),
((SELECT id FROM donors WHERE passport_number='10001007'), 'BONE_MARROW', '2026-03-16 08:00:00', '2026-03-16 12:00:00', 'CANCELLED', '骨髓采集室', '李医生'),

-- 爽约的预约
((SELECT id FROM donors WHERE passport_number='10001008'), 'BLOOD', '2026-03-10 09:00:00', '2026-03-10 10:00:00', 'NO_SHOW', '采血室B', '王医生'),
((SELECT id FROM donors WHERE passport_number='10001009'), 'BLOOD', '2026-03-12 14:00:00', '2026-03-12 15:00:00', 'NO_SHOW', '采血室A', '李医生');

-- =====================================================
-- 完成提示
-- =====================================================
SELECT '测试数据导入完成！' AS message;
SELECT CONCAT('捐献者数量: ', COUNT(*)) AS donors_count FROM donors;
SELECT CONCAT('体检记录数量: ', COUNT(*)) AS medical_checks_count FROM medical_checks;
SELECT CONCAT('捐献记录数量: ', COUNT(*)) AS donations_count FROM donations;
SELECT CONCAT('库存数量: ', COUNT(*)) AS inventory_count FROM inventory_items;
SELECT CONCAT('预约数量: ', COUNT(*)) AS appointments_count FROM appointments;
