
package model;

public class ReturnTransaction {

    private int transactionId;
    private Member member;
    private Book book;
    private int lateDays;
    private double fine;

    public ReturnTransaction(int transactionId, Member member,
            Book book, int lateDays) {

        this.transactionId = transactionId;
        this.member = member;
        this.book = book;
        this.lateDays = lateDays;
        this.fine = lateDays * 10;
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

    public int getLateDays() {
        return lateDays;
    }

    public double getFine() {
        return fine;
    }

    public void displayReceipt() {

        System.out.println("\n========== RETURN RECEIPT ==========");
        System.out.println("Transaction ID : " + transactionId);
        System.out.println("Member         : " + member.getMemberName());
        System.out.println("Book           : " + book.getTitle());
        System.out.println("Late Days      : " + lateDays);
        System.out.println("Fine           : ₹" + fine);
        System.out.println("Status         : Book Returned Successfully");
        System.out.println("Available Stock: " + book.getQuantity());
        System.out.println("====================================");
    }
}