# mybatis 基础搭建

## 引入 mybatis 包
```xml
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.5</version>
</dependency>
```

## 引入 mysql 基础包 
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.17</version>
</dependency>
```
## 引入 log4j
```xml
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

## 编写 mybatis-config
- 配置文件顶层结构图
```
configuration（配置）
->properties（属性）
->settings（设置）
->typeAliases（类型别名）
->typeHandlers（类型处理器）
->objectFactory（对象工厂）
->plugins（插件）
->environments（环境配置）
---->environment（环境变量）
-------->transactionManager（事务管理器）
-------->dataSource（数据源）
->databaseIdProvider（数据库厂商标识）
->mappers（映射器）
```

- xml 方式，需要在 mybatis-config 添加
```xml
    <mappers>
        <!--编写 sql 语句的mapper xml-->
        <mapper resource="mapper/BlogMapper.xml"/>
    </mappers>
```

- 注解方式需要在 mybatis-config 添加
```xml
    <mappers>
        <!--编写 sql 语句的mapper class-->
        <mapper class="com.zbcn.mybatis.mapper.anno.BlogMapperAnno"/>
    </mappers>
```

### 属性（properties）
这些属性可以在外部进行配置，并可以进行动态替换。如 sql 链接信息
```xml
<properties resource="sql.properties"></properties>
```
### 设置（settings）
这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为。
```xml
<settings>
  <setting name="cacheEnabled" value="true"/>
  <setting name="lazyLoadingEnabled" value="true"/>
  <setting name="multipleResultSetsEnabled" value="true"/>
  <setting name="useColumnLabel" value="true"/>
  <setting name="useGeneratedKeys" value="false"/>
  <setting name="autoMappingBehavior" value="PARTIAL"/>
  <setting name="autoMappingUnknownColumnBehavior" value="WARNING"/>
  <setting name="defaultExecutorType" value="SIMPLE"/>
  <setting name="defaultStatementTimeout" value="25"/>
  <setting name="defaultFetchSize" value="100"/>
  <setting name="safeRowBoundsEnabled" value="false"/>
  <setting name="mapUnderscoreToCamelCase" value="false"/>
  <setting name="localCacheScope" value="SESSION"/>
  <setting name="jdbcTypeForNull" value="OTHER"/>
  <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString"/>
</settings>
```
### 类型别名（typeAliases）
类型别名可为 Java 类型设置一个缩写名字。 它仅用于 XML 配置，意在降低冗余的全限定类名书写。
```xml
<typeAliases>
    <!-- 别名方式，自动扫描，将JAVA类的类名作为类的类别名 -->
    <package name="com.zbcn.pojo"/>
</typeAliases>
```
这样在 domain 包下的 Java Bean，在没有注解的情况下，会使用 Bean 的首字母小写的非限定类名来作为它的别名（其实不区分大小写）
- 说明：当为package时，要和 @Alias("author") 配合使用，否则不识别
## 编写log4j.properties

## 编写 jdbc链接配置信息 sql.properties

# 类型处理器（typeHandlers）
## 自定义 `XXXTypeHandler extends BaseTypeHandler<T>` 并且添加注解：`@MappedJdbcTypes(JdbcType.XXX)`
## 添加config
```xml
<typeHandlers>
  <typeHandler handler="xxx.xxx.XXXTypeHandler"/>
</typeHandlers>
```

# 枚举映射处理类型
若想映射枚举类型 Enum，则需要从 EnumTypeHandler 或者 EnumOrdinalTypeHandler 中选择一个来使用

# 对象工厂 ObjectFactory
**每次 MyBatis 创建结果对象的新实例时，它都会使用一个对象工厂（ObjectFactory）实例来完成实例化工作**。默认的对象工厂需要做的仅仅是实例化目标类，要么通过默认无参构造方法，要么通过存在的参数映射来调用带有参数的构造方法。 如果想覆盖对象工厂的默认行为，可以通过创建自己的对象工厂来实现。
 Mybatis 查询的数据都封装在实体对象中，实体对象的创建是由吧ObjectFactory 来完成的。
即先用 ObjectFactory 创建空对象，然后讲数据库中查到的数据填充到 ObjectFactory 创建的对象中。
实例详见：`com.zbcn.mybatis.factory.CustomObjectFactory` , 给实体对象可以赋数据库中没有的值。

