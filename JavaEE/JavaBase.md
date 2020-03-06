# JavaLearn


MacOS 默认JDK存放位置 /Library/Internet Plug-Ins/JavaAppletPlugin.plugin
Oracle下载的JDK存放位置：/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk

MacOS Java 多版本JDK管理

利用 jenv (jenv 是一个专门用于配置 JAVA_HOME 环境变量工具)你可以用它来管理 Mac 上的 Java 版本。

1. 安装 jenv

命令 $ brew install jenv，安装完成后你需要配置一下 jenv：

$ echo 'export PATH="$HOME/.jenv/bin:$PATH"' >> ~/.bash_profile
$ echo 'eval "$(jenv init -)"' >> ~/.bash_profile
这将在 ~/.bash_profile 文件中追加一个 export 命令和 eval 命令。前者追加 jenv 的路径到环境变量 PATH 中，后者执行 jenv init -命令。这样每打开一个 bash 终端窗就可以调用 jenv 命令，并且默认执行一次 jenv init - 命令了。

2. 查看 java 版本

可以用 jenv -versions 命令查看一下当前 java 版本：

$ jenv versions
* system (set by /Users/kmyhy/.jenv/version)
可以看到 jenv 只列出了系统内置的 Java 版本（system）,因为其它两个版本虽然安装了，但需要我们手动添加到 jenv 中，这样 jenv 才能管理它们。

3. 添加新的 java 版本

使用 jenv add 添加 JDK 1.7:

$ jenv add /Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/
oracle64-1.7.0.71 added
1.7.0.71 added
1.7 added
还记得你的 JDK 1.7 安装目录吗，它是 /Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk 目录。

再添加 JDK 1.8：

$ jenv add /Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home
oracle64-1.8.0.101 added
1.8.0.101 added
1.8 added

记得把 1.8 的路径换一下，换成你自己的路径。

4. 删除 java 版本

你会发现添加新版本时，每个版本都会一次性自动加入 3 个不同版本，比如添加 1.8 的时候报告：

oracle64-1.8.0.101 added
1.8.0.101 added
1.8 added

我们没有必要保留这么多版本，其实它们都指向同一个 JDK，我们可以把 1.8 以外的两个版本删除：

$ jenv remove oracle64-1.8.0.101
JDK oracle64-1.8.0.101 removed
$ jenv versions
* system (set by /Users/kmyhy/.jenv/version)
1.8
1.8.0.101
$ jenv remove 1.8.0.101

这样就清爽多了。

5. 指定 Java 版本

这要用 jenv local 命令：

$ jenv local 1.8
$ jenv versions
system
* 1.8 (set by /Users/kmyhy/.java-version)
1.8.0.101
这样当前版本就变成 1.8 了，你可以看一下：

$ java -version
java version "1.8.0_101"
Java(TM) SE Runtime Environment (build 1.8.0_101-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.101-b13, mixed mode)
你要是想查看当前版本在硬盘上的哪个位置，可以用 jenv which java：

$ jenv which java
/Users/kmyhy/.jenv/versions/1.8/bin/java
这当然不是真实的路径，而是一个硬链接。你可以找到 /Users/kmyhy/.jenv/versions/ 目录，发现所有的 java 版本都被列在了这里，这些硬链接（相当于 windows 的快捷方式）都指向了对应的 java 安装目录。
你还可以指定一个全局的版本：

$ jenv global 1.8
这样，默认的 java 版本就是 1.8 了。如果你想在某个项目中使用 1.6 版本，可以在项目文件夹下新建一个.java-version 文件，将文件内容编辑为 1.6.0.65 保存。这样，你进入这个文件夹时 jenv 会自动使用 1.6 作为当前版本（即 local 版本）。

Java Content

###集合
Collection,ArrayList(Vector),LinkedList, Interator, Generic,HashSet,TreeSet,
List ->Collection (List (ArrayList, LinkedList,Vector))Vector 和ArrayList 都是数组实现的，Vector县城安全的，效率低，ArrayList线程不安全，效率高 LinkedList 链表实现的


