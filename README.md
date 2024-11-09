# Woori Zip: 우리 집
🏡 소비 내역 기반 집 추천 서비스
> 청년들을 대상으로, 주변 인프라를 고려하며 집을 선택할 수 있도록 `카테고리` 를 통한 집 추천 서비스를 제공합니다. </br>
> 사용자의 `소비내역` 에 기반하여 자동으로 카테고리를 선정하여 집을 추천받을 수 있습니다. </br>
> 체크리스트를 통해 현재 사용자에게 적합한 전세 또는 월세 `대출 상품` 을 추천받을 수 있습니다.

## Team 👥
| 이름 | 맡은 기능 |
|----------|----------|
| 곽지은  | 북마크 API |
| 길가은  | 회원 API  | 
| 김민지  | 대출 상품 API   |
| 김혜빈  | 지도 API |
| 이성희  | 인프라 구축 |
| 정석진  | 소비 패턴 분석 API, 집 상세 조회 API |

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
    url: jdbc:mysql://localhost:3306/woori_zip
    username: fisa_fire
    password: 1234
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
show tables;
```
