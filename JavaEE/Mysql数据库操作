MySQL相关知识


#MySQL性能优化

# MySQL架构和存储引擎
	

##业务设计

###事务
	1.特性
		原子性
		一致性
		隔离性
		持久性
	2.隔离级别
		未提交读
		提交读
		可重复读
		串行化

###锁
	排它锁
	共享锁
	存储引擎
		innodb
		myisam
	物理结构修改
		锁表
		解决办法


### 逻辑设计
	数据库范式：优化数据数据存储方式
		第一范式：当关系模式R的所有属性都不能在分解为更基本的数据单位时，称R是满足第一范式的，简记为1NF，eg：表（姓名，性别，教课时间（开始时间，结束时间））教课时间仍可拆分，不具备原子性
			eg:
				字段：地址(省份，城市，详细地址)如果需求知道那个省那个市并按其分类，那么显然第一个表格是不容易满足需求的
		第二范式：如果关系模式R满足第一范式，并且R得所有非主属性都完全依赖于R的每一个候选关键属性，称R满足第二范式，简记为2NF
			eg:
				每一行的数据只能与其中一列相关，即一行数据只做一件事。
		第三范式：设R是一个满足第一范式条件的关系模式，X是R的任意属性集，如果X非传递依赖于R的任意一个候选关键字，称R满足第三范式，简记为3NF.
			eg:
				数据不能存在传递关系，即没个属性都跟主键有直接关系而不是间接关系。像：a-->b-->c  属性之间含有这样的关系，是不符合第三范式的
		注：关系实质上是一张二维表，其中每一行是一个元组，每一列是一个属性
		小结：
			三大范式只是一般设计数据库的基本理念，可以建立冗余较小、结构合理的数据库。如果有特殊情况，当然要特殊对待，数据库设计最重要的是看需求跟性能，需求>性能>表结构。所以不能一味的去追求范式建立数据库。
### 物理设计
	数据类型选择
		当一个列可以有多种选择时
		1.优先考虑数字类型
		2.其次是日期，时间类型
		3.最后是字符类型
		4.对于相同级别的数据类型，应该优先选择占用空间小的数据类型
	命名规范
	存储引擎选择

## 查询和索引
### 索引相关
	索引分类： 
		主键索引：不能重复，不能有空值
		唯一索引：不能重复，可以有空值
		单值索引：单列，一个表可以有多个单值索引
		复合索引：多个列构成的索引
	添加索引： create index 'indexname' on ‘tablename(tableField)’;
				或
			  alter table ‘tablename’ add index 'indexname(tableField)';
	删除索引：
		drop index 'indexname' on 'tablename'
	查询索引：
		show index from 'tablename'
