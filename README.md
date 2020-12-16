经验教训：
1.没有人无所不知(开发人员不需要知道一切，需要具备解决问题的能力)
	提高解决问题的能力

	提高团队合作和沟通技巧

	增加对编程概念和语言的了解

	创建优秀的项目来展示你的工作

	专注于编写整洁高效的代码
2.Bug有助于学习
3.没有必要接收正规的教育（可以辩证看待）	
4.上网搜索也是一门技术
5.勤思考
6.当心网上教程
	当你按照教程学习编程时，实际上可能什么都学不到。虽然你可能学到了一些知识，但你并没有真正明白自己编写的代码以及这样做的原因。

	此外，这些教程还可能会漏掉一些重要的代码。初学者更喜欢直接从教程中复制和粘贴代码，而不会认真思考问题。

	一种更好的学习编程的方式是构建自己的项目。找到一小段代码，然后自己编写代码、解决bug、阅读文章和帖子。

	最有效的学习方法是东拼西凑出一个能够运行的程序
7、制表符比空格好


1.常用工具的下载
1.掌握开发工具，(常用的快捷方式，提供的功能)
2.学习工程中常用的jar包的下载地址，jar包的用法
3.使用jar中常用的类；
4.了解开发架构的思想，一些常用的设计模式的使用

Java查看源码使用反编译工具：
../picture/Enhance class Decompiler.png
JavaXML和Java类提示功能
../picture/Spring Tools(aka spring IDE and Spring Tool Suite)(sts).png


PxCook - 最高效易用的自动标注工具,设计研发协作利器

JavaSE 开发教程
	https://docs.oracle.com/javase/tutorial/index.html
### PC同时配置内外网
	为保持网络的可用性：配置路由数据为永久有效 （目的地之 子网掩码 下一跳地址）
		route add -p 192.168.0.0 mask 255.255.0.0 192.168.1.1
		route add -p 10.8.0.0 mask 255.255.0.0 10.8.0.200
### istio为已部署服务建立网络，该网络具有负载均衡，服务间认证，监控等功能


### 数据结构可视化网站
https://www.cs.usfca.edu/~galles/visualization/Algorithms.html
https://visualgo.net/zh

### 分布式Session解决方案
	方案一：客户端存储
	方案二：session复制
	方案三：session绑定
	方案四：基于Redis存储session方案（RedisCluster）（基于sentinel的主从复制）

### 通过OAuth2实现同一用户不同平台多token的管理，解决某一个平台客户退出的登录，而其他平台账号已登录账号仍保持在线？
	通过用户名+token+sessionid的方式实现多端同时登陆
	移动端OAuth2实现：	
	移动端获取到code，发送到后台，
		后台通过存储的appid+appsecret+code 获取到token
		后台通过获取的token获取客户信息
		返回客户信息到移动端
	移动端获取到客户信息，实现客户登录成功

### 常见的内网穿透工具
	https://blog.csdn.net/jake_tian/article/details/99962157
		基本原理都是通过natp原理
	NGRok
### 国内包无法下载问题
	解决方法：设置代理(代理地址这里指向的是蓝灯开启的代理地址)
		export http_proxy='http:127.0.0.1:52015'
		export https_proxy=$http_proxy
	解决案例： go get -v github.com/golang/protobuf/protoc-gen-go
		错误：unrecognized import path "google.golang.org/protobuf/types/descriptorpb": https fetch: Get "https://google.golang.org/protobuf/types/descriptorpb?go-get=1": dial tcp 216.239.37.1:443: i/o timeout
### 编程语言的实现，从AST(抽象语法树)开始

### Java多线程并发编程艺术
	happen-before 原则：
	1).程序顺序规则: 一个线程中的每个操作，happens-before于该线程中的任意后续操作。
	2).监视器锁:对一个锁的解锁，happens-before于随后对这个锁的加锁
	3).volatile变量规则：对一个volatile域的写，happens-before于任意后续对这个volatile域的读
	4).传递性：如果A happens-before B，且 B happens-before C，那么A happens-before C
	注意：两个操作之间具有happens-before关系，并不意味着前一个操作必须要在后一个操作之前执行！happens-before仅仅要求前一个操作（执行的结果）对后一个操作可见，且前一个操作按顺序排在第二个操作之前（the first is visible to and ordered before the second)
	as-if-serial:
		不管怎么重排序，程序的执行结果不能被改变
	顺序一致性内存模型：
		1).一个线程所有操作必须按照程序顺序来执行
		2).（不管是否同步)，所有线程都只能看到一个单一的顺序.
		在顺序一致性内存模型中，每个操作必须原子执行且立刻对所有线程所见。
C10K问题
	The C10K problem
		http://www.kegel.com/c10k.html

###安装了签名错误或损毁的文件，或者安装的文件来自来路不明的恶意软件
解决办法是
1.按下shift 按键 点击重启按钮 重启后
2.疑难解答-->启动-->f7 禁用未签名强制验证.
3.即可解决.

### Mybatis 延迟加载的使用（客户体验）
	可以取用缓存接口结果，后台进行接口请求，进行接口内容回写