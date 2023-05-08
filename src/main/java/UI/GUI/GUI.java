package UI.GUI;

import Models.BookshopServices;
import UI.UI;

public class GUI implements UI {
    private BookshopServices bookshopServices;
    public GUI(BookshopServices bookshopServices) {
        this.bookshopServices=bookshopServices;
    }

    @Override
    public void show() {
        LoginPage loginPage = new LoginPage(bookshopServices);
    }
}
