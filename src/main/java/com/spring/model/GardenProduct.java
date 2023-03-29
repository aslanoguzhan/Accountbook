package com.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by oguzhanaslan on 12.10.2020.
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table
public class GardenProduct {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "userID",nullable = false)
    private AppUser user;


    @ManyToOne
    @JoinColumn(name = "gardenID",nullable = false)
    private Garden garden;

    @ManyToOne
    @JoinColumn(name = "productID",nullable = false)
    private Product product;


    @Column
    private int totalproduction;


}
