参考：https://www.jianshu.com/p/b072ecc239f5
JVM内存模型
	程序计数器，本地方法栈，虚拟机栈，堆，方法区
程序计数器：（较小的一块内存空间）
	记录线程执行字节码的行号指示器，分支，循环，跳转，异常处理，线程恢复需要依赖这个计数器完成
虚拟机栈：线程隔离 （一个线程对应一个Java栈）栈中包含多个栈帧，每调用一个方法，就会往栈中创建并压入一个栈帧，栈帧存储局部变量表，操作栈，动态链接，方法出口，方法从调用到最终返回结果的过程，就对应一个栈帧从入栈到出栈的过程。

Jvm：内存查看（Java VisualVM）
插件：
	Visual GC
JVM对象分配原则：
	对象优先在Eden区分配
	大对象直接进入老年代；
	长期存活的对象将进入老年代；
	动态年龄判断；
	空间分配担保；
JVM调优工具
	jps：查看Java程序进程状态
		jps -l 
	jstat：查看Java堆空间统计信息
		jstat -options
		jstat -gc <pid>
			S0C：第一个Survivor区域的容量（KB）
			S1C：第二个Survivor区域的容量（KB）
			S0U：第一个Survivor区域的使用容量（KB）
			S1U：第二个Survivor区域的使用容量（KB）
			EC：Eden区域容量（KB)
			EU：Eden区域使用容量（KB)
			OC：Old区容量（KB）
			OU：Old区使用容量（KB）
			MC：Method区容量（KB）
			MU：Method区使用容量（KB）
			CCSC：压缩类空间大小
			CCSU：压缩类使用空间
			YGC：年轻代回收次数
			YGCT：年轻代回收时间
			FGC：老年代回收次数
			FGCT：老年代回收时间
			GCT：垃圾回收总时间
	jmap:查看内存使用情况，以及内存中对象数量和大小				(出现内存溢出时可通过此方法进行查看)
		jmap -dump:format=b,file=/tmp/dump.dat
	jhat:对dump文件进行分析
	MAT工具对dump文件分析 
		内存溢出调试：
			-Xms8M -Xmx8M -XX:+HeapDumpOnOutOfMemoryError
	jstack:(查看线程状况) （可用于分析线程状态）

可视化日志分析工具：GCEasy
	-XX:+PrintGC
	-XX:+PrintGCDetails
	-XX:PrintGCTimeStamps
	-XX:+PrintGCDateStamps
	-XX:+PrintHeapAtGC
	-xloggc:../log/gc.log


Tomcate优化：
	tomcat参数调整：
		1.禁用AJP服务c
		2.设置线程池
		3.运行模式，使用NIO 修改连接器Protocol
	调整JVM参数：
		使用何种垃圾回收器，堆内存设置；
		通过GCeasy分析GC日志，看看是否是堆内存设置有问题、
程序优化：
	JVM字节码  javap -v  xx.class >test.txt
	官方字节码地址：https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html


	
