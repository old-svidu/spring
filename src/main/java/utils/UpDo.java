package utils;

import java.lang.reflect.Method;

/**
 * Created by root on 20.02.17.
 */
public class UpDo {
    final static String[] TABLES = {"lists.Users", "lists.Things", "lists.Reports", "lists.Moneys"};
    final static String[] XMLS = {"lists.Users.xml","lists.Things.xml","lists.Reports.xml","lists.Moneys.xml"};

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
                        Log.logger.error(e);
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
                        Log.logger.error(e);
                    }
                }
            }).start();
        }
    }
}


