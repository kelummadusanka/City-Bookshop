package Models;

import Database.ExcelDatabase;
import Models.Interfaces.Services;

import java.util.ArrayList;
import java.util.List;

public class BookshopServices implements Services {

    ExcelDatabase excelDatabase;

    public BookshopServices(ExcelDatabase excelDatabase) {
        this.excelDatabase = excelDatabase;
    }

    @Override
    public List<Book> getAllBooks() {
        return excelDatabase.getAllBooks();
    }

    @Override
    public List<Book> searchBooksByName(String searchQuery) {
        List<Book> filteredBooks = new ArrayList<>();
        List<Book> allBooks = getAllBooks();
        for (Book book : allBooks) {
            if (book.getTitle().toLowerCase().contains(searchQuery.toLowerCase())) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    @Override
    public List<Book> searchBooksByPrice(double minPrice, double maxPrice) {
        List<Book> allBooks = getAllBooks();
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getPrice() >= minPrice && book.getPrice() <= maxPrice) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    @Override
    public List<Book> searchBooksByCategory(String category) {
        List<Book> filteredBooks = new ArrayList<>();
        List<Book> allBooks = getAllBooks();
        for (Book book : allBooks) {
            if (book.getCategory().equals(category)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    @Override
    public void createAccount(int id, String name, String type) {
        List<Employee> accountsWithSameId = searchAccountById(id);
        if (!accountsWithSameId.isEmpty()) {
            throw new IllegalArgumentException("Id is already in the database!");
        } else {
            excelDatabase.addAccount(id, name, type);
        }
    }

    @Override
    public void addBook(int bookId, String title, String author, double price, int quantity, String category) {

        Book book = new Book(bookId,title, author, price,quantity, category);

        excelDatabase.addBook(book);

    }

    @Override
    public void addCategory(BookCategory bookCategory) {
        excelDatabase.addBookCategory(bookCategory);

    }

    @Override
    public List<String> getAllCategory() {
        List<String> categoryNames = new ArrayList<>();
        List<BookCategory> allBookcategories = excelDatabase.getAllBookCategories();
        for (BookCategory bookCategory: allBookcategories){
            categoryNames.add(bookCategory.getCategoryName());
        }
        return categoryNames;
    }

    private List<Employee> searchAccountById(int id){
        List<Employee> filteredEmployee = new ArrayList<>();
        List<Employee> allEmployees = excelDatabase.getAllAccounts();
        for (Employee employee : allEmployees) {
            if (employee.getId() == id) {
                filteredEmployee.add(employee);
            }
        }
        return filteredEmployee;
    }
}