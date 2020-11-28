/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Role;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gharbimedaziz
 */
public class RoleService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void createRole(Role r) {
        try {
            String req = "INSERT INTO role (rolename) VALUES(?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, r.getRoleName());
            st.executeUpdate();
            System.out.println("+ ROLE ADDED TO DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteRole(int roleId) {
        try {
            String req = "delete from role where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, roleId);
            st.executeUpdate();
            System.out.println("+ ROLE DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateRole(int roleId, String roleName) {
        try {
            String req = "update role set rolename=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, roleName);
            st.setInt(2, roleId);
            st.executeUpdate();
            System.out.println("+ ROLE UPDATED IN DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Role readRole(int roleId) {
        Role role = null;
        try {
            String req = "select * from role where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, roleId);
            ResultSet res = st.executeQuery();
            ArrayList<Role> roles = new ArrayList<>();
            while(res.next()){
                roles.add(new Role(res.getInt(1), res.getString(2)));
                role = roles.get(0);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return role;
    }
}
