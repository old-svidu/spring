package common.utils;

import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * Created by root on 20.02.17.
 */
public class UpDo {
    private static Logger logger = Logger.getLogger(UpDo.class);

    final static String[] TABLES = {"models.lists.Users", "models.lists.Things", "models.lists.Reports", "models.lists.Moneys"};
    final static String[] XMLS = {"models.lists.Users.xml","models.lists.Things.xml","models.lists.Reports.xml","models.lists.Moneys.xml"};

    public static void download() {
        for (String table : TABLES) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        Class cl = Class.forName(table);
                        Method m = cl.getMethod("selectAll");
                        Object o = cl.newInstance();
                        m.invoke(o);
                        DataManager.serialize(o);
                    } catch (Exception e) {
                        logger.error(e);
                    }
                }
            }).start();
        }
    }

    public static void upload() {
        for (String xml : XMLS) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        Class cl = DataManager.getClassFromXml(xml);
                        Method m = cl.getMethod("insert");
                        Object obj = cl.newInstance();
                        m.invoke(obj);
                    } catch (Exception e) {
                        logger.error(e);
                    }
                }
            }).start();
        }
    }
}


