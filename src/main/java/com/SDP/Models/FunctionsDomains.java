package com.SDP.Models;

import javax.persistence.*;

@Entity
@Table(name="functionsdomains")
public class FunctionsDomains {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @JoinColumn(name = "idfunctions")
    @OneToOne
    private Functions function;


    @JoinColumn(name = "iddomains")
    @OneToOne
    private Domains domain;

    private int priority;

    public Domains getDomain() {
        return domain;
    }
}