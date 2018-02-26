package com.SDP.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "iddomain")
    Domains domain;

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", domain=" + domain +
                '}';
    }

    public Integer getId() {
        return id;
    }


}
