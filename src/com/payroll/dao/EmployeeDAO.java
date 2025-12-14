package com.payroll.dao;

import com.payroll.model.Employee;
import java.util.List;

public interface EmployeeDAO {

    void addEmployee(Employee emp);

    List<Employee> getAllEmployees();

    void updateEmployee(Employee emp);

    void deleteEmployee(int employeeId);
}
