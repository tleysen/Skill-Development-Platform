package com.SDP.Models;

import javax.persistence.*;

public class Scores {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="iddomains")

    private Domains domain;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idusers")

    private Users user;

}
