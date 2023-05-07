package GUI;

import Models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BookshopGUI implements ActionListener {
    private BookshopServices bookshopServices;

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel searchPanel;
    private JPanel resultsPanel;
    private JTabbedPane tabbedPane;
    private JScrollPane scrollPane;
    private JPanel allBookPanel;

    private JTable booksTable;
    private JLabel titleLabel;
    private JLabel authorLabel;
    private JLabel priceLabel;
    private JLabel categoryLabel;
    private JLabel quantityLabel;
    private JLabel bookIdLabel;
    private JLabel categoryIdLabel;
    private JLabel categoryNameLabel;
    private  JLabel categoryDescriptionLabel;

    private JTextField categoryIdField;
    private JTextField categoryNameField;
    private JTextField categoryDescriptionField;
    private JTextField quantityTextField;;
    private JTextField bookIdTextField;
    private JTextField titleTextField;
    private JTextField authorTextField;
    private JTextField priceTextField;
    private JComboBox<String> categoryComboBox;
    private JButton addButton;
    private JButton searchButton;
    private JButton clearButton;
    private JButton addCategoryButton;
    private JButton addAcountButton;
    private JTextField searchTextField;
    private JLabel searchByLabel;
    private JRadioButton nameRadioButton;
    private JRadioButton priceRadioButton;
    private JRadioButton categoryRadioButton;

    private JLabel minLabel;
    private JTextField minTextField;
    private JLabel maxLabel;
    private JTextField maxTextField;
    private JPanel minMaxPannel;


    private JTextField userIdField;
    private JTextField nameField;
    private JComboBox<String> roleComboBox;
    private JButton createAccountButton;

    private String role;

    public BookshopGUI(BookshopServices bookshopServices,String role) {
        this.bookshopServices = bookshopServices;
        this.role = role;
        createGUI();
    }

    private void createGUI() {

        mainFrame = new JFrame("Bookshop");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(900, 600));
        contentPanel = new JPanel(new BorderLayout());
        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        resultsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        resultsPanel.setPreferredSize(new Dimension(900, 10000));
        allBookPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setPreferredSize(new Dimension(900, 600));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        minMaxPannel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bookIdLabel = new JLabel("Book Id");
        quantityLabel = new JLabel("Quantity");
        titleLabel = new JLabel("Title");
        authorLabel = new JLabel("Author");
        priceLabel = new JLabel("Price");
        categoryLabel = new JLabel("Category");

        bookIdTextField = new JTextField(20);
        quantityTextField = new JTextField(20);

        titleTextField = new JTextField(20);
        authorTextField = new JTextField(20);
        priceTextField = new JTextField(20);

        categoryComboBox = new JComboBox<>();

        List<String> categories = bookshopServices.getAllCategory();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(categories.toArray(new String[0]));
        categoryComboBox.setModel(model);


        addButton = new JButton("Add Book");
        addButton.addActionListener(this);
        addAcountButton = new JButton("Add Account");
        addAcountButton.addActionListener(this);
        addCategoryButton = new JButton("Add Category");
        addCategoryButton.addActionListener(this);

        minLabel = new JLabel("Min Price:");
        minTextField = new JTextField(5);
        maxLabel = new JLabel("Max Price:");
        maxTextField = new JTextField(5);

        searchTextField = new JTextField(23);
        searchByLabel = new JLabel("Search by:");
        nameRadioButton = new JRadioButton("Name");
        nameRadioButton.setSelected(true);
        priceRadioButton = new JRadioButton("Price");
        categoryRadioButton = new JRadioButton("Category");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(nameRadioButton);
        buttonGroup.add(priceRadioButton);
        buttonGroup.add(categoryRadioButton);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        clearButton = new JButton("Clear Results");
        clearButton.addActionListener(this);

        minMaxPannel.add(minLabel);
        minMaxPannel.add(minTextField);
        minMaxPannel.add(maxLabel);
        minMaxPannel.add(maxTextField);
        minMaxPannel.setVisible(false);

        booksTable = new JTable();
        BooksTableModel bookModel = new BooksTableModel(bookshopServices.getAllBooks());
        booksTable.setModel(bookModel);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Search Books", createSearchPanel());
        tabbedPane.addTab("All Books", createAllBookPanel());
        tabbedPane.addTab("Stock Details", createStockPanel());
        tabbedPane.addTab("Add Book", createAddBookPanel());
        tabbedPane.addTab("Add Category", createAddCatergoryPanel());
        tabbedPane.addTab("Create Account", createAddAccountPanel());


        // Set the visibility of the tabs based on the user's role
        if (role.equals("Manager")) {

            tabbedPane.setEnabledAt(3, true);
            tabbedPane.setEnabledAt(4, true);
        }
        else if(role.equals("Cashier")) {

            tabbedPane.setEnabledAt(3, false);
            tabbedPane.setEnabledAt(4, false);
        }

        //mainPanel.add(searchPanel, BorderLayout.NORTH);
        titleLabel = new JLabel("Hi, " + role);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        mainFrame.setContentPane(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);

        priceRadioButton.addActionListener(this);
        nameRadioButton.addActionListener(this);
        categoryRadioButton.addActionListener(this);
    }


    private JPanel createAddCatergoryPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        categoryIdLabel = new JLabel("Category ID:");
        categoryIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(categoryIdLabel, gbc);

        categoryIdField = new JTextField(20);
        categoryIdField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(categoryIdField, gbc);

        categoryNameLabel = new JLabel("Category Name:");
        categoryNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(categoryNameLabel, gbc);

        categoryNameField = new JTextField(20);
        categoryNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(categoryNameField, gbc);

        categoryDescriptionLabel = new JLabel("Category Description:");
        categoryDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(categoryDescriptionLabel, gbc);

        categoryDescriptionField = new JTextField(20);
        categoryDescriptionField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(categoryDescriptionField, gbc);

        addCategoryButton.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(addCategoryButton, gbc);

        return inputPanel;
    }


    private JPanel createStockPanel(){
        JPanel panel = new JPanel();
        //StockDetails stockDetails = new StockDetails(bookshopServices.getAllBooks());
        panel = StockDetails.getStockDetails(bookshopServices.getAllBooks());
        return  panel;
    }

    private JPanel createAddAccountPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(userIdLabel, gbc);

        userIdField = new JTextField(20);
        userIdField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(userIdField, gbc);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(nameLabel, gbc);

        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(nameField, gbc);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(roleLabel, gbc);

        String[] roles = {"Cashier", "Manager", "Employee"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(roleComboBox, gbc);

        addAcountButton.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(addAcountButton, gbc);

        return inputPanel;
    }


    private JPanel createAllBookPanel() {
        booksTable = new JTable();
        BooksTableModel model = new BooksTableModel(bookshopServices.getAllBooks());
        booksTable.setModel(model);

        JPanel allBookPanel = new JPanel(new BorderLayout());
        allBookPanel.add(new JScrollPane(booksTable), BorderLayout.CENTER);

        return allBookPanel;
    }

    private JPanel createAddBookPanel() {
        JPanel addBookPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10); // Add a 10-pixel margin

        gbc.gridx = 0;
        gbc.gridy = 0;
        addBookPanel.add(bookIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        addBookPanel.add(bookIdTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        addBookPanel.add(titleLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        addBookPanel.add(titleTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        addBookPanel.add(authorLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        addBookPanel.add(authorTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        addBookPanel.add(priceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        addBookPanel.add(priceTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        addBookPanel.add(categoryLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        addBookPanel.add(categoryComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        addBookPanel.add(quantityLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        addBookPanel.add(quantityTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        addBookPanel.add(addButton, gbc);

        return addBookPanel;
    }

    private JPanel createSerchBar(){
        JPanel searchBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Add spacing between components
        searchBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add an empty border for padding

        // Add components to the search panel with appropriate margins
        searchBar.add(searchByLabel);
        searchBar.add(nameRadioButton);
        searchBar.add(priceRadioButton);
        searchBar.add(categoryRadioButton);
        searchBar.add(searchTextField);
        searchBar.add(minMaxPannel);
        searchBar.add(searchButton);
        searchBar.add(clearButton);

        return searchBar;
    }

    private JPanel createSearchPanel() {

        JPanel searchPanel = new JPanel(new BorderLayout());
        booksTable = new JTable();
        searchPanel.add(createSerchBar(), BorderLayout.NORTH);
        searchPanel.add(scrollPane, BorderLayout.CENTER);

        //searchPanel.add(new JScrollPane(booksTable), BorderLayout.CENTER);
        return searchPanel;
    }

    private JPanel createCardPanel(Book book) {
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setPreferredSize(new Dimension(200, 300));

        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel titleLabel = new JLabel(book.getTitle(), SwingConstants.CENTER);
        JLabel authorLabel = new JLabel(book.getAuthor(), SwingConstants.CENTER);
        JLabel priceLabel = new JLabel(String.valueOf(book.getPrice()), SwingConstants.CENTER);
        JLabel categoryLabel = new JLabel(book.getCategory().toString(), SwingConstants.CENTER);
        JLabel quantityLabel = new JLabel("Available: "+ String.valueOf(book.getQuantity()), SwingConstants.CENTER);

        JPanel labelsPanel = new JPanel(new GridLayout(5, 1));
        labelsPanel.add(titleLabel);
        labelsPanel.add(authorLabel);
        labelsPanel.add(priceLabel);
        labelsPanel.add(categoryLabel);
        labelsPanel.add(quantityLabel);

        cardPanel.add(labelsPanel, BorderLayout.CENTER);

        return cardPanel;
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {

            try {
                String title = titleTextField.getText();
                String author = authorTextField.getText();
                String category = categoryComboBox.getSelectedItem().toString();
                double price = Double.parseDouble(priceTextField.getText());
                int quantity = Integer.parseInt(quantityTextField.getText());
                int bookId = Integer.parseInt(bookIdTextField.getText());

                Book book = new Book(bookId,title, author, price,quantity, category);
                bookshopServices.addBook(book);

                JOptionPane.showMessageDialog(mainFrame, "Book added successfully.");
                clearAddBookFields();
            }
            catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(mainFrame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }




        }
        if (e.getSource() == addAcountButton) {

            try {
                String name = nameField.getText();
                int userId = Integer.parseInt(userIdField.getText());
                String role = (String) roleComboBox.getSelectedItem();

                bookshopServices.createAccount(userId,name,role);

                JOptionPane.showMessageDialog(mainFrame, "Account added successfully.");
                clearAdcountFields();
            }
            catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(mainFrame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }




        }
        if (priceRadioButton.isSelected()) {
            searchTextField.setVisible(false);
            minMaxPannel.setVisible(true);
        }
        if (categoryRadioButton.isSelected()) {
            searchTextField.setVisible(true);
            minMaxPannel.setVisible(false);
        }
        if (nameRadioButton.isSelected()) {

            searchTextField.setVisible(true);
            minMaxPannel.setVisible(false);
        }

        if (e.getSource() == searchButton) {
            String searchTerm = searchTextField.getText();

            List<Book> results = null;

            if (nameRadioButton.isSelected()) {
                results = bookshopServices.searchBooksByName(searchTerm);
            } else if (priceRadioButton.isSelected()) {
                double min = Integer.parseInt(minTextField.getText());
                double max = Integer.parseInt(maxTextField.getText());
                results = bookshopServices.searchBooksByPrice(min,max);
            } else if (categoryRadioButton.isSelected()) {
                String category = categoryComboBox.getSelectedItem().toString();
                results = bookshopServices.searchBooksByCategory(category);
            }

            if (results != null)
            {

                resultsPanel.removeAll();
                for (Book book : results) {
                    JPanel cardPanel = createCardPanel(book);
                    resultsPanel.add(cardPanel);
                }

                mainFrame.revalidate();
                mainFrame.repaint();

            }
            if(results.isEmpty()){
                JOptionPane.showMessageDialog(mainFrame, "No Results! " , "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getSource() == clearButton) {
           resultsPanel.removeAll();
            mainFrame.revalidate();
            mainFrame.repaint();
       }
        if(e.getSource()==addCategoryButton){
            try{

                int categoryId = Integer.parseInt(categoryIdField.getText());
                String categoryName = categoryNameField.getText();
                String categoryDes = categoryDescriptionField.getText();
                bookshopServices.addCategory(new BookCategory(categoryId,categoryName,categoryDes));
                JOptionPane.showMessageDialog(mainFrame, "Category added successfully.");
                clearAdcountFields();
            }
            catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(mainFrame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            }

        }

    }

    private void clearAdcountFields() {
        nameField.setText("");
        userIdField.setText("");
        roleComboBox.setSelectedIndex(0);
    }

    private void clearAddBookFields() {
        titleTextField.setText("");
        authorTextField.setText("");
        priceTextField.setText("");
        quantityTextField.setText("");
        bookIdTextField.setText("");
        categoryComboBox.setSelectedIndex(0);
    }

}


