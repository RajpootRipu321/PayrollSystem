package com.payroll.dao;

import com.payroll.model.Employee;
import java.util.List;

/**
 * EmployeeDAOTest
 * ----------------
 * Simple DAO test (NO UI)
 */
public class EmployeeDAOTest {

    public static void main(String[] args) {

        // Use interface reference
        EmployeeDAO dao = new EmployeeDAOImpl();

        // =========================
        // INSERT TEST
        // =========================
        dao.addEmployee(new Employee(
                "Test",
                "User",
                "IT",
                "Developer",
                25000
        ));

        // =========================
        // FETCH TEST
        // =========================
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
