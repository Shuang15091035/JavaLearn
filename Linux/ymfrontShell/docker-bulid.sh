#!/usr/bin/bash
# 脚本运行正确方式eg:./docker-build.sh test(运行环境：test,dev,prod)

mirrorAddr="hub.c.163.com/shuangtest/"
imagesName=(ymfront-center ymfront-gm ymfront-finchina-ms ymfront-route ymfront-admin)
function dockerBuildShell(){
source /etc/profile
mvn clean &&
mvn install -P$1 -Dmaven.test.skip=true &&
rm=$(docker ps -aq|awk '{print $1}')
if [ "$rm" != "" ]; then
    for imageName in ${imagesName[@]};do
        docker rm -f "${mirrorAddr}${imageName}:$1"
    done
    echo "docker contains remove"
fi
rmi=$(docker images -aq|awk '{print $1}')
if [ "$rmi" != "" ]; then
   for imageName in ${imagesName[@]};do
        docker rmi -f "${mirrorAddr}${imageName}:$1"
   done
   echo "docker images remove"
fi
for imageName in ${imagesName[@]};do
    if [ "${imageName}" == "ymfront-finchina-ms" ]; then
       docker build -t "${mirrorAddr}"${imageName}:$1"" ymfront-ms/${imageName}/
    else
        docker build -t "${mirrorAddr}"${imageName}:$1"" ${imageName}/
    fi
    echo "docker images build"
# remove <none>:<none> 如果我们使用了和之前一样的镜像名来命名，那么docker会把这个名字赋给新的镜像，
# 以前的镜像并不会被删除，但是此时它的名字被新的镜像剥夺了，就成为了一个无名镜像，也就是<none>:<none>出现的原因
    docker rmi $(docker images -f "dangling=true" -q)
done

##   login Netease cloud docker
#docker login -u 17621235620 -p 158399shuang hub.c.163.com
###   docker images toggle tag && docker images push
#for imageName in ${imagesName[@]}
#do
#    docker push "hub.c.163.com/shuangtest/${imageName}:$1"
#done
}

if [ "$#" != 1 ]; then
    read -p "请输入项目环境:dev,test,prod? " env;
    dockerBuildShell $env
 else
    dockerBuildShell "$1"
fi