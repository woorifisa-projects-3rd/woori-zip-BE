CREATE TABLE IF NOT EXISTS `loan_goods`
(
    `id`              BIGINT       NOT NULL auto_increment,
    `name`            VARCHAR(255) NOT NULL,
    `image_url`       VARCHAR(255),
    `description`     TEXT         NOT NULL,
    `content`         TEXT         NOT NULL,
    `loan_goods_type` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
)
    engine = innodb
    auto_increment = 1
    DEFAULT charset = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `facility`
(
    `id`          BIGINT       NOT NULL auto_increment,
    `category`    VARCHAR(255) NOT NULL,
    `name`        VARCHAR(255) NOT NULL,
    `latitude`    DECIMAL(9,6) NOT NULL,
    `longitude`   DECIMAL(9,6) NOT NULL,
    `gu`          VARCHAR(255) NOT NULL,
    `dong`        VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
)
    engine = innodb
    auto_increment = 1
    DEFAULT charset = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `house`
(
    `id`                       BIGINT       NOT NULL auto_increment,
    `name`                     VARCHAR(255) NOT NULL,
    `house_type`               VARCHAR(255) NOT NULL,
    `housing_expenses`         VARCHAR(255) NOT NULL,
    `latitude`                 DECIMAL(9,6) NOT NULL,
    `longitude`                DECIMAL(9,6) NOT NULL,
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
    `id`       BIGINT       NOT NULL auto_increment,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `name`     VARCHAR(255) NOT NULL,
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
    PRIMARY KEY (`id`)
)
    engine = innodb
    auto_increment = 1
    DEFAULT charset = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;