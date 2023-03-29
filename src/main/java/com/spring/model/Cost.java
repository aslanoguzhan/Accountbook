package com.spring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by oguzhanaslan on 28.08.2020.
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table
public class Cost {

    @Id
    @GeneratedValue
    private int costID;


    @ManyToOne
    @JoinColumn(name = "userID",nullable = false)
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "productID",nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "gardenID",nullable = false)
    private Garden garden;


    @Column
    private int machine;

    @Column
    private int seed;

    @Column
    private int pesticide;

    @Column
    private int chemicalfertilizers;

    @Column
    private int organicfertilizers;

    @Column
    private int irrigation;

    @Column
    private int laborforce;

    @Column
    private int fuel;


    @JsonFormat(pattern="dd-MM-yyyy")
    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date costDate;


  //  private DATE cosDate = LocalDate.now();
}