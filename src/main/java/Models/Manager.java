package Models;

public class Manager extends Cashier {
    public Manager(int id, String name,  String type, BookshopServices bookshopServices) {
        super(id, name, type, bookshopServices);
    }

    public Manager(int id, String name,  String type) {
        super(id, name, type);
    }

    public void addBook(int bookId,String title,String author,double price, int quantity, String category) {
        bookshopServices.addBook(bookId, title, author, price, quantity, category);
    }

    public void addBookCategory(BookCategory bookCategory) {
        bookshopServices.addCategory(bookCategory);
    }

    public void addAccount(int id,String name,String type){
        bookshopServices.createAccount(id, name, type);
    }
}

