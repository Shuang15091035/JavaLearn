SpringBoot日志实现：
简单来说，该模式就是把一些复杂的流程封装成一个接口供给外部用户更简单的使用。这个模式中，设计到3个角色。

1）.门面角色：外观模式的核心。它被客户角色调用，它熟悉子系统的功能。内部根据客户角色的需求预定了几种功能的组合（模块）。

2）.子系统（模块）角色：实现了子系统的功能。它对客户角色和 Facade 是未知的。它内部可以有系统内的相互交互，也可以由供外界调用的接口。

3）.客户角色：通过调用 Facede 来完成要实现的功能。
日志门面：
    JCL（Jakarta Commons Logging），SLF4j（Simple Logging Facade for Java），Jboss-logging
日志实现：
    Log4j 、JUL（java.util.logging） 、Log4j2 、 Logback
Hibernate 中使用的日志系统为 jboss-logging
Spring 中使用的日志系统为 commons-logging