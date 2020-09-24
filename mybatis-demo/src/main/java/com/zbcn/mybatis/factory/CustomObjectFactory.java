package com.zbcn.mybatis.factory;

import com.zbcn.mybatis.vo.Blog;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * MyBatis 每次创建结果对象的新实例时，它都会使用一个对象工厂（ObjectFactory）实例来完成。
 * 默认的对象工厂需要做的仅仅是实例化目标类，要么通过默认构造方法，要么在参数映射存在的时候通过参数构造方法来实例化。
 * 如果想覆盖对象工厂的默认行为，则可以通过创建自己的对象工厂来实现
 * 覆盖默认的ObjectFactory
 * ObjectFactory 接口很简单，它包含两个创建实例用的方法，一个是处理默认无参构造方法的，另外一个是处理带参数的构造方法的。
 *
 * 方法的执行顺序是：setProperties -> isCollection -> create(有构造参数) -> create 无构造参数
 */
public class CustomObjectFactory extends DefaultObjectFactory {

    /**
     * 全局属性
     */
    private Properties properties;

    //使用默认构造方法创建对象实例
    public Object create(Class type) {
        if(type.isAssignableFrom(Blog.class)){
            Blog blog =  (Blog)super.create(type);
            //先创建的是空对象，然后通过查询出数据库中的字段进行匹配。所以查出来的值可以替换掉此处设置的值
            blog.setUpdateTime(new Date());
            String glob = properties.getProperty("glob");
            blog.setGlob(glob);
            return blog;
        }else{
            return super.create(type);
        }
    }

    //有构造参数列表和构造参数值列表的创建对象实例的方式
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    //为自定义ObjectFactory设置配置参数
    public void setProperties(Properties properties) {
        this.properties =properties;
        super.setProperties(properties);
    }
    public <T> boolean isCollection(Class<T> type) {
        return Collection.class.isAssignableFrom(type);
    }
}
