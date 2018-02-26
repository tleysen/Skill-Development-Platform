package com.SDP.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String lastname;

    private Date birth_date;

    private Date hiring_date;

    private String sex;

    @ManyToOne
    @JoinColumn(name = "idfunction")
    Functions function;

    public Functions getFunction() {
        return function;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public Date getHiring_date() {
        return hiring_date;
    }

    public String getSex() {
        return sex;
    }

    public void setFunction(Functions function) {
        this.function = function;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birth_date=" + birth_date +
                ", hiring_date=" + hiring_date +
                ", sex='" + sex + '\'' +
                ", function=" + function.toString() +
                '}';
    }

    public Integer getId() {
        return id;


    }
}