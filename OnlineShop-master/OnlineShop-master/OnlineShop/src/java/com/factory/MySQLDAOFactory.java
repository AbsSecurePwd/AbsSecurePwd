/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory;

import com.dao.MySQLAccountDAO;
import com.dao.MySQLOrderDAO;
import com.dao.MySQLProductDAO;
import com.interfaces.AccountDAO;
import com.interfaces.OrderDAO;
import com.interfaces.ProductDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 *
 * @author 
 */
public class MySQLDAOFactory {

    private static DataSource getOnlineshopDS() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/onlineshopDS");
    }

    public static Connection getConnection() {
        try {
            return getOnlineshopDS().getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(MySQLDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public AccountDAO getAccountDAO() {
        // MySQLAccountDAO implements AccountDAO
        return new MySQLAccountDAO();
    }

    public OrderDAO getOrderDAO() {
        // MySQLOrderDAO implements OrderDAO
        return new MySQLOrderDAO();
    }
    
    public ProductDAO getProductDAO() {
        // MySQLProductDAO implements OrderDAO
        return new MySQLProductDAO();
    }

}
