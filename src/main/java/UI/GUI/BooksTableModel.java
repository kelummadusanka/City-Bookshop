package UI.GUI;

import Models.Book;
import Models.Interfaces.IBook;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BooksTableModel extends AbstractTableModel {
    private static final int ID_COL = 0;
    private static final int TITLE_COL = 1;
    private static final int AUTHOR_COL = 2;
    private static final int PRICE_COL = 3;
    private static final int QUANTITY_COL = 4;
    private static final int CATEGORY_COL = 5;
    private String[] columnNames = {"ID", "Title", "Author", "Price", "Quantity", "Category"};
    private List<Book> books;

    public BooksTableModel(List<Book> books) {
        this.books = books;
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        IBook book = books.get(row);
        switch (col) {
            case ID_COL:
                return book.getId();
            case TITLE_COL:
                return book.getTitle();
            case AUTHOR_COL:
                return book.getAuthor();
            case PRICE_COL:
                return book.getPrice();
            case QUANTITY_COL:
                return book.getQuantity();
            case CATEGORY_COL:
                return book.getCategory();
            default:
                return null;
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
