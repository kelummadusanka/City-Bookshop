//package GUI;
//
//import Database.ExcelDatabase;
//import Models.BookshopServices;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class LoginPage extends JFrame {
//    private JButton managerButton;
//    private JButton cashierButton;
//    private JPanel loginPanel;
//
//    public LoginPage() {
//        setTitle("Login Page");
//        setSize(300, 200);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        loginPanel = new JPanel();
//        loginPanel.setLayout(new GridLayout(2, 1, 10, 10));
//
//        managerButton = new JButton("Manager");
//        managerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                openManagerDashboard();
//            }
//        });
//        loginPanel.add(managerButton);
//
//        cashierButton = new JButton("Cashier");
//        cashierButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                openCashierDashboard();
//            }
//        });
//        loginPanel.add(cashierButton);
//
//        getContentPane().add(loginPanel);
//        setVisible(true);
//    }
//
//    private void openManagerDashboard() {
//        // Code to open Manager dashboard goes here
//        System.out.println("Manager Dashboard opened.");
//        setVisible(false);
//        ExcelDatabase excelDatabase = new ExcelDatabase();
//        BookshopServices bookshopServices = new BookshopServices(excelDatabase);
//
//        BookshopGUI bookshopGUI = new BookshopGUI(bookshopServices,"Manager");
//
//    }
//
//    private void openCashierDashboard() {
//        // Code to open Cashier dashboard goes here
//        System.out.println("Cashier Dashboard opened.");
//        setVisible(false);
//        ExcelDatabase excelDatabase = new ExcelDatabase();
//        BookshopServices bookshopServices = new BookshopServices(excelDatabase);
//
//        BookshopGUI bookshopGUI = new BookshopGUI(bookshopServices, "Cashier");
//    }
//
//}


package GUI;

import Database.ExcelDatabase;
import Models.BookshopServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JButton managerButton;
    private JButton cashierButton;
    private JPanel loginPanel;
    private JLabel titleLabel;
    private JLabel imageLabel;

    public LoginPage() {
        setTitle("Bookshop Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center the frame on the screen

        // Create a panel for the login form
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add a title label to the panel
        titleLabel = new JLabel("Welcome to the Bookshop Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(titleLabel, gbc);

        // Add an image to the panel
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/login.png"));
        Image image = imageIcon.getImage().getScaledInstance(160, -1, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        imageLabel = new JLabel(imageIcon);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(imageLabel, gbc);

        // Add buttons for Manager and Cashier
        managerButton = new JButton("Manager");
        managerButton.setPreferredSize(new Dimension(150, 50));
        managerButton.setFont(new Font("Arial", Font.BOLD, 16));
        managerButton.setBackground(Color.GREEN);
        managerButton.setForeground(Color.WHITE);
        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openManagerDashboard();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(managerButton, gbc);

        cashierButton = new JButton("Cashier");
        cashierButton.setPreferredSize(new Dimension(150, 50));
        cashierButton.setFont(new Font("Arial", Font.BOLD, 16));
        cashierButton.setBackground(Color.ORANGE);
        cashierButton.setForeground(Color.WHITE);
        cashierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCashierDashboard();
            }
        });
        loginPanel.add(cashierButton);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(cashierButton, gbc);

        getContentPane().add(loginPanel);
        setVisible(true);
    }

    private void openManagerDashboard() {
        // Code to open Manager dashboard goes here
        System.out.println("Manager Dashboard opened.");
        setVisible(false);
        ExcelDatabase excelDatabase = new ExcelDatabase();
        BookshopServices bookshopServices = new BookshopServices(excelDatabase);

        BookshopGUI bookshopGUI = new BookshopGUI(bookshopServices,"Manager");

    }



    private void openCashierDashboard() {
        // Code to open Cashier dashboard goes here
        System.out.println("Cashier Dashboard opened.");
        setVisible(false);
        ExcelDatabase excelDatabase = new ExcelDatabase();
        BookshopServices bookshopServices = new BookshopServices(excelDatabase);

        BookshopGUI bookshopGUI = new BookshopGUI(bookshopServices, "Cashier");
    }
}
