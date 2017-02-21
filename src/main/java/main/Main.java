package main;


import databases.*;

import utils.*;




/**
 * Created by root on 18.02.17.
 */
public class Main {

    public static void main(String[] args) {
        UserDB.deleteAll();
        UpDo.upload();
    }
}



