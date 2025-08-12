import java.io.*;
import java.util.*;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private static final String FILE_NAME = "library.txt";

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Book added!");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }

    public void issueBook(int index) {
        if (index < 0 || index >= books.size()) {
            System.out.println("Invalid book number.");
            return;
        }
        Book b = books.get(index);
        if (b.isIssued()) {
            System.out.println("Book already issued.");
        } else {
            b.issueBook();
            System.out.println("Book issued successfully!");
        }
    }

    public void returnBook(int index) {
        if (index < 0 || index >= books.size()) {
            System.out.println("Invalid book number.");
            return;
        }
        Book b = books.get(index);
        if (!b.isIssued()) {
            System.out.println("Book is not issued.");
        } else {
            b.returnBook();
            System.out.println("Book returned successfully!");
        }
    }

    public void saveLibrary() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book b : books) {
                writer.write(b.getTitle() + ";" + b.getAuthor() + ";" + b.isIssued());
                writer.newLine();
            }
            System.out.println("Library saved.");
        } catch (IOException e) {
            System.out.println("Error saving library: " + e.getMessage());
        }
    }

    public void loadLibrary() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 3);
                Book book = new Book(parts[0], parts[1]);
                if (Boolean.parseBoolean(parts[2])) {
                    book.issueBook();
                }
                books.add(book);
            }
        } catch (IOException e) {
            System.out.println("Error loading library: " + e.getMessage());
        }
    }
}
