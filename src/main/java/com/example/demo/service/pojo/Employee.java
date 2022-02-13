package com.example.demo.service.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize()
public class Employee {
    private String employeeId;
    private String employeeName;
    private String designation;
    private int salary;

    public Employee()
    {

    }
    public Employee(String employeeId, String employeeName, String designation, int salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public int getSalary() {
        return salary;
    }
}
