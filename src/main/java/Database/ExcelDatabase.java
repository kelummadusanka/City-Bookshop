package Database;

import Models.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelDatabase {

    private Workbook workbook;
    private Sheet booksSheet;
    private Sheet accountsSheet;
    private Sheet bookCategoriesSheet;

    private String filename;

    public ExcelDatabase() {
        filename = "src/main/java/Database/ExcelDB.xlsx";


        File file = new File(filename);

        if (!file.exists()) {

            CreateExcelDatabase();
        }

    }

    public void CreateExcelDatabase() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        workbook.createSheet("books");
        workbook.createSheet("account");
        workbook.createSheet("bookCategory");

        Sheet booksSheet = workbook.getSheet("books");
        Row booksHeaderRow = booksSheet.createRow(0);
        booksHeaderRow.createCell(0).setCellValue("Book Id");
        booksHeaderRow.createCell(1).setCellValue("Title");
        booksHeaderRow.createCell(2).setCellValue("Author");
        booksHeaderRow.createCell(3).setCellValue("Price");
        booksHeaderRow.createCell(4).setCellValue("Quantity");
        booksHeaderRow.createCell(5).setCellValue("Book Category");

        Sheet accountSheet = workbook.getSheet("account");
        Row accountHeaderRow = accountSheet.createRow(0);
        accountHeaderRow.createCell(0).setCellValue("Name");
        accountHeaderRow.createCell(1).setCellValue("ID");
        accountHeaderRow.createCell(2).setCellValue("Account Type");

        Sheet bookCategorySheet = workbook.getSheet("bookCategory");
        Row bookCategoryHeaderRow = bookCategorySheet.createRow(0);
        bookCategoryHeaderRow.createCell(0).setCellValue("Category ID");
        bookCategoryHeaderRow.createCell(1).setCellValue("Name");
        bookCategoryHeaderRow.createCell(2).setCellValue("Description");

        try (FileOutputStream outputStream = new FileOutputStream(filename)) {
            workbook.write(outputStream);
            System.out.println("Excel file created successfully!");
        } catch (IOException e) {
            System.err.println("Error creating Excel file: " + e.getMessage());
        }
    }

    public boolean addBook(Book book) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            workbook = WorkbookFactory.create(fis);
            booksSheet = workbook.getSheet("books");

            int lastRowNum = booksSheet.getLastRowNum();
            int newId = lastRowNum > 0 ? (int) booksSheet.getRow(lastRowNum).getCell(0).getNumericCellValue() + 1 : 1;

            Row newRow = booksSheet.createRow(lastRowNum + 1);
            newRow.createCell(0).setCellValue(newId);
            newRow.createCell(1).setCellValue(book.getTitle());
            newRow.createCell(2).setCellValue(book.getAuthor());
            newRow.createCell(3).setCellValue(book.getPrice());
            newRow.createCell(4).setCellValue(book.getQuantity());
            newRow.createCell(5).setCellValue(book.getCategory());

            try (FileOutputStream fos = new FileOutputStream(filename)) {
                workbook.write(fos);
                System.out.println("successfully added book named "+ book.getTitle());
                return true;
            }

        } catch (IOException e) {
            System.err.println("Error writing to Excel file: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
            return false;
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filename)) {
            workbook = WorkbookFactory.create(fis);
            booksSheet = workbook.getSheet("books");

            for (int i = 1; i <= booksSheet.getLastRowNum(); i++) {
                Row row = booksSheet.getRow(i);
                if (row != null) {
                    int id = (int) row.getCell(0).getNumericCellValue();
                    String title = row.getCell(1).getStringCellValue();
                    String author = row.getCell(2).getStringCellValue();
                    double price = row.getCell(3).getNumericCellValue();
                    int quantity = (int) row.getCell(4).getNumericCellValue();
                    int categoryId = (int) row.getCell(5).getNumericCellValue();

                    Book book = new Book(id, title, author, price, quantity, categoryId);
                    books.add(book);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading from Excel file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
        }

        return books;
    }


    //---------------------------------------------------------------

    public boolean addAccount(int id, String name, String type) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            workbook = WorkbookFactory.create(fis);
            accountsSheet = workbook.getSheet("account");

            int lastRowNum = accountsSheet.getLastRowNum();
            int newId = lastRowNum > 0 ? (int) accountsSheet.getRow(lastRowNum).getCell(0).getNumericCellValue() + 1 : 1;

            Row newRow = accountsSheet.createRow(lastRowNum + 1);
            newRow.createCell(0).setCellValue(newId);
            newRow.createCell(1).setCellValue(name);
            newRow.createCell(2).setCellValue(type);

            try (FileOutputStream fos = new FileOutputStream(filename)) {
                workbook.write(fos);
                return  true;
            }

        } catch (IOException e) {
            System.err.println("Error writing to Excel file: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
            return false;
        }
    }

    //---------------------------------------------------------------


    public boolean addBookCategory(BookCategory category) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            workbook = WorkbookFactory.create(fis);
            bookCategoriesSheet = workbook.getSheet("bookCategory");

            int lastRowNum = bookCategoriesSheet.getLastRowNum();
            int newId = lastRowNum > 0 ? (int) bookCategoriesSheet.getRow(lastRowNum).getCell(0).getNumericCellValue() + 1 : 1;

            Row newRow = bookCategoriesSheet.createRow(lastRowNum + 1);
            newRow.createCell(0).setCellValue(newId);
            newRow.createCell(1).setCellValue(category.getCategoryName());
            newRow.createCell(2).setCellValue(category.getCategoryDescription());

            try (FileOutputStream fos = new FileOutputStream(filename)) {
                workbook.write(fos);
                return true;
            }

        } catch (IOException e) {
            System.err.println("Error writing to Excel file: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
            return false;
        }
    }



    public List<UserAccount> getAllAccounts() {
        List<UserAccount> accounts = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filename)) {
            workbook = WorkbookFactory.create(fis);
            accountsSheet = workbook.getSheet("accounts");

            for (int i = 1; i <= accountsSheet.getLastRowNum(); i++) {
                Row row = accountsSheet.getRow(i);
                if (row != null) {
                    int id = (int) row.getCell(0).getNumericCellValue();
                    String name = row.getCell(1).getStringCellValue();
                    String type = row.getCell(2).getStringCellValue();

                    if (type.equals("Manager")) {
                        Manager manager = new Manager(id, name, type);
                        accounts.add(manager);
                    } else if (type.equals("Cashier")) {
                        Cashier cashier = new Cashier(id, name, type);
                        accounts.add(cashier);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading from Excel file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
        }

        return accounts;
    }





    public List<BookCategory> getAllBookCategories() {
        List<BookCategory> categories = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filename)) {
            workbook = WorkbookFactory.create(fis);
            bookCategoriesSheet = workbook.getSheet("bookCategory");

            for (int i = 1; i <= bookCategoriesSheet.getLastRowNum(); i++) {
                Row row = bookCategoriesSheet.getRow(i);
                if (row != null) {
                    int id = (int) row.getCell(0).getNumericCellValue();
                    String name = row.getCell(1).getStringCellValue();
                    String description = row.getCell(2).getStringCellValue();

                    BookCategory category = new BookCategory(id, name, description);
                    categories.add(category);
                }
            }

        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
        }

        return categories;
    }





    public void updateBook(Book book) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            workbook = WorkbookFactory.create(fis);
            booksSheet = workbook.getSheet("books");

            for (int i = 1; i <= booksSheet.getLastRowNum(); i++) {
                Row row = booksSheet.getRow(i);
                int id = (int) row.getCell(0).getNumericCellValue();
                if (id == book.getId()) {
                    row.getCell(1).setCellValue(book.getTitle());
                    row.getCell(2).setCellValue(book.getAuthor());
                    row.getCell(3).setCellValue(book.getPrice());
                    row.getCell(4).setCellValue(book.getQuantity());
                    row.getCell(5).setCellValue(book.getCategory());

                    try (FileOutputStream fos = new FileOutputStream(filename)) {
                        workbook.write(fos);
                    }
                    return;
                }
            }

            System.err.println("Book with ID " + book.getId() + " not found.");

        } catch (IOException e) {
            System.err.println("Error writing to Excel file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
        }
    }

    public void updateAccount(UserAccount account) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            workbook = WorkbookFactory.create(fis);
            accountsSheet = workbook.getSheet("account");

            for (int i = 1; i <= accountsSheet.getLastRowNum(); i++) {
                Row row = accountsSheet.getRow(i);
                int id = (int) row.getCell(0).getNumericCellValue();
                if (id == account.getId()) {
                    row.getCell(1).setCellValue(account.getName());
                    row.getCell(2).setCellValue(account.getAccountType());

                    try (FileOutputStream fos = new FileOutputStream(filename)) {
                        workbook.write(fos);
                    }
                    return;
                }
            }

            System.err.println("Account with ID " + account.getId() + " not found.");

        } catch (IOException e) {
            System.err.println("Error writing to Excel file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
        }
    }

    public void updateBookCategory(BookCategory category) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            workbook = WorkbookFactory.create(fis);
            bookCategoriesSheet = workbook.getSheet("bookCategory");

            for (int i = 1; i <= bookCategoriesSheet.getLastRowNum(); i++) {
                Row row = bookCategoriesSheet.getRow(i);
                int id = (int) row.getCell(0).getNumericCellValue();
                if (id == category.getCategoryId()) {
                    row.getCell(1).setCellValue(category.getCategoryName());
                    row.getCell(2).setCellValue(category.getCategoryDescription());

                    try (FileOutputStream fos = new FileOutputStream(filename)) {
                        workbook.write(fos);
                    }
                    return;
                }
            }

            System.err.println("Category with ID " + category.getCategoryId() + " not found.");

        } catch (IOException e) {
            System.err.println("Error writing to Excel file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
        }
    }

    public void removeBook(int id) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            workbook = WorkbookFactory.create(fis);
            booksSheet = workbook.getSheet("books");

            for (int i = 1; i <= booksSheet.getLastRowNum(); i++) {
                Row row = booksSheet.getRow(i);
                int bookId = (int) row.getCell(0).getNumericCellValue();
                if (bookId == id) {
                    booksSheet.removeRow(row);

                    try (FileOutputStream fos = new FileOutputStream(filename)) {
                        workbook.write(fos);
                    }
                    return;
                }
            }

            System.err.println("Book with ID " + id + " not found.");

        } catch (IOException e) {
            System.err.println("Error writing to Excel file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
        }
    }

    public void removeAccount(int id) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            workbook = WorkbookFactory.create(fis);
            accountsSheet = workbook.getSheet("account");

            for (int i = 1; i <= accountsSheet.getLastRowNum(); i++) {
                Row row = accountsSheet.getRow(i);
                int accountId = (int) row.getCell(0).getNumericCellValue();
                if (accountId == id) {
                    accountsSheet.removeRow(row);

                    try (FileOutputStream fos = new FileOutputStream(filename)) {
                        workbook.write(fos);
                    }
                    return;
                }
            }

            System.err.println("Account with ID " + id + " not found.");

        } catch (IOException e) {
            System.err.println("Error writing to Excel file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
        }
    }

    public void removeBookCategory(int id) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            workbook = WorkbookFactory.create(fis);
            bookCategoriesSheet = workbook.getSheet("bookCategory");

            for (int i = 1; i <= bookCategoriesSheet.getLastRowNum(); i++) {
                Row row = bookCategoriesSheet.getRow(i);
                int categoryId = (int) row.getCell(0).getNumericCellValue();
                if (categoryId == id) {
                    bookCategoriesSheet.removeRow(row);

                    try (FileOutputStream fos = new FileOutputStream(filename)) {
                        workbook.write(fos);
                    }
                    return;
                }
            }

            System.err.println("Category with ID " + id + " not found.");

        } catch (IOException e) {
            System.err.println("Error writing to Excel file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
        }
    }
}