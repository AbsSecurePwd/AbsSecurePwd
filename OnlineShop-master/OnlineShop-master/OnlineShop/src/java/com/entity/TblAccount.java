/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;

/**
 *
 * @author 
 */
public class TblAccount implements Serializable {

    private Integer accountId;
    private String fullname;
    private String username;
    private String password;
    private String rolename;

    public TblAccount() {
    }

    public TblAccount(Integer accountId) {
        this.accountId = accountId;
    }

    public TblAccount(Integer accountId, String fullname, String username, String password, String rolename) {
        this.accountId = accountId;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.rolename = rolename;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountId != null ? accountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAccount)) {
            return false;
        }
        TblAccount other = (TblAccount) object;
        if ((this.accountId == null && other.accountId != null) || (this.accountId != null && !this.accountId.equals(other.accountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.TblAccount[ accountId=" + accountId + " ]";
    }
}
