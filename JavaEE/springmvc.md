# JavaLearn (springmvc)

1.springmvc 接收参数
springmvc使用mode对象，Model对象相当于application
Application对象中数据可以是el表达式进行获取

2.springmvc中的页面回显 ？

3.URL模板映射 /rest/user/update/${user.id} 映射到 @RequestMapping("updata/{id}") 通过 @PathVariable 传递给参数 Integer id
主要为了请求restful设计模式
restful设计模式：请求简洁，安全，方便搜索引擎使用
@RequestParam
@RequestParam(defaultValue="222", value="ss",required=true) // value=“ss” 表示参数的别名,字段可省略
4.转发和重定向
本类转发， return "forward:list.do"
                return "forward:/user/list.do"
跨类转发，
            return "forward:/items/list.do"
redirect
本类重定向：return "redirect:list.do"
    跨类重定向: 根目录开始
                return "redirect:/items/list.do"

5.<mvc:annotation-driven/>
<mvc:annotation-driven/>默认创建RequestMappingHandlerMapping，RequestMappingHandlerAdapter, 默认提供json数据格式支持
6.requestBody,resposeBody
    requestBody:把前台页面传递的json格式数据，强制转换为Javabean
    resposeBody:后台把Javabean转换为json格式数据返回给页面
    注解不能直接使用，依赖两个Jackson的jar包，
/Users/admin/Desktop/JavaLearn/1B0A8CC3-AB48-4E23-955B-CCD6DBB4E713.png
静态文件放到webRoot下
Ajax?
配置json格式转换
在springMVC.xml中
定义页面

7.springmvc多视图支持

###数据源

浏览器层：JavaScript ，jQuery

服务器层：
struts: web层， （valuestack值栈，拦截器) 被取缔  springmvc
spring： service层，重要
hibernate: dao层，知识点杂乱  被取缔 mybatis

day01： spring（loc(控制反转)，DI(依赖注入)）整合Junit，整合web
day02: AOP(切面编程)， jdbcTemplate
day03: 事务管理，SSH整合

spring核心：IOC 和AOP
spring生成的东西为 核心包：bean，core，context，EL
4+1(4个核心一个依赖) 导入的jar包，+
spring ：IOC，创建对象交给spring

### 服务器推技术
	
ajax短轮询：
	setInterval()
	服务器压力/资源浪费
	HTTP长连接：
		服务器推：
			Comet
	Servlet3 异步任务：
		
	Spring :
		DeferedResult<> 
		ThreadLocal:
		key: threadId
		value: 线程

	服务器实时推送
		SSE：流方式 （H5)
			服务器：spring配合
			单向通信，默认支持断线重连
		WebSocket:
			HTML5中协议，实现与客户端和服务器双向，基于消息的文本或二进制数据通信
			双向通信
	推送方式比较：
					短轮询： 				长轮询：		SSE： 				WebSocket
		浏览器支持：  最高     			很高     	中（IE，Edge均不支持)  中（早起浏览器不支持）
		实时性：		最低					较高			很高					很高
		代码复杂度：	最容易				较容易		容易					最复杂
		连接性质：	短连接				长连接		长连接				长连接
		适用：		需要服务极大量			准实时性应用，	实时，基本都是文本		实时，需要支持多样化的用户数
					或极小量，实时性		比较关注浏览	交互的应用			据类型的应用或者是原生程序
					要求不高				器兼容性


	redis 缓存穿透终极解决方案->布隆过滤

	同一进程中的多条线程将共享该进程中的全部系统资源，如虚拟地址空间，文件描述符，信号处理等，同一进程的多个线程有各自的调用栈，自己的寄存器环境，自己的线程本地存储。
	线程实体：程序，数据，TCB（Thread Control Block）描述。
	TCB：
		线程状态，
		不运行时，保存的现场资源。
		一组执行堆栈
		存放每个线程的局部变量主存区
		访问同一个进程的主存和其他资源


###springMVC 注解
一：@Controller： 用于标记在一个类上，使用它标记的类就是一个SpringMVC Controller 对象

二：@RequestMapping：RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
1、 value， method；
value：     指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；
method：  指定请求的method类型， GET、POST、PUT、DELETE等；
2、consumes，produces (GET:默认按照application/form-data)（POST:form-data,x-www-form-urlencoded,raw,binary，指定其中的一个，）
consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
produces:    指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；
3、params，headers
params： 指定request中必须包含某些参数值是，才让该方法处理。
headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求
三：@Resource和@Autowired都是做bean的注入时使用，其实@Resource并不是Spring的注解，它的包是javax.annotation.Resource，需要导入，但是Spring支持该注解的注入
@Autowired注解是按照类型（byType）装配依赖对象，默认情况下它要求依赖对象必须存在，如果允许null值，可以设置它的required属性为false。如果我们想使用按照名称（byName）来装配，可以结合@Qualifier注解一起使用
@Autowired
@Qualifier("userDao")
private UserDao userDao;
四： @ModelAttribute和 @SessionAttributes
代表的是：该Controller的所有方法在调用前，先执行此@ModelAttribute方法，可用于注解和方法参数中，可以把这个@ModelAttribute特性，应用在BaseController当中，所有的Controller继承BaseController，即可实现在调用Controller时，先执行@ModelAttribute方法。
五：@PathVariable
