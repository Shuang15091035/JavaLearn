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

/**
* 公募使用钰金宝购买或赎回到钰金宝
* @param fundCustNo: 客户编号
* @param applicationamount 申请金额
* @param buyflag 强制购买标志，'0' 非强制购买，'1' 强 制购买
* @param fundcode 基金代码
* @param targetfundcode 目标基金代码
* @param tpasswd 交易密码 base64加密
* @param transactionaccountid 交易账号
* @param vastredeemflag 巨额赎回标志，0-放弃超额部分 1-继续 赎回
* @return Map： 结果
*/
@RequestMapping(value = "/transformationFundYJB")
public Map transformationFundYJB (String applicationamount, String buyflag, String fundCustNo, String fundcode,
String targetfundcode, String tpasswd, String transactionaccountid, String vastredeemflag){
String targetsharetype = "";
String tano = "";
String branchcode = "353";
Map<String, Object> map = new HashMap<String, Object>();

tpasswd = Base64Util.getFromBase64(tpasswd);//页面用了base64加密，此处base64解密为明文
tpasswd = DESEncrypt.strEnc(tpasswd);//金证的des加密

//获取tano
Map<String,Object> tabodyMap = new HashMap<String,Object>();
tabodyMap = InterfaceUtil.getFundTa(fundcode);
if(!tabodyMap.isEmpty()){
tano = tabodyMap.get("tano").toString();
}

//基金转换接口
map.put("MethodType", Const.JR_DIRECT);
map.put("MethodName", "fundconvert");
//以下是私有参数
map.put("applicationamount", applicationamount);//
map.put("branchcode", branchcode);//
map.put("buyflag", buyflag);//'0' 非强制购买，'1' 强制购买
map.put("custno", fundCustNo);//客户号
map.put("fundcode",fundcode);//
map.put("tano", tano);//TA代码
map.put("targetfundcode", targetfundcode);//
map.put("targetsharetype", targetsharetype);//
map.put("tpasswd", tpasswd);//
map.put("transactionaccountid",transactionaccountid );//交易账户
map.put("transactorcertno", "");//
map.put("transactorcerttype", "");//
map.put("transactorname", "");//
map.put("vastredeemflag", vastredeemflag);//巨额赎回标志0-放弃超额部分1-继续赎回
JSONObject GrpBody = InterfaceUtil.connetOpenapi(map);
String code=(String)GrpBody.get("ReturnCode");
String msg=(String)GrpBody.get("ReturnMsg");
Map<String, Object> result = new HashMap<>();
result.put("code",code);
result.put("msg",msg);
result.put("data","");
if("0000".equals(code)||"0000"==code) {
Map<String, Object> obj = new HashMap<String, Object>();
String data = GrpBody.get("data").toString();//返回数据
JSONArray array = JSONArray.fromObject(data);
JSONArray arr = JSONArray.fromObject(array.get(0));
if (arr != null && arr.size() > 0) {
obj = StringUtil.jsonToMap(arr.get(0));//取返回结果的第一结果集
obj.put("confirmeddate", obj.get("transactiondate").toString());
}
result.put("data", obj);
}
return result;
}

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


SQL注入预防：
通过正则表达式(不常用)
参数化存储过程进行数据查询
参数化SQL语句
添加新框架

如何防止SQL Injection。
总的来说有以下几点：
1.永远不要信任用户的输入，要对用户的输入进行校验，可以通过正则表达式，或限制长度，对单引号和双"-"进行转换等。
2.永远不要使用动态拼装SQL，可以使用参数化的SQL或者直接使用存储过程进行数据查询存取。
3.永远不要使用管理员权限的数据库连接，为每个应用使用单独的权限有限的数据库连接。
4.不要把机密信息明文存放，请加密或者hash掉密码和敏感的信息。
5.应用的异常信息应该给出尽可能少的提示，最好使用自定义的错误信息对原始错误信息进行包装，把异常信息存放在独立的表中。
LINQtoSQL
