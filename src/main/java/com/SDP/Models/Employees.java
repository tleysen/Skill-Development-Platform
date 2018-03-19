package com.SDP.Models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Employees {

    public Employees() {
    }

    public Employees(String name, String lastname, Date birth_date, Date hiring_date, String sex, int level) {
        this.name = name;
        this.lastname = lastname;
        this.birth_date = birth_date;
        this.hiring_date = hiring_date;
        this.sex = sex;
        this.level = level;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String lastname;

    private Date birth_date;

    private Date hiring_date;

    private String sex;

    private int level;

    public Integer getId() { return id; }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public void setHiring_date(Date hiring_date) {
        this.hiring_date = hiring_date;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}