### SQL性能
	分析SQL的执行计划：explain 模拟SQL优化器执行SQL语句 参考：https://www.cnblogs.com/annsshadow/p/5037667.html
	MySQL查询优化(Query optimize)会干扰我们的优化
	优化方法：官网介绍

	 id ： 编号
	 select_type ：查询类型
	 table  ：表
	 partitions ：
	 type ：类型
	 possible_keys：预测用到的索引
	 key  ：实际用到的索引
	 key_len ：实际使用索引的长度
	 ref  ：表之间的引用
	 rows ：通过索引查询到的数据量
	 filtered ：
	 Extra ：额外的信息


	create table course(
		cid int(3),
    	cname varchar(20),
    	tid int(3)
    );


	 insert into course(cid,cname,tid) values(1,'java',1);
	 insert into course(cid,cname,tid) values(2,'html',1);
	 insert into course(cid,cname,tid) values(3,'sql',2);
	 insert into course(cid,cname,tid) values(4,'web',3);

	 insert into teacher(tid,tname,tcid) values(1,'tz',1);
	 insert into teacher(tid,tname,tcid) values(2,'tw',2);
	 insert into teacher(tid,tname,tcid) values(3,'tw',3);

	 insert into teachercard(tcid,tcdes) values(1,'tzdesc');
	 insert into teachercard(tcid,tcdes) values(2,'twdesc');
	 insert into teachercard(tcid,tcdes) values(3,'tldesc');
	### explain id
		执行计划的执行过程：
			from->join->on->where->group by ->having->select->distinct->order by->limit
		 1.查询课程为2或教师表为3的所有信息
		 explain select * from course c, teacher t, teachercard tc where c.tid = t.tid and t.tcid = tc.tcid and (c.cid = 2 or tc.tcid = 3);
		 explain select * from course c, teacher t, teachercard tc where c.tid = t.tid and t.tcid = tc.tcid and (c.cid = 2 or tc.tcid = 3);
			+----+-------------+-------+------------+------+---------------+------+---------+------+------+----------+----------------------------------------------------+
			| id | select_type | table | partitions | type | possible_keys | key  | key_len | ref  | rows | filtered | Extra                                              |
			+----+-------------+-------+------------+------+---------------+------+---------+------+------+----------+----------------------------------------------------+
			|  1 | SIMPLE      | c     | NULL       | ALL  | NULL          | NULL | NULL    | NULL |    3 |   100.00 | NULL                                               |
			|  1 | SIMPLE      | t     | NULL       | ALL  | NULL          | NULL | NULL    | NULL |    3 |    33.33 | Using where; Using join buffer (Block Nested Loop) |
			|  1 | SIMPLE      | tc    | NULL       | ALL  | NULL          | NULL | NULL    | NULL |    3 |    33.33 | Using where; Using join buffer (Block Nested Loop) |
			+----+-------------+-------+------------+------+---------------+------+---------+------+------+----------+----------------------------------------------------+
			3 rows in set, 1 warning (0.00 sec)
		 id值相同： 表中数据量少的优先查询，从上往下执行
		 2.查询教SQL课程的老师的描述
		 select tc.tcdes from teachercard tc, course c, teacher t where c.tid = t.tid and t.tcid = tc.tcid and c.cname = 'sql'
		 多表查询转化为子查询
		 select tc.tcdes 
		 from teachercard tc
		 where tc.tcid = (select t.tcid
		 				  from teacher t
		 				  where t.tid = (select c.tid
		 				  				 from course c
		 				  				 where c.cname = 'sql'
		 				  				)
		 				  )
		 explain select tc.tcdes   from teachercard tc  where tc.tcid = (select t.tcid    from teacher t    where t.tid = (select c.tid     from course c     where c.cname = 'sql'    )    );
			+----+-------------+-------+------------+------+---------------+------+---------+------+------+----------+-------------+
			| id | select_type | table | partitions | type | possible_keys | key  | key_len | ref  | rows | filtered | Extra       |
			+----+-------------+-------+------------+------+---------------+------+---------+------+------+----------+-------------+
			|  1 | PRIMARY     | tc    | NULL       | ALL  | NULL          | NULL | NULL    | NULL |    3 |    33.33 | Using where |
			|  2 | SUBQUERY    | t     | NULL       | ALL  | NULL          | NULL | NULL    | NULL |    3 |    33.33 | Using where |
			|  3 | SUBQUERY    | c     | NULL       | ALL  | NULL          | NULL | NULL    | NULL |    3 |    33.33 | Using where |
			+----+-------------+-------+------------+------+---------------+------+---------+------+------+----------+-------------+
			3 rows in set, 1 warning (0.00 sec)
		 在嵌套子查询，id值越大优先查询
	### explain id
		 select_type:
		 	PRIMARY:包含子查询SQL中的主查询（最外层）
		 	SUBQUERY:包含子查询SQL中的子查询（非最外层）
		 	SIMPEL:简单查询（不包含子查询，union）
		 	derived：衍生查询（使用到了临时表）
		 		a.在from子查询中只有一张表

		 		b.在from子查询中，如果有table1 union table2 则table 就是drived，table2就是 union
	### explain type:索引类型，类型(左到右性能逐渐变差) 要对type进行优化前提，有索引
		system>const>eq_ref>ref>range>index>all
		其中system.const理想情况，实际达到 ref>range

		system:只有一条数据的系统表；或衍生表只有一条数据主查询
		const:仅仅能查到一条数据的SQL，用于Primary Key 或unique （类型和索引类型有关）
			alter table 'tablename' add constraint tid_pk primary key(tid);
			explain select tid from test01 where tid = 1;
		eq_ref:唯一性索引：对于每一个索引键的查询，返回匹配唯一行数据(有且只有一个，不能多，不能为0) (查询结果和表中条数一致)
			select .. from..where id=... 常见于唯一索引，和主键索引
			查看是否含有主键信息： show create table teacher
		ref:非唯一性索引，对于每一个索引建查询，返回0个或多个(主键列含相同值)
		range:检索指定范围的行，where后面是一个范围查询（between，in，>< >=）in可能会失效 转为all
			alter table teacher add index tid_index (tid);
		index: 查询全部索引中数据
			explain select tid from teacher;
		all:查询全部表中数据
	### explain possible_keys:可能用到的索引，是一种预测
		如果possible_keys和key没有索引
	### explain key：实际使用的索引	

	### explain key_len:索引长度 作用：判断复合索引是否完全被使用
		在utf-8中：1个字符占3个字节（3）
					如果索引字段可以为null，会用一个字节表示（+1）
					如果是varchar： 2（用2个字节标识可变长度）（+2）
					int 一个字节
		gbk:一个字符两个字节
		Latin：一个字符1个字节
	### explain ref:注意与type中ref区别
		作用：指明当前表所参照的字段
			select .. where a.c = b.x;(其中b.x可以是常量，const)
	### explain rows:被索引优化查询的数据个数
	### explain Extra(Mysql query Optimize)
			（1）using filesort: 性能消耗大，需要额外一次排序（查找）标识当前这个数据需要先做一次查询
			（2）：using temporary:性能消耗较大，用到了临时表 一般出现在group by，已经有表了不用，必须再来一张临时表
				解析过程：from..on..join..where..group by .. having..select distinct..order by..limit..
			（3）：using index: 性能提升索引覆盖 原因：不读取源文件，只从索引文件中获取，不需要回表查询
			（4）：using where: （需要回表查询），select name,age from ... where name-.. age为索引 此时会显示using where
			（5）：using join buffer:（表示当前SQL引擎使用了连接缓存）
		小结：
			单表中添加复合索引	：最佳最前缀原则，逐步优化，in索引可能会失效，复合索引，部分字段using index,部分using where
			多表优化：
				1.小表驱动大表。
				2.给使用频繁的字段添加索引，（左外连接，给左表字段添加索引，右外连接，给右表添加索引）（参考编程中的嵌套循环，外层数据量小于内层）
			单索引:
				如果排序和查找不是同一个字段会出现using filesort
						避免：where哪些字段就order by哪些字段
			复合索引:
				1.不能跨列（最佳左前缀）
					 避免：where，order by按照复合索引的顺序使用不能跨列
				2.尽量使用全索引匹配
				3.不要在索引上进行任何操作(计算，函数，类型转换(隐式转换，显式转换))，否则索引失效
				4.不能使用不等于(!=,<>)或is NULL,IS NOT NULL 否则自身以及右侧索引全部失效。
					4.1一种概率事件发生，至于是否使用了我们的优化，需要通过explain推测
						explain select * from book where authorid != 1 and typeid = 2;
						explain select * from book where authorid != 1 and typeid != 2;
					4.2.明显概率问题，服务层中有SQL优化器，可能会影响我们的优化
						explain select * from book where authorid < 1 and typeid = 2;
						explain select * from book where authorid < 4 and typeid = 2;
					4.3.索引优化是大部分情况适用的结论，一般而言，范围查询 <>,<, in,之后的索引全部失效
					4.4.可以使用索引覆盖进行补救
				5.like尽量以常量开头，不要以%号开头，否则索引失效(如果必须使用like，进行index buffer)
				6.尽量不要使用or，否则索引全失效。
				 	explain select * from teacher t where t.tname = '' or tcid > 1; 左侧tname也会失效
			GROUP BY 与临时表的关系 : 
				　　1. 如果GROUP BY 的列没有索引,产生临时表. 
				　　2. 如果GROUP BY时,SELECT的列不止GROUP BY列一个,并且GROUP BY的列不是主键 ,产生临时表. 
				　　3. 如果GROUP BY的列有索引,ORDER BY的列没索引.产生临时表. 
				　　4. 如果GROUP BY的列和ORDER BY的列不一样,即使都有索引也会产生临时表. 
				　　5. 如果GROUP BY或ORDER BY的列不是来自JOIN语句第一个表.会产生临时表. 
				　　6. 如果DISTINCT 和 ORDER BY的列没有索引,产生临时表.
			order by:
				using filesort:两种算法 双路排序，单路排序（根据IO次数）
				mysql 4.1 前，默认双路，之后，默认单路排序
					单路排序需要占用更大的buffer，如果数据量过大可通过调节buffer的大小： set max_length_for_sort_data = 1024；
					如果max_length_for_sort_data值太小，MySQL会自动 从单路排序转换为双路排序
				策略：
					1.选择使用单路，双路，调整buffer的容量大小。
					2.避免select * (内部需要做转换，*，转换为对应的表中字段)
					3.复核索引遵循最佳左前缀原则
					4.保证排序字段的一致性（全部升序/降序）
		### 慢查询分析：
			慢查询日志默认关闭：show variables like %slow_query_log%;
				临时开启：
					set global slow_query_log = 1;
					exit
					server mysql restart
				永久开启：
					追加配置 vi /etc/my.cnf 
					mysqld
					slow_query_log=1
					slow_query_log_file=/var/lib/mysql/localhsot-slow.log
			慢查询阈值：
				show variables like '%long_query_time%'
				临时设置阈值：
					set global long_query_time = 5; 重新登录生效
				永久设置阈值：
					vi /etc/my.cnf 
					mysqld
					long_query_time=3
				select sleep(4)
				查询超过阈值的SQL，show global status like '%slow_quries%';
				慢查询的sql被记录在日志中，因此可以通过日志，慢SQL
					cat /var/lib/mysql/localhsot-slow.log
				通过mysqldumpslow工具查慢SQL，
					mysqldumpslow --help
					s:排序方式
					l:锁定时间
					g:正则表达式
					mysqldumpslow -s r -t 3 /var/lib/mysql/localhsot-slow.log
		### 海量数据分析profiles(存储函数/存储过程)  
		 	随机字符串
		 		randstring(6)
		 		delimiter $ --MySQL数据库中; 表示结束
		 		create function randstring(n int) returns varchar(255)
		 		begin
		 			declare all_str varchar(100) default 'abcdefghijklmnopqrstyuvwxyzABCDEFGHIJKLMNOPQRSTYUVWXYZ'
		 			declare return_str varchar(255) default '';
		 			declare i int default 0;
		 			while
		 			do

		 			end while;
		 		end $
		 	执行时报错：this function has none of deterministic no sql,or reads sql data in it declaration ....
		 		存储过程和开启的慢查询日志冲突了
		 		临时解决
		 			show variables like '%log_bin_trust_function_creators%'
		 			set log_bin_trust_function_creators=1;
		 		永久解决：
		 			配置文件中添加log_bin_trust_function_creators=1
		 	create procedure insert_emp(in eid_start int(10),in data_times int(10))
		 	begin
		 		delcare i int default 0;
		 		set autocommit = 0;
		 		repeat
		 			insert into emp values()
		 			set i=i=1；
		 			until i =data_times
		 		end repeat;
		 		commit;


		 	end $

		 	改变结束符 delimiter $ 
		 	调用存储过程：call insert_emp();
		 分析海量数据：Profiles
		 	show profiles:默认关闭
		 	show variables like '%profiling%'
		 	set profiling = on;
###SQL优化口诀：
	全职匹配我最爱，最左前缀要遵守；
	带头大哥不能死，中间兄弟不能断；
	索引列上少计算，范围之后全失效；
	Like百分写最右，覆盖索引不写星；
	不等空值还有or，索引失效要少用；