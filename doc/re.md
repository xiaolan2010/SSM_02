Q.1
<context:property-placeholder ignore-unresolvable="true" />  
ignore-resource-not-found：如果属性文件找不到，是否忽略，默认false，即不忽略，找不到文件并不会抛出异常。 
ignore-unresolvable：是否忽略解析不到的属性，如果不忽略，找不到将抛出异常。但它设置为true的主要原因是：

理解：ignore-unresolvable为true时，配置文件${}找不到对应占位符的值 不会报错，会直接赋值'${}'；如果设为false，会直接报错。 设置它为true的主要原因，是一个xml中有多个配置文件时的情况：

Q.2 配置数据源 （两种）
com.mchange.v2.c3p0.ComboPooledDataSource  与  org.apache.commons.dbcp.BasicDataSource
Spring在第三方依赖包中包含了两个数据源的实现类包，其一是Apache的DBCP，其二是 C3P0。可以在Spring配置文件中利用这两者中任何一个配置数据源。

-----------------------
mybatis和Hibernate的本质区别与应用场景
hibernate：是一个标准ORM框架（对象关系映射），入门门槛较高的，不需要程序写sql，sql语句自动生成了，对sql语句进行优化、修改比较困难的。

应用场景：

     适用与需求变化不多的中小型项目，比如：后台管理系统，erp、orm、oa。。

mybatis：专注是sql本身，需要程序员自己编写sql语句，sql修改、优化比较方便。mybatis是一个不完全 的ORM框架，虽然程序员自己写sql，mybatis 也可以实现映射（输入映射、输出映射）。

应用场景：

     适用与需求变化较多的项目，比如：互联网项目。
     
=========================
mybatis 配置
优:https://blog.csdn.net/zht741322694/article/details/78743220
https://www.cnblogs.com/junzi2099/p/8315881.html#_label0
http://www.cnblogs.com/selene/p/4604605.html
https://blog.csdn.net/qq_36381855/article/details/78309869

