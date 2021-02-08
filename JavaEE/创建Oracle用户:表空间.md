IDE：PLSQL 安装下载地址：
	https://www.allroundautomations.com/registered/plsqldev.html
IDE：PLSQL Developer 13注册码
	product code：4vkjwhfeh3ufnqnmpr9brvcuyujrx3n3le
	serial Number： 226959
	password：xs374ca

Oracle ：一般一个数据库对应一个实例
	数据库：指的是固定的，基于磁盘的数据文件，控制文件，日志文件，参数文件和归档日志文件等。
	实例：非固定的，基于内存的基本进程与内存结构，当服务关闭，实例就不存在了；
DDL(表)
	create, drop,alter,
DML(记录)
	select ,insert ,update,delete
DCL（用户）
	grant，revoke，

### 创建临时表空间（未使用）
create temporary tablespace NOTIFYDB_TEMP tempfile '${ORACLE_HOME}\oradata\NOTIFYDB_TEMP.bdf' size 100m reuse autoextend on next 20m maxsize unlimited; 

### 创建表空间
create tablespace YMFRONT datafile '/u01/app/oracle/oradata/XE/YMFRONT.dbf' size 512M reuse autoextend on next 40M maxsize unlimited default storage(initial 128k next 128k minextents 2 maxextents unlimited);
### 查看数据文件位置
	select name from v$datafile
### 查看所有的表空间
	select * from dba_tablespaces;
	select * from v$tablespaces;
### 查看当前用户的表空间
	select default_tablespaces 
	from dba_users
	where username='username'
### 查看用户下的所有表
	select * from user_tables;
	select * from dba_tables 
	where owner='username';
### 查看表空间下的所有用户
	select distinct s.owner
	from dba_segments s
	whree s.tablespace_name = 'username'
### 创建用户并指定表空间
	create user ymfront identified by ymfront default tablespace YMFRONT 
### 给已存在的用户分配表空间
	alter user username default tablespace userspace;
### 通过sysdba给用户分配权限/角色
	grant dba to ymfront;
	grant connect,resource to ymfront;
	grant select any table to ymfront;
	grant delete any table to ymfront;
	grant update any table to ymfront;
	grant insert any table to ymfront;
扩展：
	Oracle中已经存在三个重要的角色，connect，resource，dba
	CONNECT角色：一般是授予最终用户的典型权利，最基本的
		ALTER SESSION  --修改会话
		CREATE CLUSTER --建立聚簇
		CREATE DATABASE LINK --建立数据库链接
		CREATE SEQUENCE --建立序列
		CREATE SESSION --建立会话
		CREATE SYNONYM --建立同义词
		CREATE VIEW --建立视图
	RESOURCE：一般是授予开发人员的
		CREATE CLUSTER --创建集群
		CREATE PROCEDURE --创建存储过程
		CREATE SEQUENCE	--创建队列
		CREATE TABLE 	--创建表
		CRREATE TRIGGER	--创建触发器
		CTEATE TYPE	--创建类型
	DBA：最高权限，相当于管理员角色
	Oracle 用户，角色(权限的集合，具有相同权限的用户归纳为某种角色)，权限
		权限赋值给角色，角色赋值给用户，也可以直接把权限赋值给用户
		权限类别：Object对象级，System系统级，Role角色级
		权限可以授予给用户，特殊用户，或角色，授予权限给特殊用户（public） 每个用户都享有的权限
		Oracle角色放在dba_roles表中，某角色包含的系统权限放在dba_sys_privs， 对象权限dba_tab_privs
		系统权限：系统规定用户使用数据库的权限（系统权限对用户而言）
		对象权限：某种权限用户对其他用户的表或视图的存取权限（针对表和视图）
		系统权限分类：
			DBA: 拥有全部特权，是系统最高权限，只有DBA才可以创建数据库结构。
			RESOURCE:拥有Resource权限的用户只可以创建实体，不可以创建数据库结构。
			CONNECT:拥有Connect权限的用户只可以登录Oracle，不可以创建实体，不可以创建数据库结构。
			授权
				grant connect, resource, dba to 用户名， ；
### 删除指定用户下的所有数据
	conn / as sysdba
	drop user 'username' cascade
	create user 'username' identified by password default tablespace 'tablespace';
	conn user/pwd


### 创建表空间和用户后，通过conn ymfront/ymfront登录报错
	Error accessing PRODUCT_USER_PROFILE
	Warning:  Product user profile information not loaded!
	You may need to run PUPBLD.SQL as SYSTEM
原因：
	SQL*PLus用户环境设置存在问题
