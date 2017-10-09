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

Collection,ArrayList(Vector),LinkedList, Interator, Generic,HashSet,TreeSet,
