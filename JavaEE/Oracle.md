###  PLSQL 编译卡死的解决方案
select va.sid,va.OBJECT from v$access va where object like 'PK_HISTORYNAV_MANUAL'   

SELECT SID,SERIAL#,PADDR FROM V$SESSION WHERE SID= 25

alter system kill session '25,17423' immediate; 

Oracle支持的数据隔离级别：read commited(读已提交)，read serialization(串行化)
数据库：是一个物理概念(存储在硬盘上)，数据库实例：数据库文件读取到内存中 关系至少是一对一，可为一对多
###  -------------SQL命令-------------
数据库的集群：RAC(Real application cluster)
1：负载均衡（load balance）
2：fail over （失败迁移，提高可靠性）
数据库逻辑概念和物理概念：
方案，表空间(逻辑)和数据文件(物理）（.dbf)
段区Oracle数据块(逻辑）和OS 块(物理）

OracleServiceORCL(本机需要手动启动，启动服务才算启动)占用系统资源较大

Scott(薪资，员工，部分)/HR(职位，员工，工作历史，国家)

SQL语句：
spool ：spool d:基本查询.txt
清屏：host clear/cls
当前用户：show user
当前用户下的表：select * from tab;
表结构：desc emp(表)
设置行宽：show linesize/set linesize a8（8表示字符）
设置列宽：col ename for a8/col sal for 9999 （9数字：4位）
SQL优化：尽量使用列名，
SQL出错：C命令 change
SQL: 2 // ‘2’表示错误的行号
SQL> c /form/from
SQL>/  // ‘/’表示运行上面的修改
SQL中的null值：1.包含null的表达式都为null
2.null 永远!=null
nvl(a,b) a为空返回b，否则返回a/nvl2
select * from where emp comm=null(不成立)/ where em comm is null
--列的表名 ed (edit)
select empno as "员工号", ename "姓名”, sal 月薪， sal*12，comn，sal*12+nal(comn,0)
from emp
以上别名的区别：empno as "员工号", ename "姓名”无区别，
区别： ename "姓名”, sal 月薪 月薪不能有特殊符号，数字，空格。
distinct：去掉重复，作用于后面所有列
concat: SQL99（ANSI） select concat('hello', 'world') from dual(和操作无关);
dual 表：伪表 ，伪列
select ename ||'的薪水‘||sal 信息 from emp
日期和字符只能在单引号中，别名双引号
sql语句(不能缩写)和sqlplus（可以缩写）区别：
lsnrctl status, start ,stop （listenercontrol）
port:5560服务中isql http://localhost:5560/isqlplus/(10g可用,废弃不安全) 抓包工具（httpwatch)
schedulerORCL（任务调度器服务）
emctrl status start stop(企业管理界面）
http://localhost:1158/em：(Enterprise Manager 10g) 11g https:安全传输协议
where:
group by:

spool off
spool：录屏命令，录制为文本文件

### ---------函数--------- (单行函数，多行函数)
单行函数：通用函数，字符函数，数值函数，日期，转换，条件表达式
大小写控制：lower(’hello Word‘),upper,initcap首字母大写
字符控制函数：concat， substr(a,b，c（o）)从a中，b开始取值，取C位 length/lengthb， instr（a，b）a中查找b返回位置， lpad左填充，rpad右填充， trim， replace
转换函数：隐形类型转换：(显示类型转换)to_char,to_number, to_date
to_char常用的集中格式: 9数字，0，$美元符， L本地货币符号，.小数点 ，千位符
select to_char(sal,'L9,999.99') from emp;
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss"今天是"day)  from dual;
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual （转化为字符）
select (sysdate-1) 昨天, sysdate 今天, sysdate+1 明天 from dual;
日期函数: months_between(sysdate,hiredate), add_months(sysdate,56)56个月以后, last_day(sysdate) 当前月的最后一天；round(sysdate,"month"),trunc(sysdate,"year")
next_day(sysdate,”星期一“) from dual,
/*
next_day的应用：每个周一自动备份数据
1.分布式数据库
2.快照，触发器*/
通用函数： nvl(expre1,expre2),
nvl2(a,b,c) 当a 为空 b， a不为空，c
nullif(a,b) 当a=b的时候，返回null，否则返回a,
coalesce(a,b,c....)从左到右找到第一个不为空的值；
case expre when comparison_expr1 then return_expr1
                    when comparison_expr1 then return_expr1
                    when comparison_expr1 then return_expr1
                    else result;
 end 涨后；
 select ename, job, sal 涨前，
             case job when 'Persident' then sal+1000
                             when 'manager' then sal+800
                             else sal+400
             end 涨后
 from emp;
 表示范围：
 case when sal<3000 then ***
          when sal>3000 and sal <6000 then ***
          else ***
  end
 select ename, job, sal 涨前，
             decode(job, 'persident', sal+1000,
                                 'manager', sal+800,
                                                     sal+400)涨后
 from emp;
                 

多行函数：max,min,avg

### -------------过滤和排序数据-------------
---字符大小写敏感，日期格式敏感
select * from emp where ename='KING';
select * from emp where hiredate='17-11月-81';

### -------------组函数-------------
常用组函数：AVG，MAX，MIN，SUM， COUNT(*)
平均奖金有空值 count(*) count(comm)  *表示有列
组函数（多行函数）自动滤空，通过nvl可以不过滤
count 返回expr非空且不重复的记录总数
select count(distinct deptno)
from emp;
--group by
未包含在组函数中的都需要写到group by 后面；
select deptno ,avg(sal)
from emp
group by deptno;
-- having 过滤分组
select deptno ,avg(sal)
from emp
group by deptno;
having avg(sal) > 2000
where 和 having 的区别 ：where 不能使用多行函数
SQL优化：尽量使用where ，如何having和where可以通用
--group by 增强(sqlplus 报表)

===-
select deptno, job sum(sal) from emp group by rollup(deptno,job);
break on deptno skip 2

break on nulll 取消报表格式

select 语句是可以相加的，通过集合运算

------ 多表查询——————
原始数据 表TQTest
rowkey
2
3;5
查询数据
rowkey
2
3
5
SQL实现：
with t as (select * from TQTest)
select regexp_substr(TRD_CODE_SECU, '[^;]+', 1, level) temp, t.*
from t
connect by level <= regexp_count(TRD_CODE_SECU, ';') + 1
and TRD_CODE_SECU = prior TRD_CODE_SECU
and prior dbms_random.value > 0);

