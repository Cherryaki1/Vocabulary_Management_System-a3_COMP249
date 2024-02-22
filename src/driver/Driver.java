package driver;

import java.util.Scanner;

import client.Client;
import library.Book;
import library.Item;
import library.Journal;
import library.Media;

public class Driver {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
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

            }

        } while (choice != 7);

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

}
