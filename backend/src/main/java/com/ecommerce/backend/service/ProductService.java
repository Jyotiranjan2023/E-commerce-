package com.ecommerce.backend.service;

import java.util.List;
import com.ecommerce.backend.model.Product;

public interface ProductService {

    Product addProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    void deleteProduct(Long id);


}