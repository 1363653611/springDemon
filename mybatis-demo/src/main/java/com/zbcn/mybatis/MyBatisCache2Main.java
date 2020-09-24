package com.zbcn.mybatis;

import com.alibaba.fastjson.JSON;
import com.zbcn.mybatis.mapper.xml.BlogAuthorMapper;
import com.zbcn.mybatis.mapper.xml.BlogMapper;
import com.zbcn.mybatis.vo.AuthorWithBlog;
import com.zbcn.mybatis.vo.Blog;
import com.zbcn.mybatis.vo.BlogAuthor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.omg.CORBA.MARSHAL;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 *  二级缓存测试
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/22 19:50
 */
public class MyBatisCache2Main {
    private static String configPath = "mybatis-config-cache2.xml";

    /**
     * 测试
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        test4();
    }

    /**
     * 由于**MyBatis的二级缓存是基于`namespace`的**，
     * 多表查询语句所在的`namspace`无法感应到其他`namespace`中的语句对多表查询中涉及的表进行的修改，
     * 引发脏数据问题
     * @throws IOException
     */
    private static void test4() throws IOException {
        SqlSessionFactory sessionFactory = getSqoSessionFactory();
        try (SqlSession session = sessionFactory.openSession(true);
             SqlSession session2 = sessionFactory.openSession(true);
             SqlSession session3 = sessionFactory.openSession(true)) {
            BlogAuthorMapper authorMapper = session.getMapper(BlogAuthorMapper.class);
            BlogAuthorMapper authorMapper2 = session2.getMapper(BlogAuthorMapper.class);
            BlogMapper blogMapper = session3.getMapper(BlogMapper.class);
            BlogAuthor author = authorMapper.getAuthorWithBlog(1L);
            System.out.println("authorMapper:"+JSON.toJSONString(author));
            session.close();
            AuthorWithBlog authorWithBlog = authorMapper2.getAuthorWithBlog(1L);
            System.out.println("authorMapper2:" + JSON.toJSONString(authorWithBlog));
            //blogMapper插入没有二级缓存的 blog
            insertBlog(blogMapper);
            session3.commit();
            //第二次重复读取
            AuthorWithBlog authorWithBlog2 = authorMapper2.getAuthorWithBlog(1L);
            System.out.println("authorMapper2:" + JSON.toJSONString(authorWithBlog2));
        }
    }

    private static void insertBlog(BlogMapper mapper){
        Blog blog = new Blog();
        blog.setAuthorId(1L);
        blog.setContent("测试博客");
        blog.setCreateTime(new Date());
        blog.setName("张三");
        blog.setTitle("我的北漂时光");
        mapper.insert(blog);
    }

    /**
     * 数据更新后,提交事务,则二级缓存失效
     * @throws IOException
     */
    private static void test3() throws IOException {
        SqlSessionFactory sessionFactory = getSqoSessionFactory();
        try (SqlSession session = sessionFactory.openSession(true);
             SqlSession session2 = sessionFactory.openSession(true);
             SqlSession session3 = sessionFactory.openSession(true)) {
            BlogAuthorMapper mapper = session.getMapper(BlogAuthorMapper.class);
            BlogAuthorMapper mapper2 = session2.getMapper(BlogAuthorMapper.class);
            BlogAuthorMapper mapper3 = session3.getMapper(BlogAuthorMapper.class);
            BlogAuthor author = mapper.getBlogAuthorById(1L);
            System.out.println(JSON.toJSONString(author));
            session.commit();
            BlogAuthor author3 = new BlogAuthor();
            author3.setId(2L);
            author3.setName("王五");
            mapper3.updateById(author3);
            session3.commit();
            BlogAuthor author1 = mapper2.getBlogAuthorById(1L);
            System.out.println(JSON.toJSONString(author1));
        }
    }

    /**
     * 第一个session 的事物提交时，二级缓存生效，而且命中率是0.5
     * @param sessionFactory
     */
    private static void test2(SqlSessionFactory sessionFactory) {
        try (SqlSession session = sessionFactory.openSession(true);
             SqlSession session2 = sessionFactory.openSession(true)) {
        BlogAuthorMapper mapper = session.getMapper(BlogAuthorMapper.class);
        BlogAuthorMapper mapper2 = session2.getMapper(BlogAuthorMapper.class);
        BlogAuthor author = mapper.getBlogAuthorById(1L);
        System.out.println(JSON.toJSONString(author));
        session.commit();
        BlogAuthor author1 = mapper2.getBlogAuthorById(1L);
        System.out.println(JSON.toJSONString(author1));
        }
    }

    /**
     * 当事物未提交时，2级缓存不生效
     * @param session
     * @param session2
     */
    private static void test1(SqlSession session, SqlSession session2) {
        BlogAuthorMapper mapper = session.getMapper(BlogAuthorMapper.class);
        BlogAuthorMapper mapper2 = session2.getMapper(BlogAuthorMapper.class);
        BlogAuthor author = mapper.getBlogAuthorById(1L);
        System.out.println(JSON.toJSONString(author));
        BlogAuthor author1 = mapper2.getBlogAuthorById(1L);
        System.out.println(JSON.toJSONString(author1));
    }

    private static void insert(SqlSessionFactory sessionFactory) {
        try (SqlSession sqlSession = sessionFactory.openSession(true)){
            BlogAuthorMapper mapper = sqlSession.getMapper(BlogAuthorMapper.class);
            BlogAuthor blogAuthor = new BlogAuthor();
            blogAuthor.setAge(25);
            blogAuthor.setCreateTime(new Date());
            blogAuthor.setGender("1");
            blogAuthor.setName("张三1");
            int insert = mapper.insert(blogAuthor);
            System.out.println(insert);
        }
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
