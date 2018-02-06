package com.SDP.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Scores {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter @Setter private Integer id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="iddomains")

    @Getter @Setter private Domains domain;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idusers")

    @Getter @Setter private Users user;

    @Getter @Setter private int points;

    @Getter @Setter private String remarks;
}
