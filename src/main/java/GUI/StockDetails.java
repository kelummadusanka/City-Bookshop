package GUI;

import Models.Book;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StockDetails extends JPanel {
    List<Book> books;

    public StockDetails(List<Book> books) {

        this.books = books;

        //Dimension size = new Dimension(900,1000);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //setPreferredSize(size);
        setBackground(Color.WHITE);

        for (Book book : books) {
            add(new BookDetailsBox(book));
            add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }

    private class BookDetailsBox extends JComponent {
        private static final int BOX_HEIGHT = 80;
        private static final int BOX_WIDTH = 500;
        private Book book;

        public BookDetailsBox(Book book) {
            this.book = book;
            setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
            setMaximumSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
            setMinimumSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            FontMetrics fm = g.getFontMetrics();

            // Draw colored box
            double percentage = (double) book.getQuantity() / 100;
            int boxWidth = (int) (percentage * (BOX_WIDTH - 40)); // subtracted 40 for the margin
            int boxX = 0;
            int boxY = (getHeight() - BOX_HEIGHT) / 2 + fm.getAscent() + 10;
            Color color = getRandomBrightColor();
            g.setColor(color);
            g.fillRect(boxX, boxY, boxWidth, BOX_HEIGHT - 20);

            // Draw book name
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 14));
            int nameWidth = fm.stringWidth(book.getTitle());
            int nameX = boxX;
            int nameY = boxY -10;
            g.drawString(book.getTitle(), nameX, nameY);

            // Draw quantity
            g.setColor(color);
            g.setFont(new Font("Arial", Font.BOLD, 12));

            String quantity = String.valueOf(book.getQuantity());
            int quantityWidth = fm.stringWidth(quantity);
            int quantityX = boxX + boxWidth + 10;
            int quantityY = boxY + (BOX_HEIGHT - 20) / 2 + fm.getAscent() / 2;
            g.drawString(quantity, quantityX, quantityY);
        }


        private Color getRandomBrightColor() {
            int red = (int) (Math.random() * 256);
            int green = (int) (Math.random() * 256);
            int blue = (int) (Math.random() * 256);

            while (red + green + blue < 300) {
                red = (int) (Math.random() * 256);
                green = (int) (Math.random() * 256);
                blue = (int) (Math.random() * 256);
            }

            return new Color(red, green, blue);
        }
    }



    public static JPanel getStockDetails(List<Book> books) {
        StockDetails stockDetails = new StockDetails(books);
        JPanel panel = new JPanel();

        JScrollPane scrollPane = new JScrollPane(stockDetails);
        scrollPane.setPreferredSize(new Dimension(900, 700)); // Set the preferred size of the JScrollPane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // set policy here
        panel.add(scrollPane);

        return panel;
    }

}