Map双列集合，Set->Collection单列结合的
Map 两种迭代方法，增强for循环 1.通过建迭代，2.通过键值对对象
HashMap : 元素保证数据唯一，重写hashSet和Equal ，
TreeMap: 实现Comparable 方法， 传比较器，
HashMap 和 Hashtable 相同点： hash算法,都是双列集合
//        区别: hashMap,线程不安全的， 效率高,v1.2, Hashtable: 线程安全，效率低，v1.0
//        HashMap可以存储null键null值，Hashtable:不可以

Collection
List:(存取有序，有索引，可以重复)
ArrayList：
LinkedList:
Vector
Set:(存取无序，无索引，不可以重复)
HashSet:(底层是哈希算法实现yun)
LinkedHashSet:底层是链表算法实现，可以保证数据唯一性，和hashSetyuna
TreeSet: 底层二叉树算法实现
Map:
HashMap:(底层是链表，针对键)
LinkedHashMap:
TreeMap: 考虑到排序使用

Java异常：
try catch catch finally

编译时异常(Exception必须进行处理)和运行时异常(RuntimeException)，

private static void hashMap() throws Exception {
try{
HashMap<Student,String> m = new HashMap<>();
m.put(new Student(10,"张三"),"北京");
m.put(new Student(10,"李四"),"上海");
m.put(new Student(10,"王五"),"广州");
m.put(new Student(10,"张三"),"深圳");

System.out.print(m);
}catch(ArithmeticException a){
throw new Exception("数据异常，可处理");
}finally{

}
}

final ,finally, finalize的区别：
final: 可以修饰类，不能被继承
修饰方法，不能被重写，
修饰变量，只能赋值一次，
finally: try语句中的一个语句体，不能单独使用，用来释放资源，
finalize: Object类有
自定义异常

File : File类，创建文件/夹，操作，判断

IO流：
字节流：InputStream, OutputStream
字符流：Reader，Writer，

多线程:java中多线程，
JavaVirtualMachines 是多线程么？： 垃圾处理线程，主线程
验证主线程和垃圾处理线程是否交替？ 利用finalize
线程的两种创建方式，区别
线程名称的设置，
线程中获取对象

JDK1.5互斥锁:
RentrantLock , lock , unlock
Java GUI（了解）
FlowLayout 流式布局 pannal 默认布局方式
BoardLayout 边界布局 button默认布局方式
GridLayout 网格布局管理器 （CollectionView）
CardLayout (类似TabBarController)
GridBagLayout 网格包布局管理器 (计算器界面)
事件监听：窗体监听，键盘监听，鼠标监听

<!--注解和xml混合使用-->

1.所有bean配合到xml中
2.所用依赖使用注解
@Autowired 默认不生效，
为了生效： <context:annotation-config></context:annotation-config>

注解1<context:component-scan base-package="com.java.annotation"/>
注解2<context:annotation-config></context:annotation-config>
1.一般情况下两个注解不一起使用
2.注解1扫描含有@Component类，注解自动生效
注解2：只有在xml和注解混合使用时，使注解生效

### hashMap 关于负载因子0.75
	https://blog.csdn.net/reliveIT/article/details/82960063
负载因子太小了：频繁resize， 太大了哈希冲突增加导致性能不好，0.75只是一个折中的选择，和泊松分布没有特别大的关系
eg:
	对于HashMap.table[].length的空间来说，放入0.75*length个数据，某一个bin中放入节点数量的概率情况如上图注释中给出的数据（表示数组某一个下标存放数据数量为0~8时的概率情况）
举个例子说明，
HashMap默认的table[].length=16，在长度为16的HashMap中放入12（0.75*length）个数据，某一个bin中存放了8个节点的概率是0.00000006
扩容一次，16*2=32，在长度为32的HashMap中放入24个数据，某一个bin中存放了8个节点的概率是0.00000006
再扩容一次，32*2=64，在长度为64的HashMap中放入48个数据，某一个bin中存放了8个节点的概率是0.00000006
————————————————
版权声明：本文为CSDN博主「扶我起来我还要写代码」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/reliveIT/article/details/82960063


