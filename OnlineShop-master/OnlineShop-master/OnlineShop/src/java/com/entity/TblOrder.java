/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 
 */
public class TblOrder implements Serializable {

    private Integer orderId;
    private int accountId;
    private String address;
    private Date created;

    public TblOrder() {
    }

    public TblOrder(Integer orderId) {
        this.orderId = orderId;
    }

    public TblOrder(Integer orderId, int accountId, String address, Date created) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.address = address;
        this.created = created;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblOrder)) {
            return false;
        }
        TblOrder other = (TblOrder) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.TblOrder[ orderId=" + orderId + " ]";
    }
}
