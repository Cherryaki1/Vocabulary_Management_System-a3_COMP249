//-------------------------------------------------------------------------------------
// Assignment 1
// Part 2
// Written by: Botao Yang (40213554) and Valerie Nguyen (40284261)
//
// This program lets library employees manage all the items in the
// library (book, journal, and media), clients, and leased/returned items from clients. 
//-------------------------------------------------------------------------------------

package library;

public class Book extends Item {
    private int numberOfPages;
    private static int numberOfBooks;

    // Default constructor
    public Book() {
        super();
        this.numberOfPages = 0;
        this.id = "B" + ++Book.numberOfBooks;
    }

    // Parameterized constructor
    public Book(String name, String author, int year, int numberOfPages) {
        super(name, author, year);
        this.numberOfPages = numberOfPages;
        this.id = "B" + ++Book.numberOfBooks;
    }

    // Copy constructor
    public Book(Book book) {
        super(book);
        this.numberOfPages = book.numberOfPages;
        this.id = "B" + ++Book.numberOfBooks;
    }

    // Getters and Setters
    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        if (numberOfPages < 0) {
            this.numberOfPages = 0;
        } else
            this.numberOfPages = numberOfPages;
    }

    public static int getNumberOfBooks() {
        return Book.numberOfBooks;
    }

    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Number of pages: " + this.numberOfPages;
    }

    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        }

        if (this.getClass() != otherObject.getClass()) {
            return false;
        }

        Book otherBook = (Book) otherObject;
        if ((super.equals(otherBook)) && (this.numberOfPages == otherBook.numberOfPages)) {
            return true;
        } else {
            return false;
        }

    }

}
