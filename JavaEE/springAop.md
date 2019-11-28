Aop: 面向切面编程 横向抽取机制，取代纵向继承重复性代码
实现原理:
    （使用代理方式实现）
经典应用：事务管理，性能检测，安全检测，缓存
手动方式：
    接口-实现类 ：spring采用JDK的动态代理proxy
        JDK 动态代理 对装饰者设计模式简化，使用前提，必须有接口
        目标类：
        切面类：
        工厂类：编写工厂生成代理
            Proxy.newProxyInstance
                参数1：loader类加载器，任何类都需要类加载器加载到内存
                    一般当前类.class.getClassLoader();
                    目标类.getCalss().get..
                参数2：interface 代理类需要实现的接口
                    方法1：目标类实例.getClass().getInterfaces();只能获取自己，不能获取父类
                    方法2： new Class[]{UserService.class}
                参数3: InvocationHandler 处理类，接口，必须进行实现类，一般采用匿名内部类
                    提供invoke，代理类的每一个方法指定，都将调用一次invoke
                        Object Proxy ：代理对象
                        Method Method： 代理对象当前指定方法的描述对象（反射）
                        Object 【】
    实现类：spring采用cglib字节码增加
spring 编写代理：
spring AOP编程：


AOP术语：
    target: 目标类，需要被代理的类
    joinpoint(连接点): 指可能会被拦截的方法，例如：目标类中所有方法
    pointCut:切入点，已经被增强的连接点，例如：已经被使用的方法
    advice:通知增强，增强代码
    weaving(织入) ：把增强代码advice 应用到target中
    Aspect:(切面)：切入点(切入点)和通知(advice)的结合
    切入点表达式
        execution(修饰符，返回值 包名 类名 方法 (参数))
        execution(* com.ith.com.*.service..*.*(..))
        within 匹配包或者子包
        this匹配实现接口的搭理对象方法
        target目标对象中的方法
        args：参数服务标准的方法
        bean：对指定的Bean中的所有的方法
    AspectJ 通知类型：
        Aop 联盟定义通知类型，具有特性接口，必须实现，从而确定方法名称
        aspectJ:通知类型，只定义了类型名称
CGLIB 字节码增强：
没有接口，只有实现类
在运行时，创建目标类的子类，从而对目标类进行增强
导入jar包
核心： hibernate
依赖：Struts
spring-core整合了两项内容
spring Aop 增强类型
环绕通知：必须手动执行目标类

### AOP 联盟通知类型：五类通知 （规范）
    前后，环绕，异常通知，引介通知
    spring-aop实现