# mapper 映射配置
问题: `mybatis binding.BindingException: Invalid bound statement (not found)`
复现:当mappers下的mapper 配置为: 
```xml
<mappers>
    <!-- class 级别的指定 -->
    <mapper class="com.zbcn.mybatis.mapper.xml.BlogMapper"/>
</mappers>

<mappers>
    <!--以包的方式指定-->
    <package name="com.zbcn.mybatis.mapper.xml"/>
</mappers>
```
会出现以上问题.
**原因:** ​ xml配置文件必须和这个类在同一级目录，且与Mapper类同名

## 以xml 资源的方式指定
使用这个方案，可以单独指定Mapper的位置
```xml
<mappers>
    <!--编写 sql 语句的mapper xml-->
    <mapper resource="mapper/BlogMapper.xml"/>
</mappers>
```

## class 级别的指定
** 1. xml配置文件必须和这个类在同一级目录，且与Mapper类同名**
** 2. mapper 以注解的方式集成.**
```xml
<mappers>
    <!--class 级别的指定-->
    <mapper class="com.zbcn.mybatis.mapper.anno.BlogMapperAnno"/>
    <!--class 级别的指定-->
    <mapper class="com.zbcn.mybatis.mapper.pkg.BlogMapper2"/>
</mappers>
```
## 以 package 的方式指定
** 1. xml配置文件必须和这个类在同一级目录，且与Mapper类同名**
** 2. mapper 以注解的方式集成.**
```xml
<mappers>
    <!--package 级别的指定-->
   <package name="com.zbcn.mybatis.mapper.anno"/>
    <!--package 级别的指定-->
   <package name="com.zbcn.mybatis.mapper.pkg."/>
</mappers>
```

**注意:**
class 和package 的方式指定,需要让class文件同级可以加载 .xml 文件,所以需要在pom.xml 中配置如下:
```xml
<build>
    <resources> <!--资源文件夹，可配置多个-->
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.xml</include>
                <include>**/*.properties</include>
            </includes>
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
```

# 插件（plugins） -拦截器

MyBatis 允许在映射语句执行过程中的某一点进行拦截调用。默认情况下，MyBatis 允许使用插件来拦截的方法调用包括：

1. Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
2. ParameterHandler (getParameterObject, setParameters)
3. ResultSetHandler (handleResultSets, handleOutputParameters)
4.  StatementHandler (prepare, parameterize, batch, update, query)

我们可以选择在这些被拦截的方法执行前后加上某些逻辑，也可以在执行这些被拦截的方法时执行自己的逻辑而不再执行被拦截的方法。Mybatis拦截器设计的一个初衷就是为了供用户在某些时候可以实现自己的逻辑而不必去动Mybatis固有的逻辑.打个比方,对于Executor,Mybatis中有几种实现：BatchExecutor,ReuseExecutor,SimpleExecutor和CachingExecutor。
这个时候如果你觉得这几种实现对于Executor接口的query方法都不能满足我们的要求。我们可以建立一个Mybatis拦截器用于拦截Executor接口的query方法，在拦截之后实现自己的query方法逻辑，之后可以选择是否继续执行原来的query方法.
详见代码： `com.zbcn.mybatis.plugins.MyInterceptorPlugin`和 `com.zbcn.mybatis.MyBatisMapperMain`

### 覆盖配置类
除了用插件来修改 MyBatis 核心行为以外，还可以通过完全覆盖配置类来达到目的。只需继承配置类后覆盖其中的某个方法，再把它传递到 SqlSessionFactoryBuilder.build(myConfig) 方法即可。再次重申，这可能会极大影响 MyBatis 的行为，务请慎之又慎。

# 环境配置（environments）
MyBatis 可以配置成适应多种环境，这种机制有助于将 SQL 映射应用于多种数据库之中， 现实情况下有多种理由需要这么做。例如，开发、测试和生产环境需要有不同的配置；或者想在具有相同 Schema 的多个生产数据库中使用相同的 SQL 映射。还有许多类似的使用场景。

**不过要记住：尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境。**

如果我们想链接两个数据库。就需要创建两个 SqlSessionFactory 实例，每个数据库对应一个。而如果是三个数据库，就需要三个实例，依此类推。

