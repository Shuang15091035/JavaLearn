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

1.@Component 取代<bean class=">"
@Component(id) 取代 <bean id="" class="">
2.web开发，提供3个@Component注解衍生注解（功能一样）  取代<bean class="'">
@Repository:dao层
@Service: service
@Controller: web层
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
