/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.TblAccount;
import com.factory.MySQLDAOFactory;
import com.interfaces.AccountDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class MySQLAccountDAO implements AccountDAO {

    public MySQLAccountDAO() {
    }

    public TblAccount checkLogin(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = MySQLDAOFactory.getConnection();
            ps = con.prepareStatement("SELECT * FROM tbl_account WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new TblAccount(
                        rs.getInt("account_id"),
                        rs.getString("fullname"),
                        username,
                        password,
                        rs.getString("rolename"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(MySQLProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
