package com.SDP.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="functionsdomains")
public class FunctionsDomains {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    Integer id;

    @JoinColumn(name = "idfunctions")
    @OneToOne
    @Getter
    @Setter
    private Functions function;


    @JoinColumn(name = "iddomains")
    @OneToOne
    @Getter
    @Setter
    private Domains domain;


}