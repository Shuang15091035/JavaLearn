小结一下Spring容器配置类逻辑：
    1.找出配置类
    2.找出配置类中的import注解
    3.import注解的值是class，如果该class实现了importSelector接口，就调用SelectImport方法，将返回的名称实例化
        3.1：ConfigurationClassParser类的processImports方法会对ImportSelector中的selectmImports进行调用
     SpringFactoriesLoader.loadFactoryNames：会在所用的Jar包下查找META-INF/spring.factorise 文件并对相应的key值进行筛选，通过反射实例化这些类到IOC容器中，最后容器中就有了一系列标注了@Configuration的JavaConfig形式的配置类

Spring Boot 解决跨域问题：CORS 8080服务请求8181服务
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
Spring Boot编译插件：
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
Spring Boot 注解：
	@SpringBootApplication，开启自动配置
		@Configuration,@EnableAutoConfiguration,@ComponentScan
		@EnableAutoConfiguration:根据项目jar进行项目自动配置
	关闭自动配置：
		@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
	定制Banner：
		1.src/main/resources 新建一个banner.txt
		2.http://patorjk.com/software/taag 生成字符，复制到banner.txt中
	关闭Banner：
		SpringApplication app = new SpringApplication(MainApplication.class);
		app.setShowBanner(false);
		app.run(args);
	外部xml：
		@ImportResource({"classpath:some-context.xml","classpath:another-context.xml"})
	运行：
		mvn spring-boot:run
		java -jar xx.jar
		java -jar xx.jar --server.port=9090 //可修改端口
	Spring 中需要通过@PropertySource指明properties的位置，通过@Value注入值，
	Spring Boot在application.yml/properties指定属性，通过@Value直接获取值
	类型安全的配置：
		Spring Boot通过@ConfigurationProperties将Properties属性和一个Bean及其属性关联
Spring Boot 日志配置：
	logging.file=D:/mylog/log.log
	logging.level.org.springframework.web=DEBUG 指定每个jar包的日志级别
Spring Profile配置：多环境配置
	Profiles文件：application-{profile}.properties eg:application-prod.properties/yml
	spring.profiles.active=prod 指定激活的配置文件
	java -jar xx.jar --debug //查看已启用的配置报告
Spring starter 自定义：
	1.pom引入自动配置类
	2./src/main/resources/META-INF/spring.factories 创建文件，
	3.添加配置：org.springframework.boot.autoconfigure.EnableAutoConfiguration=  com.jpym.springbootstarthello.HelloServiceAutoConfiguration
	4.在其他类中引入starter
