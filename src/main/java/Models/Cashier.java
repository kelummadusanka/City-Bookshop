package Models;

import java.util.List;

public class Cashier extends Employee {
    BookshopServices bookshopServices;
    public Cashier(int id, String name, String type, BookshopServices bookshopServices) {
        super(id,name,type);
        this.bookshopServices = bookshopServices;
    }

    public Cashier(int id, String name, String type) {
        super(id,name,type);
    }

    public List<Book> searchBooksByName(String query) {
        return bookshopServices.searchBooksByName(query);
    }

    public List<Book> searchBooksByPrice(double min, double max) {
        return bookshopServices.searchBooksByPrice(min, max);
    }

    public List<Book> searchBooksByCategory(String category) {
        return bookshopServices.searchBooksByCategory(category);
    }

    public List<Book> getAllBooks(){
        return bookshopServices.getAllBooks();
    }

    public List<String> getAllBookCategory(){
        return bookshopServices.getAllCategory();
    }
}

