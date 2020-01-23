私有仓库的使用：
	下载：docker pull registry
	启动：docker run -d --name reg -p 5000:5000 registry
	打标：docker tag hello-world http://127.0.0.1:5000/hello-world
	上传：docker push  http://127.0.0.1:5000/hello-world
	查询：curl http://127.0.0.1:5000/v2/_catalog | http://127.0.0.1:5000/v2/hello-world/tags/list
## 通过nexus搭建镜像仓库，docker stack 部署问题无法获取镜像问题
	1. docker stack deploy -c *.yml * --with-registry-auth
		获取镜像tag为<none>，通过docker stack ps 可正确查看运行镜像
			https://www.cnblogs.com/nieqibest/p/11408373.html
