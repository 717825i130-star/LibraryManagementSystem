package service;

import java.util.ArrayList;
import java.util.List;

import exception.BookNotAvailableException;
import model.Book;
import model.BorrowTransaction;
import model.Member;
import model.ReturnTransaction;

public class Library {

    private List<Book> books;
    private List<Member> members;
    private List<BorrowTransaction> borrowTransactions;
    private List<ReturnTransaction> returnTransactions;

    private int transactionId = 1001;

    public Library() {

        books = new ArrayList<>();
        members = new ArrayList<>();
        borrowTransactions = new ArrayList<>();
        returnTransactions = new ArrayList<>();
    }

    // Add a book
    public void addBook(Book book) {

        books.add(book);

        System.out.println("Book added successfully!");
    }

    // Add a member
    public void addMember(Member member) {

        members.add(member);

        System.out.println("Member added successfully!");
    }

    // Find book using book ID
    public Book findBook(int bookId) {

        for (Book book : books) {

            if (book.getBookId() == bookId) {
                return book;
            }
        }

        return null;
    }

    // Find member using member ID
    public Member findMember(int memberId) {

        for (Member member : members) {

            if (member.getMemberId() == memberId) {
                return member;
            }
        }

        return null;
    }

    // Borrow a book
    public void borrowBook(int memberId, int bookId)
            throws BookNotAvailableException {

        Member member = findMember(memberId);
        Book book = findBook(bookId);

        // Check member
        if (member == null) {
            System.out.println("Error: Member not found.");
            return;
        }

        // Check book
        if (book == null) {
            System.out.println("Error: Book not found.");
            return;
        }

        // Check stock
        if (book.getQuantity() <= 0) {

            throw new BookNotAvailableException(
                    "Book is not available in stock."
            );
        }

        // Count currently borrowed books
        int borrowedCount = 0;

        for (BorrowTransaction transaction : borrowTransactions) {

            if (transaction.getMember().getMemberId() == memberId) {
                borrowedCount++;
            }
        }

        // Check borrowing limit
        if (borrowedCount >= member.getMaxBooks()) {

            System.out.println(
                    "Error: Member has reached maximum borrowing limit."
            );

            return;
        }

        // Reduce stock
        book.decreaseStock();

        // Create transaction
        BorrowTransaction transaction =
                new BorrowTransaction(
                        transactionId++,
                        member,
                        book,
                        member.getBorrowDays()
                );

        // Store transaction
        borrowTransactions.add(transaction);

        // Display receipt
        transaction.displayReceipt();
    }

    // Return a book
    public void returnBook(int memberId, int bookId, int lateDays) {

        Member member = findMember(memberId);
        Book book = findBook(bookId);

        // Check member
        if (member == null) {

            System.out.println("Error: Member not found.");
            return;
        }

        // Check book
        if (book == null) {

            System.out.println("Error: Book not found.");
            return;
        }

        // Check late days
        if (lateDays < 0) {

            System.out.println(
                    "Error: Late days cannot be negative."
            );

            return;
        }

        // Find active borrow transaction
        BorrowTransaction borrowedTransaction = null;

        for (BorrowTransaction transaction : borrowTransactions) {

            if (transaction.getMember().getMemberId() == memberId
                    && transaction.getBook().getBookId() == bookId) {

                borrowedTransaction = transaction;
                break;
            }
        }

        // Check whether member actually borrowed the book
        if (borrowedTransaction == null) {

            System.out.println(
                    "Error: This member has not borrowed this book."
            );

            return;
        }

        // Increase stock
        book.increaseStock();

        // Remove active borrow transaction
        borrowTransactions.remove(borrowedTransaction);

        // Create return transaction
        ReturnTransaction transaction =
                new ReturnTransaction(
                        transactionId++,
                        member,
                        book,
                        lateDays
                );

        // Store return transaction
        returnTransactions.add(transaction);

        // Display receipt
        transaction.displayReceipt();
    }

    // Display available books
    public void displayAvailableBooks() {

        System.out.println("\n========== AVAILABLE BOOKS ==========");

        boolean found = false;

        for (Book book : books) {

            if (book.getQuantity() > 0) {

                System.out.println("-------------------------------------");

                book.displayBook();

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No books are currently available."
            );
        }

        System.out.println("=====================================");
    }
}