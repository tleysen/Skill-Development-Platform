package com.SDP.Models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Scores {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "iddomains")
    private Domains domain;

    @ManyToOne
    @JoinColumn(name = "idemployees")
    private Employees employee;

    private int points;

    private String remarks;

    private Date date;

    public Integer getId() {
        return id;
    }

    public Domains getDomain() {
        return domain;
    }

    public Employees getEmployee() {
        return employee;
    }

    public int getPoints() {
        return points;
    }

    public String getRemarks() {
        return remarks;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDomain(Domains domain) {
        this.domain = domain;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Scores{" +
                "id=" + id +
                ", domain=" + domain +
                ", employee=" + employee +
                ", points=" + points +
                ", remarks='" + remarks + '\'' +
                ", date=" + date +
                '}';
    }
}
