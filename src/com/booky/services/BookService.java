/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.utils.DataSource;
import java.sql.Connection;

/**
 *
 * @author gharbimedaziz
 */
public class BookService {
    Connection cnx = DataSource.getInstance().getCnx();
    

}
