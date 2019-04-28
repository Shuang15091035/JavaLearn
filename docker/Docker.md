## Dockerfile
Dockerfile必须以FROM命令开始：FROM指定镜像基于哪个基础镜像创建，接下来的镜像也会基于这个基础镜像
MAINTAINER <author name> 设置镜像的作者
RUN： 在shell或者exec环境下执行的命令，RUN指令会在新创建的镜像上添加新的层面，接下来提交的结果用在Dockerfile的下一条指令中。语法如下：RUN 《command》
-p: 端口映射
-v 目录映射：宿主机目录(可以为相对路径)：容器目录(绝对路径)
ADD：复制文件指令。它有两个参数<source>和<description>。destination是容器内的路径。source可以是URL或者启动配置文件上下文中的一个文件。语法如下：ADD <src> 《destination》
CMD：提供了容器默认的执行命令。 Dockerfile只允许使用一次CMD指令 ，使用多个CMD会抵消之前所有的指令，只有最后一个指令生效。 CMD有三种形式：
CMD ["executable","param1","param2"]
CMD ["param1","param2"]
CMD command param1 param2
CMD ["/bin/echo"] or CMD /bin/echo  (ENDPOINT)
EXPOSE:指定容器在运行时监听的端口。语法如下：
EXPOSE <port>;

如果你使用第一种语法：CMD（或ENTRYPOINT）是一个数组，它执行的命令完全像你期望的那样
如果使用第二种语法，Docker会在你的命令前面加上/bin/sh -c
## docker 镜像
docker pull jenkinsci/Jenkins
docker log -f 查看容器日志
-f: 实时查看日志最新内容，偏于了解jenkins的运行情况
gitLab:的Docker镜像：http://doc.gitlab.com/omnibus/docker/
docker pull gitlab/gitlab-ce


### docker run --link 可以解决同一宿主机中两个容器之间的连通性问题，不同宿主机之间通信，可以通过 weave 或者Docker Networking 技术 解决“跨宿主机”连通性问题。
地址：Docker Networking: https://docs.docker.com/engine/userguide/networking/

### 如何在Jenkins容器中运行Docker命令
* 在宿主机上安装Jenkins，宿主机上有Docker服务
* 不使用官方Jenkins镜像，自己构造一个带有Docker服务的Jenkins镜像
* 使用 Docker-in-Docker 在Docker容器中运行其他Docker容器(父子关系)
* 使用 Docker-out-of-Docker 在Docker容器中创建宿主机的Docker容器（兄弟关系）
http://www.dockone.io/article/431

### docker 自动构建docker镜像
API_NAME=""
API_VERSION="1.0.0"
API_PROT=""
IMAGE_NAME"127.0.0.1:50000/ymfront/$API_NAME:$BUILD_NUMBER"
CONTAINER_NAME=$API_NAME-$API_VERSION
cd $WORKSPACE/ymfrornt/$API_NAME/target
cp classes/Dockerfile
docker build -t $IMAGE_NAME
docker push $IMAGE_NAME
cid=$(docker ps | grep "$CONTAINER_NAME" | awk '{print $1}')
if [ "$cid != ""]; then
    docker rm -f $cid
fi
docker run -d -p $API_PROT:8080 --name $CONTAINER_NAME $IMAGE_NAME
rm -f Dockerfile

docker run -d -h gitlab.shuang.com -p 22:22 -p 8081:80 -v ~/gitlab/etc:/etc/gitlab -v ~/gitlab/log:/var/log/gitlab -v ~/gitlab/opt:/var/opt/gitlab --name gitlab gitlab/gitlab-ce

### 在Jenkins内部运行处在宿主机上的Docker：
docker run -d -p 8080:8080 -p 50000:50000 -v ~/jenkins:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -v $(which docker):/usr/bin/docker -v ~/.m2/repository:/repository --link gitlab:gitlab.ymfront.com --name jenkins jenkinsci/jenkins

docker run -d --name ymfront-center -p 8761:8761 ymfront-center:test

docker run --link ymfront-center:8761 -p 8762:8762 ymfront-finchina-ms:test

### docker --link 可用于解决容器的连通性问题，但是该方式仅局限于统一宿主机上，不同宿主机无法使用该方式进行通信，docker1.9使用docker network create创建docker网络。
 --link gitlab:gitlab.ymfront.com  //gitlab = docker run --name gitlab -h gitlab.ymfront.com gitlab/gitlab-ce

### 批量删除指定名称的docker镜像
docker rmi -f $(docker images | grep "<none>" | awk '{print $3}')
sudo docker rmi -f $(sudo docker images -aq)
sudo docker rm -f $(sudo docker ps -aq)
sudo docker stack deploy -c eureka.yml eureka
sudo docker stack rm eureka
sudo vi eureka.yml

sudo docker stack deploy -c gmservice.yml ymfront