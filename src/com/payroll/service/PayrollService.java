package com.payroll.service;

public class PayrollService {

    public double calculateHRA(double baseSalary) {
        return baseSalary * 0.20;
    }

    public double calculateDA(double baseSalary) {
        return baseSalary * 0.10;
    }

    public double calculateTax(double baseSalary) {
        return baseSalary * 0.05;
    }

    public double calculateNetSalary(double baseSalary) {
        double hra = calculateHRA(baseSalary);
        double da = calculateDA(baseSalary);
        double tax = calculateTax(baseSalary);
        return baseSalary + hra + da - tax;
    }
}
