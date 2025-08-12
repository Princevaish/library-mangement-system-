import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Library library = new Library();
        library.loadLibrary();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Save & Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    library.addBook(title, author);
                }
                case 2 -> library.viewBooks();
                case 3 -> {
                    library.viewBooks();
                    System.out.print("Enter book number to issue: ");
                    int idx = sc.nextInt() - 1;
                    library.issueBook(idx);
                }
                case 4 -> {
                    library.viewBooks();
                    System.out.print("Enter book number to return: ");
                    int idx = sc.nextInt() - 1;
                    library.returnBook(idx);
                }
                case 5 -> library.saveLibrary();
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}
