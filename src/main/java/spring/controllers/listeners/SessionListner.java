package spring.controllers.listeners;

import org.springframework.web.context.support.WebApplicationContextUtils;
import spring.common.exceptions.UserDaoException;
import spring.common.utils.Flags;
import spring.common.utils.Mailer;

import spring.models.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.services.interfaces.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by root on 27.02.17.
 */
@Component
public class SessionListner implements HttpSessionListener,HttpSessionAttributeListener {
    private static Logger logger = Logger.getLogger(SessionListner.class);

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private static final String EMAIL = "vidunov_pc@mail.ru";

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if ("id".equals(event.getName())&&event.getValue()!=null&& Flags.flag) {
                    try {
                        User user = userService.selectUserById((Integer) event.getValue());
                        if (user.getRole().equals("admin")){
                            Mailer.sendMail(EMAIL,"AdminLogged", "admin logged");
                        }
                    } catch (UserDaoException e) {
                        logger.error(e);
                    }
                }
            }
        }).start();
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }

 }
