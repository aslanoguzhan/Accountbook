package com.spring.token;

import com.spring.model.Cost;

/**
 * Created by oguzhanaslan on 07.09.2020.
 */
public interface Validation {

    Boolean isvalidate(Token token);
    Boolean isValidateAction(Cost cost, String email, String password);
    Boolean isValidateRequest(String email,String token);
    String generatetoken();
}

