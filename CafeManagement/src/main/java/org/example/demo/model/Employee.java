package org.example.demo.model;

import java.io.Serializable;

public class Employee implements Serializable {

    private int employeeId;
    private String fullName;
    private String gender;
    private String phone;
    private String address;
    private String position;
    private double salary;

    public Employee() {
    }

    public Employee(int employeeId,
                    String fullName,
                    String gender,
                    String phone,
                    String address,
                    String position,
                    double salary) {

        this.employeeId = employeeId;
        this.fullName = fullName;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.position = position;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}