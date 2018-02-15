package com.SDP.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Scores {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "iddomains")

    @Getter
    @Setter
    private Domains domain;

    @ManyToOne
    @JoinColumn(name = "idemployees")

    @Getter
    @Setter
    private Employees employee;

    @Getter
    @Setter
    private int points;

    @Getter
    @Setter
    private String remarks;

    @Getter
    @Setter
    private Date date;


}
