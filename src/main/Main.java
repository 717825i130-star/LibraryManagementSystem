package main;

import java.util.Scanner;

import exception.BookNotAvailableException;
import exception.InvalidInputException;
import model.Author;
import model.Book;
import model.Member;
import model.PremiumMember;
import service.Library;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static Library library = new Library();

    public static void main(String[] args) {

        int choice = 0;

        do {

            System.out.println("\n========================================");
            System.out.println("       LIBRARY MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Available Books");
            System.out.println("6. Exit");
            System.out.println("========================================");

            try {

                System.out.print("Enter your choice: ");

                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        addBook();
                        break;

                    case 2:
                        addMember();
                        break;

                    case 3:
                        borrowBook();
                        break;

                    case 4:
                        returnBook();
                        break;

                    case 5:
                        library.displayAvailableBooks();
                        break;

                    case 6:
                        System.out.println(
                                "Thank you for using the Library Management System."
                        );
                        break;

                    default:
                        throw new InvalidInputException(
                                "Invalid menu choice. Please enter 1 to 6."
                        );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Error: Please enter numbers only."
                );

            } catch (InvalidInputException e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );

            } catch (BookNotAvailableException e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );
            }

        } while (choice != 6);

        sc.close();
    }

    // Add Book
    static void addBook() {

        try {

            System.out.print("Enter Book ID: ");
            int id = Integer.parseInt(sc.nextLine());

            if (id <= 0) {
                throw new NumberFormatException();
            }

            System.out.print("Enter Book Title: ");
            String title = sc.nextLine();

            if (title.trim().isEmpty()) {
                throw new InvalidInputException(
                        "Book title cannot be empty."
                );
            }

            System.out.print("Enter Author ID: ");
            int authorId = Integer.parseInt(sc.nextLine());

            if (authorId <= 0) {
                throw new NumberFormatException();
            }

            System.out.print("Enter Author Name: ");
            String authorName = sc.nextLine();

            if (authorName.trim().isEmpty()) {
                throw new InvalidInputException(
                        "Author name cannot be empty."
                );
            }

            System.out.print("Enter Quantity: ");
            int quantity = Integer.parseInt(sc.nextLine());

            if (quantity <= 0) {
                throw new InvalidInputException(
                        "Quantity must be greater than zero."
                );
            }

            // Check duplicate book ID
            if (library.findBook(id) != null) {

                throw new InvalidInputException(
                        "Book ID already exists."
                );
            }

            Author author = new Author(
                    authorId,
                    authorName
            );

            Book book = new Book(
                    id,
                    title,
                    author,
                    quantity
            );

            library.addBook(book);

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error: Please enter valid positive numbers."
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    // Add Member
    static void addMember() {

        try {

            System.out.print("Enter Member ID: ");
            int id = Integer.parseInt(sc.nextLine());

            if (id <= 0) {
                throw new NumberFormatException();
            }

            System.out.print("Enter Member Name: ");
            String name = sc.nextLine();

            if (name.trim().isEmpty()) {

                throw new InvalidInputException(
                        "Member name cannot be empty."
                );
            }

            // Check duplicate member ID
            if (library.findMember(id) != null) {

                throw new InvalidInputException(
                        "Member ID already exists."
                );
            }

            System.out.println("1. Regular Member");
            System.out.println("2. Premium Member");

            System.out.print("Enter Member Type: ");
            int type = Integer.parseInt(sc.nextLine());

            Member member;

            if (type == 1) {

                member = new Member(id, name);

            } else if (type == 2) {

                member = new PremiumMember(id, name);

            } else {

                throw new InvalidInputException(
                        "Invalid member type."
                );
            }

            library.addMember(member);

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error: Please enter valid numbers."
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    // Borrow Book
    static void borrowBook()
            throws BookNotAvailableException {

        try {

            System.out.print("Enter Member ID: ");
            int memberId = Integer.parseInt(sc.nextLine());

            if (memberId <= 0) {
                throw new NumberFormatException();
            }

            System.out.print("Enter Book ID: ");
            int bookId = Integer.parseInt(sc.nextLine());

            if (bookId <= 0) {
                throw new NumberFormatException();
            }

            library.borrowBook(
                    memberId,
                    bookId
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error: Please enter valid positive numbers."
            );
        }
    }

    // Return Book
    static void returnBook() {

        try {

            System.out.print("Enter Member ID: ");
            int memberId = Integer.parseInt(sc.nextLine());

            if (memberId <= 0) {
                throw new NumberFormatException();
            }

            System.out.print("Enter Book ID: ");
            int bookId = Integer.parseInt(sc.nextLine());

            if (bookId <= 0) {
                throw new NumberFormatException();
            }

            System.out.print("Enter Late Days: ");
            int lateDays = Integer.parseInt(sc.nextLine());

            if (lateDays < 0) {

                throw new InvalidInputException(
                        "Late days cannot be negative."
                );
            }

            library.returnBook(
                    memberId,
                    bookId,
                    lateDays
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error: Please enter valid numbers."
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }
}