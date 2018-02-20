package com.SDP.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    Integer id;

    @Getter
    @Setter
    private String name;

    @ManyToOne
    @JoinColumn(name = "iddomain")
    @Getter
    @Setter
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
