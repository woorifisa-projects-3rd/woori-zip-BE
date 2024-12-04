ALTER TABLE `house`
    MODIFY `exclusive_area` DOUBLE NOT NULL;

ALTER TABLE `member`
DROP COLUMN `available_assets`;

ALTER TABLE `loan_checklist`
    MODIFY `annual_income` BIGINT NULL,
    MODIFY `total_assets` BIGINT NULL,
    MODIFY `lease_deposit` BIGINT NULL,
    MODIFY `monthly_rent` BIGINT NULL,
    MODIFY `exclusive_area` DOUBLE NULL;
