package com.spring.service;

import com.spring.dao.UserDAO;
import com.spring.model.AppUser;
import com.spring.model.Cost;
import com.spring.model.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by oguzhanaslan on 7.09.2020.
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void insertUser(AppUser user) {
        userDAO.insertUser(user);
    }

    @Override
    public List<Object> listAllUsers() {
        return userDAO.listAllUsers();
    }

    @Override
    public Boolean isUserExist(String email) {
        return userDAO.isUserExist(email);
    }

    @Override
    public List<AppUser>  checkStandardCredentials(String userEmail, String password) {
        return userDAO.checkStandardCredentials(userEmail, password);
    }

    @Override
    public Boolean checkUserCode(String email, String code) {
        return userDAO.checkUserCode(email, code);
    }

    @Override
    public AppUser updateUserStatus(String email) {
        return userDAO.updateUserStatus(email);
    }

    @Override
    public Boolean isUserActive(String email) {
        return userDAO.isUserActive(email);
    }

    @Override
    public String changeusername(String email, String userName) {
        return userDAO.changeusername(email, userName);
    }

    @Override
    public Long getCostcount(String email, String password) {
        return userDAO.getCostcount(email, password);
    }

    @Override
    public String changepassword(String email, String password, String newpw) {
        return userDAO.changepassword(email, password, newpw);
    }

    @Override
    public CustomUser findUserByEmail(String userEmail) {
        return userDAO.findUserByEmail(userEmail);
    }

    @Override
    public Boolean isUser(AppUser user) {
        return userDAO.isUser(user);
    }

    @Override
    public List<Cost> getCost(String email) {
        return userDAO.getCost(email);
    }

    @Override
    public List<Object> getCategorizecosts(String email, String category) {
        return userDAO.getCategorizecosts(email, category);
    }

    @Override
    public List<Object> getcategoryinfo(String email) {
        return userDAO.getcategoryinfo(email);
    }

    @Override
    public Boolean insertpwcode(String email, String code) {
        return userDAO.insertpwcode(email,code);
    }

    @Override
    public Boolean setpassword(String email, String newpw, String token) {
        return userDAO.setpassword(email,newpw,token);
    }

    @Override
    public List<Object> findAllgarden(int userID) {
        return userDAO.findAllgarden(userID);
    }

    @Override
    public List<Object> finaAllproduct(int userID) {
        return userDAO.finaAllproduct(userID);
    }

    @Override
    public List<Object> findAllcost(int userID) {
        return userDAO.findAllcost(userID);
    }

    @Override
    public Boolean newpassword(String email, String password) {
        return userDAO.newpassword(email,password);
    }

    @Override
    public List<Object> userinfo(int userID) {
        return userDAO.userinfo(userID);
    }


}
