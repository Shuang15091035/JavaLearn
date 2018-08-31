#!/bin/bash

#param=$1
#if [ -z "$1" -o "$1" = "all" ]; then
#    for ((i=0; i<3; i++)); do
#    echo "if $i"
#    done
#else
#    echo "else $i"
#fi



mirrorAddr="hub.c.163.com/shuangtest/"
imagesName=(ymfront-center ymfront-gm ymfront-finchina-ms ymfront-route ymfront-admin)

rm=$(docker ps -aq|awk '{print $1}')
if [ "$rm" != "" ]; then
for imageName in ${imagesName[@]};do
docker rm -f "${mirrorAddr}${imageName}:$1"
done
docker rmi $(docker images -f "dangling=true" -q)
echo "docker contains remove"
fi
rmi=$(docker images -aq|awk '{print $1}')
if [ "$rmi" != "" ]; then
for imageName in ${imagesName[@]};do
docker rmi -f "${mirrorAddr}${imageName}:$1"
done
echo "docker images remove"
fi

tasklist -ano | findstr 8761

tasklist
