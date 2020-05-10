package com.spring.service;

import com.spring.model.AppUser;
import com.sun.corba.se.impl.oa.poa.AOMEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by egulocak on 8.04.2020.
 */
public interface UserService {
    String checkUserType(AppUser user);
    AppUser insertUser(AppUser user);
    List<Object> listAllUsers();
    AppUser updateUser(AppUser user);
    Boolean isUserExist(String email);
    boolean checkStandardCredentials(String userEmail,String password);
    AppUser findUserByEmail(String userEmail);
    Boolean checkUserCode(String email,long code);
    AppUser updateUserStatus(String email);
    Boolean isUserActive(String email);


    //....
    //....
    //....
    //....

}