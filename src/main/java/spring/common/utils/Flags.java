package spring.common.utils;

/**
 * Created by root on 02.03.17.
 */
public class Flags {
    public static boolean flag = true;

    public static void changeFlag(){
        Flags.flag=!Flags.flag;
    }
}
