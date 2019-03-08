#!/bin/bash

#source /etc/profile
#mvn clean
#mvn install -Pdev -Dmaven.test.skip=true

currentDir=$(cd `dirname $0`; pwd)
PROJECTDIRS=(/ymfront-center/target/ /ymfront-gm/target/ /ymfront-ms/ymfront-finchina-ms/target/ /ymfront-route/target/)
APPJARS=(ymfront-center.jar ymfront-gm.jar ymfront-finchina-ms-exec.jar ymfront-route.jar)
APPNAMES=(center gm ms route)

function runApplication(){

    PID=$(ps -ef |grep ${APPJARS[$1]} | grep -v grep | awk '{print $2}')
    if [ -n "${PID}" ]; then
        echo "${APPJARS[$1]}:服务已运行,PID=$PID"
    else
        cd ${currentDir}${PROJECTDIRS[$1]}
        java -jar ${APPJARS[$1]} &
    fi
}

function destoryApplication(){
    PID=$(ps -ef | grep ${APPJARS[$1]} | grep -v grep | awk '{print $2}')
    if [ -n "${PID}" ]; then
        kill $PID
    else
       echo "${APPJARS[$1]}:服务未启动"
    fi
}


function start() {
if [ -z "$1" -o "$1" = "all" ]; then
    for ((i=0; i<${#APPJARS[@]}; i++)); do
        runApplication $i
    done
else
    for ((i=0; i<${#APPNAMES[@]}; i++)); do
        if [ "$1" = "${APPNAMES[$i]}" ]; then
            runApplication $i
        fi
    done
fi
}

function stop() {
    if [ -z "$1" -o "$1" = "all" ]; then
        for ((i=0; i<${#APPJARS[@]}; i++)); do
            destoryApplication $i
        done
    else
        for ((i=0; i<${#APPNAMES[@]}; i++)); do
            if [ "$1" = "${APPNAMES[$i]}" ]; then
                destoryApplication $i
            fi
        done
    fi
}

case "$1" in
    start)
        start "$2"
        exit 1;
    ;;
    stop)
        stop "$2"
    ;;
    restart)
        stop "$2"
        start "$2"
    ;;
    *)
        echo "command1: start|stop"
        exit
    ;;
esac
