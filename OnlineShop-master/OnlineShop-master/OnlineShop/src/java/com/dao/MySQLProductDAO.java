/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.TblProduct;
import com.factory.MySQLDAOFactory;
import com.interfaces.ProductDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class MySQLProductDAO implements ProductDAO {

    public MySQLProductDAO() {
    }

    public boolean insertProduct(TblProduct product) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = MySQLDAOFactory.getConnection();
            ps = con.prepareStatement("INSERT INTO tbl_product(name, description, price, quantity) VALUES(?,?,?,?)");
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
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
        return false;
    }

    public boolean deleteProduct(int productId) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = MySQLDAOFactory.getConnection();
            ps = con.prepareStatement("DELETE FROM tbl_product WHERE product_id = ?");
            ps.setInt(1, productId);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
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
        return false;
    }

    public boolean updateProduct(TblProduct product) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = MySQLDAOFactory.getConnection();
            ps = con.prepareStatement("UPDATE tbl_product SET name = ?, "
                    + "description = ?, price = ?, quantity = ? "
                    + "WHERE product_id = ?");
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getProductId());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
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
        return false;
    }

    public List<TblProduct> searchProductByName(String name) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = MySQLDAOFactory.getConnection();
            ps = con.prepareStatement("SELECT * FROM tbl_product WHERE name LIKE ?");
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            List<TblProduct> products = new ArrayList<TblProduct>();
            while (rs.next()) {
                products.add(new TblProduct(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")));
            }
            return products;
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

    public List<TblProduct> searchProductByPrice(double priceFrom, double priceTo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<TblProduct> getAllProduct() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = MySQLDAOFactory.getConnection();
            ps = con.prepareStatement("SELECT * FROM tbl_product");
            rs = ps.executeQuery();
            List<TblProduct> products = new ArrayList<TblProduct>();
            while (rs.next()) {
                products.add(new TblProduct(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")));
            }
            return products;
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

    public TblProduct getProductById(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = MySQLDAOFactory.getConnection();
            ps = con.prepareStatement("SELECT * FROM tbl_product WHERE product_id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            List<TblProduct> products = new ArrayList<TblProduct>();
            if (rs.next()) {
                return new TblProduct(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"));
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

    public boolean reduceProductQuantity(int productId, int amount) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = MySQLDAOFactory.getConnection();
            ps = con.prepareStatement("UPDATE tbl_product SET quantity = quantity - ? WHERE product_id = ?");
            ps.setInt(1, amount);
            ps.setInt(2, productId);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
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
        return false;
    }
}
