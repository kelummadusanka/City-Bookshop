package Models.Interfaces;

import Models.Book;
import Models.BookCategory;
import java.util.List;

public interface Services {
    List<Book> getAllBooks();
    List<Book> searchBooksByName(String searchQuery);
    List<Book> searchBooksByPrice(double minPrice, double maxPrice);
    List<Book> searchBooksByCategory(String category);
    void createAccount(int id, String name, String type);
    void addBook(int bookId, String title, String author, double price, int quantity, String category);
    void addCategory(BookCategory category);
    List<String> getAllCategory();
}
