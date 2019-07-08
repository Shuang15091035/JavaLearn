# JavaLearn (spring)

spring: IOC (控制反转) spring创建对象实例，获取实例通过spring工厂获取(需要将实现类的全限定名配置到xml中)
配置文件:添加约束 <bean>

(DI)依赖注入 将对象需要的实例设置过去 表现形式<property>

核心API

BeanFactory
ApplicationContext: 是Bean子接口(国际化，事件传递，bean装配)
ClassPathXmlApplicationContext: 用于加载classpath（类路径，src）下的xml，加载xml运行时位置
->/WEB-INF/classes/...xml
FileSystemXmlApplicationContext: 用于加载指定盘符下的xml
加载xml运行时位置->/WEB-INF/..xml 通过Java web ServletContext getRealPath获取具体盘符

装配bean基于XML 
实例化方式：三种方式：默认构造，静态工厂，工厂
bean种类：
普通bean：<bean id="" class="A"> </bean> ,spring直接创建A实例，并返回
FactoryBean：是一个特殊的bean，具有工厂生产对象的能力，只能生产特定的对象，bean必须实现factoryBean的接口，此接口提供的方法 getObject() 用于获取特定的bean
<bean id="" class="FB"> </bean> 先创建FB实例，使用getObject（）方法，并返回返回值
BeanFactory和FactoryBean?
BeanFactory：工厂，用于生产任意bean
FactoryBean：特殊bean，用于生产另一个特定的bean，例如ProxyFactoryBean，此工厂bean用于生产代理

###作用域
singleton：单例
prototype：多例

生命周期：
    初始化和销毁
    后处理bean
    
    ###属性的依赖注入
    手动装配:
        基于xml：构造方法，setter方法
        基于注解
    自动装配：Struts和spring整合可以自动装配
    byTpe:
    byName:
    auto:自动装配:
    <!-- 构造方法注入 constructor-arg 用一个配置方法的一个参数argument
    1.使用name 此方式在开放中不常用，一般不知道属性
    value:用于基本数据
    ref:引用数据
    
    2.结合使用Type和index
    type:参数的类型
    index：参数索引
    -->
    <bean id="userId" class="com.java.constructor.User">
    <constructor-arg index="0" value="shuang" type="java.lang.String"></constructor-arg>
    <constructor-arg index="1" value="20" type="java.lang.Integer"></constructor-arg>
    </bean>
    
    基于xmlsetter方法
    <bean id="userId" class="com.java.constructor.User">
    <property name="name" value="jack"></property> <!-- 两种情况等效 -->
    <property name="age">
    <value>90</value> <!-- 子标签的和属性相同 -->
    </property>
    </bean>
    
    p命名空间
    setter方法进行简化，使用必须添加命名空间xmlns:p="http://www.springframework.org/schema/p"
    
<!--    注解-->
就是一个类 @注解名称
开发中使用注解取代XML配置文件
<context:component-scan base-package="...">
1.@Component 取代<bean class=">"
@Component(id) 取代 <bean id="" class="">
2.web开发，提供3个@Component注解衍生注解（功能一样）  取代<bean class="'">
@Repository:dao层
@Service: service
@Controller: web层
@Aspect :声明一个切面
@PointCut: 声明一个切点
    @Pointcut( "execution(*  com.jpym.kingdomms.util.JzInterfaceUtil.*(..))" )
    public void logServiceAccessPointCut() {
    }
@After，@Before 声明一个建言：@PointCut定义切点
    @After( "logServiceAccessPointCut()" )