##每个数据库对应一个 SqlSessionFactory 实例
为了指定创建哪种环境，只要将它作为可选的参数传递给 SqlSessionFactoryBuilder 即可。
```java
SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader, environment);
SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader, environment, properties);
```
- 如果忽略了环境配置
```java
SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader, properties);
```
- environments 元素定义了如何配置环境
```xml
<environments default="development">
  <environment id="development">
    <transactionManager type="JDBC">
      <property name="..." value="..."/>
    </transactionManager>
    <dataSource type="POOLED">
      <property name="driver" value="${driver}"/>
      <property name="url" value="${url}"/>
      <property name="username" value="${username}"/>
      <property name="password" value="${password}"/>
    </dataSource>
  </environment>
</environments>
```

注意一些关键点:

1. 默认使用的环境 ID（比如：default="development"）。
2. 每个 environment 元素定义的环境 ID（比如：id="development"）。
3. 事务管理器的配置（比如：type="JDBC"）。
4. 数据源的配置（比如：type="POOLED"）。

默认环境和环境 ID 顾名思义。 环境可以随意命名，但务必保证默认的环境 ID 要匹配其中一个环境 ID。

## 事务管理器（transactionManager）
在 MyBatis 中有两种类型的事务管理器（也就是 type="[JDBC|MANAGED]"）：
1. JDBC（JdbcTransactionFactory） – 这个配置直接使用了 JDBC 的提交和回滚设施，它依赖从数据源获得的连接来管理事务作用域。
2. MANAGED（ManagedTransactionFactory） – 这个配置几乎没做什么。它从不提交或回滚一个连接，而是让容器来管理事务的整个生命周期（比如 JEE 应用服务器的上下文）。
       默认情况下它会关闭连接。然而一些容器并不希望连接被关闭，因此需要将 closeConnection 属性设置为 false 来阻止默认的关闭行为
```xml
<transactionManager type="JDBC"/>
<!--
<transactionManager type="MANAGED">
    <property name="closeConnection" value="false"/>
</transactionManager>
-->
```
**提示** 如果正在使用 Spring + MyBatis，则没有必要配置事务管理器，因为 Spring 模块会使用自带的管理器来覆盖前面的配置。
          
### 自定义事物
可以用 TransactionFactory 接口实现类的全限定名或类型别名代替默认配置
1. 自定义 `com.zbcn.mybatis.transaction.CustomTransactionFactory` 实现 `TransactionFactory`
2. 自定义 `com.zbcn.mybatis.transaction.CustomTransaction`  实现 `Transaction`  

## 数据源（dataSource）
environment 的主要作用是配置数据库，在 MyBatis 中，数据库通过 PooledDataSource Factory、UnpooledDataSourceFactory 和 JndiDataSourceFactory 三个工厂类来提供，前两者对应产生 PooledDataSource、UnpooledDataSource 类对象，而 JndiDataSourceFactory 则会根据 JNDI 的信息拿到外部容器实现的数据库连接对象。
无论如何这三个工厂类，最后生成的产品都会是一个实现了 DataSource 接口的数据库连接对象。
由于存在三种数据源，所以可以按照下面的形式配置它们。
```xml
<dataSource type="UNPOOLED">
<dataSource type="POOLED">
<dataSource type="JNDI">
```
### UNPOOLED
UNPOOLED 采用非数据库池的管理方式，每次请求都会打开一个新的数据库连接，所以创建会比较慢。在一些对性能没有很高要求的场合可以使用它。

