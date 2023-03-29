package com.spring.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by oguzhanaslan on 07.09.2020.
 */
@Getter
@Setter
@NoArgsConstructor
public class Token {
    String email;
    String password;




    public Token(String email, String password){
        this.email = email;
        this.password = password;

    }
}


