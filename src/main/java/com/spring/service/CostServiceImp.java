package com.spring.service;

import com.spring.dao.CostDAO;
import com.spring.model.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oguzhanaslan on 4.09.2020.
 */
@Service
@Transactional
public class CostServiceImp implements CostService {

    @Autowired
    private CostDAO costDAO;

    @Override
    public void saveCost(Cost cost) {
        costDAO.saveCost(cost);
    }

    @Override
    public void Delete(long id) {
        costDAO.Delete(id);
    }

    @Override
    public List<Object> findAlltotalcost(int userID) {
        return costDAO.findAlltotalcost(userID);
    }

    @Override
    public List<Object> getCategorycost(int productID, int gardenID) {
        return costDAO.getCategorycost(productID, gardenID);
    }

    @Override
    public List<Object> getMachineCost(int productID, int gardenID) {
        return costDAO.getMachineCost(productID, gardenID);
    }

    @Override
    public List<Object> getFuelCost(int productID, int gardenID) {
        return costDAO.getFuelCost(productID, gardenID);
    }

    @Override
    public List<Object> getChemicalfertilizersCost(int productID, int gardenID) {
        return costDAO.getChemicalfertilizersCost(productID, gardenID);
    }

    @Override
    public List<Object> getOrganicfertilizersCost(int productID, int gardenID) {
        return costDAO.getOrganicfertilizersCost(productID, gardenID);
    }

    @Override
    public List<Object> getİrrigationCost(int productID, int gardenID) {
        return costDAO.getİrrigationCost(productID, gardenID);
    }

    @Override
    public List<Object> getLaborforceCost(int productID, int gardenID) {
        return costDAO.getLaborforceCost(productID, gardenID);
    }

    @Override
    public List<Object> getPresticideCost(int productID, int gardenID) {
        return costDAO.getPresticideCost(productID, gardenID);
    }

    @Override
    public List<Object> getSeedCost(int productID, int gardenID) {
        return costDAO.getSeedCost(productID, gardenID);
    }


    @Override
    public List<Object> getMauntCost(int productID, int gardenID) {
        return costDAO.getMauntCost(productID, gardenID);
    }




}