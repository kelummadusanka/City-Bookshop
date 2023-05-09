import Database.ExcelDatabase;
import Models.BookshopServices;
import UI.GUI.GUI;
import UI.UI;

public class City_Bookshop {
    public static void main(String[] args) {
        ExcelDatabase excelDatabase = new ExcelDatabase();
        BookshopServices bookshopServices = new BookshopServices(excelDatabase);
        UI gui = new GUI(bookshopServices);
        gui.show();
    }
}

