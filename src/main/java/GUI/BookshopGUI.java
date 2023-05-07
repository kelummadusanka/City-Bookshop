package GUI;

import Models.Book;
import Models.BookshopServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookshopGUI implements ActionListener {
    private BookshopServices bookshopServices;

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel searchPanel;
    private JPanel resultsPanel;
    private JTabbedPane tabbedPane;

    private JTable booksTable;
    private JLabel titleLabel;
    private JLabel authorLabel;
    private JLabel priceLabel;
    private JLabel categoryLabel;
    private JLabel quantityLabel;
    private JLabel bookIdLabel;
    private JTextField quantityTextField;;
    private JTextField bookIdTextField;
    private JTextField titleTextField;
    private JTextField authorTextField;
    private JTextField priceTextField;
    private JComboBox<String> categoryComboBox;
    private JButton addButton;
    private JButton searchButton;
    private JButton clearButton;
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

    public BookshopGUI(BookshopServices bookshopServices) {
        this.bookshopServices = bookshopServices;
        createGUI();
    }

    private void createGUI() {


        mainFrame = new JFrame("Bookshop");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());
        contentPanel = new JPanel(new BorderLayout());
        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        resultsPanel = new JPanel(new BorderLayout());
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
        resultsPanel.add(new JScrollPane(booksTable), BorderLayout.CENTER);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Add Book", createAddBookPanel());
        tabbedPane.addTab("Search Books", createSearchPanel());
        tabbedPane.addTab("All Books", resultsPanel);

        //mainPanel.add(searchPanel, BorderLayout.NORTH);
        contentPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        mainFrame.setContentPane(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);

        priceRadioButton.addActionListener(this);
        nameRadioButton.addActionListener(this);
        categoryRadioButton.addActionListener(this);
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


    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Add spacing between components
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add an empty border for padding

        // Add components to the search panel with appropriate margins
        searchPanel.add(searchByLabel);
        searchPanel.add(nameRadioButton);
        searchPanel.add(priceRadioButton);
        searchPanel.add(categoryRadioButton);
        searchPanel.add(searchTextField);
        searchPanel.add(minMaxPannel);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);

        JPanel searchPanel1 = new JPanel(new BorderLayout());
        booksTable = new JTable();
        searchPanel1.add(searchPanel, BorderLayout.NORTH);
        searchPanel1.add(new JScrollPane(booksTable), BorderLayout.CENTER);
        return searchPanel1;
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

                Book book = new Book(bookId,title, author, price,quantity, 5);// reset...........................................................................................
                bookshopServices.addBook(book);

                JOptionPane.showMessageDialog(mainFrame, "Book added successfully.");
                clearAddBookFields();
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
                results = bookshopServices.searchBooksByCategory(2);// reset.............................................................
            }
            if (results != null) {
                BooksTableModel model = new BooksTableModel(results);
                booksTable.setModel(model);
           } else {
                JOptionPane.showMessageDialog(mainFrame, "No results found.");
            }
        }
        else if (e.getSource() == clearButton) {
           BooksTableModel model = new BooksTableModel(bookshopServices.getAllBooks());
           booksTable.setModel(model);
       }


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


