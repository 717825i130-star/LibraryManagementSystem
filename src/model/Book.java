package model;

public class Book {

    private int bookId;
    private String title;
    private Author author;
    private int quantity;

    public Book(int bookId, String title, Author author, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseStock() {
        quantity++;
    }

    public void decreaseStock() {
        quantity--;
    }

    public void displayBook() {
        System.out.println("Book ID     : " + bookId);
        System.out.println("Title       : " + title);
        System.out.println("Author      : " + author.getAuthorName());
        System.out.println("Available   : " + quantity);
    }
}