-- Migration script to convert blood type data from separate bloodGroup and rhesusFactor columns to unified bloodType column
-- This script should be run manually after deploying the new code

-- For donors table
-- Step 1: Add new bloodType column if not exists (Hibernate will create it automatically)
-- ALTER TABLE donors ADD COLUMN blood_type VARCHAR(15);

-- Step 2: Migrate existing data
UPDATE donors
SET blood_type = CASE
    WHEN blood_group = 'O' AND rhesus_factor = 'POSITIVE' THEN 'I_POSITIVE'
    WHEN blood_group = 'O' AND rhesus_factor = 'NEGATIVE' THEN 'I_NEGATIVE'
    WHEN blood_group = 'A' AND rhesus_factor = 'POSITIVE' THEN 'II_POSITIVE'
    WHEN blood_group = 'A' AND rhesus_factor = 'NEGATIVE' THEN 'II_NEGATIVE'
    WHEN blood_group = 'B' AND rhesus_factor = 'POSITIVE' THEN 'III_POSITIVE'
    WHEN blood_group = 'B' AND rhesus_factor = 'NEGATIVE' THEN 'III_NEGATIVE'
    WHEN blood_group = 'AB' AND rhesus_factor = 'POSITIVE' THEN 'IV_POSITIVE'
    WHEN blood_group = 'AB' AND rhesus_factor = 'NEGATIVE' THEN 'IV_NEGATIVE'
END
WHERE blood_type IS NULL;

-- Step 3: Drop old columns (only after verifying data migration is successful)
-- ALTER TABLE donors DROP COLUMN blood_group;
-- ALTER TABLE donors DROP COLUMN rhesus_factor;

-- For inventory_items table
-- Step 1: Add new bloodType column if not exists (Hibernate will create it automatically)
-- ALTER TABLE inventory_items ADD COLUMN blood_type VARCHAR(15);

-- Step 2: Migrate existing data from string columns
UPDATE inventory_items
SET blood_type = CASE
    WHEN blood_group = 'O' AND rhesus_factor = 'POSITIVE' THEN 'I_POSITIVE'
    WHEN blood_group = 'O' AND rhesus_factor = 'NEGATIVE' THEN 'I_NEGATIVE'
    WHEN blood_group = 'A' AND rhesus_factor = 'POSITIVE' THEN 'II_POSITIVE'
    WHEN blood_group = 'A' AND rhesus_factor = 'NEGATIVE' THEN 'II_NEGATIVE'
    WHEN blood_group = 'B' AND rhesus_factor = 'POSITIVE' THEN 'III_POSITIVE'
    WHEN blood_group = 'B' AND rhesus_factor = 'NEGATIVE' THEN 'III_NEGATIVE'
    WHEN blood_group = 'AB' AND rhesus_factor = 'POSITIVE' THEN 'IV_POSITIVE'
    WHEN blood_group = 'AB' AND rhesus_factor = 'NEGATIVE' THEN 'IV_NEGATIVE'
END
WHERE blood_type IS NULL;

-- Step 3: Drop old columns (only after verifying data migration is successful)
-- ALTER TABLE inventory_items DROP COLUMN blood_group;
-- ALTER TABLE inventory_items DROP COLUMN rhesus_factor;

-- Verification queries:
-- SELECT blood_type, COUNT(*) FROM donors GROUP BY blood_type;
-- SELECT blood_type, COUNT(*) FROM inventory_items GROUP BY blood_type;
