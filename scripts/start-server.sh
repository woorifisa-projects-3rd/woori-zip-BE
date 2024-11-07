#!/bin/bash

# JAR 파일 위치로 이동
cd /home/ubuntu/woori-zip-BE/build/libs

# Spring Boot 애플리케이션 시작
nohup java -jar backend-0.0.1-SNAPSHOT.jar > /home/ubuntu/woori-zip-BE/logs/app.log 2>&1 &
