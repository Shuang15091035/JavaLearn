Oracle中sysdate的时区偏差

school104 2012-03-07 23:36:24   541   收藏 1
文章标签： 数据库 操作系统
版权
转自： www.fengbin.com/2011/10/oracle-sysdate-timezone.html 

国庆前有个应用上线，让DBA装了一个双机的Oracle RAC，装好后发现数据的sysdate不太对头，和机器时间差了16小时。这个问题后来解决了，也不复杂，只是这个过程异常曲折。 
网上有很多帖子说到了这个问题，操作系统本身的时间是正确的，Oracle里的current_date、current_timestamp都是正常的，就是sysdate有问题。提供的解决方法都是类似的一条命令： 

srvctl setenv database -d <dbname> -t TZ=EAT-8 

看起来容易理解，差了16小时，可能是时区不对，于是把时区改成东八区（EAT-8），可DBA试了几次，都不见效果。其间也请教过其他人，也怀疑过是不是操作系统装的有问题，打算重装系统。直到在网上看到另外一段资料，才明白了其中的奥秘。 

Why is my SYSDATE time not the same as my system clock on Unix? Sysdate is just a system call to the OS to get the time (a "gettimeofday" call). 
Sysdate does NOT use timezones in the database (select dbtimezone, sessiontimezone from dual . But OS (unix level) TZ settings DO alter the time that the OS will pass on to Oracle. 
To debug: 
telnet to the unix box and connect using sqlplus in the telnet session: 
1) once trought the listener using a tnsnames alias 
select to_char(sysdate,'DD-MON-YY HH24:MI:SS') from dual; 
2) once trough a "local" ORACLE_SID connection 
select to_char(sysdate,'DD-MON-YY HH24:MI:SS') from dual; 
if the result is different then it means that the listener is started with a different TZ 
then you current user env ->; stop and start listener with the TZ you want . 
If you are using RAC then use 'srvctl setenv database -d <dbname>; -t TZ=<the TZ you want>;' to define the correct TZ. 

这段文字说sysdate是调用操作系统里的gettimeofday函数，不依赖oracle数据库里设置的时区，用的是操作系统的时区。而对linux来说，就是环境变量里设置的时区。按照这个解释，运行之前命令是正确的，问题就出在TZ的值上。 
运行命令 

cat /etc/sysconfig/clock 

看到的时区是Asia/Beijing，而不是EAT-8，再到系统目录/usr/share/zoneinfo下查询，发现没有EAT-8。于是修改以前的命令，把TZ设成Asia/Beijing，问题解决。 
在此之前，已经有人有过这方面的提示，只是当时不是很清楚数据库里面的机制，误打误撞，总是设不对，明白原理之后，也就有的放矢了。 


改Oracle的时区设置。 
如ALTER DATABASE SET TIME_ZONE='+8.00'; 
修改之后，重新启动Oracle数据库。