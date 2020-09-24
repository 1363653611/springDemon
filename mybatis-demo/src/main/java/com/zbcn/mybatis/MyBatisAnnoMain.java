package com.zbcn.mybatis;

import com.alibaba.fastjson.JSON;
import com.zbcn.mybatis.mapper.anno.BlogMapperAnno;
import com.zbcn.mybatis.vo.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 *  注解方式测试
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/16 18:45
 */
public class MyBatisAnnoMain<T> {

    private static String configPath = "mybatis-anno-config.xml";

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

    /**
     * 测试
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sessionFactory = getSqoSessionFactory();
        try (SqlSession sqlSession = sessionFactory.openSession()){
            testAnno(sqlSession);

        }
    }

    private static void testAnno(SqlSession sqlSession) {
        BlogMapperAnno mapper = sqlSession.getMapper(BlogMapperAnno.class);
        Blog blog = mapper.selectOne(4);
        System.out.println(JSON.toJSONString(blog));
    }

}
