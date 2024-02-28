package driver;

import java.util.Scanner;

import client.Client;
import library.*;

public class Driver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Client[] clientList = new Client[1];

        System.out.print("Would you like view the menu (1) or run a predefined scenario (2): ");
        int menuOrPredefined = sc.nextInt();

        // If the user does not enter 1 or 2, keep prompting the user
        // until they enter a valid option
        while (menuOrPredefined != 1 && menuOrPredefined != 2) {
            System.out.print("Invalid choice. Please enter a valid number: ");
            menuOrPredefined = sc.nextInt();
            System.out.println("");
        }

        // Menu
        if (menuOrPredefined == 1) {

            System.out.print("Enter the size of the library: ");
            int size = sc.nextInt();
            System.out.println("");
            Item[] library = new Item[size];

            int choice;
            do {
                displayMenu();
                choice = sc.nextInt();

                // If the user does not enter a number between 1 and 7, keep prompting the user
                // until they enter a valid option
                while (choice < 1 || choice > 7) {
                    System.out.print("Invalid choice. Please enter a valid number: ");
                    choice = sc.nextInt();
                    System.out.println("");
                }

                switch (choice) {
                    
                    //-----------------------------------------------
                    //                 ITEM MANAGER                 
                    //-----------------------------------------------

                    case 1:
                        System.out.print(
                                "-----------------------------------------------\n" +
                                "                  ITEM MANAGER                 \n" +
                                "-----------------------------------------------\n" +
                                "Please select the operation you would like to do from the following list " +
                                "\n1. Add an item" +
                                "\n2. Delete an item" +
                                "\n3. Change information of an item" +
                                "\n4. List all items in a specific category (book, journal, or media)" +
                                "\n5. Print all items (from all categories)" +
                                "\nEnter your choice: ");

                        choice = sc.nextInt();
                        System.out.println("");

                        while (choice < 1 || choice > 5) {
                            System.out.print("Invalid choice. Please enter a valid number. ");
                            choice = sc.nextInt();
                            System.out.println("");
                        }

                        switch (choice) {
                            // Add an item
                            case 1:

                                // If there's enough space in the library
                                if (Item.getNumberOfItems() < library.length) {
                                    sc.nextLine();
                                    System.out.println("Please provide the information of the item");
                                    System.out.print("Name : ");
                                    String name = sc.nextLine();
                                    System.out.print("Author: ");
                                    String author = sc.nextLine();
                                    System.out.print("Year: ");
                                    int year = sc.nextInt();
                                    System.out.print("Type of item: ");
                                    String itemType = sc.next();

                                    if (itemType.equalsIgnoreCase("Book")) {
                                        System.out.print("Number of pages: ");
                                        int numberOfPages = sc.nextInt();
                                        // Creating a new book and adding it to the library
                                        library[Item.getNumberOfItems() + 1] = new Book(name, author, year,
                                                numberOfPages);
                                    } else if (itemType.equalsIgnoreCase("Journal")) {
                                        System.out.print("Volume number: ");
                                        int volumeNumber = sc.nextInt();
                                        // Creating a new journal and adding it to the library
                                        library[Item.getNumberOfItems() + 1] = new Journal(name, author, year,
                                                volumeNumber);
                                    } else if (itemType.equalsIgnoreCase("Media")) {
                                        System.out.print("Type: ");
                                        String type = sc.next();
                                        // Creating a new media and adding it to the library
                                        library[Item.getNumberOfItems() + 1] = new Media(name, author, year, type);
                                    }

                                } else {
                                    System.out.println("There isn't enough space in the library.");
                                }
                                System.out.println("");

                                break;

                            // Delete an item
                            case 2:

                                // If the array isn't empty
                                if (library != null) {
                                    System.out.print("Enter the index of the item you would like to remove: ");
                                    int index = sc.nextInt();
                                    // If the user enters a number that's out of bounds of the array or if there's
                                    // no item at the index, keep prompting the user until they enter a valid number
                                    while (index < 0 || index >= library.length || library[index] == null) {
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

                                // If the library isn't empty
                                if (library != null) {
                                    System.out.print("Enter the number of the item that you would like to edit: ");
                                    int index = sc.nextInt();

                                    // If the user enters a number that's out of bounds of the array or if there's
                                    // no item at the index, keep prompting the user until they enter a valid number
                                    while (index < 0 || index >= library.length || library[index] == null) {
                                        System.out.print("Invalid item number. Please enter a valid item number.");
                                        index = sc.nextInt();
                                    }

                                    System.out.print("Which information would you like to modify?" +
                                            "\n1. Name" +
                                            "\n2. Author" +
                                            "\n3. Year" +
                                            "\n4. Number of pages (Book)" +
                                            "\n5. Volume number (Journal)" +
                                            "\n6. Type (Media)");

                                    int information = sc.nextInt();
                                    switch (information) {
                                        case 1:
                                            sc.nextLine();
                                            System.out.print("Name: ");
                                            String name = sc.next();
                                            library[index].setName(name);
                                            break;

                                        case 2:
                                            sc.nextLine();
                                            System.out.print("Author: ");
                                            String author = sc.next();
                                            library[index].setAuthor(author);
                                            break;

                                        case 3:
                                            System.out.print("Year: ");
                                            int year = sc.nextInt();
                                            library[index].setYear(year);
                                            break;

                                        case 4:
                                            // If the item the user is trying to modify is a book
                                            if (library[index] instanceof Book) {
                                                System.out.print("Number of pages: ");
                                                int pages = sc.nextInt();
                                                ((Book) library[index]).setNumberOfPages(pages);
                                            } else {
                                                System.out.println("You can't modify this attribute.");
                                            }
                                            break;

                                        case 5:
                                            // If the item the user is trying to modify is a journal
                                            if (library[index] instanceof Journal) {
                                                System.out.print("Volume Number: ");
                                                int volumeNumber = sc.nextInt();
                                                ((Journal) library[index]).setVolumeNumber(volumeNumber);
                                            } else {
                                                System.out.println("You can't modify this attribute.");
                                            }
                                            break;

                                        case 6:
                                            sc.nextLine();
                                            // If the item the user is trying to modify is a media
                                            if (library[index] instanceof Media) {
                                                System.out.print("Type: ");
                                                String type = sc.next();
                                                ((Media) library[index]).setType(type);
                                            } else {
                                                System.out.println("You can't modify this attribute.");
                                            }
                                            break;
                                    }

                                } else {
                                    System.out.println("The library is empty.");
                                }

                                break;

                            // List all items in a specific category (book, journal, or media)
                            case 4:
                                System.out.print("Enter the category: book (1), journal (2), or media (3): ");
                                int category = sc.nextInt();

                                // If the user enters a number that isn't between 1 and 3,
                                // keep prompting the user until they enter a valid number
                                while (category < 1 || category > 3) {
                                    System.out.print("Invalid category. Please enter a valid category. ");
                                    category = sc.nextInt();
                                }

                                switch (category) {
                                    case 1:
                                        // Go through every element in the array
                                        for (Item item : library) {
                                            // If the current item isn't null and is a Book, print information of item
                                            if (item != null && item instanceof Book) {
                                                System.out.println(item + "\n");
                                            }
                                        }

                                        break;

                                    case 2:
                                        // Go through every element in the array
                                        for (Item item : library) {
                                            // If the current item isn't null and is a Journal, print information of
                                            // item
                                            if (item != null && item instanceof Journal) {
                                                System.out.println(item + "\n");
                                            }
                                        }

                                        break;

                                    case 3:
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

                    //-----------------------------------------------
                    //                CLIENT MANAGER                 
                    //-----------------------------------------------
                    case 2:
                        System.out.println("");
                        System.out.print(
                                "-----------------------------------------------\n" +
                                "                 CLIENT MANAGER                \n" +
                                "-----------------------------------------------\n" +
                                "Please select the operation you would like to do from the following list " +
                                "\n1. Add a client" +
                                "\n2. Edit a client info" +
                                "\n3. Delete a client" +
                                "\nEnter your choice: ");

                        int clientChoice = sc.nextInt();
                        System.out.println("");
                        switch (clientChoice) {
                            case 1:
                                sc.nextLine();
                                System.out.println("Please provide the client's information:");
                                System.out.print("Name: ");
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
                                System.out.println("Client successfully added!\n");
                                break;

                            case 2:
                                sc.nextLine();
                                System.out.print("Please input the index of the client you would like to edit: ");
                                int indexEdit = sc.nextInt();
                                System.out.print(
                                        "\n-----------------------------------------------\n" +
                                        "               EDITING CLIENT " + indexEdit + "                \n" +
                                        "-----------------------------------------------\n" +
                                        "Please select which information you would like to edit" +
                                        "\n1. Name" +
                                        "\n2. Phone number" +
                                        "\n3. Email" +
                                        "\nEnter your choice: ");
                                int editChoice = sc.nextInt();

                                switch (editChoice) {
                                    case 1:
                                        sc.nextLine();
                                        System.out.println("");
                                        System.out.print("Please enter a new name: ");
                                        String newName = sc.nextLine();
                                        clientList[indexEdit].setName(newName);
                                        System.out.println("Client name has been changed!\n");
                                        break;

                                    case 2:
                                        sc.nextLine();
                                        System.out.println("");
                                        System.out.print("Please enter a new phone number: ");
                                        String newPhone = sc.nextLine();
                                        clientList[indexEdit].setPhone(newPhone);
                                        System.out.println("Client phone number has been changed!\n");
                                        break;

                                    case 3:
                                        sc.nextLine();
                                        System.out.println("");
                                        System.out.print("Please enter a new email: ");
                                        String newEmail = sc.nextLine();
                                        clientList[indexEdit].setEmail(newEmail);
                                        System.out.println("Client email has been changed!\n");
                                        break;
                                }

                                break;

                            case 3:
                                sc.nextLine();
                                if (clientList != null) {
                                    System.out.print("Enter the index of the client you would like to remove: ");
                                    int index = sc.nextInt();
                                    // If the user enters a number that's out of bounds of the array, keep prompting
                                    // the user until they enter a valid number
                                    while (index < 0 || index >= clientList.length) {
                                        System.out.print("Invalid client index. Please enter a valid client index.");
                                        index = sc.nextInt();
                                    }

                                    // Create a new array that has one less element than the original array
                                    Client[] clientListDel = new Client[clientList.length - 1];
                                    for (int i = 0, j = 0; i < clientList.length; i++) {
                                        // If i isn't equal to index, add element from clientList to clientListDel and
                                        // increment j
                                        if (i != index) {
                                            clientListDel[j++] = clientList[i];
                                        }
                                    }
                                    clientList = clientListDel;
                                } else { // If the array is empty, display error message
                                    System.out.println("There are no clients to remove.");
                                }
                                break;
                        }

                        break;

                    //-----------------------------------------------
                    //                 LEASE MANAGER                 
                    //-----------------------------------------------
                    case 3:
                        System.out.print(
                                "-----------------------------------------------\n" +
                                "                  LEASE MANAGER                \n" +
                                "-----------------------------------------------\n" +
                                "Please select the operation you would like to do from the following list " +
                                "\n1. Lease an item" +
                                "\n2. Return an item" +
                                "\nEnter your choice: ");

                        int choiceLease = sc.nextInt();
                        switch (choiceLease) {

                            // Lease an item
                            case 1:
                                sc.nextLine();

                                System.out.print("Please enter the index of the client: ");
                                int clientLeaseIndex = sc.nextInt();

                                // If the user enters a number that's out of bounds of clientList, keep
                                // prompting the user until they enter a valid number
                                while (clientLeaseIndex < 0 || clientLeaseIndex >= clientList.length) {
                                    System.out.print("Invalid client number. Please enter a valid client number.");
                                    clientLeaseIndex = sc.nextInt();
                                }

                                System.out.print("Please enter the index of the item you would like to lease: ");
                                int indexLease = sc.nextInt();

                                // If the user enters a number that's out of bounds of library, keep prompting
                                // the user until they enter a valid number
                                while (indexLease < 0 || indexLease >= library.length) {
                                    System.out.print("Invalid item number. Please enter a valid item number.");
                                    indexLease = sc.nextInt();
                                }

                                // If the item is not leased
                                if (library[indexLease].getLeased() == false) {
                                    // Lease the item
                                    library[indexLease].leaseItem();
                                    // Add leased item to array
                                    clientList[clientLeaseIndex].addLeasedItem(library[indexLease]);
                                } else {
                                    System.out.println("This item is leased already.");
                                }

                                break;

                            // Return an item
                            case 2:
                                sc.nextLine();

                                System.out.print("Please enter the index of the client: ");
                                int clientReturnIndex = sc.nextInt();

                                // If the user enters a number that's out of bounds of clientList, keep
                                // prompting the user until they enter a valid number
                                while (clientReturnIndex < 0 || clientReturnIndex >= clientList.length) {
                                    System.out.print("Invalid client number. Please enter a valid client number.");
                                    clientReturnIndex = sc.nextInt();
                                }

                                System.out.print("Please enter the index of the item you would like to return: ");
                                int indexReturn = sc.nextInt();

                                // If the user enters a number that's out of bounds of library, keep prompting
                                // the user until they enter a valid number
                                while (indexReturn < 0 || indexReturn >= library.length) {
                                    System.out.print("Invalid item number. Please enter a valid item number.");
                                    indexReturn = sc.nextInt();
                                }

                                // If the item is leased
                                if (library[indexReturn].getLeased() == true) {
                                    // Return the item
                                    library[indexReturn].returnItem();
                                    // Remove leased item from array
                                    clientList[clientReturnIndex].removeLeasedItem(indexReturn);
                                } else {
                                    System.out.println("This item is not leased.");
                                }

                                break;
                        }
                        break;

                    // Option 4
                    case 4:
                        sc.nextLine();
                        System.out.print("Please input the index of the client you would like to display: ");
                        int indexClient = sc.nextInt();
                        System.out.println("Here is the list of all the items listed by the selected client: ");
                        System.out.println(clientList[indexClient].displayLeasedItems() + "\n");
                        break;

                    // Option 5
                    case 5:
                        sc.nextLine();
                        System.out.println("Here is the list of all leased items: ");
                        for (int i = 0; i < library.length; i++) {
                            if (library[i].getLeased()) {
                                System.out.println(library[i] + "\n");
                            }
                        }
                        break;

                    // Option 6
                    case 6:
                        System.out.println("The biggest book is " + getBiggestBook(library));
                        break;

                    // Option 7
                    case 7:
                        // Creating an array of books
                        Book[] booksList = new Book[Book.getNumberOfBooks()];
                        for (int i = 0, j = 0; i < library.length; i++) {
                            // If the item is a book
                            if ((library[i].getId()).charAt(0) == ('B')) {
                                booksList[j++] = (Book) library[i];
                            }
                        }
                        Book[] copyBooksArray = copyBooks(booksList);
                        System.out.println("Copy successful!\n");
                        break;


                }

            } while (choice != 8);
        } else {

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
        }

        System.out.println("Exiting the program.");
        sc.close();

    }

    // Displays main menu
    public static void displayMenu() {
        System.out.print(
                "-----------------------------------------------\n" +
                "                    MAIN MENU                  \n" +
                "-----------------------------------------------\n" +
                "1. Managa items" +
                "\n2. Manage clients"+
                "\n3. Manage leases" +
                "\n4. Show all items leased by a client" +
                "\n5. Show all leased items (by all clients)" +
                "\n6. Display the biggest book" +
                "\n7. Make a copy of the books array" +
                "\n8. Quit" +
                "\nEnter your choice: ");
    }

    public static Item getBiggestBook(Item[] library) {

        Book biggestBook = (Book) library[0];
        for (int i = 1; i < library.length; i++) {
            // If the number of pages of the current book is higher than the ones of
            // biggestBook

            if ((library[i].getId()).charAt(0) == ('B')) {
                if (((Book) library[i]).getNumberOfPages() > biggestBook.getNumberOfPages()) {
                    // Update biggestBook with the book with the highest number of pages
                    biggestBook = (Book) library[i];
                }
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
