package com.payroll.ui;

import com.payroll.dao.EmployeeDAO;
import com.payroll.dao.EmployeeDAOImpl;
import com.payroll.model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmployeeUI extends JFrame {


    private JTextField txtFirstName, txtLastName, txtDept, txtPosition, txtSalary;

    private JTable table;
    private DefaultTableModel tableModel;

    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    private int selectedEmployeeId = -1;

    public EmployeeUI() {

        setTitle("Employee Management - Payroll System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtFirstName = new JTextField(15);
        txtLastName  = new JTextField(15);
        txtDept      = new JTextField(15);
        txtPosition  = new JTextField(15);
        txtSalary    = new JTextField(15);

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("First Name"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtFirstName, gbc);

        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Last Name"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtLastName, gbc);

        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Department"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtDept, gbc);

        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Position"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtPosition, gbc);

        gbc.gridx = 0; gbc.gridy = ++row;
        formPanel.add(new JLabel("Base Salary"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtSalary, gbc);

        JButton btnAdd    = new JButton("Add Employee");
        JButton btnUpdate = new JButton("Update Employee");
        JButton btnClear  = new JButton("Clear");
        JButton btnDelete = new JButton("Delete Employee");


        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 2;
        formPanel.add(btnDelete, gbc);
        gbc.gridwidth = 1; // reset


        gbc.gridx = 0;
        gbc.gridy = ++row;
        formPanel.add(btnAdd, gbc);

        gbc.gridx = 1;
        formPanel.add(btnUpdate, gbc);


        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 2;
        formPanel.add(btnClear, gbc);
        gbc.gridwidth = 1;

        add(formPanel, BorderLayout.NORTH);


        tableModel = new DefaultTableModel(
                new String[]{"ID", "First Name", "Last Name", "Department", "Position", "Salary"}, 0
        );

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);


        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {

                int r = table.getSelectedRow();
                selectedEmployeeId = Integer.parseInt(tableModel.getValueAt(r, 0).toString());

                txtFirstName.setText(tableModel.getValueAt(r, 1).toString());
                txtLastName.setText(tableModel.getValueAt(r, 2).toString());
                txtDept.setText(tableModel.getValueAt(r, 3).toString());
                txtPosition.setText(tableModel.getValueAt(r, 4).toString());
                txtSalary.setText(tableModel.getValueAt(r, 5).toString());
            }
        });


        btnAdd.addActionListener(e -> addEmployee());
        btnUpdate.addActionListener(e -> updateEmployee());
        btnClear.addActionListener(e -> clearForm());
        btnDelete.addActionListener(e -> deleteEmployee());

        loadEmployees();
    }


    private void addEmployee() {
        try {
            Employee emp = new Employee(
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtDept.getText(),
                    txtPosition.getText(),
                    Double.parseDouble(txtSalary.getText())
            );

            employeeDAO.addEmployee(emp);
            JOptionPane.showMessageDialog(this, "Employee Added Successfully");

            clearForm();
            loadEmployees();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }


    private void updateEmployee() {
        if (selectedEmployeeId == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee first");
            return;
        }

        try {
            Employee emp = new Employee(
                    selectedEmployeeId,
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtDept.getText(),
                    txtPosition.getText(),
                    Double.parseDouble(txtSalary.getText())
            );

            employeeDAO.updateEmployee(emp);
            JOptionPane.showMessageDialog(this, "Employee Updated Successfully");

            clearForm();
            loadEmployees();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }


    private void deleteEmployee() {
        if (selectedEmployeeId == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee first");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this employee?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            employeeDAO.deleteEmployee(selectedEmployeeId);
            clearForm();
            loadEmployees();
        }
    }


    private void loadEmployees() {
        tableModel.setRowCount(0);
        List<Employee> list = employeeDAO.getAllEmployees();

        for (Employee e : list) {
            tableModel.addRow(new Object[]{
                    e.getEmployeeId(),
                    e.getFirstName(),
                    e.getLastName(),
                    e.getDepartment(),
                    e.getPosition(),
                    e.getBaseSalary()
            });
        }
    }


    private void clearForm() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtDept.setText("");
        txtPosition.setText("");
        txtSalary.setText("");
        selectedEmployeeId = -1;
        table.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeUI().setVisible(true));
    }
}