配置文件
<!-- 创建目标类 -->
<bean id="userServiceId" class="com.java.factoryBean.UserServiceImpl"></bean>
<!-- 创建切面类 -->
<bean id="myAspectId" class="com.java.factoryBean.MyAspect"></bean>
<!-- 创建代理类
使用bean FactoryBean 底层调用getObject（） 返回bean
ProxyFactoryBean 用于创建代理bean 生成代理对象
通过Array可以设置多个值 只有一个， value = ""
interceptorNames: 通知切面类的名称 类型是 string[]
<property name="optimize" value="true"></property> 强制使用cglib 默认使用jdk动态代理
如果目标类有借口使用jdk动态代理 没有接口使用cglib
-->
<bean id="proxyServiceId" class="org.springframework.aop.framework.ProxyFactoryBean">
<property name="Interfaces" value="com.java.factoryBean.UserServicei"></property>
<property name="target" ref="userServiceId"></property>
<property name="interceptorNames" value="myAspectId"></property>
<!-- <property name="optimize" value="true"></property> -->
</bean>
###springaop全自动
从spring容器获取目标类，如果配置AOP将自动生成代理
要确定目标类，aspectj的切入点表达式，导入jar包
<!-- aop
3.1:导入AOP的命名空间
3.2:
<aop:pointcut切入点：从目标对象获取具体方法
<aop:advisor:是一个特殊的切面，只有一个通知和一个切点
advice-ref=""：通知的引用
pointcut-ref=""切入点的引用
execution(* com.java.factoryBean.*. *(..)):切入点表达式
选择方法， 返回值任意，包，类名任意，方法名任意，参数任意
proxy-target-class="true" ：表示使用的事cglib
-->
<aop:config >
<aop:pointcut expression="execution(* com.java.factoryBean.*. *(..))" id="myPointCut"/>
<aop:advisor advice-ref="myAspectId" pointcut-ref="myPointCut"/>
</aop:config>
###Aspectj 基于Java语言的AOP框架
3.1: 基于xml
execution()用于描述方法
    语法：execution(修饰符 返回值 包 类 方法 参数 throws异常）
    修饰符：一般省略，public
        * ：任意
        返回值：
            void：无返回值
            *任意：
        包：省略
        com.itheima.*.servlet :* 表示包中间一层任意
        com.itheima..:包下所有内容
        类：省略
        方法名：不能省略
         *：任意
         参数：
         （）无参数
         （..）任意参数。
     execution(* com.itheima.. * *controller (..) || execution(省略)
3.2:使用注解

Aop:
AOP联盟定义的通知类型：具有特定的接口，必须实现，从而确定方法名称
Aspectj：通知类型，只定义类型名称，已经方法格式
个数：6种，知道五种，掌握一种
before:
aterRuntime:
Around:方法执行前后分别执行，可以阻止方法的执行，必须手动执行目标方法
afterThrowing:
after


3.5:基于xml
1.目标类：接口 + 实现
2.切面类：编写多个通知，采用aspectj通知名称任意（方法名任意)
3.aop编程

Aop注解：
@Aspects 声明切面，修饰切面类，从而获取通知
通知：
@Before 前置
@AfterReturning 后置
@Around 环绕
@AfterThrowing 抛出异常
@After 最终
切入类：
    @Pointcut 修饰方法 private void xxx() 之后通过 “方法名” 获得切入点引用
    @Pointcut("execution(* com.java.bean.anno..*(..))")
    
    
    ### JdbcTemplate
    spring 提供用于操作JDBC工具类，类似 DBUtils
    依赖：连接池 DataSource (数据源）
    
    事务管理：
    事务： 一组业务操作，ABCD，要么全部成功，要不全部失败，
    特性：ACID
        原子性，整体
        一致性，数据完整性
        隔离性，并发
        持久性，结果
    隔离问题：
    脏读，一个事物读到一个事务未提交数据
    不可重复读，一个事务读到另一个事务已提交数据，（update）
    虚读，一个事务读到另一个事务已提交的数据，（insert）
    隔离级别：
    read uncommitted: 读未提交，存在3个问题
    read committed:读已提交，解决脏读，存在2个问题
    repeatable:可重复读，解决 脏读，不可重复读，存在一个
    serializable：串行化，都解决， 单事务
    mysql: 事务操作 --简单
    ABCD 一个事务
    try {
        1.获得连接
        2.开启事务
        connect。setAutoCommit（false）
        A
        B
        C
        D
        3.提交事务
        connect.commit()
        
    }catche{
        4.回滚事务
        }finally{
            释放资源
        }
}
    MySQL事务操作 Savepoint //记录操作的位置，可以会滚到指定的位置，（可以回滚一部分），
    需求： AB(必须) CD(可选)
    try {
    1.获得连接
    2.开启事务
    connect。setAutoCommit（false）
    A
    B
    Savepoint = connect.SetSavePoint();
    C
    D
    3.提交事务
    connect.commit()
    
    }catche{
    4.回滚事务
    if(savepoint != null) {
        //回滚到CD之前
        connect。rollback（savePoint)
        //提交 AB
        conne.commit();
        }else{ //AB异常
        connect.rollback();
        }
    }finally{
    释放资源
    }
    }
    
事务管理介绍
    导入jar包
    spring-tx-5.0.0.RELEASE.jar
    三个顶级接口
    PlatformTransactionManges 平台事务管理，spring要管理事务，必须使用事务管理器
    进行事务配置时，必须配置事务管理器
    TransactionDefination：事务详情（事务定义，事务属性），spring用于确定事务具体详情，
    例如：隔离级别，是否只读，超时时间
    进行事务配置是，必须配置详情，spring将配置封装到对象实现
    TransactionStatus： 事务状态，spring用于记录当前事务运行状态，例如，是否有保存点，事务是否完成，
    spring底层根据状态进行相应操作
  PlatformTransactionManges ：事务管理器
  需要实现类，导入jar包，jdbc (jdbc开发)， orm(对象关系映射):整合hibernate时使用
  常见的事务管理器:
  <p color="red">
  DataSourceTransactionManager jdbc 开发时的事务管理器，采用jdbcTemplate
  hibernateTransactionManager   hibernate开发是事务管理器，整合hibernate </p>
  
  api 详解：
  rollback :根据状态回滚
  commit：根据状态提交
  TransactionDefination：
  getName()String 配置事务名称，一般方法名称：save，add。。。
  IsolationLevel:隔离级别
  getPropagationBehavior:传播行为
  
  传播行为：
整合web：
    
### AOP + EL 动态获取传递参数值
    > 自定义注解
        @Retention(RetentionPolicy.RUNTIME)
        @Target({ElementType.METHOD})
        public @interface DoSomethingAnno {

            String key() default "";
            String cacheName() default "";
            boolean needLog() default false;

        }
    > 注解使用
        @DoSomethingAnno(key = "#key",cacheName = "hello",needLog = true)
        public String hello(@RequestParam(name = "key") String key){}
    > 注解解释
        @Around("@annotation(dst)")
    public void methodParamInterceptor(ProceedingJoinPoint joinPoint, DoSomethingAnno dst) {
        String key = getRealKey(dst.key(),joinPoint);
        String cacheName = dst.cacheName();
        Boolean needLog = dst.needLog();
    }
    public String getRealKey(String key, ProceedingJoinPoint joinPoint) {
        DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();
        SpelExpressionParser parser = new SpelExpressionParser();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
        Expression expression = parser.parseExpression(key);
        EvaluationContext context = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        for(int i = 0 ; i < args.length ; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        return expression.getValue(context).toString();
    }

