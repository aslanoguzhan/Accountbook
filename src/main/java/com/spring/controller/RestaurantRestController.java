package com.spring.controller;

import com.spring.model.Restaurant;
import com.spring.model.Review;
import com.spring.model.UserRecords;
import com.spring.service.RestaurantService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantRestController {

    @Setter
    @Autowired
    private RestaurantService restaurantService;


    //add new context
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Void> createRestaurant(@RequestBody Restaurant restaurant) {

        restaurantService.Create(restaurant);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @RequestMapping(value = "/deletebyId", method = RequestMethod.GET)
    public ResponseEntity<Void> delete(@RequestParam("id") long id) {

        restaurantService.Delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    // deleteAllRestaurants
    @RequestMapping(value = "/deleteAllRestaurants", method = RequestMethod.GET)
    public ResponseEntity<Void> deleteAllRestaurants()   //Bütün restorantları silen endpoint
    {

        return null;
    }


    @RequestMapping(value = "/voteRestaurant", method = RequestMethod.POST)
    public ResponseEntity<String> voteRest(@RequestBody Review review) {

        try {
        
        if (!restaurantService.isVoteExist(review.getUser().getUserID(), review.getRestaurant().getRestaurantID())) {
            restaurantService.voteRestaurant(review);
            restaurantService.updateRestaurantReview(review.getRestaurant().getRestaurantID());
            return new ResponseEntity<>("Inserted", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Vote already exist", HttpStatus.CONFLICT);
    }
    catch(Exception e){
        return new ResponseEntity<>("Something went wrong.", HttpStatus.NOT_MODIFIED);
    }

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Object>> findRestaurantbyName(@RequestParam("name") String name,@RequestParam("page") int page)
    {
        try {
            return new ResponseEntity<>(restaurantService.findByName(name,page), HttpStatus.OK); //
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @RequestMapping(value = "/findRestaurantbyID",method = RequestMethod.GET)
    public ResponseEntity<Object> findRestaurantbyID(@RequestParam("id") long id){
        try {
            return new ResponseEntity<>(restaurantService.findById(id), HttpStatus.OK); //
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @RequestMapping(value = "/findRestaurantbyCity",method = RequestMethod.GET)
    public ResponseEntity<List<Object>> findRestaurantbyCity(@RequestParam("city") String city,@RequestParam("page") int page){
        try {
            return new ResponseEntity<>(restaurantService.findByCity(city,page), HttpStatus.OK); //
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
   @RequestMapping(value = "/findRestaurantbyTown",method = RequestMethod.GET)
    public ResponseEntity<List<Object>> findRestaurantbyLocality(@RequestParam("town") String town,@RequestParam("page") int page){
        try {
            return new ResponseEntity<>(restaurantService.findByTown(town,page), HttpStatus.OK); //
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/allRestaurants",method = RequestMethod.GET)
    public ResponseEntity<List<Object>> listAllRestaurants(@RequestParam("page") int page){
        try {
            return new ResponseEntity<>(restaurantService.findAllRestaurant(page), HttpStatus.OK); //
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/allRestaurantsAdmin",method = RequestMethod.GET)
    public ResponseEntity<List<Object>> listAllRestaurantsAdmin(@RequestParam("page") int page){
        try {
            return new ResponseEntity<>(restaurantService.findAllRestaurantAdmin(page), HttpStatus.OK); //
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/votedRestaurants",method = RequestMethod.GET)
    public ResponseEntity<List<Object>> votedRestaurants(@RequestParam("user") long id,@RequestParam("page")int page){
        try {
            return new ResponseEntity<>(restaurantService.votedRestaurantList(id,page), HttpStatus.OK); //
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @RequestMapping(value = "/detailRestaurant", method = RequestMethod.GET)
    public ResponseEntity<Object> detailRestaurant(@RequestParam("restaurant") long id){
        try {
            return new ResponseEntity<>(restaurantService.detailRestaurant(id), HttpStatus.OK); //
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @RequestMapping(value = "/detailVote", method = RequestMethod.GET)
    public ResponseEntity<Object> detailVote(@RequestParam("vote") long id){
        try {
            return new ResponseEntity<>(restaurantService.detailVote(id), HttpStatus.OK); //
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/updateVote", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Review review) {
        try {
            restaurantService.updateVote(review);
            restaurantService.updateRestaurantReview(review.getRestaurant().getRestaurantID());
            return ResponseEntity.ok().body("Vote has been updated successfully.");
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

    }

    //get userID and restaurantID service
    @RequestMapping(value = "/infoAdmin", method = RequestMethod.GET)
    public ResponseEntity<ArrayList> getInfo() {
        try {
            return new ResponseEntity<ArrayList>(restaurantService.getInfo(),HttpStatus.OK); //
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

    }
    @RequestMapping(value = "/getcategorized", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> topRated(@RequestParam("page") int page,@RequestParam("type") String type) {

        try {
            return new ResponseEntity<>(restaurantService.getTopRated(page,type), HttpStatus.OK); //
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

    }
    @RequestMapping(value = "/getRecord", method = RequestMethod.POST)
    public ResponseEntity<Void> addRecord(@RequestBody UserRecords userRecords){
        try {
            restaurantService.createRecord(userRecords);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        }
    }

    @RequestMapping(value = "/allbycategory",method = RequestMethod.GET)
    public ResponseEntity<List<Object>> listAllbyCategory(@RequestParam("category") String category,@RequestParam("page") int page){
        try {
            return new ResponseEntity<>(restaurantService.findAllbyCategory(category,page), HttpStatus.OK); //
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/city",method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getCities(){
        try {
            return new ResponseEntity<>(restaurantService.getCity(), HttpStatus.OK); //
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/town",method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getTowns(@RequestParam("cityName") String cityName){
        try {
            return new ResponseEntity<>(restaurantService.getTown(cityName), HttpStatus.OK); //
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/deleteRecord", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteRecord(@RequestParam("recordId") long recordId){
        try {
            restaurantService.deleteRecordId(recordId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        }
    }
    @RequestMapping(value = "/saveRecord", method = RequestMethod.POST)
    public ResponseEntity<Void> saveRecord(@RequestBody Restaurant restaurant){
        try {
            restaurantService.saveRecord(restaurant);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        }
    }



}



