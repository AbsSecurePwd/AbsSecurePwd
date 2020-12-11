/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.entity.TblProduct;
import com.factory.MySQLDAOFactory;
import com.interfaces.ProductDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 
 */
@ManagedBean
@SessionScoped
public class ProductBean {

    MySQLDAOFactory fac = new MySQLDAOFactory();
    ProductDAO productDAO = fac.getProductDAO();
    String message = "";
    TblProduct product;
    int selectedProduct = 0;

    public ProductBean() {
        product = new TblProduct();
    }

    public TblProduct getProduct() {
        return product;
    }

    public void setProduct(TblProduct product) {
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String prepareEdit() {
        product = productDAO.getProductById(selectedProduct);
        message = "";
        return "Edit.xhtml";
    }

    public String prepareCreate() {
        product = new TblProduct();
        message = "";
        return "Create.xhtml";
    }
    
    public void editProduct() {
        if (productDAO.updateProduct(product)) {
            message = "Changes have been saved!";
        } else {
            message = "";
        }
    }

    public void createProduct() {
        if (productDAO.insertProduct(product)) {
            message = "Product added!";
        } else {
            message = "";
        }
    }

    public int getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(int selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void deleteProduct() {
        if (selectedProduct > 0) {
            productDAO.deleteProduct(selectedProduct);
        }
    }
}