解决方法：
	1.账户进行解锁
	alter user system account unlock;
	2.账户密码修改
	alter user system identified by system;
	3.pupbld.ql文件查找(find ××× -iname ×××)执行
	@?/sqlplus/admin/pupbld.sql
小结：
	这个问题是在手工建库之后出现的。鉴于此，可以在手工创建数据库之后顺便执行该脚本以防该问题的出现。
	有关PRODUCT_USER_PROFILE的更多参考：《【实验】使用PRODUCT_USER_PROFILE禁止特定用户在SQL*Plus中使用 delete语句》（http://space.itpub.net/519536/viewspace-609906）

### 查询当前用户下的所有表
	select table_name from user_tables;
	或者
	select * from all_tables where owner='TEST'；用户名必须大写

### 配置Tns

### oracle数据库迁移
	1.使用Oracle exp，imp
		从数据库导出数据
			exp user/pwd@ip:port/servername file='filepaht'
		向目标数据库导入数据：
			imp user/pwd@ip:port/servername file='filepaht' full=y;
	2.通过Navicat进行数据库迁移

### 给用户分配操作procedure的权限 
	grant execute on SYS.DBMS_AQADM to ymfront with grant option;
### 使用
Grant Create Job To ymfront;
### 赋予用户AQ相关权限
	GRANT EXECUTE ON DBMS_AQ TO ymfront;
	GRANT EXECUTE ON DBMS_AQADM TO ymfront;
### 强制删除Oracle Jobs,停止运行中的Job
 begin
 sys.dbms_scheduler.drop_job('YMFRONT."JOB NAME"',true);
 end;

 ###  以下跨网段执行SQL执行速度慢，通过explain plan 进行解决
  select * from VW_SF_FUND_PERFORMANCE VSFP WHERE VSFP.FUND_CODE = '260109'
  1.通过SQLplus进行操作 设置行宽(一行显示多少字符) set linesize 500
  2.


### 执行计划分析
	字段解释：
		ID：一个序号，但不是执行的的先后顺序，执行顺序右上原则）
		OPERATION：操作的内容
		Rows：当前操作影响的行数(当前操作的Cardinality,oracle估计当前操作的返回结果数)
		Cost(CPU)：Oracle计算出来的一个数据（代价），SQL执行代价
		Time：Oracle估计当前操作的时间
	谓语分析：
		Access：
			通过某种范式定位了需要的数据，然后读取这些结果集，
			表示谓语条件的值将会影响数据的访问路径（表或者索引）
		Filter:
			把所有的数据都访问了，然后过滤掉不需要的数据
			表示谓语条件的值不会影响到数据的访问路径，只起到过滤的作用。
	统计信息：
		recursive calls:产生的递归sql调用的条数
			当执行一条SQL语句时，产生的对其他SQL语句的调用，这些额外的语句称之为''recursive calls''或''recursive SQL statements''. 我们做一条insert 时，没有足够的空间来保存row记录，Oracle 通过Recursive Call 来动态的分配空间
		db block gets:从buffer cache中读取的block的数量
			当前模式块意思就是在操作中正好提取的块数目，而不是在一致性读的情况下而产生的块数。正常的情况下，一个查询提取的块是在查询开始的那个时间点上存在的数据块，当前块是在这个时刻存在的数据块，而不是在这个时间点之前或者之后的数据块数目。
		consistent gets:从buffer cache中读取的undo数据的block的数量
			这里的概念是在处理你这个操作的时候需要在一致性读状态上处理多少个块，这些块产生的主要原因是因为由于在你查询的过程中，由于其他会话对数据块进行操作，而对所要查询的块有了修改，但是由于我们的查询是在这些修改之前调用的，所以需要对回滚段中的数据块的前映像进行查询，以保证数据的一致性。这样就产 生了一致性读。
		physical reads: 从磁盘读取的block的数量  
			就是从磁盘上读取数据块的数量，其产生的主要原因是：
			（1） 在数据库高速缓存中不存在这些块
			（2） 全表扫描
			（3） 磁盘排序
			它们三者之间的关系大致可概括为：
			逻辑读指的是Oracle从内存读到的数据块数量。一般来说是'consistent gets' + 'db block gets'。当在内存中找不到所需的数据块的话就需要从磁盘中获取，于是就产生了'physical reads'。
			Physical Reads通常是我们最关心的，如果这个值很高，说明要从磁盘请求大量的数据到Buffer Cache里，通常意味着系统里存在大量全表扫描的SQL语句，这会影响到数据库的性能，因此尽量避免语句做全表扫描，对于全表扫描的SQL语句，建议增 加相关的索引，优化SQL语句来解决。
			关于physical reads ，db block gets 和consistent gets这三个参数之间有一个换算公式：
			数据缓冲区的使用命中率=1 - ( physical reads / (db block gets + consistent gets) )
		redo size:DML生成的redo的大小   
		bytes sent via SQL*Net to client:数据库服务器通过SQL*Net向查询客户端发送的查询结果字节数
		bytes received via SQL*Net from client:通过SQL*Net接受的来自客户端的数据字节数
		SQL*Net roundtrips to/from client:服务器和客户端来回往返通信的Oracle Net messages条数
		sorts(memory):在内存执行的排序量   
		sorts(disk):在磁盘上执行的排序量
		rows processed:处理的数据的行数
	JOIN 方式：
		hash join:
		merge join:
		nested loop:
	表访问方式：
		full table scans:
		table access by rowId:
		indexScan:
			index range scan:
			index unique scan:
			index full scan:
			index fast full scan:
			index skip scan:

