package com.zbcn.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisMain {
    private static String configPath = "mybatis-config.xml";

    /**
     * 测试
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sessionFactory = getSqoSessionFactory();
        try (SqlSession sqlSession = sessionFactory.openSession(true)){
            

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
