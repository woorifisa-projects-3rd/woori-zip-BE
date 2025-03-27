# Woori Zip: 우리 집
🏡 소비 내역 기반 집 추천 서비스
> 청년들을 대상으로, 주변 인프라를 고려하며 집을 선택할 수 있도록 `카테고리` 를 통한 집 추천 서비스를 제공합니다. </br>
> 사용자의 `소비내역` 에 기반하여 자동으로 카테고리를 선정하여 집을 추천받을 수 있습니다. </br>
> 체크리스트를 통해 현재 사용자에게 적합한 전세 또는 월세 `대출 상품` 을 추천받을 수 있습니다.

## Team 👥
| 이름 | 맡은 기능 |
|----------|----------|
| 곽지은  | 북마크 API |
| 길가은  | 회원 API, 은행 서버 API, 로그 API  | 
| 김민지  | 대출 상품 API   |
| 김혜빈  | 지도, 대출상품추천, 소비패턴분석, 로그 API |
| 이성희  | 인프라 구축 |
| 정석진  | 집 상세 조회 API |

## application.yml
```yaml
spring:
  server:
    servlet:
      encoding:
        charset: utf-8
        force: true
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://${DATABASE_URL}
    username: ${DATABASE_USERNAME}
    password: ${DATABAE_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true
    properties:
      hibernate:
        format_sql: true
  flyway:
    enabled: true
    locations: classpath:db/migration
  aop:
    proxy-target-class: false

security:
  refresh:
    expiration: ${JWT_REFRESH_EXPIRATION}
  jwt:
    key: ${JWT_KEY}
    expiration:
      access: ${JWT_ACCESS_EXPIRATION}
```

## V2__load_csv.sql
```SQL
LOAD DATA INFILE '${본인의 csv 파일 절대경로}'
INTO TABLE consumption_analysis
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(customer_type, book, car, cloth, culture, food, grocery, customer_count);
```

## Drop Table Query
```SQL
DROP TABLE bookmark;
DROP TABLE facility;
DROP TABLE house;
DROP TABLE house_facility_relation;
DROP TABLE house_image;
DROP TABLE loan_goods;
DROP TABLE member;
DROP TABLE flyway_schema_history;
DROP TABLE loan_check_list;
DROP TABLE log;
DROP TABLE recently_loan_goods;
DROP TABLE refresh_token;
DROP TABLE consumption_analysis;
show tables;
```

## Dummy Data Insert Query
```SQL
INSERT INTO house (id, member_id, name, comment, address, housing_expenses, latitude, longitude, house_type, gu, dong, deposit, monthly_rent_fee, maintenance_fee, total_parking_spaces, household_parking_spaces, total_area, exclusive_area, rooms, bathrooms, floor, total_floors, move_in_date, approval_date, direction) VALUES (1, 1, '피사아파트', '10층 중 3층. 신축 풀옵션', '서울 마포구 월드컵북로 434 피사아파트', 'JEONSE', 37.5815199, 126.8860032, 'APARTMENT', '마포구', '상암동', 70000000, 0, 50000, 110, 1, '80.58', '73.54', 2, 1, 3, 10, '2025-02-01', '2024-11-22', '남향');
INSERT INTO house (id, member_id, name, comment, address, housing_expenses, latitude, longitude, house_type, gu, dong, deposit, monthly_rent_fee, maintenance_fee, total_parking_spaces, household_parking_spaces, total_area, exclusive_area, rooms, bathrooms, floor, total_floors, move_in_date, approval_date, direction) VALUES (2, 1, '피사원룸', '10층 중 3층. 신축 풀옵션', '서울 마포구 월드컵북로 434 피사원룸', 'MONTHLY_RENT', 37.5815199, 126.8860032, 'ONE_ROOM', '마포구', '상암동', 70000000, 0, 50000, 110, 1, '80.58', '73.54', 2, 1, 3, 10, '2025-02-01', '2024-11-22', '남향');
INSERT INTO house (id, member_id, name, comment, address, housing_expenses, latitude, longitude, house_type, gu, dong, deposit, monthly_rent_fee, maintenance_fee, total_parking_spaces, household_parking_spaces, total_area, exclusive_area, rooms, bathrooms, floor, total_floors, move_in_date, approval_date, direction) VALUES (3, 1, '피사빌라', '10층 중 3층. 신축 풀옵션', '서울 마포구 월드컵북로 434 피사빌라', 'MONTHLY_RENT', 37.5815199, 126.8860032, 'VILLA', '마포구', ' 아현동', 7000000, 500000, 50000, 110, 1, '80.58', '73.54', 2, 1, 3, 10, '2025-02-01', '2024-11-22', '남향');

INSERT INTO facility (id, category, name, gu, dong, latitude, longitude, address) VALUES (1, 'FOOD', '이선생짜글이', '마포구', '상암동', 37.5813506, 126.886193, '서울 마포구 월드컵북로 434 112호');
INSERT INTO facility (id, category, name, gu, dong, latitude, longitude, address) VALUES (2, 'FOOD', '김치도가', '마포구', '상암동', 37.5813506, 126.886193, '서울 마포구 월드컵북로 434 112호');

INSERT INTO house_facility_relation (id, house_id, facility_id, walking, distance) VALUES (1, 1, 1, 5, '1km');
INSERT INTO house_facility_relation (id, house_id, facility_id, walking, distance) VALUES (2, 1, 2, 7, '1km');
INSERT INTO house_facility_relation (id, house_id, facility_id, walking, distance) VALUES (3, 2, 2, 7, '1km');
```
