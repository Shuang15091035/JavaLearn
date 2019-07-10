Windows搭建Gitblit服务
> 搭建Gitblit服务器
	下载Gitblit > http://www.gitblit.com
	解压表示安装成功
	 注：下文$gitblit_home表示安装目录
> 服务器配置
	编辑$gitblit_home/data/gitblit.properties
	修改配置
		server.httpPort = 8000  #配置http请求的端口
		server.httpsPort = 8001  #配置https请求的端口
		server.httpBindInterface = localhost  #配置http请求绑定的IP
		server.httpsBindInterface = localhost  #配置https请求绑定的IP
		git.repositoriesFolder = D:/gitRepositories  #配置git仓库存放目录
		注：如果配置nginx等负载均衡工具使用，推荐设置为localhost
> 开启gitblit
	窗口启动：
		$gitblit_home/gitblit.cmd
	服务启动：
		编辑$gitblit_home/installService.cmd
		修改ARCH
			32位系统：SET ARCH=x86
			64位系统：SET ARCH=amd64
		添加CD为程序目录
			SET CD=$gitblit_home
		StartParams参数：
			--StartParams=“”
		以管理员身份运行installService.cmd
		windows服务界面可以查看运行的gitblit服务
> 浏览器访问 http://localhost:8000  默认用户名/密码: admin/admin

> 通过gitblit实现webhook功能
	编写test.groovy文件
		// define the trigger url
		def triggerUrl = "http://192.168.30.100:8085/actuator/refresh"

		// trigger the build
		def _url = new URL(triggerUrl)
		def _con = _url.openConnection()

		// set up connection
		_con.setRequestMethod("POST")

		// send post request
		_con.setDoOutput(true)

		logger.info("gmRefresh.groovyresponse code: ${_con.responseCode}")
	添加文件到$gitblit_home/data/groovy目录下
	gitblit仓库中通过 ->编辑->接收->post-receive脚本->选择test.groovy文件
		注：可实现通过git push操作，动态更新Git Config项目内容