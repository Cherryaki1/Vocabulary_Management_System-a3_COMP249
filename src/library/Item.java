//-------------------------------------------------------------------------------------
// Assignment 1
// Part 2
// Written by: Botao Yang (40213554) and Valerie Nguyen (40284261)
//
// This program lets library employees manage all the items in the
// library (book, journal, and media), clients, and leased/returned items from clients. 
//-------------------------------------------------------------------------------------

package library;

public abstract class Item {
    protected String name;
    protected String id;
    protected String author;
    protected int year;
    private static int numberOfItems;
    protected boolean leased;

    // Default constructor
    public Item() {
        this.name = "";
        this.author = "";
        this.year = 0;
        Item.numberOfItems++;
        this.leased = false;
    }

    // Parameterized constructor
    public Item(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
        Item.numberOfItems++;
        this.leased = false;
    }

    // Copy constructor
    public Item(Item item) {
        this.name = item.name;
        this.author = item.author;
        this.year = item.year;
        Item.numberOfItems++;
        this.leased = item.leased;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static int getNumberOfItems() {
        return Item.numberOfItems;
    }

    public Boolean getLeased() {
        return this.leased;
    }

    public void leaseItem() {
        this.leased = true;
    }

    public void returnItem() {
        this.leased = false;
    }

    public static void deleteItem() {
        Item.numberOfItems--;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\n"
                + "ID: " + this.id + "\n"
                + "Author: " + this.author + "\n"
                + "Year of publication: " + this.year;
    }

    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        }

        if (this.getClass() != otherObject.getClass()) {
            return false;
        }

        Item otherItem = (Item) otherObject;
        if ((this.name.equals(otherItem.name)) && (this.author.equals(otherItem.author))
                && (this.year == otherItem.year)) {
            return true;
        } else
            return false;

    }
}
