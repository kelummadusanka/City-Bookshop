package Models;

import java.util.List;

public interface Services {
    List<Book> getAllBooks();

    List<Book> searchBooksByName(String searchQuery);
    List<Book> searchBooksByPrice(double minPrice, double maxPrice);
    List<Book> searchBooksByCategory(String category);

    boolean createAccount(int id, String name, String type);
    boolean addBook(Book book);
    boolean addCategory(BookCategory category);


    //..........supplementary.....................
    List<String> getAllCategory();

}
