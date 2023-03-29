package com.spring.dao;

import com.spring.model.Cost;

import java.util.List;

/**
 * Created by oguzhanaslan on 4.09.2020.
 */
public interface CostDAO {
    void saveCost(Cost cost);
    void Delete(long id);
    List<Object> findAlltotalcost(int userID);
    List<Object> getCategorycost(int productID, int gardenID);
    List<Object> getMachineCost(int productID, int gardenID);
    List<Object> getFuelCost(int productID, int gardenID);
    List<Object> getSeedCost(int productID, int gardenID);
    List<Object> getChemicalfertilizersCost(int productID, int gardenID);
    List<Object> getOrganicfertilizersCost(int productID, int gardenID);
    List<Object> getİrrigationCost(int productID, int gardenID);
    List<Object> getLaborforceCost(int productID, int gardenID);
    List<Object> getPresticideCost(int productID, int gardenID);
    List<Object> getMauntCost(int productID, int gardenID);


}
