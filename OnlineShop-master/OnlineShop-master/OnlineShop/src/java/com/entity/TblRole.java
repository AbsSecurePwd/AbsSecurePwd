/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;

public class TblRole implements Serializable {

    private String rolename;

    public TblRole() {
    }

    public TblRole(String rolename) {
        this.rolename = rolename;
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
        hash += (rolename != null ? rolename.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblRole)) {
            return false;
        }
        TblRole other = (TblRole) object;
        if ((this.rolename == null && other.rolename != null) || (this.rolename != null && !this.rolename.equals(other.rolename))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.TblRole[ rolename=" + rolename + " ]";
    }
}
