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
    
    private final String URL="jdbc:mysql://localhost:3306/bookydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USERNAME ="root";
    private final String PASSWORD = "";
    
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
