import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem {

    // ----------- Book Class -----------
    static class Book {
        private String title;
        private String author;
        private boolean isIssued;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.isIssued = false;
        }

        public String getTitle() {
            return title;
        }

        public boolean isIssued() {
            return isIssued;
        }

        public void issue() {
            if (!isIssued) {
                isIssued = true;
                System.out.println(title + " has been issued.");
            } else {
                System.out.println(title + " is already issued.");
            }
        }

        public void returnBook() {
            if (isIssued) {
                isIssued = false;
                System.out.println(title + " has been returned.");
            } else {
                System.out.println(title + " was not issued.");
            }
        }
    }

    // ----------- User Class -----------
    static class User {
        private String name;
        private int userId;

        public User(String name, int userId) {
            this.name = name;
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public int getUserId() {
            return userId;
        }
    }

    // ----------- Library Class -----------
    static class Library {
        private List<Book> books;
        private List<User> users;

        public Library() {
            books = new ArrayList<>();
            users = new ArrayList<>();
        }

        public void addBook(Book book) {
            books.add(book);
            System.out.println("Book added: " + book.getTitle());
        }

        public void addUser(User user) {
            users.add(user);
            System.out.println("User added: " + user.getName());
        }

        public void issueBook(String title) {
            Book bookToIssue = findBook(title);
            if (bookToIssue != null) {
                bookToIssue.issue();
            } else {
                System.out.println(title + " does not exist in the library.");
            }
        }

        public void returnBook(String title) {
            Book bookToReturn = findBook(title);
            if (bookToReturn != null) {
                bookToReturn.returnBook();
            } else {
                System.out.println(title + " does not exist in the library.");
            }
        }

        private Book findBook(String title) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    return book;
                }
            }
            return null;
        }
    }

    // ----------- Main Method (Menu-Driven) -----------
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author name: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(title, author));
                    break;

                case 2:
                    System.out.print("Enter user name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter user ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    library.addUser(new User(name, userId));
                    break;

                case 3:
                    System.out.print("Enter book title to issue: ");
                    String issueTitle = sc.nextLine();
                    library.issueBook(issueTitle);
                    break;

                case 4:
                    System.out.print("Enter book title to return: ");
                    String returnTitle = sc.nextLine();
                    library.returnBook(returnTitle);
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
