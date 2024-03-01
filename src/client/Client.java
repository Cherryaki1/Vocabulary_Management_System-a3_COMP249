//-------------------------------------------------------------------------------------
// Assignment 1
// Part 2
// Written by: Botao Yang (40213554) and Valerie Nguyen (40284261)
//
// This program lets library employees manage all the items in the
// library (book, journal, and media), clients, and leased/returned items from clients. 
//-------------------------------------------------------------------------------------

package client;

import library.*;

public class Client {
    private String name;
    private String id;
    private String email;
    private String phone;
    private Item[] leasedItems;

    // Default constructor
    public Client() {
        this.name = "";
        this.email = "";
        this.phone = "";
        this.id = "C" + phone;
        this.leasedItems = null;
    }

    // Parameterized constructor
    public Client(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = "C" + phone;
        this.leasedItems = new Item[0];
    }

    // Copy constructor
    public Client(Client client) {
        this.name = client.name;
        this.email = client.email;
        this.phone = client.phone;
        this.id = client.id;
        this.leasedItems = client.leasedItems;
    }

    // Getters and Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Item[] getLeasedItems() {
        return this.leasedItems;
    }
    
    public void addLeasedItem(Item item) {
        // Create a deep copy of leasedItems
        Item[] copyLeasedItems = new Item[this.leasedItems.length+1];
        for (int i = 0; i < leasedItems.length; i++) {
            // Storing the elements from leasedItems to copyLeasedItems
            copyLeasedItems[i] = this.leasedItems[i];
        }
        this.leasedItems = copyLeasedItems;
        this.leasedItems[this.leasedItems.length - 1] = item;
    }

    public void removeLeasedItem(int index) {
        // Create a new array that has one less element than the original array
        Item[] copyLeasedItem = new Item[this.leasedItems.length - 1];
        for (int i = 0, j = 0; i < this.leasedItems.length; i++) {
            // If i isn't equal to index, add element from leasedItems to copyLeasedItems
            // and increment j
            if (i != index) {
                copyLeasedItem[j++] = this.leasedItems[i];
            }
        }
        this.leasedItems = copyLeasedItem;
    }

    public String displayLeasedItems() {
        String leasedList = "";
        for (int i = 0; i < this.leasedItems.length; i++) {
            leasedList += this.leasedItems[i] + "\n";
        }
        return leasedList;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\n"
                + "ID: " + this.id + "\n"
                + "Email: " + this.email + "\n"
                + "Phone number: " + this.phone;
    }


    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        }

        if (this.getClass() != otherObject.getClass()) {
            return false;
        }

        Client otherClient = (Client) otherObject;
        if ((this.name.equals(otherClient.name)) && (this.email.equals(otherClient.email))
                && (this.phone.equals(otherClient.phone))) {
            return true;
        } else
            return false;
    }

}
