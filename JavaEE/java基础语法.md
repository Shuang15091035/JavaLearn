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


8.关于Java反射相关操作
	http://tutorials.jenkov.com/java-reflection/dynamic-proxies.html
    简单使用：
        Class clazz = Class.forName("com.jpym.ymfrontcenter.Base");
        Constructor constructor = clazz.getConstructor(new Class[]{Integer.class,Integer.class});
        Object retObj = constructor.newInstance(new Object[]{new Integer(27),new Integer(22)});
        Field field = retObj.getClass().getDeclaredField("num1");
        field.setAccessible(true);
        field.set(retObj,20);
    反射内部生产的代理对象导出：
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", RealObject.class.getInterfaces());
        String path = "/Users/admin/Desktop/RealObject.class";
        try(FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
### Java 大小端问题
    public static void main(String[] args) throws IOException {

        /**
        * 代码默认大小端实现
        */
        ByteBuffer buf =ByteBuffer.allocate(6);
        System.out.println("Default java endian: "+buf.order().toString());

        /**
        * 大小端显示结果
        */
        buf.order(ByteOrder.LITTLE_ENDIAN);
        System.out.println("Now: "+buf.order().toString());
        buf.putShort((short)1);
        buf.putShort((short)2);
        buf.putShort((short)3);
        buf.flip();
        for(int i=0;i<buf.limit();i++)
            System.out.println(buf.get()&0xFF);
        /**
        *   本机大小端方式
        */
        System.out.println("My PC: "+ByteOrder.nativeOrder().toString());
    }