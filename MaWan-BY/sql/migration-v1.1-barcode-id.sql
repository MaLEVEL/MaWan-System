-- 数据库迁移脚本 v1.1: 将身份证号字段改为条形码ID字段
-- 执行日期: 2025-12-28
-- 描述: 修改donors表的passport_number字段，从身份证号(18位)改为条形码ID(8-14位数字)

-- 1. 首先检查现有数据是否符合新的格式
-- 注意: 如果现有数据不符合8-14位数字，需要手动处理
SELECT 
    COUNT(*) as total_records,
    COUNT(CASE WHEN passport_number REGEXP '^[0-9]{8,14}$' THEN 1 END) as valid_records,
    COUNT(CASE WHEN NOT passport_number REGEXP '^[0-9]{8,14}$' THEN 1 END) as invalid_records
FROM donors;

-- 2. 修改字段长度和添加注释 (如果数据库支持)
-- MySQL语法
ALTER TABLE donors 
MODIFY COLUMN passport_number VARCHAR(14) NOT NULL COMMENT '条形码ID (8-14位数字)';

-- 3. 为现有数据生成测试条形码ID (仅当需要时)
-- 假设我们需要为现有数据生成新的ID，可以使用以下脚本
-- 注意: 这只是一个示例，实际使用时需要根据业务需求调整
/*
UPDATE donors 
SET passport_number = LPAD(FLOOR(RAND() * 99999999) + 10000000, 8, '0')
WHERE NOT passport_number REGEXP '^[0-9]{8,14}$' OR passport_number IS NULL;
*/

-- 4. 验证修改结果
DESCRIBE donors;

-- 5. 检查唯一性约束
SELECT passport_number, COUNT(*) as count
FROM donors
GROUP BY passport_number
HAVING COUNT(*) > 1;

-- 6. 为扫码枪优化: 确保ID为纯数字，便于扫码枪输入
-- 如果需要，可以添加触发器确保数据格式
/*
DELIMITER //
CREATE TRIGGER validate_donor_id_format
BEFORE INSERT ON donors
FOR EACH ROW
BEGIN
    IF NEW.passport_number NOT REGEXP '^[0-9]{8,14}$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'ID must be 8-14 digits';
    END IF;
END//
DELIMITER ;
*/

-- 重要提示:
-- 1. 在生产环境执行前，务必备份数据
-- 2. 根据实际数据库类型调整SQL语法
-- 3. 考虑现有应用程序的兼容性
-- 4. 测试扫码枪输入: 扫码枪通常模拟键盘输入，无需特殊修改