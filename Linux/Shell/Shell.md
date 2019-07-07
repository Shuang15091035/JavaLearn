Shell
linux正则表达式以及三剑客grep,awk,sed命令

linux 图形界面程序：GNOME,KED,UNITY

Shell通过两个环境变量控制提示符格式
PS1：控制最外层命令行的提示符格式。
PS2：控制第二层命令行的提示符格式。

<!-- :<<!
! -->
linux 输入指令后不显示,回车后指令有效果：
eg:stty sane

Linux 文件上传下载功能：
	sudo yum install lrzsz -y
关系运算符：
-eq
-ne
-gt
-lt
-ge
-le
布尔运算符：
!
-o
-a
逻辑运算符：
&&
||
字符串运算符：
=：检查两个字符是否相等，相等返回true
!=: 不相等返回true
-z:检测长度是否为0，为0返回true
-n: 长度不为0，返回true
$：字符串是否为空，不为空返回true
文件测试运算符：
-b
-c
-f
-g:检测文件是否设置了SGID为
-k：检测文件是否设置了粘着位(sticky Bit)
-p:是否是有名管道
-u:是否设置了SUID
-r:
-w:
wx:
-s:检测文件是否为空(文件大小是否大于0)，不为空返回true
-e:检查文件或者目录是否存在，
-S：判断文件是否是socket
-L:判断文件是否存在并且是一个符号链接