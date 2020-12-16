ssh-keygen 所在包安装：yum  install -y openssh-server openssh-clients
启动ssh：

　　service sshd start 或 /etc/init.d/sshd start

　　配置开机启动：

　　chkconfig --level 2345 sshd on


查看linux程序的内核调用：
	strace -ff -o out 程序 .class



man :
 查询基本命令

 2类
 man 2 系统调用

 查看进程线程数：(proc：内核映射，内部管理进程)
 cat /etc/proc/


 lsof 展示已打开的文件描述符


 ### Mac 防止文件误删
 alias rm="trash"

