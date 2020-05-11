### spring-jdbc
1. 创建 表 `user_test`
2. 对应实体类 `userTest`
3. 表与实体类之间的映射关系 `UserTestRowMapper implements RowMapper`
4. 创建操作 service `IUserTestService`
5. 引入 `JdbcTemplate`
6. 配置文件
    1. 数据源 `dataSource`
    2. 配置  `JdbcTemplate`
    
7. 单元测试:
```java
//直接控制容器启动
@ContextConfiguration(locations = {"classpath:spring-jdbc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
```