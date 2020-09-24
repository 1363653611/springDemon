package com.zbcn.mybatis;

import com.alibaba.fastjson.JSON;
import com.zbcn.mybatis.mapper.xml.BlogMapper;
import com.zbcn.mybatis.vo.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 *  一级缓存测试
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/22 16:49
 */
public class MyBatisCacheMain {
    private static String configPath = "mybatis-config-cache.xml";

    /**
     * 测试
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sessionFactory = getSqoSessionFactory();
//        try (SqlSession sqlSession = sessionFactory.openSession(true)){
//            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
//            //test1(mapper);
//            test2(mapper);
//        }

        test3(sessionFactory);
    }

    private static void test3(SqlSessionFactory sessionFactory) {
        try (SqlSession sqlSession1 = sessionFactory.openSession(true);
             SqlSession sqlSession2 = sessionFactory.openSession(true)){
            BlogMapper mapper1 = sqlSession1.getMapper(BlogMapper.class);
            BlogMapper mapper2 = sqlSession2.getMapper(BlogMapper.class);
            Blog blog = mapper1.selectOne(4);
            System.out.println("查询1："+ JSON.toJSONString(blog));
            Blog blog2 = mapper1.selectOne(4);
            System.out.println("查询2："+ JSON.toJSONString(blog2));
            System.out.println("mapper2 新增数据");
            insert(mapper2);
            Blog blog3 = mapper1.selectOne(4);
            System.out.println("mapper1查询3："+ JSON.toJSONString(blog3));
            Blog blog4 = mapper2.selectOne(4);
            System.out.println("mapper2查询4："+ JSON.toJSONString(blog4));

        }
    }


    private static void test1(BlogMapper mapper) {
        Blog blog = mapper.selectOne(4);
        System.out.println("查询1："+ JSON.toJSONString(blog));
        Blog blog2 = mapper.selectOne(4);
        System.out.println("查询2："+ JSON.toJSONString(blog2));
        Blog blog3 = mapper.selectOne(4);
        System.out.println("查询3："+ JSON.toJSONString(blog3));
        Blog blog4 = mapper.selectOne(4);
        System.out.println("查询4："+ JSON.toJSONString(blog4));
    }

    /**
     * 增加了对数据库的修改操作，验证在一次数据库会话中，如果对数据库 表中的数据发生了修改操作，一级缓存会失效
     * @param mapper
     */
    private static void test2(BlogMapper mapper) {
        Blog blog = mapper.selectOne(4);
        System.out.println("查询1："+ JSON.toJSONString(blog));
        Blog blog2 = mapper.selectOne(4);
        System.out.println("查询2："+ JSON.toJSONString(blog2));
        Blog blog3 = mapper.selectOne(4);
        System.out.println("查询3："+ JSON.toJSONString(blog3));
        insert(mapper);
        Blog blog4 = mapper.selectOne(4);
        System.out.println("查询4："+ JSON.toJSONString(blog4));
    }

    private static void insert(BlogMapper mapper){
        Blog blog = new Blog();
        blog.setAuthorId(1L);
        blog.setContent("测试博客");
        blog.setCreateTime(new Date());
        blog.setName("张三");
        blog.setTitle("我的北漂时光");
        mapper.insert(blog);
    }

    /**
     * 获取sqlSessionFactory
     * @return
     * @throws IOException
     */
    public static SqlSessionFactory getSqoSessionFactory() throws IOException {
        //获取配置文件流:MyBatis 包含一个名叫 Resources 的工具类
        InputStream inputStream = Resources.getResourceAsStream(configPath);
        //利用sqlSessionBuilder 创建SqlSessionFactory
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        return build;
    }
}
