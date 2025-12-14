package com.payroll.main;

import com.payroll.dao.SalaryDAO;
import com.payroll.dao.SalaryDAOImpl;
import com.payroll.model.Salary;
import com.payroll.service.PayrollService;

public class SalaryDBTest {

    public static void main(String[] args) {

        // ✅ Use an existing employee_id from employees table
        int employeeId = 1;

        double baseSalary = 30000;

        PayrollService service = new PayrollService();

        double hra = service.calculateHRA(baseSalary);
        double da = service.calculateDA(baseSalary);
        double tax = service.calculateTax(baseSalary);
        double netSalary = service.calculateNetSalary(baseSalary);

        // Create Salary object
        Salary salary = new Salary(
                employeeId,
                hra,
                da,
                tax,
                netSalary
        );

        // 🔥 SAVE SALARY TO DATABASE
        SalaryDAO dao = new SalaryDAOImpl();
        dao.addSalary(salary);
    }
}
