/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interfaces;

import com.entity.TblProduct;
import java.util.List;

/**
 *
 * @author 
 */
public interface ProductDAO {
    public boolean insertProduct(TblProduct product);
    public boolean deleteProduct(int productId);
    public boolean updateProduct(TblProduct product);
    public boolean reduceProductQuantity(int productId, int amount);
    public List<TblProduct> searchProductByName(String name);
    public List<TblProduct> searchProductByPrice(double priceFrom, double priceTo);
    public List<TblProduct> getAllProduct();
    public TblProduct getProductById(int id);
}