Spring Boot WEB
	引入spring-boot-starter-web -> web自动配置存储在spring-boot-autoconfigure.jar中的org.springframework.boot.autoconfigure.web下，
		ServerPropertiesAutoConfiguration 和 ServerProperties自动配置内嵌Servlet
	Web相关配置：
		1.自动配置的ViewResolve
			ContentNegotiatingViewResolver: 不自己处理view，代理给其他ViewResolve来处理不同的view，最高的优先级
			BeanNameViewResolver：控制器中的返回值的字符串，由当前ViewResolve去查找Bean的名称返回字符串的View来渲染
			InternalResourceViewResolve：通过设置前缀，后缀，以及控制器中返回视图的字符串，得到实际页面。
		2.自动配置的静态资源
			类路径下的/static, /public, /resources /META-INF/resources 静态文件直接映射为/** 通过 http://localhost:8080/**访问
			/META-INF/resources/webjars 静态文件映射为/webjar/** 通过http://localhost:8080/webjar/**访问
		3.自动配置formatter和Convert
		4.自动配置的HTTPMessageConverters
		5.静态首页的支持
			classpath:/static/index.html
			classpath:/public/index.html
			classpath:/resources/index.html
	Spring Boot Web配置：
		自定义MVC配置
			@Configuration @EnableWebMvc
		 部分改造WebMVC：（不会覆盖Spring Boot的自动配置）
		 	@Configuration
		 	? extends WebMvcConfigurerAdapter 替代 @EnableWebMvc
	注册Servlet，Filter，Listener
		1.直接注册Bean
			@Bean
			public XxServlet xxServlet() {
				return new XxServlet();
			}
		2.通过RegistrationBean (ServletRegistrationBean;FilterRegistrationBean;ServletListenerRegistrationBean)
			@Bean
		    public ServletListenerRegistrationBean registrationBean () {
		        return new ServletListenerRegistrationBean(new ZzListener());
		    } 
	Tomcat配置：
		Spring Boot内嵌Tomcat为Servlet容器
			通过org.springframework.boot.autoconfigure.web.ServerProperties声明内容，在application.yml中配置即可。
			通用Servlet容器通过server作为前缀，Tomcat特有配置，server.tomcat作为前缀，
			通过代码方式配置Bean：EmbeddedServletContainerCustomizer接口Bean
			直接配置：TomcatEmbededServletContainerFactory
			@Bean
			public EmbeddedServletContainerFactory servletContainer() {
				TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
				factory.setPort(8888);
				return factory;
			}
		替换Tomcat：
			<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                </exclusion>
            </exclusions>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework.boot</groupId>
	            <<artifactId>spring-boot-starter-jetty</artifactId>
	        </dependency>
	     SSL配置：（Secure Sockets Layer）安全套接层 SSL 在网络传输层对网络连接进行加密，SSL协议位于TCP/IP 协议与各种应用层协议之间，通通信提供安全支持，SSL协议分为两层：SSL记录协议（SSLRecordProtocol，建立在TCP协议之上，提供数据封装，压缩，加密，SSL握手协议，（SSLHandshakeProtocol）建立在SSL记录协议上，用于实际数据传输开始前，提供身份认证，协商加密算法，交换加密秘钥
	     	1.生成证书
	     		JDK/JRE中工具 keytool证书管理工具可以生成自签名的证书，也可从SSL证书授权中心获取。
	     			keytool -genkey -alias tomcat 
	     			-> .keystore
	     	2.Spring Boot 配置SSL
	     			拷贝到项目根目录
	     			配置Application.yml 
	     			server:
		     			ssl:
						    key-store: .keystore
						    key-password: shuang
						    key-alias: tomcat
						    key-store-type: JKS
			3.http转向https
			4.Favicon配置 (浏览器输入地址后，标题前面的icon)
				可以通过spring.mvc.favicon.enabled=false
				设置自己的Favicon
					将favicon.ico（文件名不可变）放置到META-INF/resource/,resource/,static/,public/
		WebSocket:
			为浏览器和服务端提供了双工异步通信的功能，

		B/S系统软件特色：
			单页面应用、响应式设计，数据导向
			Bootstrap：开发响应式和移动优先的Web应用的最流行的HTML，CSS，JavaScript框架。通过一套代码，实现在不同设备上显示想要的视图的功能，Bootstrap还为我们提供了大量美观的HTML元素前端组件和JQuery插件


 <!--<script th:src="@{jquery-3.4.1.min.js}" type="text/javascript"></script>-->
    <!--<script th:src="@{bootstrap/js/bootstrap.min.js}"></script>-->



    <!--<div class="pannel panel-primary">-->
        <!--<div class="pannel-heading">-->
            <!--<h3 class="pannel-title">列表</h3>-->
        <!--</div>-->
        <!--<div class="pannel-body">-->
            <!--<ul class="list-group">-->
                <!--<li class="list-group-item" th:each="person:${people}">-->
                    <!--<span th:text="${person.age}"></span>-->
                <!--</li>-->
            <!--</ul>-->
            <!--<span th:text="${singlePerson.name}"></span>-->
        <!--</div>-->
    <!--</div>-->
    <!--<div th:if="${not #lists.isEmpty{people}">-->
        <!--<div class="pannel pannel-primary">-->
            <!--<div class="pannel-heading">-->
                <!--<h3 class="pannel-title">列表</h3>-->
            <!--</div>-->
            <!--<div class="pannel-body">-->
                <!--<ul class="list-group">-->
                    <!--<li class="list-group-item" th:each="person:${people}">-->
                        <!--<span th:text="${person.age}"></span>-->
                    <!--</li>-->
                <!--</ul>-->
                <!--<span th:text="${singlePerson.name}"></span>-->
            <!--</div>-->
        <!--</div>-->
    <!--</div>-->

    <!--<script th:inline="javascript">-->
<!--//        [[${}]] 获取实际的值-->
        <!--var single = [[${singlePerson}]];-->
        <!--console.log(single.name="/"+single.age)-->
    <!--</script>-->

    <!--<li class="list-group-item" th:each="person:${people}">-->
        <!--<span th:text="${person.name}"></span>-->
        <!--<span th:text="${person.age}"></span>-->
        <!--&lt;!&ndash;th:onclick="'getName(\'' + ${person.name}+'\');'"&ndash;&gt;-->
        <!--<button class="btn" th:onclick="'getName(\'' + ${person.name} +'\');'">获取名字</button>-->
    <!--</li>-->

    <!--详细使用使用参考：http://www.thymeleaf.org-->