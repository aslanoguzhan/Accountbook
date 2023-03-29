package com.spring.feedback;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

/**
 * Created by oguzhanaslan on 07.09.2020.
 */
@Getter
@Setter
@Repository
public class Error {
    private String feedback;
    private int code;



}
