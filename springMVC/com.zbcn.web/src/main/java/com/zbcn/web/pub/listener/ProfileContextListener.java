package com.zbcn.web.pub.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 自定义profile 监听器
 */
@Slf4j
public class ProfileContextListener  implements ServletContextListener {

    private static String profile;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        profile = sce.getServletContext().getInitParameter("spring.profiles.active");
        if (profile == null || profile.equals("")) {
            profile = sce.getServletContext().getInitParameter("spring.profiles.default");
        }
        profile = "config_" + profile + "/";
        log.info("环境配置中的profile：" + profile);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
