package com.SDP.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Domains {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String Name;

}
