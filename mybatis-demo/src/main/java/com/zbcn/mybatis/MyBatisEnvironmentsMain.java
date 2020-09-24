package com.zbcn.mybatis;

import com.alibaba.fastjson.JSON;
import com.zbcn.mybatis.mapper.anno.BlogMapperAnno;
import com.zbcn.mybatis.mapper.pkg.BlogMapper2;
import com.zbcn.mybatis.mapper.xml.BlogMapper;
import com.zbcn.mybatis.vo.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 *  Mapper 方式测试: class 和 package 级别 需要将 Mapper类和 .xml 文件同名称且放在同一个目录下面
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/16 18:45
 */
public class MyBatisEnvironmentsMain {

    private static String configPath = "mybatis-config-env.xml";

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
            test(sqlSession);

        }
    }

    private static void test(SqlSession sqlSession) {
        //以xml 的方式引入
        BlogMapper mapper3 = sqlSession.getMapper(BlogMapper.class);
        System.out.println(mapper3.getAllBlogs());
    }

}
