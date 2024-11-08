#!/bin/bash

# Spring Boot 애플리케이션이 이미 실행 중인 경우 종료
PID=$(pgrep -f 'backend-0.0.1-SNAPSHOT.jar')
if [ -n "$PID" ]; then
    echo "Stopping existing Spring Boot application (PID: $PID)"
    kill -15 $PID
    sleep 5

    # 강제 종료 (필요시)
    if ps -p $PID > /dev/null; then
        echo "Force killing application (PID: $PID)"
        kill -9 $PID
    fi
fi

# 기존 JAR 파일 삭제
JAR_PATH="/home/ubuntu/woori-zip-BE/build/libs/backend-0.0.1-SNAPSHOT.jar"
if [ -f "$JAR_PATH" ]; then
    echo "Deleting existing JAR file at $JAR_PATH"
    rm "$JAR_PATH"
fi
