package com.payroll.main;

import com.payroll.dao.EmployeeDAO;
import com.payroll.dao.EmployeeDAOImpl;
import com.payroll.model.Employee;

import java.util.List;

public class PayrollTest {

    public static void main(String[] args) {

        EmployeeDAO dao = new EmployeeDAOImpl();

        List<Employee> employees = dao.getAllEmployees();

        System.out.println("---- Employee List ----");
        for (Employee e : employees) {
            System.out.println(
                    e.getEmployeeId() + " | " +
                            e.getFirstName() + " | " +
                            e.getLastName() + " | " +
                            e.getDepartment() + " | " +
                            e.getPosition() + " | " +
                            e.getBaseSalary()
            );
        }
    }
}
