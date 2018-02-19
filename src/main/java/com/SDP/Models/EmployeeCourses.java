package com.SDP.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="employeecourses")
public class EmployeeCourses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    Integer id;

    @JoinColumn(name = "idcourses")
    @OneToOne
    @Getter
    @Setter
    private Courses course;

    @JoinColumn(name = "idemployees")
    @OneToOne
    @Getter
    @Setter
    private Employees employee;

}
