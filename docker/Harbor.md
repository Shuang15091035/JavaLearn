Harbor镜像仓库搭建遇到的坑

harborGitHub地址：https://github.com/goharbor/harbor/releases
docker官网docker-compose安装地址：https://docs.docker.com/compose/install/

1.Harbor搭建依赖于docker-compose工具
	docker-compose安装：
		官方提示下载安装方式：
			安装命令：
				sudo curl -L "https://github.com/docker/compose/releases/download/1.25.4/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose安装：
			错误信息：
		正确的安装方式：
			 wget https://github.com/docker/compose/releases/download/1.25.0/docker-compose-$(uname -s)-$(uname -m) -O /usr/local/bin/docker-compose
2.配置harbor.yml文件
	1.通过Http方式访问harbor仓库
		1.填写hostname,http方式port
		2.注释https相关配置
		3.sudo ./install
    2.通过本地客户端登录harbor仓库 
    	错误：Error response from daemon: http: server gave HTTP response to HTTPS client
    	需要配置docker客户端，当前所在主机的docker 配置
    	解决方式：
    		docker客户端将https请求更改为http请求， 将以下配置添加到 /etc/docker/daemon.json 中
    		{ "insecure-registries":["harbordehostname:harbor中http的port"] }
    3.使用方式
    	docker login harbor的hostname:harbor的port
    note:此种方式对于生产环境镜像使用机器不方便,需要配置生产环境中的每台docker机器，使用http方式获取镜像
 3.Harbor官方文档的也建议生产环境使用https方式
  Harbor does not ship with any certificates. In versions up to and including 1.9.x, by default Harbor uses HTTP to serve registry requests. This is acceptable only in air-gapped test or development environments. In production environments, always use HTTPS. If you enable Content Trust with Notary to properly sign all images, you must use HTTPS.


  自签证书必须配置为域名方式，如果配置为IP地址会报一下错误
	Error response from daemon: Get https://10.116.132.110/v2/: x509: cannot validate certificate for 10.116.132.110 because it doesn't contain any IP SANs

 note:如果域名没有注册使用的是自定义域名,需要在每台机器上的host文件中配置 域名和IP地址的对应关系，比较麻烦

最终的使用方案：nexus3