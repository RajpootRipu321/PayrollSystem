package com.payroll.dao;

import com.payroll.model.Salary;
import java.util.List;


public interface SalaryDAO {


    void addSalary(Salary salary);


    List<Salary> getAllSalaries();
}
