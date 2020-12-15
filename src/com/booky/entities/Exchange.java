/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

import java.util.ArrayList;

/**
 *
 * @author Malek
 */
public class Exchange {
    private int idExchange;
    private int idCustomerS;
    private int idCustomerR;
    private String exchangeDescription;
    private boolean exchangeStatus = false;

  

    public Exchange(int idCustomerR, String exchangeDescription,boolean exchangeStatus) {
        this.idCustomerS = idCustomerR;
        this.exchangeDescription = exchangeDescription;
        this.exchangeStatus=exchangeStatus;
    }

    public Exchange(int idExchange, int idCustomerR, int idCustomerS, String exchangeDescription) {
        this.idExchange = idExchange;
        this.idCustomerS = idCustomerR;
        this.idCustomerR = idCustomerS;
        this.exchangeDescription = exchangeDescription;
    }

    public Exchange(int idCustomerR, int idCustomerS, String exchangeDescription,boolean exchangeStatus) {
        this.idCustomerS = idCustomerS;
        this.idCustomerR = idCustomerR;
        this.exchangeDescription = exchangeDescription;
        this.exchangeStatus=exchangeStatus;
    }
    
    

    

    public Exchange() {
    }

    public int getIdExchange() {
        return idExchange;
    }

    public void setIdExchange(int idExchange) {
        this.idExchange = idExchange;
    }

    public int getIdCustomerS() {
        return idCustomerS;
    }

    public void setIdCustomerS(int idCustomerS) {
        this.idCustomerS = idCustomerS;
    }

    public int getIdCustomerR() {
        return idCustomerR;
    }

    public void setIdCustomerR(int idCustomerR) {
        this.idCustomerR = idCustomerR;
    }

   

    public String getExchangeDescription() {
        return exchangeDescription;
    }

    public void setExchangeDescription(String exchangeDescription) {
        this.exchangeDescription = exchangeDescription;
    }

    public boolean isExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(String message) {
        if (message.equals("Done"))
            this.exchangeStatus = true;

        if (message.equals("Available"))
            this.exchangeStatus = false;
    }
    public String getExchangeStatus(){
        return exchangeStatus? "Done" : "Available";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Exchange other = (Exchange) obj;
        if (this.idExchange != other.idExchange) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Exchange{" + "idCustomerS=" + idCustomerS + ", idCustomerR=" + idCustomerR + ", exchangeDescription=" + exchangeDescription + ", exchangeStatus=" + exchangeStatus + '}';
    }
    
}
