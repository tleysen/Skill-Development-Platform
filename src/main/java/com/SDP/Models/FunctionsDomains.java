package com.SDP.Models;

import javax.persistence.*;

@Entity
@Table(name="functionsdomains")
public class FunctionsDomains {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @JoinColumn(name = "idfunctions")
    @OneToOne
    private Functions function;


    @JoinColumn(name = "iddomains")
    @OneToOne
    private Domains domain;

    private int priority;

    public Functions getFunction() {
        return function;
    }

    public void setFunction(Functions function) {
        this.function = function;
    }

    public void setDomain(Domains domain) {
        this.domain = domain;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Domains getDomain() {
        return domain;
    }
}