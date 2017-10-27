接口重写问题
分红管理：分红方式修改，修改按钮对应接口
持仓基金：交易查询->修改分红方式






SQLite
SQLite 基础知识学习
SQL中的单行注释: -- 多行注释：/*  */
SQLite运算符:
算术运算符(+,-,*,/,%)，比较运算符(>,<,<>,!<,!>,=,!=,<=,>=,)，逻辑运算符(and,or,isnull,exists,uinique,||,like,glob,in,not in,between,is, is not,)，位运算符(&,|,~,<<,>>)
SQLite表达式:是一个或多个值，运算符和计算值得SQL函数的组合，

常用的SQL子句：create,drop,insert into, alter,delete,update,select,order by, group by,having, distinct, limit,

/*
CREATE TABLE 语句用于在任何给定的数据库创建一个新表
*/
create table Accout(
id integer primary key autoincrement,
name text,
habit varchar(50),
age integer
);

/*
insert：向数据库某个表添加新的数据行
INSERT INTO TABLE_NAME [(column1, column2, column3,...columnN)]
VALUES (value1, value2, value3,...valueN);
*/
insert into Account(name,habit,age) values('shuang','ball',20);
insert into Account(name,habit,age) values('fang','ball',20);

/*
alter：除了重命名表和在已有的表中添加列，ALTER TABLE 命令不支持其他操作
*/
alter table Account add height integer not null;

alter table Account rename to account;

/*
update: 查询用于修改表中已有的记录
*/
update account set aget = 20 where habit = 'ball', age = 20 ;

/*
LIKE %:代表零个，一个，或多个字符
*/

-- SELECT * from account WHERE username LIKE 's%';

/*
GLOB: *:代表零个一个多个，？代表一个单一的数字或者字符
*/


/*
LIMIT: 用于限制 SELECT 语句返回的数据数量
LIMIT 和 offset从第limit位开始，提取offset个
*/

-- SELECT * from account  LIMIT 2;
--
-- SELECT * from account LIMIT 2 OFFSET 1;

/*
ORDER BY :用来基于一个或多个列按升序或降序顺序排列数据
*/


-- SELECT * from account ORDER BY money, age ASC;


/*
GROUP BY GROUP BY 子句用于与 SELECT 语句一起使用，来对相同的数据进行分组

SELECT COLUMN
FROM table_name
WHERE [conditions]
GROUP BY COLUMN,COLUMN2...
ORDER BY COLUMN1,COLUMN2....
*/

-- SELECT username, sum(money) AS result from account GROUP BY username ORDER BY username ASC;

/*
HAVING:许指定条件来过滤将出现在最终结果中的分组结果
SELECT COLUMN
FROM table_name
WHERE [condations]
GROUP BY COLUMN,COLUMN2...
HAVING [conditions]
ORDER BY COLUMN1,COLUMN2....
*/


-- SELECT * from account GROUP BY username HAVING count(username) < 2;

/*
DISTINCT:来消除所有重复的记录
*/

-- SELECT DISTINCT username  from account;

-- CREATE TABLE if NOT EXISTS subject (
--     id integer PRIMARY KEY AUTOINCREMENT,
--     math integer,
--     english integer,
--     chinese integer
-- );

-- insert into subject(math,english,chinese) values(100,80,90);
-- insert into subject(math,english,chinese) values(78,90,76);
-- insert into subject(math,english,chinese) values(121,80,90);
-- insert into subject(math,english,chinese) values(78,80,40);
--
--
-- SELECT * from subject;
--
-- DELETE from subject WHERE id > 4;

-- ALTER TABLE account ADD subj text;

-- CREATE TABLE DEPARTMENT(
--    ID INT PRIMARY KEY      NOT NULL,
--    DEPT           CHAR(50) NOT NULL,
--    EMP_ID         INT      NOT NULL
-- );
-- INSERT INTO DEPARTMENT (ID, DEPT, EMP_ID)
-- VALUES (1, 'IT Billing', 1 );
--
-- INSERT INTO DEPARTMENT (ID, DEPT, EMP_ID)
-- VALUES (2, 'Engineering', 2 );
--
-- INSERT INTO DEPARTMENT (ID, DEPT, EMP_ID)
-- VALUES (3, 'Finance', 7 );

/*
JOIN: CROSS JOIN: 把第一个表的第一行和第二个标的每一行进行匹配， x y 列，结果为x+y列
*/
-- DROP TABLE subject;

-- SELECT EMP_ID, username, DEPT FROM account CROSS JOIN DEPARTMENT;

SELECT EMP_ID, username, DEPT FROM account INNER JOIN DEPARTMENT ON


/**
INNER JOIN: 最常见的连接类型，是默认的连接类型 INNER关键字是可选的。
SELECT .. from table1 [INNER] JOIN table2 ON condition_expression ..
为了避免冗余，使用 USING 表达式声明内连接条件，
SELECT ... frome table1 JOIN table2 USING (COLUMN, ...) ...
NATURAL JOIN: 类似于join...USING,会自动测试存在两个表的每一列的值之间的相等值
SELECT ... from TABLE1 NATURAL JOIN table2 ...
*/
-- SELECT EMP_ID, username, DEPT FROM account INNER JOIN DEPARTMENT ON account.subj = DEPARTMENT.EMP_ID;


/*
OUTER JOIN: 内连接的扩展，三种类型的外链接：left,right,full,SQLite只支持左连接
SELECT ... frome table1 LEFT OUTER JOIN table2 USING (column1, ....);
最初的结果表以相同的方式进行计算。一旦主连接计算完成，外连接（OUTER JOIN）将从一个或两个表中任何未连接的行合并进来，
外连接的列使用 NULL 值，将它们附加到结果表中 (和内连接的结果对比一下，就可以看出来差别)
*/

-- SELECT EMP_ID, username, DEPT FROM account LEFT OUTER JOIN DEPARTMENT
--         ON account.subj = DEPARTMENT.EMP_ID;

/*
UNION:用于合并两个或多个select语句的结果，不返回任何重复的行。
UNION ALL ,结合连个select语句的结果，包括重复行
*/
-- SELECT username, age, EMP_ID FROM account JOIN DEPARTMENT ON account.subj = DEPARTMENT.EMP_ID
-- UNION
-- SELECT username, age, EMP_ID from account LEFT OUTER JOIN DEPARTMENT ON account.subj = DEPARTMENT.EMP_ID;
--
-- SELECT username, age, EMP_ID FROM account JOIN DEPARTMENT ON account.subj = DEPARTMENT.EMP_ID
-- UNION ALL
-- SELECT username, age, EMP_ID from account LEFT OUTER JOIN DEPARTMENT ON account.subj = DEPARTMENT.EMP_ID;
-- 

