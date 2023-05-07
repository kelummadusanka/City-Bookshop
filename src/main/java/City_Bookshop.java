import Database.ExcelDatabase;
import GUI.BookshopGUI;
import Models.Book;
import Models.BookCategory;
import Models.BookshopServices;
import Models.Manager;

import java.util.List;

public class City_Bookshop {
    public static void main(String[] args) {

        ExcelDatabase excelDatabase = new ExcelDatabase();
        BookshopServices bookshopServices = new BookshopServices(excelDatabase);

        BookshopGUI bookshopGUI = new BookshopGUI(bookshopServices);




    }
}
