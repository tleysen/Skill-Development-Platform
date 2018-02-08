package com.SDP.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.List;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter @Setter private Integer id;

    @Getter @Setter private String name;

    @Getter @Setter private String lastname;

    @Getter @Setter private Date birth_date;

    @Getter @Setter private Date hiring_date;

    @Getter @Setter private String sex;



}