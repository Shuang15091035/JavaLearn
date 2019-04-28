### spring security加入
	application.yml文件
	security.basic.enabled=true
	security.user.name=user
	security.user.password=password
	eureka.client.serviceUrl.defaultZone: http://user:password@localhost:8761/eureka/
### spring cloud配置注册中心高可用时 控制面板含义
 DS Replicas：显示集群中的其他成员
 available replicas：表示集群中的其他注册中心
	 1.通过IP而非主机名配置注册中心
		eureka.instance.instance-id: ${spring.cloud.clent.ip-address}:${server.port}
	 2.available-replicas不正常显示
		2.1.每一个副本的eureka.instance.appname属性值相同，如果未配置此项，spring cloud会优先使用spring.application.name 覆盖此值。
		2.2. 如果两个副本的spring.application.name不同，appname相同，也被识别为可用并正常显示（此种情况会将这两个服务识别为同一个服务的不同副本，而不会根据application.name 视为不同服务）
		2.3. 为实现高可用，注册中心必须注册自己(默认值都为true)
			eureka.client.registerWithEureka: true
			eureka.client.fetchRegistry: true
		2.3. 所有的服务在注册时必须指向所有的注册中心，否则高可用就没有意义
		2.4. 服务发现通过配置eureka.client.serviceUrl.defaultZone，如果配置 eureka.instance.prefer-ip-address: ture,服务通过IP地址进行服务查找，需要配置spring.cloud.in
		tip： 另外需要注意的是，第一个注册中心启动的时候，会抛出一个错误：
		　　com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server
		　　这是因为它在试图向指定的注册中心注册自己的时候，目标中心还没有启动。这个错误无伤大雅，因为eureka的注册是有心跳的，等目标中心启动之后，这个服务会在下一次心跳的时候自动注册进去
### 服务发现机制
	peer（对等体）Eureka Client会缓存服务注册表中的信息，无需每次都请求Eureka Server,降低压力，Eureka宕机仍可保证消费者可以使用缓存找到服务提供者完成调用
	综上，通过心跳检查，客户端缓存，可以提高系统灵活性，可伸缩性，可用性
