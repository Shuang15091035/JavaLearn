RabbitMq五个概念：
+ 虚拟主机(VM)：一个虚拟主机有一组消息交换机，队列和绑定。

+ 消息(Message)

+ 绑定(Bind):交换机和队列绑定，多对多的关系，

+ 信道(Channel)

+ 交换机(Exchange)


消息代理规范：
	JMS（Java Message Service）：Java消息服务 (ActiveMQ，HornetMQ是JMS实现)
		定义：java-api
		两种消息模型：
			1.Peer-2-Peer （每条消息只有一个发送者和一个接收者）
			2.Pub/Sub
		消息类型：
			TextMessage
			MapMessage
			BytesMessage
			StreamMessage
			ObjectMessage
			Message(只有消息头和属性)
	AMQP（Advanced Message Queuing Protocol）：高级消息队列协议（兼容JMS-RabbitMQ是AMQP的实现）
		定义：网络协议栈（跨平台，跨语言）
		五种消息模型：(本质上讲：后四种和JMS的pub/sub模型一致，仅在路由机制上做了更详细划分)
			1.direct exchange
			2.fanout exchange
			3.topic change
			4.headers exchange
			5.system exchange
		消息类型：
			当实际应用时，有复杂的消息，可以将消息序列化之后发送
Spring支持
	spring-jms 提供对JMS的支持
	spring-rabbitmq 提供对AMQP的支持
	需要ConnectionFactory的实现来连接消息代理
	提供JMSTemplate，rabbitTemplate来发送消息
	@JmsListener（Jms） @RabbitListener（AMQP)注解在方法上监听消息代理发布的消息
	@EnableJms @EnableRabbit开启支持
spring boot自动配置
	JmsAutoConfiguration
	RabbitAutoConfiguration
应用场景：
	1.流量削峰	
	2.日志处理
	3.消息通信
	4.客户端不需要服务端反馈，非核心异步话处理(订单处理->邮件通知->短信通知)

MacOS RabbitMq使用
安装方式：
	1.通过brew 安装
 		brew install rabbitmq
 	2.通过官方下载安装包（https://www.rabbitmq.com）




