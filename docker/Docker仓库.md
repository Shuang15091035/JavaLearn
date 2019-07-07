私有仓库的使用：
	下载：docker pull registry
	启动：docker run -d --name reg -p 5000:5000 registry
	打标：docker tag hello-world http://127.0.0.1:5000/hello-world
	上传：docker push  http://127.0.0.1:5000/hello-world
	查询：curl http://127.0.0.1:5000/v2/_catalog | http://127.0.0.1:5000/v2/hello-world/tags/list