@Configuration 声明一个Java配置类
@ComponentScan
@EnableAspectJAutoProxy :开启Spring对AspectJ代理的支持
3.依赖注入
普通值 @value
引用之：
    方式1：按照[类型]注入
        @Autowired
    方式2：名称注入1
        @Autowired
        @Qualifier
    方式3：名称注入
        @Resource("名称“)
注解和XML混合：
    Bean配置XML中
依赖使用注解
@Autowire
    默认不生效，xml配置<context:annotation-config>

1.<context:component-scan base-package="...">
2.<context:annotation-config>
不同时使用，
    1扫描@Component注解是，注入注解自动生效
    2xml和注解（注入）混合使用时，注解生效
sprng整合junit
jar包导入： 4 + 1 + logging + spring-test

//制定了单元测试的执行类，
@RunWith(SpringUnit4ClassRunner.class)
@ContextConfiguration：指定Spring配置文件所在的路径，可以同时指定多个文件。
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring2.xml"})
@TestExecutionListenners 这个用于指定在测试类执行之前，可以做的一些动作 ，
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
@Transactional：这里的@Transactional不是必须的，这里是和@TestExecutionListeners中的TransactionalTestExecutionListener.class配合使用，用于保证插入的数据库中的测试数据，在测试完后，事务回滚，将插入的数据给删除掉，保证数据库的干净。如果没有显示的指定@Transactional，那么插入到数据库中的数据就是真实的插入了
RunWith改变了JUnit的默认执行类(Suite)，继承了org.junit.runners.BlockJUnit4ClassRunner的单元测试执行类 springJUnit4ClassRunner既是子类

4整合web
导入jar包：
1.tomcat启动加载配置文件
servlet  - init(ServiceConfig) -> <load-on-startup>
filter -> init(FilterConfig) -> web.xml注册过滤器自动调用初始化
listener ->ServletContextListenner ->servletContext对象监听

spring 提供监听器 ContextLoaderListener ->web.xml <listenner>
<listener>
    <
</Listener>

spring中bean的继承和Java中的继承不同：前者是实例之间的参数的延续，后者是特殊的细化，前者是对象之间的关系，后者是 类之间的关系。
a.Spring 中的子bean和父bean可以是不同的类型，Java中继承，子类是一种特殊的父类；
b.Spring中的bean的继承是实例之间的关系，主要表现是参数的延续，而java中的继承是类和类之间的关系，主要体现在方法和属性的延续。
c.Spring中子bean不可以作为父bean使用，不具备多态性，Java中的子类实例完全可以做父类实例使用。

事务：
事务失效：@Transaction 注解，捕获RuntimeExection，Error，Checked Exection
日志数据丢失：事务的传播性 不正确的使用导致数据丢失


### JavaEE开发颠覆SPring Boot实战（publisher：2016.3）
####Spring 常用配置
2.1@Scope：Spring容器如何新建Bean实例
    Prototype ：每次调用新建一个Bean实例
    Singleton ：全局共用一个Bean实例
    Request ：Web项目，每一个http request新建一个Bean实例
    Session ：Web项目，给每一个http session新建一个实例
    GlobalSession：使用在portal应用中，每一个global http Session新建一个Bean实例
    Spring Batch中还有一个Scope是使用，@StepScope

Spring EL-Spring （SPring表达式语言）
2.2spring主要在注解@Value中使用表达式
    （1）.注入普通字符
    （2）.注入操作系统属性
    （3）.注入表达式运算结果
    （4）.注入其他Bean的属性
    （5）.注入文件内容
    （6）.注入网址内容
    （7）.注入属性文件
    以下依赖可以简化文件相关操作，common-io可将文件转化为字符串

    <denpendency>
        <groupId>commons-io</groupId>
        <artifactId>common-io</artifactId>
        <version>2.3</version>
    </denpendency>
    @Value("I Love You!")
    @Value("#{systemProperties['os.name']}")
    @Value("#{T(java.lang.Math).random() * 100.0}")
    @Value("#{demoService.anther}")
    @Value("classpath:com/el/test.txt")
    private Resource testFile;
    @Value("http://www.baidu.com")
    private Resource testUrl;
    @Value(${book.name})  //test.properties
    @Bean
    public static PropertySourcesPlaceholderConfigurer PropertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
2.3Bean的初始化和销毁
    （1）Java配置方式：使用@Bean的initMethod和destroyMethod（相当于XML配置的init-Method和destroy-Method）
    (2) 注解方式：利用JSR-250的@PostConstruct(构造函数执行完成之后执行)和@PreDestroy(Bean销毁之前执行)
    配置类：
    方式一：
    @Bean(initMethod="init",DestroyMethod="destroy")
    BeanWayService beanWayService() {
        return BeanWayService();
    }
    方式二：不需要执行，方法上已经加上注解
    JSR250WayService jSR250WayService() {
        reeturn JSR250WayService();
    }
2.4 Profile
    配置类：
    @Bean
    @Profile("dev")
    public DemoBean devDemoBean() {
        return new DemoBean();
    }
    @Bean
    @Profile("prod")
    public DemoBean devDemoBean() {
        return new DemoBean();
    }
    启动类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod"); //设置prod为活跃状态
        context.register(ProfileConfig.class); //后置注册Bean配置类，不然会报Bean未定义错误
        context.refresh(); //刷新容器
2.5 Application Event（Bean和Bean之间消息通信）（观察者模式的实现）
    Spring事件：
        （1）：自定义事件，集成ApplicationEvent
        （2）：定义事件监听器，实现ApplicationListener
        （3）：使用容器发布事件 applicationContext.publishEvent
###Spring 高级话题
3.1 Spring Aware（Spring Aware设计用来框架内部使用，使用了SpringAware你的Bean和Spring框架耦合）
    Spring提供的Aware接口   （可以让Bean获取到Spring容器的服务）
        BeanNameAware：获取容器Bean名称
        BeanFactoryAware：获取当前Bean factory调用容器服务
        ApplicationContextAware：当前Application Context调用容器服务
        MessageSourceAware：获取message source 获取文本信息
        ApplicationEventPublisherAware：应用事件发布器，可以实现接口发布时间
        ResourceLoaderAware：获取资源加载器，可以获取外部资源
3.2 多线程（MutilThread）
    Spring通过TaskExecutor（任务执行器）通过在配置类中通过@EnableAsync开启异步任务支持，并通过在实际执行的Bean的方法中使用@Async声明是一个异步方法
    如果注解在类级别，则表明该类所有的方法都是异步方法，而这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor
3.3 计划任务（scheduling）
    @EnableScheduling 开启对计划任务的支持
    @Scheduled 声明一个计划任务 支持多种类型的计划任务 包含cron（UNIX和类UNIX系统定时任务）,fixDelay,fixTate等
3.4 条件注解 @Conditional
    通过Profile获取不同的Bean，@Condition提供更通用的基于条件的Bean的创建
    条件类： 
        import org.springframework.context.annotation.Condition;
        import org.springframework.context.annotation.ConditionContext;
        import org.springframework.core.type.AnnotatedTypeMetadata;
        public class LinuxCondition implements Condition {
            @Override
            public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
                return conditionContext.getEnvironment().getProperty("os.name").contains("Window");
            }
        } 

    配置类：
        @Bean
        @Conditional(WindowCondition.class)
        public ListService windowsListService() {
            return new WindowsListService();
        }
