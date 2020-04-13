
认证：
	CAS，SAML2，OAUTH2
授权：
	SpringSecurity，shiro
	一套安全狂框架，依据RBAC对用户访问权限进行控制，

Spring Security身份认证方式：
	内存中的身份验证
	JDBC身份验证
	LDAP认证
	OAuth2
权限控制模型(社区，论坛，论文)
	RBAC 基于角色
	ACL 访问控制列表
	ABAC 基于属性
	PBAC 基于策略


Oauth2 Framework

1 introduction
2.client registration
3.protocol endpoint
4.obtaining authorization
5.issuing a access token
6.refresh a access token
7.accessing protected resources

角色：
	客户端
	资源所有者
	认证服务器
	资源服务器

客户端注册（https://blog.csdn.net/dkfajsldfsdfsd/article/details/83614798）
	客户端注册到授权服务器，通常提供给客户一个HTML注册表单给客户填写
	client Password:
		将client的identifer与password用‘：’连接起来，如：
			s6BhdRkqt3:7Fjfp0ZBr1KtDRbnfVdmIw
		对以上结果进行base64编码，结果：
			czZCaGRSa3F0Mzo3RmpmcDBaQnIxS3REUmJuZlZkbUl3
		加上basic 前缀：
			czZCaGRSa3F0Mzo3RmpmcDBaQnIxS3REUmJuZlZkbUl3
		生成get请求的头部信息：
			Authorization: Basic czZCaGRSa3F0Mzo3RmpmcDBaQnIxS3REUmJuZlZkbUl3
		将get请求发送出去。
		这种方式对password进行了编码并没有加密，因此为了保证安全，要使用传输层安全通道，如TLS，

协议端点：
	授权端点: /oauth/authorize
	token端点:/oauth/token

获取授权类型："password", "authorization_code", "refresh_token", "implicit"
	授权码
	http://localhost:8080/oauth/authorize?client_id=client&response_type=code
	密码

	隐式(纯前端应用，没有后台)&redirect_url=https://www.taobao.com）此处的回调地址，后台根据存储的地址进行回调（无效参数）
	http://localhost:8080/oauth/authorize?client_id=client&response_type=token
		Response：
			https://www.baidu.com/#access_token=3142c4e7-abfd-45fa-a89e-e97965d29370&token_type=bearer&expires_in=43125&scope=app
	客户端凭证

颁发令牌

刷新令牌

获取受保护的资源


### 由OAuth2引发的关于如何标识来自浏览器的客户？
	粘性session （session绑定 Nginx可实现）
	Session复制 （Tomcat实现）
	缓存集中式管理 (redis集群方式管理)
	通过JWT实现 （加解密方式，SpringSecurity）


	Session: 保存在服务器，存储的是对象，需要借助Cookie才能正常。客户端禁用Cookie，session将失效。
	Cookie：保存在客户端，存储的是字符串，
	http是无状态的协议，客户每次读取web页面时，服务器都会打开一个新的回话。服务器不会维护客户的上下文信息。
	实现网上购物车，session保存上下文信息的机制，针对每一个用户，变量值保存在服务器端。通过sessionID区分。
	session已cookie或URL重写为基础，默认使用cookie来实现，系统或创建一个名为JSESSIONID 的输出cookie。称为session cookie。
	禁用cookie，可通过URL重写的方式传递sessionId，session cookie针对某一次回话而言，回话结束session cookie消失。persistent cookie存储在硬盘上，通常加密。而且会遭遇cookie欺骗以及针对cookie的跨站脚本攻击，安全性比较差。

	session id保存:
		采用cookie，发送给服务器端；
		表单隐藏字段；

	Session创建：
		调用
		HttpServletRequest.getSession(true)

	Session删除情况：
		程序调用httpSession.invalidate();
		Sessionid失效：
		服务器进程被停止：
	Session有效期：
		如果客户超时未访问，通过
