#!/usr/bin/env bash

function nativeBuild() {
    source /etc/profile
    mvn clean &&
    mvn install -P$1 -Dmaven.test.skip=true  &&
    cp ./ymfront-center/target/ymfront-center.jar /Users/admin/Documents &&
    cp ./ymfront-gm/target/ymfront-gm.jar /Users/admin/Documents &&
    cp ./ymfront-ms/ymfront-finchina-ms/target/ymfront-finchina-ms-exec.jar /Users/admin/Documents &&
    cp ./ymfront-route/target/ymfront-route.jar /Users/admin/Documents
#    cp ./ymfront-admin/target/ymfront-admin.jar /Users/admin/Documents
}
if [ "$#" != 1 ]; then
    read -p "请输入项目环境:dev,test,prod? " env;
    nativeBuild $env
 else
     case "$1" in
        dev | test | prod)
            nativeBuild "$1"
        ;;
        *)
            echo "请输入项目环境:dev,test,prod? "
            exit
        ;;
    esac
fi