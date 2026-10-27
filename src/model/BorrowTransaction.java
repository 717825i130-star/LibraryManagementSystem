package model;

public class BorrowTransaction {

    private int transactionId;
    private Member member;
    private Book book;
    private int borrowDays;

    public BorrowTransaction(int transactionId, Member member,
            Book book, int borrowDays) {

        this.transactionId = transactionId;
        this.member = member;
        this.book = book;
        this.borrowDays = borrowDays;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public int getBorrowDays() {
        return borrowDays;
    }

    public void displayReceipt() {

        System.out.println("\n========== BORROW RECEIPT ==========");
        System.out.println("Transaction ID : " + transactionId);
        System.out.println("Member         : " + member.getMemberName());
        System.out.println("Book           : " + book.getTitle());
        System.out.println("Borrow Period  : " + borrowDays + " days");
        System.out.println("Status         : Book Borrowed Successfully");
        System.out.println("Available Stock: " + book.getQuantity());
        System.out.println("====================================");
    }
}