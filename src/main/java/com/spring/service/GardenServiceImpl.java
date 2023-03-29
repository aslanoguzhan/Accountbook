package com.spring.service;

import com.spring.dao.GardenDAO;
import com.spring.model.Garden;
import com.spring.model.GardenProduct;
import com.spring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oguzhanaslan on 24.09.2020.
 */
@Service
@Transactional
public class GardenServiceImpl implements GardenService {

    @Autowired
    private GardenDAO gardenDAO;


    @Override
    public void saveGarden(Garden garden) {
        gardenDAO.saveGarden(garden);
    }

    @Override
    public List<Object> findAllgarden(int userID) {
        return gardenDAO.findAllgarden(userID);
    }

    @Override
    public List<Object> listGardencost(int gardenID) {
        return gardenDAO.listGardencost(gardenID);
    }

    @Override
    public List<Object> listGardenproduct(int gardenID) {
        return gardenDAO.listGardenproduct(gardenID);
    }

    @Override
    public List<Garden> getGarden(int userID) {
        return gardenDAO.getGarden(userID);
    }

    @Override
    public List<Product> getGardenproduct(int gardenID, int userID) {
        return gardenDAO.getGardenproduct(gardenID, userID);
    }

    @Override
    public void saveGardenproduct(GardenProduct gardenProduct) {
        gardenDAO.saveGardenproduct(gardenProduct);

    }
    @Override
    public void gardenupdate(int userID,int ada, int area, int parsel, String gardenName, String city, String town, String district, int gardenID) {
            gardenDAO.gardenupdate(userID,ada, area, parsel, gardenName, city, town, district, gardenID);
    }

    @Override
    public void updategarden(Garden garden) {
       gardenDAO.updategarden(garden);
    }

    @Override
    public void deletegarden(int gardenID, int userID) {
        gardenDAO.deletegarden(gardenID,userID);
    }

}