### 一下语句执行速度很慢原因
select *
from vw_sf_fund_performance vsfp
where trim(vsfp.fund_code) = '260109'

select count(1) as count
from finchina.tq_fd_basicinfo@finchinadb ftfbi,
	 finchina.tq_fd_derivedn@finchinadb ftfd
where ftfbi.securityid = ftfd.securityid

### 关于基金公告中通过dblink获取远程数据库中clob类型的基金公告详情内容的解决方案
	解决方案一：
		创建和远程数据库中同名数据表
### 在PL/SQL development中好使的SQL语句在Java的mybatis中包ora-00911的错误
	SQL语句最后的一个分号，删除
### 关于本地不安装Oracle，使用PLSQL远程连接Oracle数据库
	https://blog.csdn.net/qq_21875331/article/details/82890183
	note：需要安装vc_redis.x64.exe附件


	SELECT tcga.CUST_NO, COUNT(tcga.FUND_CODE)
FROM T_CUST_GM_ASSET tcga
GROUP BY tcga.CUST_NO


### 创建只读用户
1.创建用户;
	create user jpcf identified by jpcf2233;
2.分配基本权限
	grant connect to jpcf ;
 	grant create synonym to jpcf;
 	grant create session to jpcf;
3.分配查询权限
	select 'grant select on '||owner||'.'||object_name||' to jpcf;'
 	from dba_objects
 	where owner in ('YMFRONT')
 		and object_type='TABLE';
4.创建同义词
	SELECT 'create or replace SYNONYM  jpcf.' || object_name|| ' FOR ' || owner || '.' || object_name|| ';'  from dba_objects 
 	where owner in ('YMFRONT')
 	and object_type='TABLE';
5.表数据查询


## 数据库Oracle解决Ora-01653无法扩展表空间

SELECT UPPER(F.TABLESPACE_NAME) "表空间名", 
D.TOT_GROOTTE_MB "表空间大小M)", 
D.TOT_GROOTTE_MB - F.TOTAL_BYTES "已使用空间M)", 
TO_CHAR(ROUND((D.TOT_GROOTTE_MB - F.TOTAL_BYTES) / D.TOT_GROOTTE_MB * 100,2),'990.99') "使用比", 
F.TOTAL_BYTES "空闲空间M)", 
F.MAX_BYTES "最大块M)" 
FROM (SELECT TABLESPACE_NAME, 
ROUND(SUM(BYTES) / (1024 * 1024), 2) TOTAL_BYTES, 
ROUND(MAX(BYTES) / (1024 * 1024), 2) MAX_BYTES 
FROM SYS.DBA_FREE_SPACE 
GROUP BY TABLESPACE_NAME) F, 
(SELECT DD.TABLESPACE_NAME, 
ROUND(SUM(DD.BYTES) / (1024 * 1024), 2) TOT_GROOTTE_MB 
FROM SYS.DBA_DATA_FILES DD 
GROUP BY DD.TABLESPACE_NAME) D 
WHERE D.TABLESPACE_NAME = F.TABLESPACE_NAME 
ORDER BY 4 DESC; 


SELECT T.TABLESPACE_NAME,D.FILE_NAME,D.AUTOEXTENSIBLE,D.BYTES,D.MAXBYTES,D.STATUS 
FROM DBA_TABLESPACES T,DBA_DATA_FILES D 
WHERE T.TABLESPACE_NAME =D.TABLESPACE_NAME 
ORDER BY TABLESPACE_NAME,FILE_NAME;


ALTER TABLESPACE FINCHINAFCDD ADD DATAFILE 'D:\APP\ADMINISTRATOR\ORADATA\ORCL\FINCHINA04.DBF' SIZE 1024M AUTOEXTEND ON NEXT 100M MAXSIZE 30G;