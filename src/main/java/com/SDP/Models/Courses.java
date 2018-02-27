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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Domains getDomain() {
        return domain;
    }

    public void setDomain(Domains domain) {
        this.domain = domain;
    }
}
