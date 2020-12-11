/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interfaces;

import com.entity.TblAccount;

/**
 *
 * @author 
 */
public interface AccountDAO {
    public TblAccount checkLogin(String username, String password);
}
