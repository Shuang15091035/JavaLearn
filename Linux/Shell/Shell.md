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

###Shell中IFS用法
	一 、IFS的介绍
	   Shell 脚本中有个变量叫IFS(Internal Field Seprator) ，内部域分隔符。完整定义是The shell uses the value stored in IFS, which is the space, tab, and newline characters by default, to delimit words for the read and set commands, when parsing output from command substitution, and when performing variable substitution.
	   Shell 的环境变量分为set, env 两种，其中 set 变量可以通过 export 工具导入到 env 变量中。其中，set 是显示设置shell变量，仅在本 shell 中有效；env 是显示设置用户环境变量 ，仅在当前会话中有效。换句话说，set 变量里包含了env 变量，但set变量不一定都是env 变量。这两种变量不同之处在于变量的作用域不同。显然，env 变量的作用域要大些，它可以在 subshell 中使用。
	   IFS 是一种 set 变量，当 shell 处理"命令替换"和"参数替换"时，shell 根据 IFS 的值，默认是 space, tab, newline 来拆解读入的变量，然后对特殊字符进行处理，最后重新组合赋值给该变量。
	二、 IFS的简单实例
	1 查看IFS的值
	echo "$IFS"
	 
	echo "$IFS"|od -b
	0000000 040 011 012 012  
	0000004
	直接输出IFS是看不到值的，转化为二进制就可以看到了，"040"是空格，"011"是Tab，"012"是换行符"\n" 。最后一个 012 是因为 echo 默认是会换行的。
	2 实际中的应用
	#!/bin/bash
	OLD_IFS=$IFS #保存原始值
	IFS="" #改变IFS的值
	...
	...
	IFS=$OLD_IFS #还原IFS的原始值
###shell中的set -- "$x"和set -- $x的使用
	先看下set --的英文使用说明
	set –

	Set 1 to the value of x,even if it begins with ′−′ or ′+′ :set −− &quot;
	1 to the value of x,even if it begins with  
	′
	 − 
	′
	  or  
	′
	 + 
	′
	  :set−−"x"

	Set the positional parameters to the expansion of x, even if x expands with a leading ‘-’ or ‘+’ :
	set – $x

	set – "$X"就是把X的值返回给$1, set – $X就是把X作为一个表达式的值一一返回

	说明：set --是根据分隔符IFS，把值依次赋给$1,$2,$3，例子2就是展示这个。

	例子1：

	通过这个例子，就可以清楚的看到他们之间的区别。
	例子2：

	注意的是：
	set --x 使用模板是：

	oIFS=$IFS //保存系统默认分隔符
	…
	IFS=** //设置分割符以便set – 处理字符串使用

	IFS=$oFIS //还原系统默认分隔符

	我之前犯了个低级错误:
	IFS=oIFS 这样会导致’o’,‘I’,‘F’,'S’都是分隔符
### 常用SHELL指令的内容
	http://bbs.chinaunix.net/forum.php?mod=viewthread&tid=218853&page=7#pid1617953
	假設我們定義了一個變量為：
file=/dir1/dir2/dir3/my.file.txt
我們可以用 ${ } 分別替換獲得不同的值：
${file#*/}：拿掉第一條 / 及其左邊的字串：dir1/dir2/dir3/my.file.txt
${file##*/}：拿掉最後一條 / 及其左邊的字串：my.file.txt
${file#*.}：拿掉第一個 .  及其左邊的字串：file.txt
${file##*.}：拿掉最後一個 .  及其左邊的字串：txt
${file%/*}：拿掉最後條 / 及其右邊的字串：/dir1/dir2/dir3
${file%%/*}：拿掉第一條 / 及其右邊的字串：(空值)
${file%.*}：拿掉最後一個 .  及其右邊的字串：/dir1/dir2/dir3/my.file
${file%%.*}：拿掉第一個 .  及其右邊的字串：/dir1/dir2/dir3/my
記憶的方法為：
# 是去掉左邊(在鑑盤上 # 在 $ 之左邊)
% 是去掉右邊(在鑑盤上 % 在 $ 之右邊)
單一符號是最小匹配﹔兩個符號是最大匹配。

${file:0:5}：提取最左邊的 5 個字節：/dir1
${file:5:5}：提取第 5 個字節右邊的連續 5 個字節：/dir2

我們也可以對變量值裡的字串作替換：
${file/dir/path}：將第一個 dir 提換為 path：/path1/dir2/dir3/my.file.txt
${file//dir/path}：將全部 dir 提換為 path：/path1/path2/path3/my.file.txt

利用 ${ } 還可針對不同的變數狀態賦值(沒設定、空值、非空值)： 
${file-my.file.txt} ：假如 $file 沒有設定，則使用 my.file.txt 作傳回值。(空值及非空值時不作處理) 
${file:-my.file.txt} ：假如 $file 沒有設定或為空值，則使用 my.file.txt 作傳回值。 (非空值時不作處理)
${file+my.file.txt} ：假如 $file 設為空值或非空值，均使用 my.file.txt 作傳回值。(沒設定時不作處理)
${file:+my.file.txt} ：若 $file 為非空值，則使用 my.file.txt 作傳回值。 (沒設定及空值時不作處理)
${file=my.file.txt} ：若 $file 沒設定，則使用 my.file.txt 作傳回值，同時將 $file 賦值為 my.file.txt 。 (空值及非空值時不作處理)
${file:=my.file.txt} ：若 $file 沒設定或為空值，則使用 my.file.txt 作傳回值，同時將 $file 賦值為 my.file.txt 。 (非空值時不作處理)
${file?my.file.txt} ：若 $file 沒設定，則將 my.file.txt 輸出至 STDERR。 (空值及非空值時不作處理)
${file:?my.file.txt} ：若 $file 沒設定或為空值，則將 my.file.txt 輸出至 STDERR。 (非空值時不作處理)

tips:
以上的理解在於, 你一定要分清楚 unset 與 null 及 non-null 這三種賦值狀態.
一般而言, : 與 null 有關, 若不帶 : 的話, null 不受影響, 若帶 : 則連 null 也受影響.
### 仅执行一次的工作调度 at  对应的守护进程atd
	atq:查询目前主机上有多少的at工作调度 3 jobnumber
	atrm: 	移除工作号为3的调度
	batch：系统有空时(CPU 工作负载小于0.8，工作负载表示负责的工作数量)才进行背景任务(利用at下达指令)
	

