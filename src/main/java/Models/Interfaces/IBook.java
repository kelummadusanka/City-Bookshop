package Models.Interfaces;

public interface IBook {
    int getId();
    void setId(int id);
    String getTitle();
    String getAuthor();
    double getPrice();
    int getQuantity();
    void setQuantity(int quantity);
    void setCategory(String categoryId);
    String getCategory();
}
