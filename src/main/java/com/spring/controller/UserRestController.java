package com.spring.controller;

import com.spring.dao.UserDAO;
import com.spring.feedback.Error;
import com.spring.model.AppUser;
import com.spring.model.Cost;
import com.spring.service.MailService;
import com.spring.service.UserService;
import com.spring.token.Token;
import com.spring.token.Validation;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by oguzhanaslan on 7.09.2020.
 */
@RestController
@RequestMapping("/user")
public class UserRestController {
    @Setter
    @Autowired
    UserService userService;

    @Autowired
    UserDAO userDAO;


    @Autowired
    Error error;

    @Autowired
    Validation validation;

    @Autowired
    MailService mailService;


    @RequestMapping(value = "/insertuser", method = RequestMethod.POST)
    public ResponseEntity<Void> insertUser(@RequestBody AppUser user) {
        try {
            if (!userService.isUserExist(user.getUserEmail())) {


                userService.insertUser(user);

                return new ResponseEntity<>(HttpStatus.CREATED);


            } else {
                error.setCode(409);
                error.setFeedback("Bu email ile kayıtlı kullanıcı zaten var.");
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/checkstandard", method = RequestMethod.GET)
    public ResponseEntity<List<AppUser>> checkStandard(@RequestParam("email") String email, @RequestParam("password") String password)   //Kullanıcı güncelleyen endpoint

    {


        try {


            return new ResponseEntity<>(userService.checkStandardCredentials(email, password), HttpStatus.OK);


        } catch (Exception e) {

            error.setCode(405);
            error.setFeedback("Something went wrong");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }


    }

    @RequestMapping(value = "/isuserexist", method = RequestMethod.GET)
    public ResponseEntity<?> isuserexist(@RequestParam("email") String email)   //Kullanıcı ekleyen endpoint
    {
        try {

            if (userDAO.isUserExist(email)) {
                return new ResponseEntity<String>("true", HttpStatus.OK); //

            } else {
                return new ResponseEntity<String>("false", HttpStatus.UNAUTHORIZED); //

            }

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage().toString(), HttpStatus.NOT_MODIFIED); //

        }
    }

    @RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
    public ResponseEntity<Void> passwordreset(@RequestParam("email") String email) {


        try {
            if (userService.isUserExist(email)) {


                if (mailService.resetpassword(email)) {
                    return new ResponseEntity<Void>(HttpStatus.OK);
                } else
                    return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);


            } else {
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND); //

            }


        } catch (Exception e) {


            return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE); //
        }
    }

    @RequestMapping(value = "/setpassword", method = RequestMethod.GET)
    public ResponseEntity<Void> setpassword(@RequestParam("email") String email, @RequestParam("newpw") String newpw, @RequestParam("token") String token) {


        try {

            if (userService.setpassword(email, newpw, token)) {
                return new ResponseEntity<Void>(HttpStatus.OK); //

            } else
                return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE); //


        } catch (Exception e) {


            return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE); //
        }
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.GET)
    public ResponseEntity<String> changepassword(@RequestParam("email") String email, @RequestParam("password") String password,
                                                 @RequestParam("newpw") String newpw)   //Kullanıcı ekleyen endpoint
    {
        try {


            Token token = new Token(email, password);
            if (validation.isvalidate(token)) {

                String result = userService.changepassword(email, password, newpw);
                if (result.equals("ok"))
                    return new ResponseEntity<String>("OK", HttpStatus.OK);
                else {
                    return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
                }
            } else
                return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED); //


        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED); //

        }


    }


    @RequestMapping(value = "/checkusercode", method = RequestMethod.GET)
    public ResponseEntity<?> checkGoogle(@RequestParam("email") String email, @RequestParam("code") String code)   //Kullanıcı güncelleyen endpoint

    {


        if (userService.checkUserCode(email, code)) {
            System.out.println("Code: " + code + "");


            return new ResponseEntity<AppUser>(HttpStatus.OK);
        } else {
            error.setCode(204);
            error.setFeedback("Girmiş olduğunuz kod geçerli değil.");
            return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);

        }


    }

    @RequestMapping(value = "/changeusername", method = RequestMethod.GET)
    public ResponseEntity<String> changeusername(@RequestParam("email") String
                                                         email, @RequestParam("password") String password, @RequestParam("username") String
                                                         username)   //Kullanıcı ekleyen endpoint
    {


        try {
            Token myToken = new Token(email, password);
            if (validation.isvalidate(myToken)) {
                return new ResponseEntity<String>(userService.changeusername(email, username), HttpStatus.OK); //

            } else {
                return new ResponseEntity<String>(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS); //

            }
        } catch (Exception e) {


            return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE); //
        }
    }

    @RequestMapping(value = "/listcosts", method = RequestMethod.POST)
    public ResponseEntity<List<Cost>> listcosts(@RequestBody AppUser user, @RequestParam("email") String email) {
        try {
            if (userService.isUser(user))
                return new ResponseEntity<List<Cost>>(userService.getCost(email), HttpStatus.OK); //

            else if (!userService.isUser(user))
                return new ResponseEntity<List<Cost>>(userService.getCost(email), HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
            else {
                return new ResponseEntity<List<Cost>>(HttpStatus.SERVICE_UNAVAILABLE);
            }

        } catch (Exception e) {

            System.out.print(e.getMessage());

            return new ResponseEntity<List<Cost>>(HttpStatus.NOT_MODIFIED);
        }

    }


    //////user operations

    @RequestMapping(value = "/getAllmygarden", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> Gardenlist(@RequestParam("userID") int userID) {
        try {
            return new ResponseEntity<>(userService.findAllgarden(userID), HttpStatus.OK);//kullanıcını tarlalarını getiren servis
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/getAllmyproduct", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> Productlist(@RequestParam("userID") int userID) {
        try {
            return new ResponseEntity<>(userService.finaAllproduct(userID), HttpStatus.OK);//kullanıcını ürünlerini getiren servis
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/getAllmycost", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> Costlist(@RequestParam("userID") int userID) {
        try {
            return new ResponseEntity<>(userService.findAllcost(userID), HttpStatus.OK);//kullanıcını masraflarını getiren servis
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/newpassword", method = RequestMethod.POST)
    public ResponseEntity<Boolean> newpassword(@RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            return new ResponseEntity<Boolean>(userService.newpassword(email, password), HttpStatus.OK); //
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> Userİnfo(@RequestParam("userID") int userID) {
        try {
            return new ResponseEntity<>(userService.userinfo(userID), HttpStatus.OK);//kullanıcını masraflarını getiren servis
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}
