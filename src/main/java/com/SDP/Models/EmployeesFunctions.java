package com.SDP.Models;

import javax.persistence.*;

@Entity
@Table(name="employeesfunctions")
public class EmployeesFunctions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "idemployees")
    Employees employee;

    @ManyToOne
    @JoinColumn(name = "idfunctions")
    Functions function;

    private int expboost;

    public Integer getId() {
        return id;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public Functions getFunction() {
        return function;
    }

    public void setFunction(Functions function) {
        this.function = function;
    }

    public int getExpboost() {
        return expboost;
    }

    public void setExpboost(int expboost) {
        this.expboost = expboost;
    }

    @Override
    public String toString() {
        return "EmployeesFunctions{" +
                "id=" + id +
                ", employee=" + employee +
                ", function=" + function +
                '}';
    }
}
