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
pring:
  server:
    port: 8080
    servlet:
      encoding:
        charset: utf-8
        force: true

  spring:
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
```
