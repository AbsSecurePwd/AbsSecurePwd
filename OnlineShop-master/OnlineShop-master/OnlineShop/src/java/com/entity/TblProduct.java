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
public class TblProduct implements Serializable {

    private Integer productId;
    private String name;
    private String description;
    private double price;
    private int quantity;

    public TblProduct() {
    }

    public TblProduct(Integer productId) {
        this.productId = productId;
    }

    public TblProduct(Integer productId, String name, String description, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblProduct)) {
            return false;
        }
        TblProduct other = (TblProduct) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.TblProduct[ productId=" + productId + " ]";
    }
}
