package com.zbcn.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 *  自定义ServletContextListener
 *  <br/>
 *  @author zbcn8
 *  @since  2020/7/16 19:09
 */
public class MyDataContextListener implements ServletContextListener {

    private ServletContext context = null;

    //该方法在ServletContext 启动之后被调用，并准备好处理客户端请求
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.context = sce.getServletContext();
        //可以实现自己的逻辑并将结果记录在属性中
        context.setAttribute("myData", "test ServletContextListener");
    }

    //在servlet关闭的时候调用
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        this.context = null;
    }
}
