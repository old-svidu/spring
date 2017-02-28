package controllers.listeners;

import common.utils.Mailer;
import controllers.HomeServlet;
import models.dao.UserDao;
import models.pojo.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

/**
 * Created by root on 27.02.17.
 */
public class SessionListner implements HttpSessionListener,HttpSessionAttributeListener {

    private static Logger logger = Logger.getLogger(SessionListner.class);

    private static final String EMAIL = "*******";
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if ("id".equals(event.getName())&&event.getValue()!=null&& HomeServlet.flag) {
                    logger.trace("id not null");
                    Mailer.sendMail(EMAIL,"AdminLogged", "admin logged");
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
