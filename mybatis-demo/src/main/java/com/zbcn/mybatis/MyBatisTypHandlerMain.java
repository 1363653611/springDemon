package com.zbcn.mybatis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zbcn.mybatis.mapper.xml.BlogMapper;
import com.zbcn.mybatis.vo.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 *  typeHandler 类型转换
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/16 18:45
 */
public class MyBatisTypHandlerMain<T> {

    private static String configPath = "mybatis-config-typeHandler.xml";

    /**
     * 测试
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sessionFactory = getSqoSessionFactory();
        try (SqlSession sqlSession = sessionFactory.openSession()){
            testXml(sqlSession);
//            //手动提交事物
            sqlSession.commit();
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



    private static void testXml(SqlSession sqlSession) {
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        List<Blog> allBlogs = mapper.getAllBlogs();
        String s = JSONArray.toJSONString(allBlogs);
        System.out.println(s);
        Object o = sqlSession.selectOne("com.zbcn.mybatis.mapper.xml.BlogMapper.selectOne",4);
        System.out.println(JSON.toJSONString(o));
    }
}
