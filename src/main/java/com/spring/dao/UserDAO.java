package com.spring.dao;

import com.spring.model.AppUser;
import com.spring.model.Cost;
import com.spring.model.CustomUser;

import java.util.List;

/**
 * Created by oguzhanaslan on 7.09.2020.
 */
public interface UserDAO {

    void insertUser(AppUser user);
    List<Object> listAllUsers();
    Boolean isUserExist(String email);
    List<AppUser>  checkStandardCredentials(String userEmail,String password);
    Boolean checkUserCode(String email,String code);
    AppUser updateUserStatus(String email);
    Boolean isUserActive(String email);
    String changeusername(String email,String userName);
    Long getCostcount(String email,String password);
    String changepassword(String email,String password,String newpw);
    boolean changeUserCode(String email, long code);
    CustomUser findUserByEmail(String email);
    Boolean isUser(AppUser user);
    List<Cost> getCost(String email);
    List<Object> getUsercosts(String email);
    List<Object> getCategorizecosts(String email,String category);
    List<Object> getcategoryinfo(String email);
    Boolean insertpwcode(String email,String code);
    Boolean setpassword(String email,String newpw,String token);
    List<Object> findAllgarden(int userID);
    List<Object> finaAllproduct(int userID);
    List<Object> findAllcost(int userID);
    Boolean newpassword(String email,String password);
    List<Object>userinfo(int userID);

}
