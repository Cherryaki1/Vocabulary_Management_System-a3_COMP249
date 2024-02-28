package driver;

import java.util.Scanner;

import client.Client;
import library.*;

public class Driver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Client[] clientList = new Client[0];
        System.out.print("Enter the size of the library. ");
        int size = sc.nextInt();
        Item[] library = new Item[size];

        int choice = 0;
        do {
            displayMenu();
            choice = sc.nextInt();

            // If the user does not enter a number between 1 and 7, keep prompting the user
            // until they enter a valid option
            while (choice < 1 || choice > 7) {
                System.out.print("Invalid choice. Please enter a valid number. ");
                choice = sc.nextInt();
            }

            switch (choice) {
                // Option 1
                case 1:
                    System.out.print("1. Add an item" +
                            "\n2. Delete an item" +
                            "\n3. Change information of an item" +
                            "\n4. List all items in a specific category (book, journal, or media)" +
                            "\n5. Print all items (from all categories)" +
                            "\nEnter your choice: ");

                    choice = sc.nextInt();

                    while (choice < 1 || choice > 5) {
                        System.out.print("Invalid choice. Please enter a valid number. ");
                        choice = sc.nextInt();
                    }

                    switch (choice) {
                        // Add an item
                        case 1:

                            break;

                        // Delete an item
                        case 2:

                            // If the array isn't empty
                            if (library != null) {
                                System.out.print("Enter the number of the item you would like to remove. ");
                                int index = sc.nextInt();
                                // If the user enters a number that's out of bounds of the array, keep prompting
                                // the user until they enter a valid number
                                while (index < 0 || index >= library.length) {
                                    System.out.print("Invalid item number. Please enter a valid item number.");
                                    index = sc.nextInt();
                                }

                                // Create a new array that has one less element than the original array
                                Item[] newLibrary = new Item[library.length - 1];
                                for (int i = 0, j = 0; i < library.length; i++) {
                                    // If i isn't equal to index, add element from library to newLibrary and
                                    // increment j
                                    if (i != index) {
                                        newLibrary[j++] = library[i];
                                    }
                                }
                                library = newLibrary;
                            } else { // If the array is empty, display error message
                                System.out.println("There are no items to remove.");
                            }

                            break;

                        // Change information of an item
                        case 3:

                            break;

                        // List all items in a specific category (book, journal, or media)
                        case 4:
                            System.out.print("Enter the category (book, journal, or media): ");
                            String category = sc.next();

                            /*
                             * If the category entered isn't equal to "book", "jounral", or "media", keep
                             * prompting the user until they enter a valid category
                             */
                            while (!((category.equals("book")) || (category.equals("journal"))
                                    || (category.equals("media")))) {
                                System.out.print("Invalid category. Please enter a valid category. ");
                                category = sc.next();
                            }

                            switch (category) {
                                case "book":
                                    // Go through every element in the array
                                    for (Item item : library) {
                                        // If the current item isn't null and is a Book, print information of item
                                        if (item != null && item instanceof Book) {
                                            System.out.println(item + "\n");
                                        }
                                    }

                                    break;

                                case "journal":
                                    // Go through every element in the array
                                    for (Item item : library) {
                                        // If the current item isn't null and is a Journal, print information of item
                                        if (item != null && item instanceof Journal) {
                                            System.out.println(item + "\n");
                                        }
                                    }

                                    break;

                                case "media":
                                    // Go through every element in the array
                                    for (Item item : library) {
                                        // If the current item isn't null and is a Media, print information of item
                                        if (item != null && item instanceof Media) {
                                            System.out.println(item + "\n");
                                        }
                                    }

                                    break;
                            }

                            break;

                        // Print all items (from all categories)
                        case 5:
                            // Go through every element in the array
                            for (Item item : library) {
                                // If the current item isn't null, print information of item
                                if (item != null) {
                                    System.out.println(item + "\n");
                                }
                            }

                            break;
                    }

                    break;

                // Option 2
                case 2:

                    System.out.print(
                            "1. Add a client" +
                                    "\n2. Edit a client info" +
                                    "\n3. Delete a client");

                    int clientChoice = sc.nextInt();

                    switch (clientChoice) {
                        case 1:
                            sc.nextLine();
                            System.out.print("Please provide the clients information:");
                            System.out.print("Name :");
                            String cName = sc.nextLine();
                            System.out.print("Phone number: ");
                            String cPhone = sc.nextLine();
                            System.out.print("Email: ");
                            String cEmail = sc.nextLine();

                            // Create a deep copy of client list
                            Client[] clientListDeep = new Client[clientList.length];
                            for (int i = 0; i < clientList.length; i++) {
                                // Storing the elements from books to copyBooks
                                clientListDeep[i] = clientList[i];
                            }
                            clientList = clientListDeep;
                            clientList[clientList.length - 1] = new Client(cName, cPhone, cEmail);
                            break;

                        case 3:
                            if (clientList != null) {
                                System.out.print("Enter the index of the client you would like to remove: ");
                                int index = sc.nextInt();
                                // If the user enters a number that's out of bounds of the array, keep prompting
                                // the user until they enter a valid number
                                while (index < 0 || index >= clientList.length) {
                                    System.out.print("Invalid item number. Please enter a valid item number.");
                                    index = sc.nextInt();
                                }

                                // Create a new array that has one less element than the original array
                                Client[] clientListDel = new Client[clientList.length - 1];
                                for (int i = 0, j = 0; i < clientList.length; i++) {
                                    // If i isn't equal to index, add element from library to newLibrary and
                                    // increment j
                                    if (i != index) {
                                        clientListDeep[j++] = clientList[i];
                                    }
                                }
                                clientList = clientListDel;
                            } else { // If the array is empty, display error message
                                System.out.println("There are no items to remove.");
                            }
                            break;
                    }

                    break;

                // Option 3
                case 3:

                    break;

                // Option 4
                case 4:

                    break;

                // Option 5
                case 5:

                    break;

                // Option 6
                case 6:

                    break;

                // Option 7
                case 7:

                    break;

            }

        } while (choice != 7);

        // Predefined/Hard-coded scenario

        Book b1 = new Book();
        Book b2 = new Book();
        Book b3 = new Book();

        Journal j1 = new Journal();
        Journal j2 = new Journal();
        Journal j3 = new Journal();

        Media m1 = new Media();
        Media m2 = new Media();
        Media m3 = new Media();

        Client c1 = new Client();
        Client c2 = new Client();
        Client c3 = new Client();

        Item[] predefinedLibrary = { b1, b2, b3, j1, j2, j3, m1, m2, m3 };
        Book[] books = { b1, b2, b3 };
        Journal[] journals = { j1, j2, j3 };
        Media[] medias = { m1, m2, m3 };

        // Display information of each item
        for (Item item : predefinedLibrary) {
            System.out.println(item);
        }

        // Testing the equality of the items

        // Book with the highest number of pages
        System.out.println(getBiggestBook(books));

        // Copy of books array with the array of Media
        try {
            copyBooks(medias);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid parameter.");
        }

        sc.close();

    }

    // Displays main menu
    public static void displayMenu() {
        System.out.print(
                "1. Add an item, delete an item, change information of an item, list all items in a specific category (book, journal, or media), and print all items (from all categories)"
                        +
                        "\n2. Add a client, edit a client, and delete a client" +
                        "\n3. Lease an item to a client and return an item from a client" +
                        "\n4. Show all items leased by a client" +
                        "\n5. Show all leased items (by all clients)" +
                        "\n5. Display the biggest book" +
                        "\n6. Make a copy of the books array" +
                        "\n7. Quit" +
                        "\nEnter your choice: ");
    }

    public static Book getBiggestBook(Book[] books) {
        Book biggestBook = books[0];

        for (int i = 1; i < books.length; i++) {
            // If the number of pages of the current book is higher than the ones of
            // biggestBook
            if (books[i].getNumberOfPages() > biggestBook.getNumberOfPages()) {
                // Update biggestBook with the book with the highest number of pages
                biggestBook = books[i];
            }
        }

        return biggestBook;
    }

    public static Book[] copyBooks(Item[] books) {
        // If the array is not a Book object, throw exception
        if (!(books instanceof Book[])) {
            throw new IllegalArgumentException();
        }

        // Create a new array with the same size as books
        Book[] copyBooks = new Book[books.length];
        for (int i = 0; i < books.length; i++) {
            // Storing the elements from books to copyBooks
            copyBooks[i] = (Book) books[i];
        }
        return copyBooks;

    }

}
