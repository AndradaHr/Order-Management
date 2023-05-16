package org.example.bll;

import org.example.dao.ProductDAO;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ProductBLL {
    ProductDAO productDAO = new ProductDAO();

    public Product findProductById(int productId) {
        Product product = new Product(productId);

        productDAO.findById(product);

        if (product.getName() == null) {
            throw new NoSuchElementException("The product with id =" + productId + " was not found!");
        }

        return product;
    }

    public void insertProduct(String name, int price, int stock) {
        productDAO.insert(new Product(name, price, stock));
    }
    public void updateProduct(int id, String name, int price, int stock) {
        productDAO.update(new Product(id, name, price, stock));
    }
    public void deleteProduct(int productId) {
        productDAO.delete(new Product(productId));
    }

    public ArrayList<Product> getAllProducts() {
        return productDAO.findAll();
    }
}
