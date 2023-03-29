package com.spring.service;

import com.spring.dao.ProductDAO;
import com.spring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oguzhanaslan on 1.09.2020.
 */
@Service
@Transactional
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public void saveProduct(Product product) {
        productDAO.saveProduct(product);
    }

    @Override
    public void Delete(long id) {productDAO.Delete(id);}


    @Override
    public List<Object> findAlltotalproduct(int gardenID) {
        return productDAO.findAlltotalproduct(gardenID);
    }

    @Override
    public List<Object> findAllcategorytotalproduct(int productID, int gardenID) {
        return productDAO.findAllcategorytotalproduct(productID,gardenID);
    }

    @Override
    public double getPercost(int productID, int gardenID) {
        return productDAO.getPercost(productID,gardenID);
    }

    @Override
    public List<Product> findByProductId(int productID) {
        return productDAO.findByProductId(productID);
    }

    @Override
    public List<Product> listAllproduct() {
        return productDAO.listAllproduct();
    }


}
