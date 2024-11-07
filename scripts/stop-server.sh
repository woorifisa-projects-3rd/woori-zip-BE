#!/bin/bash

# Spring Boot 애플리케이션이 이미 실행 중인 경우 종료
PID=$(pgrep -f 'woori-zip-BE')
if [ -n "$PID" ]; then
    echo "Stopping existing Spring Boot application (PID: $PID)"
    kill -15 $PID
    sleep 5
fi
