package com.spring.service;

import com.spring.model.Product;

import java.util.List;

/**
 * Created by oguzhanaslan on 1.09.2020.
 */
public interface ProductService {

    void saveProduct(Product product);
    void Delete(long id);
    List<Object> findAlltotalproduct(int gardenID);
    List<Object> findAllcategorytotalproduct(int productID,int gardenID);
    double getPercost(int productID,int gardenID);
    List<Product> findByProductId(int productID);
    List<Product> listAllproduct();


}

