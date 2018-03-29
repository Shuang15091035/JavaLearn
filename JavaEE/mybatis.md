# JavaLearn (Mybatis)
<!-- select 表示一个MappedStatement对象 -->
<!-- parameterMap表示输入的参数类型 -->
<!-- #{} 表示一个？占位符 #{id} 表示输入的参数名称，如果变量是简单类型，那么#{}可以是任意值 -->
<!-- ${}表示sql的连接符,'${}%' -->
<!-- #{value} 表示输入的参数名称，如果变量是简单类型，那么#{}必须是value ,存在SQL注入的风险，必须使用，动态传入排序名称；排序时 ${}原样输出，#{}会做解释-->
<!-- resultMap表示输出结果的映射的Java类型 -->
<select id="queryListTest" resultMap="test" >
SELECT
tt.u_id AS "uId",
tt.u_name AS "uName"
FROM t_test tt
where 1 = 1
</select>
<!-- selectKey 查询主键，在标签内需要输入查询主键的SQL 一下为自增主键，主键返回-->
<!-- order:指定查询主键的SQL和insert语句的执行顺序，相当于insert语句来说-->
<!-- LAST_INSERT_ID 该函数是mysql的函数，获取自增主键的id，配合insert使用-->
<!--<insert id="insertTest">-->
<!--<selectKey keyProperty="id" resultType="string" order="AFTER" >-->
<!--SELECT LAST_INSERT_ID()-->
<!--</selectKey>-->
<!--</insert>-->


