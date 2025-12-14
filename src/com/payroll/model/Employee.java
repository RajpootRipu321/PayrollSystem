package com.payroll.model;

public class Employee {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String department;
    private String position;
    private double baseSalary;

    // INSERT constructor
    public Employee(String firstName, String lastName,
                    String department, String position,
                    double baseSalary) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.position = position;
        this.baseSalary = baseSalary;
    }

    // FETCH / UPDATE constructor
    public Employee(int employeeId, String firstName,
                    String lastName, String department,
                    String position, double baseSalary) {

        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.position = position;
        this.baseSalary = baseSalary;
    }

    public int getEmployeeId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }
    public double getBaseSalary() { return baseSalary; }
}
