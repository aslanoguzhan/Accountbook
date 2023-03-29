package com.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by oguzhanaslan on 4.09.2020.
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table
public class AppUser {

    @Id
    @GeneratedValue
    private int userID;

    @Column(nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userPassword;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Garden> garden;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Cost> cost;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<GardenProduct> gardenProduct;



    @Column
    private String resetCode;






}