3.5 组合注解与元注解（元注解：注解到其他注解的注解，被注解的注解称之为组合注解）

3.6 Enable*注解的工作原理
    
3.7 Test(测试)
    Spring通过Spring TestContext Framework对集成测试提供顶级支持
    Spring提供了一个SpringJunit4ClassRunner类提供Spring TestContext Framework功能
    通过@ContextConfiguration(@SpringBootTest)来配置Application Context，通过@ActiveProfiles确定活动的profile

###Spring MVC 4.X
4.2 搭建SpringMVC项目
    1.maven依赖
    2.日志配置（logback.xml）
    3.页面展示（JSP）
    4.SpringMVC配置
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.EnableWebMvc;
        import org.springframework.web.servlet.view.InternalResourceViewResolver;
        import org.springframework.web.servlet.view.JstlView;

        @Configuration
        @ComponentScan("com.jpym.ymfrontcenter")
        @EnableWebMvc
        public class MyMvcConfig {
            @Bean
            public InternalResourceViewResolver viewResolver() {
                InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
                viewResolver.setPrefix("/WEB-INF/classes/views/");
                viewResolver.setSuffix(".jsp");
                viewResolver.setViewClass(JstlView.class);
                return viewResolver;
            }
        }

    5.Web配置
        import org.springframework.web.WebApplicationInitializer;
        import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
        import org.springframework.web.servlet.DispatcherServlet;

        import javax.servlet.ServletContext;
        import javax.servlet.ServletException;
        import javax.servlet.ServletRegistration.Dynamic;

        public class WebInitializer implements WebApplicationInitializer{
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
                applicationContext.register(MyMvcConfig.class);
                applicationContext.setServletContext(servletContext);
                Dynamic servlet = servletContext.addServlet("dispatcher",new DispatcherServlet(applicationContext));
                servlet.addMapping("/");
                servlet.setLoadOnStartup(1);
            }
        }

        WebApplicationInitializer是Spring提供用来配置Servlet3.0+配置的接口从而实现了替代web.xml的位置，实现此接口将会自动被SpringServletContainerInitializer（用来启动Servlet3.0容器）
