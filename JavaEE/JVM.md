参考：https://www.jianshu.com/p/b072ecc239f5
JVM内存模型
	程序计数器，本地方法栈，虚拟机栈，堆，方法区
程序计数器：（较小的一块内存空间）
	记录线程执行字节码的行号指示器，分支，循环，跳转，异常处理，线程恢复需要依赖这个计数器完成
虚拟机栈：线程隔离 （一个线程对应一个Java栈）栈中包含多个栈帧，每调用一个方法，就会往栈中创建并压入一个栈帧，栈帧存储局部变量表，操作栈，动态链接，方法出口，方法从调用到最终返回结果的过程，就对应一个栈帧从入栈到出栈的过程。