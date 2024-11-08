#!/bin/bash

# JAR 파일 위치로 이동
cd /home/ubuntu/woori-zip-BE/build/libs

# Spring Boot 애플리케이션 시작 (환경 변수에 따라 포트 설정)
if [ "$BRANCH_NAME" == "release/v**" ]; then
    nohup java -Dserver.port=${SOCKET_PORT_TEST} \
    -jar backend-0.0.1-SNAPSHOT.jar \
    --spring.config.location=file:/home/ubuntu/config/application.yml > /home/ubuntu/woori-zip-BE/logs/app-prod.log 2>&1 &
else
    nohup java -Dserver.port=${SOCKET_PORT_PROD} \
    -jar backend-0.0.1-SNAPSHOT.jar \
    --spring.config.location=file:/home/ubuntu/config/application.yml > /home/ubuntu/woori-zip-BE/logs/app-test.log 2>&1 &
fi
