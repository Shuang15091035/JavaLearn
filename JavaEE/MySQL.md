查看数据库
	show databases;
使用数据库
	use 'databasename'
查看数据库用户
	SELECT DISTINCT CONCAT('User: ''',user,'''@''',host,''';') AS query FROM mysql.user;
查看用户对应权限
	show grants for 'shuang'@'localhost';
给用户分配权限(*.*：所有数据库，所有表)
	grant all privileges on *.* to 'username'@'Ip';

MySQL备份
	# 备份：数据表结构+数据
	mysqdump -u root db1 > db1.sql -p

	# 备份：数据表结构
	mysqdump -u root -d db1 > db1.sql -p

	#导入现有的数据到某个数据库
	#1.先创建一个新的数据库
	create database db10;
	# 2.将已有的数据库文件导入到db10数据库中
	mysqdump -u root -d db10 < db1.sql -p

常见错误解决：
	### ERROR 1819 (HY000): Your password does not satisfy the current policy requirements
		查看对应密码策略：
			show variables like 'validate_password%';
		修改免密码策略：
			set global validate_password.length=3;
	### Authentication plugin ‘caching_sha2_password’ cannot be loaded: dlopen(/usr/local/mysql/lib/plugin/caching_sha2_password.so, 2):
		https://blog.csdn.net/l569746927/article/details/80025364
		查看用户密码相关设置	
			select user, host, plugin, authentication_string from user\G; 
		修改root对应的密码
			alter user 'root' identified with mysql_native_password by 'shuang';