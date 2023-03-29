package com.spring.controller;

import com.spring.model.Product;
import com.spring.service.ProductService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by oguzhanaslan on 1.09.2020.
 */

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Setter
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public ResponseEntity<Void> saveProduct(@RequestBody Product product) {
        try {
            productService.saveProduct(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        }
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteCost(@RequestParam("productId") long productId) {


        try {
            productService.Delete(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        }


    }

    @RequestMapping(value = "/allProducts", method = RequestMethod.GET)
    public ResponseEntity<Object> listAllProducts(@RequestParam("gardenID") int gardenID) {
        try {
            return new ResponseEntity<>(productService.findAlltotalproduct(gardenID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/allCategoryproduct", method = RequestMethod.GET)
    public ResponseEntity<Object> listAllCategoryProduct(@RequestParam("productID") int productID, @RequestParam("gardenID") int gardenID) {
        try {
            return new ResponseEntity<>(productService.findAllcategorytotalproduct(productID, gardenID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/productpercost", method = RequestMethod.GET)
    public ResponseEntity<Double> listpercost(@RequestParam("productID") int productID, @RequestParam("gardenID") int gardenID) {
        try {
            return new ResponseEntity<>(productService.getPercost(productID, gardenID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/productlistgarden", method = RequestMethod.GET)
    public ResponseEntity<Object> listproductgarden(@RequestParam("productID") int productID) {
        try {
            return new ResponseEntity<>(productService.findByProductId(productID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/listallproduct", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> listAllproduct() {
        try {
            return new ResponseEntity<List<Product>>(productService.listAllproduct(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }


}
