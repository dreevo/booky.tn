/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Language;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author gharbimedaziz
 */
public class LanguageService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void createLanguage(Language l) {
        try {
            String req = "INSERT INTO language (languagename) VALUES(?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, l.getLanguageName());
            st.executeUpdate();
            System.out.println("+ LANGUAGE ADDED TO DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteLanguage(int languageid) {
        try {
            String req = "delete from language where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, languageid);
            st.executeUpdate();
            System.out.println("+ LANGUAGE DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateLanguage(Language l) {
        try {
            String req = "update language set languagename=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, l.getLanguageName());
            st.setInt(2, l.getId());
            st.executeUpdate();
            System.out.println("+ LANGUAGE UPDATED IN DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Language readLanguage(int languageId) {
        Language language = null;
        try {
            String req = "select languagename from language where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, languageId);
            ResultSet res = st.executeQuery();
            while(res.next()){
                String languageName = res.getString(1);
                System.out.println(languageName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return language;
    }
    
    public void readLanguages(ObservableList<Language> languageList){
        try{
            String req = "select id,languagename from language";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            while(res.next()){
                Language lang = new Language ();
                lang.setLanguageName(res.getString(2));
                lang.setId(res.getInt(1));
                languageList.add(lang);  
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
       }
        
    }
}
