# JavaLearn (总结)

项目开发使用 tomcat + maven + java

###WEB-INF目录服务器是不能直接访问的，需要使用Controller指定(转发访问)，js,css,image不要存放到此目录
###.通过maven构建项目，启动项目后默认进入的域名为：
http://localhost:8080/项目名称/WEB-INF/web.xml？
---程序启动后：默认的跳转页面是到项目的web.xml配置文件中去查找

servlet配置：
<servlet-mapping>
<servlet-name>DynamicProject</servlet-name>
<url-pattern>/*.do</url-pattern> //默认位置是 /工程名称(ContextPath)/*.do
</servlet-mapping>

Vector 和 ArrayList区别
1）  Vector的方法都是同步的(Synchronized),是线程安全的(thread-safe)，而ArrayList的方法不是，由于线程的同步必然要影响性能，因此,ArrayList的性能比Vector好。
2） 当Vector或ArrayList中的元素超过它的初始大小时,Vector会将它的容量翻倍,而ArrayList只增加50%的大小，这样,ArrayList就有利于节约内存空间
HashMap和HashTable区别
Hashtable和HashMap它们的性能方面的比较类似 Vector和ArrayList，比如Hashtable的方法是同步的,而HashMap的不是
ArrayList 和 LinkedList区别
ArrayList的内部实现是基于内部数组Object[],所以从概念上讲,它更象数组，但LinkedList的内部实现是基于一组连接的记录，所以，它更象一个链表结构，所以，它们在性能上有很大的差别：
配置集合类的初始大小
集合中大部分元素随着元素的增加而相应的增加，HashTable默认的初始大小为101，载入因子为0.75,当元素个数为75是，就会重新组织元素
