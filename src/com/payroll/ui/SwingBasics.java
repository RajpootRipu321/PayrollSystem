package com.payroll.ui;

import javax.swing.*;
import java.awt.*;

public class SwingBasics {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Swing Form");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout()); // 🔥 key change

        // 🔹 FORM PANEL (fixed size)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 10, 10));
        formPanel.setPreferredSize(new Dimension(350, 120));

        JLabel lblName = new JLabel("Employee Name:");
        JTextField txtName = new JTextField();

        JButton btnSubmit = new JButton("Submit");

        formPanel.add(lblName);
        formPanel.add(txtName);
        formPanel.add(new JLabel());
        formPanel.add(btnSubmit);

        // 🔹 CENTER PANEL (keeps form centered)
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(formPanel);

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        btnSubmit.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    "Hello " + txtName.getText());
        });
    }
}
