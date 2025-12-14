package com.payroll.main;

import com.payroll.service.PayrollService;

public class SalaryTest {

    public static void main(String[] args) {

        PayrollService service = new PayrollService();

        double baseSalary = 30000;

        double hra = service.calculateHRA(baseSalary);
        double da = service.calculateDA(baseSalary);
        double tax = service.calculateTax(baseSalary);
        double netSalary = service.calculateNetSalary(baseSalary);

        System.out.println("Base Salary: " + baseSalary);
        System.out.println("HRA (20%): " + hra);
        System.out.println("DA (10%): " + da);
        System.out.println("Tax (5%): " + tax);
        System.out.println("Net Salary: " + netSalary);
    }
}
