package za.ac.cput.views;

import za.ac.cput.Main;
import za.ac.cput.domain.Receiver;
import za.ac.cput.factory.ReceiverFactory;
import za.ac.cput.repository.ReceiverRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ReceiverView {

    public static void MenuReceiver() throws SQLException {
        System.out.println("\n\n");
        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to Receiver Menu: ");
        System.out.println("1. Create a Receiver");
        System.out.println("2. Read a Receiver");
        System.out.println("3. Update a Receiver");
        System.out.println("4. Delete a Receiver");
        System.out.println("5. Show all Receivers");
        System.out.println("6. Go back to the Main Menu");
        do {
            System.out.println("Enter your choice: ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    AddReceiver();
                    break;
                case 2:
                    SearchReceiver();
                    break;
                case 3:
                    UpdateReceiver();
                    break;
                case 4:
                    DeleteReceiver();
                    break;
                case 5:
                    GetAllReceiver();
                    break;
                case 6:
                    Main.MainMenu();
                    break;
                default:
                    System.out.println("\nInvalid choice ! Please try again.");
                    choice = 0;
            }
        } while (choice == 0);
    }

    public static void AddReceiver() throws SQLException {
        System.out.println("\n\n");
        Receiver receiver;

        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Add a new Receiver : ");
            System.out.print("Id Receiver : ");
            String idReceiver = scan.nextLine();
            System.out.print("First Name : ");
            String firstName = scan.nextLine();
            System.out.print("Last Name : ");
            String lastName = scan.nextLine();
            System.out.print("Phone Number : ");
            String phoneNumber = scan.nextLine();

            receiver = ReceiverFactory.createReceiver(idReceiver,firstName , lastName, phoneNumber);
            if(receiver == null)
                System.out.println("Invalid information");

        } while (receiver == null);

        try {
            ReceiverRepository receiverRepository = new ReceiverRepository();

            receiverRepository.create(receiver);

            System.out.println("Receiver created successfully: " + receiver);

        } catch (SQLException e) {
            System.err.println("Error handling Receiver creation: " + e.getMessage());
        }
        MenuReceiver();
    }

    public static void SearchReceiver() throws SQLException {
        String idReceiver;
        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Search a Receiver : ");
            System.out.print("Id Receiver : ");
            idReceiver = scan.nextLine();

            if(idReceiver == null)
                System.out.println("Invalid information");

        } while (idReceiver == null);

        try {
            ReceiverRepository receiverRepository = new ReceiverRepository();
            Receiver receiver = receiverRepository.read(idReceiver);

            if (receiver != null) {
                System.out.println("Receiver found: " + receiver);
            } else {
                System.out.println("Receiver not found for the specified id receiver.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Receiver retrieval: " + e.getMessage());
        }
        MenuReceiver();
    }

    public static void UpdateReceiver() throws SQLException {
        Receiver newReceiver;
        String idReceiver;
        try {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Receiver to Update : ");
            System.out.print("Id Receiver : ");
            idReceiver = scan.nextLine();

            ReceiverRepository receiverRepository = new ReceiverRepository();
            Receiver existingReceiver = receiverRepository.read(idReceiver);

            System.out.println(existingReceiver);

            if (existingReceiver!= null) {

                do {
                    System.out.println("\n\n");
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("Update Receiver : ");
                    System.out.print("First Name : ");
                    String firstName = scan2.nextLine();
                    System.out.print("Last Name : ");
                    String lastName = scan2.nextLine();
                    System.out.print("Phone Number : ");
                    String phoneNumber = scan2.nextLine();

                    newReceiver = ReceiverFactory.createReceiver(idReceiver, firstName, lastName, phoneNumber);
                    if(newReceiver == null)
                        System.out.println("Invalid information");

                } while (newReceiver == null);

                receiverRepository.update(newReceiver);

                System.out.println("Receiver updated successfully: " + newReceiver);
            } else {
                System.out.println("Receiver not found for the specified id receiver.");
            }

        } catch (SQLException e) {
            System.err.println("Error handling Receiver update: " + e.getMessage());
        }
        MenuReceiver();
    }

    public static void DeleteReceiver() throws SQLException {
        String idReceiver;
        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Delete a Receiver : ");
            System.out.print("Id Receiver : ");
            idReceiver = scan.nextLine();

            if(idReceiver == null)
                System.out.println("Invalid information");

        } while (idReceiver == null);

        try {
            ReceiverRepository receiverRepository = new ReceiverRepository();
            boolean isDelete = receiverRepository.delete(idReceiver);

            if (isDelete) {
                System.out.println("Receiver deleted");
            } else {
                System.out.println("Receiver not found for the specified id receiver.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Receiver retrieval: " + e.getMessage());
        }
        MenuReceiver();
    }

    public static void GetAllReceiver() throws SQLException {
        System.out.println("\n\n");
        try {
            ReceiverRepository receiverRepository = new ReceiverRepository();
            List<Receiver> allReceiver = receiverRepository.getAll();

            if (!allReceiver.isEmpty()) {
                System.out.println("All Receiver :");
                for (Receiver receiver : allReceiver) {
                    System.out.println(receiver);
                }
            } else {
                System.out.println("No receiver found.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Receiver retrieval: " + e.getMessage());
        }

        MenuReceiver();
    }
}
