/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.dao.MySQLAccountDAO;
import com.entity.TblAccount;
import com.factory.MySQLDAOFactory;
import com.interfaces.AccountDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 
 */
@ManagedBean
@SessionScoped
public class Account {
    
    MySQLDAOFactory fac = new MySQLDAOFactory();
    AccountDAO accountDAO = fac.getAccountDAO();
    
    TblAccount account = null;
    
    String username = null;
    String password = null;
    
    String errMess = "";
    
    public Account() {
    }
    
    public void login() {
       // account = accountDAO.checkLogin(username, password);
    	account = new MySQLAccountDAO().checkLogin(username, password);
        if (account == null) {
            errMess = "Incorrect username / password";
        } else {
            errMess = "";
        }
    }

    public void logout() {
        account = null;
    }
    public TblAccount getAccount() {
        return account;
    }

    public void setAccount(TblAccount account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrMess() {
        return errMess;
    }

    public void setErrMess(String errMess) {
        this.errMess = errMess;
    }
    
}
