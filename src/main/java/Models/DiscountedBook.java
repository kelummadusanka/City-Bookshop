package Models;

public class DiscountedBook extends Book {
    private final double discount;
    public DiscountedBook(int id, String title, String author, double price, int quantity, String bookCategory,double discount) {
        super(id, title, author, price, quantity, bookCategory);
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        return super.getPrice()*(100-discount)/100;
    }

}
