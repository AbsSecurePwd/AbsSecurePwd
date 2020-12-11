/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.entity.TblProduct;
import com.factory.MySQLDAOFactory;
import com.interfaces.ProductDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author 
 */
@ManagedBean
@RequestScoped
public class Home {

    MySQLDAOFactory fac = new MySQLDAOFactory();
    ProductDAO productDAO = fac.getProductDAO();
    List<TblProduct> products = null;
    String searchValue = null;

    public Home() {
        if (searchValue == null) {
            products = productDAO.getAllProduct();
        } else {
            products = productDAO.searchProductByName(searchValue);
        }
    }

    public List<TblProduct> getProducts() {
        
        
        if (searchValue == null) {
            this.products = productDAO.getAllProduct();
        } else {
            this.products = productDAO.searchProductByName(searchValue);
        }
        return products;
    }

    public void setProducts(List<TblProduct> products) {
        this.products = products;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }


}
