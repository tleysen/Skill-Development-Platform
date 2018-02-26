package com.SDP.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Domains {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    private String name;

    private int type;

    private int priority;



    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Domains{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", priority=" + priority +
                '}';
    }
}
