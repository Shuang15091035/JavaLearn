### Mac下管理多Java环境，
brew install jenv
### Mac 查看符号链接的原身
1. 安装 greadlink -> brew install coreutils
eg: greadlink $(jenv javahome)
### Mac下显示和隐藏隐藏文件快捷方式
shift+command+.

### Mac 修改Root用户密码
	1.sudo bash （以管理员方式进入bash）
	2.sudo passwd root （修改root用户密码）
	3.su -root  （用户切换验证）
### 系统引导工具
https://rufus.ie
### Mac 系统安装方法
	1.通过U盘进行系统安装
		1. * [创建可引导的 macOS 安装器](https://support.apple.com/zh-cn/HT201372)
		2.系统重启，长按Option键，选择U盘中系统
		小结：通过键盘操作，Tab（选项选择）Space（选项确认）
### Mac Ftp 服务使用
Finder->前往 --> 连接服务器--> ftp://IP:port 或 ftp://username:password@IP:port ->远程ftp服务目录
ftp服务器搭建简易方式：
Windows：
	"简单FTP Server"应用程序安装

### Mac下使用命令行工具（eg:Git工具）
	1.检查是否已经安装xcode-select -v （安装：xcode-select --install）
	2.配置路径 sudo xcode-select -s /Library/Developer/CommandLineTools