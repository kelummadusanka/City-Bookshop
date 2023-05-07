package Models;

import Models.Interfaces.IBook;

public class Book implements IBook {
    private int id;
    private String title;
    private String author;
    private double price;
    private int quantity;
    private int bookCategory;

    public Book(int id, String title, String author, double price, int quantity, int bookCategory) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.bookCategory = bookCategory;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
         this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void setCategory(int categoryId) {
        this.bookCategory = categoryId;
    }

    @Override
    public int getCategory() {
        return bookCategory;
    }
}
