package main;



import databases.*;
import lists.*;
import tables.*;
import utils.*;


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Date;


/**
 * Created by root on 18.02.17.
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, SQLException {
//        Class.forName("org.postgresql.Driver");
//        Connection conn = Conn.getInstance();


//
//        UserDB.insert(new User("vasya","vasin",1,"aaa","bbb"));
//        UserDB.insert(new User("vasya","vasin",1,"ccc","ddd"));
////
//        MoneyDB.insert(new Money("aaa",new Date(11111111112L),1000));
//        MoneyDB.insert(new Money("ccc",new Date(1111112112L),100));
//
//        ThingDB.insert(new Thing("aaa","car",100,new Date(22222222L),3));
//        ThingDB.insert(new Thing("ccc","car",100,new Date(222222232L),3));
//
//        ReportDB.insert(new Report("aaa","car","waiting",10));
//        ReportDB.insert(new Report("ccc","car","waiting",10));
//
//
//        UpDo.download();
//        UpDo.upload();

        UserDB.deleteAll();

        UpDo.upload();




        }
    }



