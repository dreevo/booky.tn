/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gharbimedaziz
 */
public class DataSource {
    private Connection cnx;
    private static DataSource instance;
    
    private final String URL="jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2378859?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USERNAME ="sql2378859";
    private final String PASSWORD = "sP9%jW7%";
    
    public DataSource(){
        try{
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to database");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static DataSource getInstance(){
        if(instance == null)
            instance = new DataSource();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
