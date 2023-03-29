package com.spring.controller;

import com.spring.model.Garden;
import com.spring.model.GardenProduct;
import com.spring.model.Product;
import com.spring.service.GardenService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by oguzhanaslan on 24.09.2020.
 */
@RestController
@RequestMapping("/garden")
public class GardenRestController {

    @Setter
    @Autowired
    private GardenService gardenService;

    @RequestMapping(value = "/getAllgarden", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> Gardenlist(@RequestParam("userID") int userID) {
        try {
            return new ResponseEntity<>(gardenService.findAllgarden(userID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/saveGarden", method = RequestMethod.POST)
    public ResponseEntity<Void> saveGarden(@RequestBody Garden garden) {
        try {
            gardenService.saveGarden(garden);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        }
    }
    @RequestMapping(value = "/gardencost", method = RequestMethod.GET)
    public ResponseEntity<Object> GardenCost(@RequestParam("gardenID") int gardenID) {
        try {
            return new ResponseEntity<>(gardenService.listGardencost(gardenID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @RequestMapping(value = "/listGardenproduct", method = RequestMethod.GET)
    public ResponseEntity<Object> Gardenproductlist(@RequestParam("gardenID") int gardenID) {
        try {
            return new ResponseEntity<>(gardenService.listGardenproduct(gardenID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @RequestMapping(value = "/gardenlist", method = RequestMethod.GET)
    public ResponseEntity<List<Garden>> getGarden(@RequestParam("userID") int userID) {
        try {
            return new ResponseEntity<List<Garden>>(gardenService.getGarden(userID), HttpStatus.OK); //
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/gardenlistproduct", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getGarden(@RequestParam("gardenID") int gardenID, @RequestParam("userID") int userID) {
        try {
            return new ResponseEntity<List<Product>>(gardenService.getGardenproduct(gardenID,userID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }




    @RequestMapping(value = "/saveGardenproduct", method = RequestMethod.POST)
    public ResponseEntity<Void> saveGarden(@RequestBody GardenProduct gardenProduct) {
        try {
            gardenService.saveGardenproduct(gardenProduct);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        }
    }

    @RequestMapping(value = "/updategarden", method = RequestMethod.POST)
    public ResponseEntity<Void> updateGarden(@RequestParam("userID") int userID,@RequestParam("ada") int ada,@RequestParam("area") int area,@RequestParam("parsel") int parsel,@RequestParam("gardenName") String gardenName,@RequestParam("city") String city,@RequestParam("town") String town,@RequestParam("district") String district,@RequestParam("gardenID") int gardenID) {
        try {
            gardenService.gardenupdate(userID,ada,area,parsel,gardenName,city,town,district,gardenID);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        }
    }

    @RequestMapping(value = "/updatemygarden", method = RequestMethod.POST)
    public ResponseEntity<Void> updategarden(@RequestBody Garden garden) {
        try {
            gardenService.updategarden(garden);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        }
    }

    @RequestMapping(value = "/deletegarden", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteGarden(@RequestParam("gardenID") int gardenID, @RequestParam("userID") int userID) {
        try {
            gardenService.deletegarden(gardenID,userID);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        }
    }
}
