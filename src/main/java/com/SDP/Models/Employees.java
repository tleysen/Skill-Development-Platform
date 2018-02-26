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
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private Date birth_date;

    @Getter
    @Setter
    private Date hiring_date;

    @Getter
    @Setter
    private String sex;

    @ManyToOne
    @JoinColumn(name = "idfunction")
    Functions function;

    public Functions getFunction() {
        return function;
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
                ", function=" + function +
                '}';
    }

    public Integer getId() {
        return id;


    }
}