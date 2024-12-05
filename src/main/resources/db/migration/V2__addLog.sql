alter table `log` add column `client_ip` VARCHAR(255) NOT NULL;
alter table `log` add column `request_url` VARCHAR(255) NOT NULL;
alter table `log` add column `request_body` TEXT NOT NULL;
alter table `log` add column `response_status` VARCHAR(255) NOT NULL;
alter table `log` add column `response_body` TEXT NOT NULL;
alter table `log` add column `is_success` TINYINT(1) NOT NULL;
alter table `log` modify member_id BIGINT NULL;
alter table `log` drop column `content`;