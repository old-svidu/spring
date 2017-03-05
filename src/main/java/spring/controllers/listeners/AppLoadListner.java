package spring.controllers.listeners;

import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by root on 03.03.17.
 */
public class AppLoadListner implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContextUtils
                .getRequiredWebApplicationContext(sce.getServletContext())
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
