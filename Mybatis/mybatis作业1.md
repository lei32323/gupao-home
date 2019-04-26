1、resultType（属性）和resultMap（标签引用）的区别？

​		resultType 是来设置 返回的集合映射成具体的对象

​        resultMap 是用来设置 返回的集合映射成map类型的变量	

2、collection和association的区别？

​       在查询中设置属性 collection 是用来注入一个集合的   一对多

​       在查询中设置属性 association 是用来注入 单个对象的  一对一

3、Statement和PreparedStatement的区别？

​     Statement 是 sql拼接，有sql注入的危险。并且每次都要编译。性能消耗多

​	PreparedStatement 是sql 中的参数映射，防止sql注入 ， 并且编译一次，方便后面多次使用

