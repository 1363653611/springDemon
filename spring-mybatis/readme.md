# 集成功能
### mybatis 功能
- 包扫描路径
```xml
    <!--扫描包路径-->
    <mybatis:scan base-package="com.zbcn.mapper"/>
    <!--与 mybatis:scan  作用一致-->
    <!--<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.zbcn.mapper" />
        <property name="processPropertyPlaceHolders" value="true"/>
    </bean>-->
```

- 数据源
```xml
 <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="jdbcUrl" value="${jdbcUrl}"/>
        <property name="driverClass" value="${driverClass}"/>
        <property name="user" value="${user}"/>
        <property name="password" value="${password}"/>
        <!--初始化时获取三个连接，取值应在minPoolSize与maxPoolSize之间。Default: 3 -->
        <property name="initialPoolSize" value="${initialPoolSize}"/>
        <!--连接池中保留的最小连接数。Default: 3 -->
        <property name="minPoolSize" value="${minPoolSize}"/>
        <!--连接池中保留的最大连接数。Default: 15 -->
        <property name="maxPoolSize" value="${maxPoolSize}"/>
        <!--当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。Default: 3 -->
        <property name="acquireIncrement" value="${acquireIncrement}"/>
        <!-- 控制数据源内加载的PreparedStatements数量。如果maxStatements与maxStatementsPerConnection均为0，则缓存被关闭。Default: 0 -->
        <property name="maxStatements" value="${maxStatements}"/>
        <!-- maxStatementsPerConnection定义了连接池内单个连接所拥有的最大缓存statements数。Default: 0 -->
        <property name="maxStatementsPerConnection" value="${maxStatementsPerConnection}"/>
        <!--最大空闲时间,1800秒内未使用则连接被丢弃。若为0则永不丢弃。Default: 0 -->
        <property name="maxIdleTime" value="${maxIdleTime}"/>
    </bean>
```
- mybatis 的核心类
```xml
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="configuration">
        <bean id="configuration" class="org.apache.ibatis.session.Configuration">
            <property name="mapUnderscoreToCamelCase" value="true"/>
            <!-- 打开全局缓存开关（二级缓存）默认值就是 true -->
            <property name="cacheEnabled" value="true"/>
        </bean>
    </property>
    <property name="dataSource" ref="dataSource"/>
    <property name="mapperLocations" value="classpath*:mappers/**/*.xml" />
    <property name="databaseIdProvider" ref="databaseIdProvider"/>
</bean>
<!---多数据源-->
<bean id="databaseIdProvider" class="org.apache.ibatis.mapping.VendorDatabaseIdProvider">
    <property name="properties">
        <props>
            <prop key="SQL Server">sqlserver</prop>
            <prop key="DB2">db2</prop>
            <prop key="Oracle">oracle</prop>
            <prop key="MySQL">mysql</prop>
        </props>
    </property>
</bean>
```
### spring 中使用Mybatis
#### 通过 org.mybatis.Spring.SqlSessionFactoryBean引入了 Mybatis 的配置和数据源
- SqlSessionFactoryBean#afterPropertiesSet() SqlSessionFactoryBean的初始化
- SqlSessionFactoryBean#getObject() 获取 sqlSessionFactory实例

#### 通过 org.mybatis.Spring.mapper.MapperFactoryBean 集成了 sqlSessionFaction 和 MapperInterface 接口类之间的关系
- MapperFactoryBean 的初始化: DaoSupport#afterPropertiesSet()
- MapperFactoryBean 的获取: MapperFactoryBean#getObject()

### 可以通过 MapperScannerConfigurer 代替配置
```xml
<!--注释掉原有代码
<bean id="userMapper" class = "org.mybatis.Spring.mapper.MapperFactoryBean">
    <property name="mapperInterface " value="test.mybatis.dao.UserMapper"></property>
    <property name= "sqlSessionFactory" ref="sqlSessionFactory"></property>
</bean>
-->
<bean class= "org.mybatis.Spring.mapper.MapperScannerConfigurer">
    <property name= "basePackage" value= " test.mybatis.dao"/>
</bean>
```

- MapperScannerConfigurer的类的初始化核心逻辑:  
> MapperScannerConfigurer#postProcessBeanDefinitionRegistry()

## TRANSACTION 事务