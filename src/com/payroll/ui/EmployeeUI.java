package com.payroll.ui;

import com.payroll.dao.EmployeeDAO;
import com.payroll.dao.EmployeeDAOImpl;
import com.payroll.model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmployeeUI extends JFrame {

    // ---------- FORM FIELDS ----------
    private JTextField txtFirstName, txtLastName, txtDept, txtPosition, txtSalary;

    // ---------- TABLE ----------
    private JTable table;
    private DefaultTableModel tableModel;

    // ---------- DAO ----------
    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    // ---------- SELECTED EMPLOYEE ----------
    private int selectedEmployeeId = -1;

    public EmployeeUI() {

        setTitle("Employee Management - Payroll System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ================= FORM PANEL =================
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

        // ----------- INPUT ROWS -----------
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

        // ----------- BUTTONS -----------
        JButton btnAdd    = new JButton("Add Employee");
        JButton btnUpdate = new JButton("Update Employee");
        JButton btnClear  = new JButton("Clear");
        JButton btnDelete = new JButton("Delete Employee");

        // DELETE (Full Width – Top Priority)
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 2;
        formPanel.add(btnDelete, gbc);
        gbc.gridwidth = 1; // reset

        // ADD + UPDATE (Same Row)
        gbc.gridx = 0;
        gbc.gridy = ++row;
        formPanel.add(btnAdd, gbc);

        gbc.gridx = 1;
        formPanel.add(btnUpdate, gbc);

        // CLEAR (Centered & Separate)
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 2;
        formPanel.add(btnClear, gbc);
        gbc.gridwidth = 1;

        add(formPanel, BorderLayout.NORTH);

        // ================= TABLE =================
        tableModel = new DefaultTableModel(
                new String[]{"ID", "First Name", "Last Name", "Department", "Position", "Salary"}, 0
        );

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ================= TABLE CLICK =================
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

        // ================= BUTTON ACTIONS =================
        btnAdd.addActionListener(e -> addEmployee());
        btnUpdate.addActionListener(e -> updateEmployee());
        btnClear.addActionListener(e -> clearForm());
        btnDelete.addActionListener(e -> deleteEmployee());

        loadEmployees();
    }

    // ================= ADD =================
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

    // ================= UPDATE =================
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

    // ================= DELETE =================
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

    // ================= LOAD =================
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

    // ================= CLEAR =================
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
