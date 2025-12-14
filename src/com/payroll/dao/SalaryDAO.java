package com.payroll.dao;

import com.payroll.model.Salary;
import java.util.List;

/**
 * SalaryDAO
 * ---------
 * Interface for salary operations
 */
public interface SalaryDAO {

    // Save salary details
    void addSalary(Salary salary);

    // Fetch salary records
    List<Salary> getAllSalaries();
}
