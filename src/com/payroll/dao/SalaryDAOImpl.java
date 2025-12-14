package com.payroll.dao;

import com.payroll.config.DBConnection;
import com.payroll.model.Salary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SalaryDAOImpl
 * --------------
 * Handles salary table operations
 */
public class SalaryDAOImpl implements SalaryDAO {

    // =========================
    // ADD SALARY
    // =========================
    @Override
    public void addSalary(Salary salary) {

        String sql = "INSERT INTO salary " +
                "(employee_id, hra, da, tax, net_salary) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, salary.getEmployeeId());
            ps.setDouble(2, salary.getHra());
            ps.setDouble(3, salary.getDa());
            ps.setDouble(4, salary.getTax());
            ps.setDouble(5, salary.getNetSalary());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // GET ALL SALARIES
    // =========================
    @Override
    public List<Salary> getAllSalaries() {

        List<Salary> list = new ArrayList<>();
        String sql = "SELECT * FROM salary";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Salary s = new Salary(
                        rs.getInt("employee_id"),
                        rs.getDouble("hra"),
                        rs.getDouble("da"),
                        rs.getDouble("tax"),
                        rs.getDouble("net_salary")
                );
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
