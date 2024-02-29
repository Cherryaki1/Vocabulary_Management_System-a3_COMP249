package driver;

import java.util.Scanner;

import client.Client;
import library.*;

public class Driver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Client[] clientList = new Client[0];

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

            // If the user does not enter a positive number, keep prompting the user
            // until they enter a valid size
            while (size <= 0) {
                System.out.println("You must enter a number greater than 0.");
                size = sc.nextInt();
            }

            Item[] library = new Item[size];

            int choice;
            do {
                displayMenu();
                choice = sc.nextInt();

                // If the user does not enter a number between 1 and 7, keep prompting the user
                // until they enter a valid option
                while (choice < 1 || choice > 8) {
                    System.out.print("Invalid choice. Please enter a valid number: ");
                    choice = sc.nextInt();
                    System.out.println("");
                }

                switch (choice) {

                    // -----------------------------------------------
                    // ITEM MANAGER
                    // -----------------------------------------------

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
                                        library[Item.getNumberOfItems()] = new Book(name, author, year,
                                                numberOfPages);
                                        System.out.println("Book successfully created.");
                                    } else if (itemType.equalsIgnoreCase("Journal")) {
                                        System.out.print("Volume number: ");
                                        int volumeNumber = sc.nextInt();
                                        // Creating a new journal and adding it to the library
                                        library[Item.getNumberOfItems()] = new Journal(name, author, year,
                                                volumeNumber);
                                        System.out.println("Journal successfully created.");
                                    } else if (itemType.equalsIgnoreCase("Media")) {
                                        System.out.print("Type: ");
                                        String type = sc.next();
                                        // Creating a new media and adding it to the library
                                        library[Item.getNumberOfItems()] = new Media(name, author, year, type);
                                        System.out.println("Media successfully created.");
                                    }

                                } else {
                                    System.out.println("There isn't enough space in the library.\n");
                                }

                                break;

                            // Delete an item
                            case 2:

                                // If the array isn't empty
                                if (!emptyLibrary(library)) {
                                    System.out.print("Enter the item ID you would like to remove: ");
                                    String id = sc.next();

                                    // Find the index of the item to delete in the array
                                    int indexItemDelete = findIndexLibrary(library, id, sc);

                                    // If the item is not leased, you can delete the item
                                    if (library[indexItemDelete].getLeased() == false) {

                                        // If the library is not of size 1
                                        if (library.length != 1) {
                                            // Create a new array that has one less element than the original array
                                            Item[] newLibrary = new Item[library.length - 1];
                                            for (int i = 0, j = 0; i < library.length; i++) {
                                                // If the ID of the current element isn't equal to the ID of the item to
                                                // remove,
                                                // add element from library to newLibrary and increment j
                                                if (!(library[i].getId().equals(id))) {
                                                    newLibrary[j++] = library[i];
                                                }
                                                library = newLibrary;
                                            }
                                        } else {
                                            // If the library is of size 1, remove item from library without changing
                                            // the size of the array
                                            Item newLibrary[] = new Item[library.length];
                                            library = newLibrary;
                                        }

                                        Item.deleteItem();
                                        System.out.println("Item removed successfully.");
                                    } else { // If the item is leased, you can't delete the item
                                        System.out.println("Item removed unsuccessfully. This item is leased.");
                                    }
                                } else { // If the array is empty, display error message
                                    System.out.println("There are no items to remove.");
                                }

                                break;

                            // Change information of an item
                            case 3:

                                // If the library isn't empty
                                if (!emptyLibrary(library)) {
                                    System.out.print("Enter the ID of the item that you would like to edit: ");
                                    String id = sc.next();

                                    // Find the index of the item in the library
                                    int indexItemEdit = findIndexLibrary(library, id, sc);

                                    System.out.print("Which information would you like to modify?" +
                                            "\n1. Name" +
                                            "\n2. Author" +
                                            "\n3. Year" +
                                            "\n4. Number of pages (Book)" +
                                            "\n5. Volume number (Journal)" +
                                            "\n6. Type (Media) ");

                                    int information = sc.nextInt();

                                    while (information < 1 || information > 6) {
                                        System.out.println("Invalid option. Please enter a valid option.");
                                        information = sc.nextInt();
                                    }

                                    switch (information) {
                                        case 1:
                                            sc.nextLine();
                                            System.out.print("Name: ");
                                            String name = sc.next();
                                            library[indexItemEdit].setName(name);
                                            System.out.println("Name changed successfully.");
                                            break;

                                        case 2:
                                            sc.nextLine();
                                            System.out.print("Author: ");
                                            String author = sc.next();
                                            library[indexItemEdit].setAuthor(author);
                                            System.out.println("Author changed successfully.");
                                            break;

                                        case 3:
                                            System.out.print("Year: ");
                                            int year = sc.nextInt();
                                            library[indexItemEdit].setYear(year);
                                            System.out.println("Year changed successfully.");
                                            break;

                                        case 4:
                                            // If the item the user is trying to modify is a book
                                            if (library[indexItemEdit] instanceof Book) {
                                                System.out.print("Number of pages: ");
                                                int pages = sc.nextInt();
                                                ((Book) library[indexItemEdit]).setNumberOfPages(pages);
                                                System.out.println("Number of pages changed successfully.");
                                            } else { // The user can't modify this attribute if the item they want to
                                                     // edit is not a book
                                                System.out.println("You can't modify this attribute.");
                                            }
                                            break;

                                        case 5:
                                            // If the item the user is trying to modify is a journal
                                            if (library[indexItemEdit] instanceof Journal) {
                                                System.out.print("Volume Number: ");
                                                int volumeNumber = sc.nextInt();
                                                ((Journal) library[indexItemEdit]).setVolumeNumber(volumeNumber);
                                                System.out.println("Volume number changed successfully.");
                                            } else { // The user can't modify this attribute if the item they want to
                                                     // edit is not a journal
                                                System.out.println("You can't modify this attribute.");
                                            }
                                            break;

                                        case 6:
                                            sc.nextLine();
                                            // If the item the user is trying to modify is a media
                                            if (library[indexItemEdit] instanceof Media) {
                                                System.out.print("Type: ");
                                                String type = sc.next();
                                                ((Media) library[indexItemEdit]).setType(type);
                                                System.out.println("Type changed successfully.");
                                            } else { // The user can't modify this attribute if the item they want to
                                                     // edit is not a media
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
                                // If the library is not empty
                                if (!emptyLibrary(library)) {
                                    System.out.print("Enter the category: book (1), journal (2), or media (3) ");
                                    int category = sc.nextInt();

                                    // If the user enters a number that isn't between 1 and 3,
                                    // keep prompting the user until they enter a valid number
                                    while (category < 1 || category > 3) {
                                        System.out.print("Invalid category. Please enter a valid category. ");
                                        category = sc.nextInt();
                                    }

                                    switch (category) {
                                        case 1:
                                            // If there is at least one book in the library
                                            if (Book.getNumberOfBooks() >= 1) {
                                                // Go through every element in the array
                                                for (Item item : library) {
                                                    // If the current item isn't null and is a Book, print information
                                                    // of
                                                    // item
                                                    if (item != null && item instanceof Book) {
                                                        System.out.println(item + "\n");
                                                    }
                                                }
                                            } else {
                                                System.out.println("There are no books in the library.");
                                            }

                                            break;

                                        case 2:
                                            // If there is at least one journal in the library
                                            if (Journal.getNumberOfJournals() >= 1) {
                                                // Go through every element in the array
                                                for (Item item : library) {
                                                    // If the current item isn't null and is a Journal, print
                                                    // information of
                                                    // item
                                                    if (item != null && item instanceof Journal) {
                                                        System.out.println(item + "\n");
                                                    }
                                                }
                                            } else {
                                                System.out.println("There are no journals in the library.");
                                            }

                                            break;

                                        case 3:
                                            // If there is at least one media in the library
                                            if (Media.getNumberOfMedia() >= 1) {
                                                // Go through every element in the array
                                                for (Item item : library) {
                                                    // If the current item isn't null and is a Media, print information
                                                    // of
                                                    // item
                                                    if (item != null && item instanceof Media) {
                                                        System.out.println(item + "\n");
                                                    }
                                                }
                                            } else {
                                                System.out.println("There are no medias in the library.");
                                            }
                                            break;
                                    }
                                } else {
                                    System.out.println("The library is empty.");
                                }

                                break;

                            // Print all items (from all categories)
                            case 5:
                                // If the library is not empty
                                if (!emptyLibrary(library)) {
                                    // Go through every element in the array
                                    for (Item item : library) {
                                        // If the current item isn't null, print information of item
                                        if (item != null) {
                                            System.out.println(item + "\n");
                                        }
                                    }
                                } else {
                                    System.out.println("The library is empty.");
                                }
                                break;
                        }

                        break;

                    // -----------------------------------------------
                    // CLIENT MANAGER
                    // -----------------------------------------------
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

                        while (clientChoice < 1 || clientChoice > 3) {
                            System.out.print("Invalid option. Please enter a valid option. ");
                            clientChoice = sc.nextInt();
                        }

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

                                // Create a deep copy of clientList with one additional element
                                Client[] clientListCopy = new Client[clientList.length + 1];
                                for (int i = 0; i < clientList.length; i++) {
                                    // Storing the elements from clientListCopy to clientList
                                    clientListCopy[i] = clientList[i];
                                }
                                clientList = clientListCopy;
                                // Store new client in the array
                                clientList[clientList.length - 1] = new Client(cName, cPhone, cEmail);
                                System.out.println("Client successfully added!\n");
                                break;

                            case 2:
                                sc.nextLine();
                                System.out.print("Please input the ID of the client you would like to edit: ");
                                String idEdit = sc.next();

                                // Find the index of the client in clientList
                                int indexClientEdit = findIndexClient(clientList, idEdit, sc);

                                System.out.print(
                                        "\n-----------------------------------------------\n" +
                                                "                 EDITING CLIENT                \n" +
                                                "-----------------------------------------------\n" +
                                                "Please select which information you would like to edit" +
                                                "\n1. Name" +
                                                "\n2. Phone number" +
                                                "\n3. Email" +
                                                "\nEnter your choice: ");
                                int editChoice = sc.nextInt();

                                while (editChoice < 1 || editChoice > 3) {
                                    System.out.print("Invalid choice. Please enter a valid choice. ");
                                    editChoice = sc.nextInt();
                                }

                                switch (editChoice) {

                                    case 1:
                                        sc.nextLine();
                                        System.out.println("");
                                        System.out.print("Please enter a new name: ");
                                        String newName = sc.nextLine();
                                        clientList[indexClientEdit].setName(newName);
                                        System.out.println("Client name has been changed!\n");
                                        break;

                                    case 2:
                                        sc.nextLine();
                                        System.out.println("");
                                        System.out.print("Please enter a new phone number: ");
                                        String newPhone = sc.nextLine();
                                        clientList[indexClientEdit].setPhone(newPhone);
                                        System.out.println("Client phone number has been changed!\n");
                                        break;

                                    case 3:
                                        sc.nextLine();
                                        System.out.println("");
                                        System.out.print("Please enter a new email: ");
                                        String newEmail = sc.nextLine();
                                        clientList[indexClientEdit].setEmail(newEmail);
                                        System.out.println("Client email has been changed!\n");
                                        break;
                                }

                                break;

                            case 3:
                                sc.nextLine();
                                // If the array of clients isn't empty
                                if (clientList.length != 0) {
                                    System.out.print("Enter the ID of the client you would like to remove: ");
                                    String idDel = sc.next();

                                    // Find the index of the client in clientList
                                    int indexClientDelete = findIndexClient(clientList, idDel, sc);

                                    // If the client has at least one leased item, you can't delete the client
                                    if (clientList[indexClientDelete].getLeasedItems().length != 0) {
                                        System.out.println(
                                                "Client removed unsuccessfully. This client has at least one leased item.");
                                    } else { // If the client has no leased item, you can delete the client
                                        // Create a new array that has one less element than the original array
                                        Client[] clientListDel = new Client[clientList.length - 1];
                                        for (int i = 0, j = 0; i < clientList.length; i++) {
                                            // If i isn't equal to index, add element from clientList to clientListDel
                                            // and
                                            // increment j
                                            if (i != indexClientDelete) {
                                                clientListDel[j++] = clientList[i];
                                            }
                                        }
                                        clientList = clientListDel;

                                        System.out.println("Client removed successfully.");
                                    }

                                } else { // If the array is empty, display error message
                                    System.out.println("There are no clients to remove.");
                                }

                        }

                        break;

                    // -----------------------------------------------
                    // LEASE MANAGER
                    // -----------------------------------------------
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

                        while (choiceLease < 1 || choiceLease > 2) {
                            System.out.print("Invalid option. Please enter a valid option. ");
                            choiceLease = sc.nextInt();
                        }

                        switch (choiceLease) {

                            // Lease an item
                            case 1:
                                sc.nextLine();

                                // If the library isn't empty
                                if (!emptyLibrary(library)) {
                                    // If clientList isn't empty
                                    if (clientList.length != 0) {
                                        System.out.print("Please enter the ID of the client: ");
                                        String idClient = sc.next(); 
                                        
                                        // Find the index of the client in clientList
                                        int idClientIndex = findIndexClient(clientList, idClient, sc);

                                        System.out.print("Please enter the ID of the item you would like to lease: ");
                                        String idItem = sc.next();

                                        // Find the index of item to lease in the library
                                        int idItemLease = findIndexLibrary(library, idItem, sc);

                                        // If the item is not leased
                                        if (library[idItemLease].getLeased() == false) {
                                            // Lease the item
                                            library[idItemLease].leaseItem();
                                            // Add leased item to array
                                            clientList[idClientIndex].addLeasedItem(library[idItemLease]);
                                            System.out.println("Item has been leased successfully.");
                                        } else {
                                            System.out.println("This item is leased already.");
                                        }
                                    } else {
                                        System.out.println("There are no clients.");
                                    }
                                } else
                                    System.out.println("The library is empty.");

                                break;

                            // Return an item
                            case 2:
                                sc.nextLine();

                                // If clientList isn't empty
                                if (clientList.length != 0) {
                                    System.out.print("Please enter the ID of the client: ");
                                    String clientReturnId = sc.next();
                                    
                                    // Find the index of the client in clientList
                                    int clientReturnIndex = findIndexClient(clientList, clientReturnId, sc);

                                    System.out.print("Please enter the ID of the item you would like to return: ");
                                    String idItemReturn = sc.next();

                                    // Find the index of item to return in the library
                                    int indexReturn = findIndexLibrary(library, idItemReturn, sc);

                                    // If the item is leased
                                    if (library[indexReturn].getLeased() == true) {
                                        // Return the item
                                        library[indexReturn].returnItem();
                                        // Remove leased item from array
                                        clientList[clientReturnIndex].removeLeasedItem(indexReturn);
                                        System.out.println("Item has been returned successfully.");
                                    } else {
                                        System.out.println("This item is not leased.");
                                    }
                                } else {
                                    System.out.println("There are no clients.");
                                }

                                break;
                        }
                        break;

                    // Option 4
                    case 4:
                        sc.nextLine();
                        // If clientList isn't empty
                        if (clientList.length != 0) {
                            System.out.print("Please input the ID of the client you would like to display: ");
                            String indexClient = sc.next();

                            // Find the index of the client in clientList
                            int indexClientDisplay = findIndexClient(clientList, indexClient, sc);

                            // If the client has at least one leased item
                            if (clientList[indexClientDisplay].getLeasedItems().length != 0) {

                                System.out.println("Here is the list of all the items listed by the selected client: ");
                                for (int i = 0; i < clientList.length; i++) {
                                    if (i == indexClientDisplay) {
                                        for (Item item : clientList[i].getLeasedItems()) {
                                            System.out.println(item + "\n");
                                        }
                                    }
                                }
                            } else {
                                System.out.println("This client has no leased item.");
                            }

                        } else {
                            System.out.println("There are no clients.");
                        }
                        break;

                    // Option 5
                    case 5:
                        sc.nextLine();
                        // If the library is not empty
                        if (!emptyLibrary(library)) {

                            boolean isLeased = false;

                            // Check if there is at least one leased item
                            for (int i = 0; i < library.length; i++) {
                                if (library[i] != null && library[i].getLeased()) {
                                    isLeased = true;
                                    break;
                                }
                            }

                            // If there is at least one leased item in the library
                            if (isLeased) {
                                System.out.println("Here is the list of all leased items: ");
                                for (int i = 0; i < library.length; i++) {
                                    if (library[i] != null && library[i].getLeased()) {
                                        System.out.println(library[i] + "\n");
                                    }
                                }
                            } else {
                                System.out.println("There are no leased items.");
                            }
                        } else {
                            System.out.println("The library is empty.");
                        }
                        break;

                    // Option 6
                    case 6:
                        // If the library is not empty
                        if (!emptyLibrary(library)) {
                            // If there is at least one book in the library
                            if (Book.getNumberOfBooks() >= 1) {
                                // Creating an array of books
                                Book[] booksList = new Book[Book.getNumberOfBooks()];
                                for (int i = 0, j = 0; i < library.length; i++) {
                                    // If the item is not null and is a book
                                    if (library[i] != null && (library[i].getId()).charAt(0) == ('B')) {
                                        booksList[j++] = (Book) library[i];
                                    }
                                }
                                System.out.println(
                                        "The information of the biggest book is as follow\n"
                                                + getBiggestBook(booksList));
                            } else { // If there are no books in the library
                                System.out.println("The library has no books.");
                            }
                        } else
                            System.out.println("The library is empty.");

                        break;

                    // Option 7
                    case 7:
                        // If the library is not empty
                        if (!emptyLibrary(library)) {
                            // If there is at least one book in the library
                            if (Book.getNumberOfBooks() >= 0) {
                                // Creating an array of books
                                Book[] booksList = new Book[Book.getNumberOfBooks()];
                                for (int i = 0, j = 0; i < library.length; i++) {
                                    // If the item is not null and is a book
                                    if (library[i] != null && (library[i].getId()).charAt(0) == ('B')) {
                                        booksList[j++] = (Book) library[i];
                                    }
                                }

                                Book[] copyBooksArray = copyBooks(booksList);
                                System.out.println("Copy successful!\n");

                                // Print information of books
                                for (Book book : copyBooksArray) {
                                    System.out.println(book + "\n");
                                }
                            } else { // If there are no books in the library
                                System.out.println("There are no books to make a copy of.");
                            }
                        } else
                            System.out.println("The library is empty.");

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
                        "1. Manage items" +
                        "\n2. Manage clients" +
                        "\n3. Manage leases" +
                        "\n4. Show all items leased by a client" +
                        "\n5. Show all leased items (by all clients)" +
                        "\n6. Display the biggest book" +
                        "\n7. Make a copy of the books array" +
                        "\n8. Quit" +
                        "\nEnter your choice: ");
    }

    public static Item getBiggestBook(Book[] books) {
        Book biggestBook = (Book) books[0];

        for (int i = 1; i < books.length; i++) {
            // Check if the item is not null
            if (books[i] != null) {
                // If the number of pages of the current book is higher than the ones of
                // biggestBook
                if (books[i].getNumberOfPages() > biggestBook.getNumberOfPages()) {
                    // Update biggestBook with the book with the highest number of pages
                    biggestBook = books[i];
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

    // Check if the library is empty
    public static boolean emptyLibrary(Item[] library) {
        boolean empty = true;
        for (int i = 0; i < library.length; i++) {
            if (library[i] != null) {
                empty = false;
                return empty;
            }

        }
        return empty;
    }

    // Find the position of the item in the library
    public static int findIndexLibrary(Item[] library, String id, Scanner sc) {
        int index = -1;

        do {
            for (int i = 0; i < library.length; i++) {
                // If the index of the item is found, exit for loop
                if (library[i] != null && library[i].getId().equals(id)) {
                    index = i;
                    break;
                }
            }

            // If the index of the item is not found, user can enter another ID
            if (index == -1) {
                System.out.print("Invalid item ID. Please enter a valid ID. ");
                id = sc.next();
            }

        } while (index == -1);

        return index;
    }

    // Find the position of the client in the array clientList
    public static int findIndexClient(Client[] clientList, String id, Scanner sc) {
        int index = -1;

        do {
            for (int i = 0; i < clientList.length; i++) {
                // If the index of the client is found, exit for loop
                if (clientList[i] != null && clientList[i].getId().equals(id)) {
                    index = i;
                    break;
                }
            }

            // If the index of the client is not found, user can enter another ID
            if (index == -1) {
                System.out.print("Invalid client ID. Please enter a valid ID. ");
                id = sc.next();
            }

        } while (index == -1);

        return index;
    }

}
