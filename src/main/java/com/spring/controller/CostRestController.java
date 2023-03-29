package com.spring.controller;

import com.spring.model.Cost;
import com.spring.service.CostService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by oguzhanaslan on 28.08.2020.
 */

@RestController
@RequestMapping("/cost")
public class CostRestController {

    @Setter
    @Autowired
    private CostService costService;

    @RequestMapping(value = "/saveCost", method = RequestMethod.POST)
    public ResponseEntity<Void> saveCost(@RequestBody Cost cost) {
        try {
            costService.saveCost(cost);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        }
    }


    @RequestMapping(value = "/deleteCost", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteCost(@RequestParam("costId") long costId) {


        try {
            costService.Delete(costId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        }


    }

    @RequestMapping(value = "/categorycost", method = RequestMethod.GET)
    public ResponseEntity<Object> Categorycost(@RequestParam("productID") int productID, @RequestParam("gardenID") int gardenID) {

        try {
            return new ResponseEntity<>(costService.getCategorycost(productID, gardenID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/totalcost", method = RequestMethod.GET)
    public ResponseEntity<Object> Totalcost(@RequestParam("userID") int userID) {
        try {
            return new ResponseEntity<>(costService.findAlltotalcost(userID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }




    //////// Category  Masrafları getiren metodlar
    @RequestMapping(value = "/machinecost", method = RequestMethod.GET)
    public ResponseEntity<Object> MachineCost(@RequestParam("productID") int productID, @RequestParam("gardenID") int gardenID) {
        try {
            return new ResponseEntity<>(costService.getMachineCost(productID, gardenID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/fuelcost", method = RequestMethod.GET)
    public ResponseEntity<Object> FuelCost(@RequestParam("productID") int productID, @RequestParam("gardenID") int gardenID) {
        try {
            return new ResponseEntity<>(costService.getFuelCost(productID, gardenID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @RequestMapping(value = "/seedcost", method = RequestMethod.GET)
    public ResponseEntity<Object> SeedCost(@RequestParam("productID") int productID, @RequestParam("gardenID") int gardenID) {
        try {
            return new ResponseEntity<>(costService.getSeedCost(productID, gardenID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @RequestMapping(value = "/presticidecost", method = RequestMethod.GET)
    public ResponseEntity<Object> PresticideCost(@RequestParam("productID") int productID, @RequestParam("gardenID") int gardenID) {
        try {
            return new ResponseEntity<>(costService.getPresticideCost(productID, gardenID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @RequestMapping(value = "/chemicalfertilizerscost", method = RequestMethod.GET)
    public ResponseEntity<Object> ChemicalfertilizersCost(@RequestParam("productID") int productID, @RequestParam("gardenID") int gardenID) {
        try {
            return new ResponseEntity<>(costService.getChemicalfertilizersCost(productID, gardenID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @RequestMapping(value = "/organicfertilizerscost", method = RequestMethod.GET)
    public ResponseEntity<Object> OrganicfertilizersCost(@RequestParam("productID") int productID, @RequestParam("gardenID") int gardenID) {
        try {
            return new ResponseEntity<>(costService.getOrganicfertilizersCost(productID, gardenID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @RequestMapping(value = "/irrigationcost", method = RequestMethod.GET)
    public ResponseEntity<Object> İrrigationCost(@RequestParam("productID") int productID, @RequestParam("gardenID") int gardenID) {
        try {
            return new ResponseEntity<>(costService.getİrrigationCost(productID, gardenID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @RequestMapping(value = "/laborforcecost", method = RequestMethod.GET)
    public ResponseEntity<Object> LaborforceCost(@RequestParam("productID") int productID, @RequestParam("gardenID") int gardenID) {
        try {
            return new ResponseEntity<>(costService.getLaborforceCost(productID, gardenID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    ///////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/mounhtcost", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> MountCost(@RequestParam("productID") int productID, @RequestParam("gardenID") int gardenID) {
        try {
            return new ResponseEntity<>(costService.getMauntCost(productID, gardenID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }



}

