package com.payroll.model;

public class Salary {

    private int employeeId;
    private double hra;
    private double da;
    private double tax;
    private double netSalary;

    public Salary(int employeeId, double hra, double da, double tax, double netSalary) {
        this.employeeId = employeeId;
        this.hra = hra;
        this.da = da;
        this.tax = tax;
        this.netSalary = netSalary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double getHra() {
        return hra;
    }

    public double getDa() {
        return da;
    }

    public double getTax() {
        return tax;
    }

    public double getNetSalary() {
        return netSalary;
    }
}
