package com.SDP.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    private String username;

    private String password;

    private String email;

    private String recovery;

    private Date creation_date;
}
