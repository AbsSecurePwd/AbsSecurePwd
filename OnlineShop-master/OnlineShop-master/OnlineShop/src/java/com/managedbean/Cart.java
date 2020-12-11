/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.entity.TblProduct;
import com.factory.MySQLDAOFactory;
import com.interfaces.ProductDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 
 */
@ManagedBean
@SessionScoped
public class Cart {

    MySQLDAOFactory fac = new MySQLDAOFactory();
    ProductDAO productDAO = fac.getProductDAO();
    List<TblProduct> cart;
    int selectedProduct = 0;
    int selectedQuantity = 1;
    String maxQuantityErr = "";

    boolean checkoutDone = false;

    public boolean isCheckoutDone() {
        return checkoutDone;
    }

    public void setCheckoutDone(boolean checkoutDone) {
        this.checkoutDone = checkoutDone;
    }
    
    public Cart() {
        cart = new ArrayList<TblProduct>();
    }

    public List<TblProduct> getCart() {
        return cart;
    }

    public void setCart(List<TblProduct> cart) {
        this.cart = cart;
    }

    public String getMaxQuantityErr() {
        return maxQuantityErr;
    }

    public void setMaxQuantityErr(String maxQuantityErr) {
        this.maxQuantityErr = maxQuantityErr;
    }

    public void addProduct() {

        if (selectedProduct > 0) {

            int index = -1;
            for (int i = 0; i < cart.size(); ++i) {
                if (cart.get(i).getProductId() == selectedProduct) {
                    index = i;
                    break;
                }
            }

            if (index > -1) {
                TblProduct databaseProduct = productDAO.getProductById(selectedProduct);
                if (databaseProduct.getQuantity()
                        - cart.get(index).getQuantity()
                        - selectedQuantity < 0) {
                    maxQuantityErr = "We only have " + databaseProduct.getQuantity()
                            + " product(s) in our store. Please order fewer!";
                    selectedQuantity = 0;
                } else {
                    maxQuantityErr = "";
                }
            } else {
                maxQuantityErr = "";
            }
            addItem(selectedProduct);
        }
        selectedQuantity = 1;
    }

    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

    public void checkout() {
        for (TblProduct product : cart) {
            productDAO.reduceProductQuantity(product.getProductId(), product.getQuantity());
        }

        cart = new ArrayList<TblProduct>();
        checkoutDone = true;
    }

    public void markCheckoutDone() {
        checkoutDone = false;
    }
    
    public void removeProduct() {
        if (selectedProduct > 0) {
            removeItem(selectedProduct);
        }
        maxQuantityErr = "";
    }

    public void removeProductAll() {
        if (selectedProduct > 0) {
            removeItemAll(selectedProduct);
        }
        maxQuantityErr = "";
    }

    private void addItem(int id) {
        int quantity = 1;
        int index = -1;
        for (int i = 0; i < cart.size(); ++i) {
            if (cart.get(i).getProductId() == id) {
                index = i;
                quantity = cart.get(i).getQuantity() + selectedQuantity;
                break;
            }
        }

        if (index > -1) {
            cart.get(index).setQuantity(quantity);
        } else {
            TblProduct product = productDAO.getProductById(id);
            product.setQuantity(selectedQuantity);
            cart.add(product);
        }
    }

    private void removeItem(int id) {
        TblProduct product = productDAO.getProductById(id);
        int index = cart.indexOf(product);
        if (index > -1) {
            if (cart.get(index).getQuantity() > 1) {
                cart.get(index).setQuantity(cart.get(index).getQuantity() - 1);
            } else {
                cart.remove(index);
            }
        }
    }

    private void removeItemAll(int id) {
        TblProduct product = productDAO.getProductById(id);
        int index = cart.indexOf(product);
        if (index > -1) {
            cart.remove(index);
        }
    }

    public int getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(int selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
}
