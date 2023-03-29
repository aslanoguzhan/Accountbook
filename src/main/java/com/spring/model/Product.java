package com.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by oguzhanaslan on 1.09.2020.
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue
    private int productID;

    @Column
    private String productName;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Cost> cost;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<GardenProduct> gardenProduct;






}
