package com.SDP.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

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

    Date completion_date;

    public Integer getId() {
        return id;
    }

    public Courses getCourse() {
        return course;
    }

    public Employees getEmployee() {
        return employee;
    }

    public Date getCompletion_date() {
        return completion_date;
    }

    @Override
    public String toString() {
        return "EmployeeCourses{" +
                "id=" + id +
                ", course=" + course +
                ", employee=" + employee +
                ", completion_date=" + completion_date +
                '}';
    }
}
