==和equal区别
    ==：比较的是对象，equal比较的是对象存储的内容
HashMap(基于拉链法实现的散列表单线程),TreeMap(基于红黑树实现的单线程)
HashTable(基于拉链法实现多线程)


Spring MVC 参数绑定
4.包装类型的绑定

5.POJO(实体类)类型绑定
    5.1.日期类型需要做注解说明
    @DateTimeFormat( pattern = "yyyyMMdd" ) //指定日期类型
    private Date birthday;
    
6. 复合POJO(实体类) 类型绑定

7. Java并发操作

    7.1  多线程发令枪；
countDownLatch：是先多线程的并发操作，new CountDownLatch(10)
countDownLatch.countDown()，调用一次减一，
countDownLatch.await();线程阻塞方法，当CountDownLatch值减为零，线程统一执行(发令枪)
    7.2.队列
    线程安全就是说多线程访问同一代码，不会产生不确定的结果。
        并发：交替轮询执行
        并行：同时执行

RabbitMq
持久性：
