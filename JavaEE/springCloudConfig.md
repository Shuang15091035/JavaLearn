SpringConfigClient： 
	默认执行策略:spring.cloud.config.server.git.uri
		eg: spring.cloud.config.server.git.uri: file://${user.home}/config-repo
			windows: file:///${user.home}/config-repo
	application: spring.config.name clent
	profile: spring.profiles.active client 添加多个值，最后一个生效
	label: git label(default to(master)) server

	spring.application.name默认值application
	skipSslValidation: true 默认为false
	timeout： 连接超时时间
SpringConfigServer： 
	Git单仓库：
		 git repository URL with placeholders for the {application} and {profile} (and {label} if you need it,
		 one repository per application:uri: https://github.com/用户名/{application}
		 	client：
		 		spring:
	  				application:
	    				name: user-service
	    			cloud:
	    				config:
	    					uri: serverURL
	    					profile: prod
	    					label: master
	    		请求地址为：https://github.com/用户名/user-service
		 one repository per profile: https://github.com/myorg/{profile}
		 search-paths: "{application}": 此参数可达到一个服务一个目录的效果
	Git多仓库配置：

