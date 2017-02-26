package common.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by root on 21.02.17.
 */
public class Log {
    public static Logger logger = Logger.getLogger(Log.class);
    static {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }
}
