package com.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by oguzhanaslan on 24.09.2020.
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table
public class Garden {



    @Id
    @GeneratedValue
    private int gardenID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private AppUser user;

    @OneToMany(mappedBy = "garden", cascade = CascadeType.ALL)
    private Set<Cost> cost;


    @OneToMany(mappedBy = "garden", cascade = CascadeType.ALL)
    private Set<GardenProduct> gardenProduct;




    @Column
    private String gardenName;

    @Column
    private int ada;

    @Column
    private int parsel;

    @Column
    private String city;

    @Column
    private String town;


    @Column
    private String district;

    @Column
    private int area;





}
