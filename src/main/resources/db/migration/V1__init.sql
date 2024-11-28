CREATE TABLE IF NOT EXISTS `facility`
(
    `id`          BIGINT       NOT NULL auto_increment,
    `category`    VARCHAR(255) NOT NULL,
    `name`        VARCHAR(255) NOT NULL,
    `latitude`    DOUBLE       NOT NULL,
    `longitude`   DOUBLE       NOT NULL,
    `gu`          VARCHAR(255) NOT NULL,
    `dong`        VARCHAR(255) NOT NULL,
    `address`     VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
)
    engine = innodb
    auto_increment = 1
    DEFAULT charset = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `house`
(
    `id`                       BIGINT       NOT NULL auto_increment,
    `member_id`                BIGINT       NOT NULL,
    `name`                     VARCHAR(255) NOT NULL,
    `comment`                  VARCHAR(255) NOT NULL,
    `address`                  VARCHAR(255) NOT NULL,
    `house_type`               VARCHAR(255) NOT NULL,
    `housing_expenses`         VARCHAR(255) NOT NULL,
    `latitude`                 DOUBLE       NOT NULL,
    `longitude`                DOUBLE       NOT NULL,
    `gu`                       VARCHAR(255) NOT NULL,
    `dong`                     VARCHAR(255) NOT NULL,
    `deposit`                  BIGINT       NOT NULL,
    `monthly_rent_fee`         BIGINT       NOT NULL,
    `maintenance_fee`          BIGINT       NOT NULL,
    `total_parking_spaces`     MEDIUMINT    NOT NULL,
    `household_parking_spaces` MEDIUMINT    NOT NULL,
    `total_area`               VARCHAR(255) NOT NULL,
    `exclusive_area`           VARCHAR(255) NOT NULL,
    `rooms`                    MEDIUMINT    NOT NULL,
    `bathrooms`                MEDIUMINT    NOT NULL,
    `floor`                    MEDIUMINT    NOT NULL,
    `total_floors`             MEDIUMINT    NOT NULL,
    `move_in_date`             DATE         NOT NULL,
    `approval_date`            DATE         NOT NULL,
    `direction`                VARCHAR(255) NOT NULL,
    `representative_image`     VARCHAR(255),
    `bookmark_count`           MEDIUMINT    NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
)
    engine = innodb
    auto_increment = 1
    DEFAULT charset = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `house_facility_relation`
(
    `id`          BIGINT       NOT NULL auto_increment,
    `house_id`    BIGINT       NOT NULL,
    `facility_id` BIGINT       NOT NULL,
    `distance`    VARCHAR(255) NOT NULL,
    `walking`     MEDIUMINT    NOT NULL,
    PRIMARY KEY (`id`)
)
    engine = innodb
    auto_increment = 1
    DEFAULT charset = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `house_image`
(
    `id`          BIGINT       NOT NULL auto_increment,
    `house_id`    BIGINT       NOT NULL,
    `url`         VARCHAR(255) NOT NULL,
    `order_index` MEDIUMINT    NOT NULL,
    PRIMARY KEY (`id`)
)
    engine = innodb
    auto_increment = 1
    DEFAULT charset = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `member`
(
    `id`                     BIGINT       NOT NULL auto_increment,
    `customer_id`            VARCHAR(255) NOT NULL,
    `license_id`             VARCHAR(255) NOT NULL,
    `username`               VARCHAR(255) NOT NULL,
    `password`               VARCHAR(255) NOT NULL,
    `status`                 VARCHAR(255) NOT NULL,
    `name`                   VARCHAR(255) NOT NULL,
    `birthday`                            NOT NULL DATE,
    `gender`                 VARCHAR(255) NOT NULL,
    `membership`             VARCHAR(255) NOT NULL DEFAULT 'NONE_MEMBERSHIP',
    `life_stage`             VARCHAR(255) NOT NULL DEFAULT 'NONE_LIFE_STAGE',
    `role`                   VARCHAR(255) NOT NULL,
    `available_assets`       BIGINT       NOT NULL,
    PRIMARY KEY (`id`)
)
    engine = innodb
    auto_increment = 1
    DEFAULT charset = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `bookmark`
(
    `id`        BIGINT NOT NULL auto_increment,
    `member_id` BIGINT NOT NULL,
    `house_id`  BIGINT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
)
    engine = innodb
    auto_increment = 1
    DEFAULT charset = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `recently_loan_goods`
(
    `id`             BIGINT    NOT NULL auto_increment,
    `member_id`      BIGINT    NOT NULL,
    `loan_goods_id`  BIGINT    NOT NULL,
    `looked_at`      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
)
    engine = innodb
    auto_increment = 1
    DEFAULT charset = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `log`
(
    `id`         BIGINT    NOT NULL auto_increment,
    `member_id`  BIGINT    NOT NULL,
    `content`    TEXT      NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`)
)
    engine = innodb
    auto_increment = 1
    DEFAULT charset = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `refresh_token`
(
    `id`         BIGINT       NOT NULL auto_increment,
    `member_id`  BIGINT       NOT NULL,
    `value`      VARCHAR(255) NOT NULL,
    `expired_at` TIMESTAMP    NOT NULL,
    PRIMARY KEY (`id`)
)
    engine = innodb
    auto_increment = 1
    DEFAULT charset = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `consumption_analysis`
(
    `id`             BIGINT       NOT NULL auto_increment,
    `customer_type`  VARCHAR(255) NOT NULL,
    `book`           DOUBLE       NOT NULL,
    `car`            DOUBLE       NOT NULL,
    `cloth`          DOUBLE       NOT NULL,
    `culture`        DOUBLE       NOT NULL,
    `food`           DOUBLE       NOT NULL,
    `grocery`        DOUBLE       NOT NULL,
    `customer_count` BIGINT       NOT NULL,
    PRIMARY KEY (`id`)
)
    engine = innodb
    auto_increment = 1
    DEFAULT charset = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `member_consumption`
(
    `id`             BIGINT       NOT NULL auto_increment,
    `member_id`      BIGINT       NOT NULL,
    `book`           DOUBLE       NOT NULL,
    `car`            DOUBLE       NOT NULL,
    `cloth`          DOUBLE       NOT NULL,
    `culture`        DOUBLE       NOT NULL,
    `food`           DOUBLE       NOT NULL,
    `grocery`        DOUBLE       NOT NULL,
    PRIMARY KEY (`id`)
)
    engine = innodb
    auto_increment = 1
    DEFAULT charset = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `agent`
(
    `id`        BIGINT NOT NULL auto_increment,
    `license_id` VARCHAR(255) NOT NULL,
    `name`      VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
)
    engine = innodb
    auto_increment = 1
    DEFAULT charset = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;