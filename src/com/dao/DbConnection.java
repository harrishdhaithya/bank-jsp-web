package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class DbConnection {
    private static Connection conn = null;
    static{
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Bank","postgres","1234");

        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static Connection getConnection(){
        return conn;
    }
}
