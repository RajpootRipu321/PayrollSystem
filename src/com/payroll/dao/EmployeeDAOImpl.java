package com.payroll.dao;

import com.payroll.config.DBConnection;
import com.payroll.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public void addEmployee(Employee emp) {
        try (Connection con = DBConnection.getConnection()) {

            String sql =
                    "INSERT INTO employees (first_name, last_name, department, position, base_salary) " +
                            "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getDepartment());
            ps.setString(4, emp.getPosition());
            ps.setDouble(5, emp.getBaseSalary());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM employees";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("department"),
                        rs.getString("position"),
                        rs.getDouble("base_salary")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void updateEmployee(Employee emp) {

        try (Connection con = DBConnection.getConnection()) {

            String sql =
                    "UPDATE employees SET first_name=?, last_name=?, department=?, position=?, base_salary=? " +
                            "WHERE employee_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getDepartment());
            ps.setString(4, emp.getPosition());
            ps.setDouble(5, emp.getBaseSalary());
            ps.setInt(6, emp.getEmployeeId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "DELETE FROM employees WHERE employee_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