4.3 Spring MVC 常用注解
    @Controller
    @Service
    @RequestMapping
    @RequestBody
    @ResponseBody
    @PathVariable
    @RestController（@ResponseBody和@Controller的组合注解）
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.ResponseBody;

    import javax.servlet.http.HttpServletRequest;

    @Controller
    @RequestMapping("/anno")
    public class DemoController {

    //    produces可定制返回的媒体类型和字符集或需要返回值是json可设置produces="application/json;chartset=UTF-8"
        @RequestMapping(produces = "text/plain;chartset=UTF-8")
        public @ResponseBody String index(HttpServletRequest request) {
            return "url" + request.getRequestURL() + "can access";
        }
    //    /anno/pathvar/xx
        @RequestMapping(value = "/pathvar/{str}", produces = "text/plain;chartset=UTF-8")
        public @ResponseBody String demoPathVar(@PathVariable String str, HttpServletRequest request) {
            return "url" + request.getRequestURL() + "can access";
        }

    //    /anno/requestParam?id=1
        @RequestMapping(value = "/requestParam", produces = "text/plain;chartset=UTF-8")
        public @ResponseBody String demoPathVar(Long id, HttpServletRequest request) {
            return "url" + request.getRequestURL() + "can access";
        }

    //    访问路径/anno/obj/?id=1&name=xx
        @RequestMapping(value = "/obj", produces = "application/json;chartset=UTF-8")
        @ResponseBody //可以使用到方法上
        public String demoPathVar(DemoObj obj, HttpServletRequest request) {
            return "url" + request.getRequestURL() + "can access";
        }

    //    不通路径映射到相同的方法 /anno/name1 或/anno/name2
        @RequestMapping(value = {"name1", "name2"}, produces = "application/json;chartset=UTF-8")
        public @ResponseBody String demoPathVar(HttpServletRequest request) {
            return "url" + request.getRequestURL() + "can access";
        }
    }

4.4 Spring MVC基本配置
    4.4.1 静态资源映射
        1.@EnableWebMvc开启SpringMVC支持，否则WebMvcConfigurerAdapter不生效
        2.继承WebMvcConfigurerAdapter，重写SpringMVC配置
        3.addResourceLocations指防止资源的位置
        SpringMVC定制配置需要继承WebMvcConfigurerAdapter类不使用@EnableWebMvc注解
        public class MyMvcConfig extends WebMvcConfigurerAdapter{
        @Bean
        public InternalResourceViewResolver viewResolver() {
            InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
            viewResolver.setPrefix("/WEB-INF/classes/views/");
            viewResolver.setSuffix(".jsp");
            viewResolver.setViewClass(JstlView.class);
            return viewResolver;
        }
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
        }
    4.4.2 拦截器配置
        （Interceptor）实现对每一个请求处理前后进行相关业务处理类似于Servlet的Filter
        普通Bean实现HandlerInterceptor或继承HandlerInterceptorAdapter实现自定义拦截器

    4.4.2 @ControllerAdvice(对于控制器的全局配置放到同一个位置，注解了@Controller的类的方法可以使用@ExceptionHandler，@InitBinder，@ModelAttribute对所有注解了@RequestMapping的控制器方法有效)
        @ExceptionHandler 用于全局处理控制器里的异常
        @InitBinder: 用来设置WebDataBinder，WebDataBinder用来自动绑定前台请求参数到Model中
            只对当前的控制器注册一个属性编辑器
        @ModelAttribute：@ModelAttribute本身的作用是绑定键值对到Model里，此处是让全局的@RequestMapping都能获取到此处设置的键值对
        * [theamleaf使用](https://www.cnblogs.com/ityouknow/p/5833560.html)
            <div th:object="${demoObj}">
            <span th:text="*{id}"></span>
            <span th:text="*{name}"></span>
            </div>
            当前标签中的*和$符号的区别
                星号表示评估对象在选定对象上表达，而不是整个上下文
        更多配置：
            WebMVCConfigurerAdapter类的API是WebMVCConfigurer的实现
    4.5 Spring MVC 的高级配置
        4.5.1文件上传配置
             1.jar包 Commons-fileupload 非必要 commons-io 简化I/O操作
             2.添加转向upload页面的视图控制器
                @Component
                public class DemoConfig extends WebMvcConfigurerAdapter {
                    @Override
                    public void addViewControllers(ViewControllerRegistry registry) {
                        registry.addViewController("/index").setViewName("/index");
                        registry.addViewController("/toUpload").setViewName("/upload");
                    }
                }
             3.单文件接收配置
                @RequestParam("file") MultipartFile file
             4.多文件接收
                 List<MultipartFile> files = ((MultipartHttpServletRequest) servletRequest).getFiles("file");
             5.文件写入
                FileUtils.writeByteArrayToFile
             6.运行测试
                ip+port/toUpload
            小结：
                spring boot运行需要配置
                    spring:
                      servlet:
                        multipart:
                          enabled: true
                    #      单文件上传要求
                          max-file-size: 1MB
                    #      多文件上传要求
                          max-request-size: 10MB
        4.5.2 HttpMessageConverter
            1. HttpMessageConverter 用来处理Request和Response里的数据的，Spring内置大量的    HttpMessageConverter，例如MappingJackson2HttpMessageConverter,StringHttpMessageConverter

###