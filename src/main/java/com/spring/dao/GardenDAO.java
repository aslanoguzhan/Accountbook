package com.spring.dao;

import com.spring.model.Garden;
import com.spring.model.GardenProduct;
import com.spring.model.Product;

import java.util.List;

/**
 * Created by oguzhanaslan on 24.09.2020.
 */
public interface GardenDAO {
    void saveGarden(Garden garden);
    List<Object> findAllgarden(int userID);
    List<Object> listGardencost(int gardenID);
    List<Object> listGardenproduct(int gardenID);
    List<Garden> getGarden(int userID);
    List<Product> getGardenproduct(int gardenID, int userID);
    void saveGardenproduct(GardenProduct gardenProduct);
    void gardenupdate(int userID,int ada,int area,int parsel,String gardenName,String city,String town,String district,int gardenID);
    void updategarden(Garden garden);
    void deletegarden(int gardenID,int userID);

}
