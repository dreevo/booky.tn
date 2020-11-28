/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.tests;

import com.booky.entities.Customer;
import com.booky.entities.Role;
import com.booky.services.CustomerService;
import com.booky.services.RoleService;

/**
 *
 * @author gharbimedaziz
 */
public class MainProg {

    public static void main(String[] args) {

//        DECLARING TWO ROLES 
        Role admin = new Role("Admin");
        Role client = new Role("Client");

//        ADDING ROLES TO DATABASE
        RoleService rs = new RoleService();
//        rs.createRole(admin);
//        rs.createRole(client);
//          DELETING ROLES FROM DATABASE
//        rs.deleteRole(1);
//        rs.updateRole(2, "Customer");
            Role admin2 = rs.readRole(3);
            System.out.println(admin2);

//         DECLARING A MANAGER
        Customer A = new Customer("Med Aziz", "Gharbi", 22, "gharbi@gmail.com", "15 rue kortoba", "+216 55-75-33-37", null, admin);
//         DECLARING A CUSTOMER
        Customer C1 = new Customer("Mounir", "Fridi", 22, "fridi@gmail.com", "10 rue lafayette", "+216 26-64-99-70", null, client);
        Customer C2 = new Customer("Sami", "Dridi", 22, "dridi@gmail.com", "8 rue carthage", "+216 98-62-20-40", null, client);
        Customer C3 = new Customer("Rami", "Kalel", 22, "kalel@gmail.com", "4 rue slimen", "+216 23-55-41-70", null, client);

    }
}