### POOLED
数据源 POOLED 利用“池”的概念将 JDBC 的 Connection 对象组织起来，它开始会有一些空置，并且已经连接好的数据库连接，所以请求时，无须再建立和验证，省去了创建新的连接实例时所必需的初始化和认证时间。它还控制最大连接数，避免过多的连接导致系统瓶颈。
更多属性用来配置 POOLED 的数据源：
| 名称                          | 说明                                                         |
| ----------------------------- | ------------------------------------------------------------ |
| poolMaximumActiveConnections  | 是在任意时间都存在的活动（也就是正在使用）连接数量，默认值为 10 |
| poolMaximumIdleConnections    | 是任意时间可能存在的空闲连接数                               |
| poolMaximumCheckoutTime       | 在被强制返回之前，池中连接被检出（checked out）的时间，默认值为 20 000 毫秒（即 20 秒） |
| poolTimeToWait                | 是一个底层设置，如果获取连接花费相当长的时间，它会给连接池打印状态日志，并重新尝试获取一个连接（避免在误配置的情况下一直失败），默认值为 20 000 毫秒（即 20 秒）。 |
| poolPingQuery                 | 为发送到数据库的侦测查询，用来检验连接是否处在正常工作秩序中，并准备接受请求。默认是“NO PING QUERY SET”，这会导致多数数据库驱动失败时带有一个恰当的错误消息。 |
| poolPingEnabled               | 为是否启用侦测查询。若开启，也必须使用一个可执行的 SQL 语句设置 poolPingQuery 属性（最好是一个非常快的 SQL），默认值为 false。 |
| poolPingConnectionsNotUsedFor | 为配置 poolPingQuery 的使用频度。这可以被设置成匹配具体的数据库连接超时时间，来避免不必要的侦测，默认值为 0（即所有连接每一时刻都被侦测——仅当 poolPingEnabled 为 true 时适用）。 |

### JNDI
数据源 JNDI 的实现是为了能在如 EJB 或应用服务器这类容器中使用，容器可以集中或在外部配置数据源，然后放置一个 JNDI 上下文的引用。这种数据源配置只需要两个属性：

#### initial_context
用来在 InitialContext 中寻找上下文（即，initialContext.lookup（initial_context））。initial_context 是个可选属性，如果忽略，那么 data_source 属性将会直接从 InitialContext 中寻找。

#### data_source
是引用数据源实例位置上下文的路径。当提供 initial_context 配置时，data_source 会在其返回的上下文中进行查找；当没有提供 initial_context 时，data_source 直接在 InitialContext 中查找。

与其他数据源配置类似，它可以通过添加前缀“env.”直接把属性传递给初始上下文（InitialContext）。比如 env.encoding=UTF8，就会在初始上下文实例化时往它的构造方法传递值为 UTF8 的 encoding 属性。

### 第三方数据源
通过实现接口 `org.apache.ibatis.datasource.DataSourceFactory` 来使用第三方数据源实现。
`org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory` 可被用作父类来构建新的数据源适配器。 如c3p0 数据源使用了UnpooledDataSourceFactory。
```java
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0DataSourceFactory extends UnpooledDataSourceFactory {

  public C3P0DataSourceFactory() {
    this.dataSource = new ComboPooledDataSource();
  }
}
```

在配置文件中为每个希望 MyBatis 调用的 setter 方法增加对应的属性,如  PostgreSQL 数据库的例子
```xml
<dataSource type="org.myproject.C3P0DataSourceFactory">
  <property name="driver" value="org.postgresql.Driver"/>
  <property name="url" value="jdbc:postgresql:mydb"/>
  <property name="username" value="postgres"/>
  <property name="password" value="root"/>
</dataSource>
```

# 数据库厂商标识（databaseIdProvider）
MyBatis 可以根据不同的数据库厂商执行不同的语句，这种多厂商的支持是基于映射语句中的 databaseId 属性。 MyBatis 会加载带有匹配当前数据库 databaseId 属性和所有不带 databaseId 属性的语句。 如果同时找到带有 databaseId 和不带 databaseId 的相同语句，则后者会被舍弃. 为支持多厂商特性，只要像下面这样在 mybatis-config.xml 文件中加入 databaseIdProvider 即可：
```xml
<databaseIdProvider type="DB_VENDOR" />
```

# 一级缓存/ 二级缓存
## 一级缓存
```xml
<!--
SESSION或者STATEMENT，默认是SESSION级别，即在一个MyBatis会话中执行的所有语句，都会共享这一个缓存。一种是STATEMENT级别，可以理解为缓存只对当前执行的这一个Statement有效
-->
<setting name="localCacheScope" value="SESSION"/>
```
## 二级缓存
在 mybatis-config.xml 中配置
```xml
<!--开启二级缓存-->
<setting name="cacheEnabled" value="true"/>
```
在 XXXMapper.xml 文件中,添加 `<cache/>`
共享 某个 命名空间二级缓存,需要 使用 `<cache-ref namespace="com.zbcn.mybatis.mapper.xml.BlogAuthorMapper"/>`
 


