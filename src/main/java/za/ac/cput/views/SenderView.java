package za.ac.cput.views;

import za.ac.cput.Main;
import za.ac.cput.domain.Sender;
import za.ac.cput.factory.SenderFactory;
import za.ac.cput.repository.SenderRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SenderView {

    public static void MenuSender() throws SQLException {
        System.out.println("\n\n");
        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to Sender Menu: ");
        System.out.println("1. Create a Sender");
        System.out.println("2. Read a Sender");
        System.out.println("3. Update a Sender");
        System.out.println("4. Delete a Sender");
        System.out.println("5. Show all Senders");
        System.out.println("6. Go back to the Main Menu");
        do {
            System.out.println("Enter your choice: ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    AddSender();
                    break;
                case 2:
                    SearchSender();
                    break;
                case 3:
                    UpdateSender();
                    break;
                case 4:
                    DeleteSender();
                    break;
                case 5:
                    GetAllSender();
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

    public static void AddSender() throws SQLException {
        System.out.println("\n\n");
        Sender sender;

        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Add a new Sender : ");
            System.out.print("Id Sender : ");
            String idSender = scan.nextLine();
            System.out.print("First Name : ");
            String firstName = scan.nextLine();
            System.out.print("Last Name : ");
            String lastName = scan.nextLine();
            System.out.print("Phone Number : ");
            String phoneNumber = scan.nextLine();

            sender = SenderFactory.createSender(idSender,firstName , lastName, phoneNumber);
            if(sender == null)
                System.out.println("Invalid information");

        } while (sender == null);

        try {
            SenderRepository senderRepository = new SenderRepository();

            senderRepository.create(sender);

            System.out.println("Sender created successfully: " + sender);

        } catch (SQLException e) {
            System.err.println("Error handling Sender creation: " + e.getMessage());
        }
        MenuSender();
    }

    public static void SearchSender() throws SQLException {
        String idSender;
        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Search a Sender : ");
            System.out.print("Id Sender : ");
            idSender = scan.nextLine();

            if(idSender == null)
                System.out.println("Invalid information");

        } while (idSender == null);

        try {
            SenderRepository senderRepository = new SenderRepository();
            Sender sender = senderRepository.read(idSender);

            if (sender != null) {
                System.out.println("Sender found: " + sender);
            } else {
                System.out.println("Sender not found for the specified id sender.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Sender retrieval: " + e.getMessage());
        }
        MenuSender();
    }

    public static void UpdateSender() throws SQLException {
        Sender newSender;
        String idSender;
        try {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Sender to Update : ");
            System.out.print("Id Sender : ");
            idSender = scan.nextLine();

            SenderRepository senderRepository = new SenderRepository();
            Sender existingSender = senderRepository.read(idSender);

            System.out.println(existingSender);

            if (existingSender != null) {

                do {
                    System.out.println("\n\n");
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("Update Sender : ");
                    System.out.print("First Name : ");
                    String firstName = scan2.nextLine();
                    System.out.print("Last Name : ");
                    String lastName = scan2.nextLine();
                    System.out.print("Phone Number : ");
                    String phoneNumber = scan2.nextLine();

                    newSender = SenderFactory.createSender(idSender, firstName, lastName, phoneNumber);
                    if(newSender == null)
                        System.out.println("Invalid information");

                } while (newSender == null);

                senderRepository.update(newSender);

                System.out.println("Sender updated successfully: " + newSender);
            } else {
                System.out.println("Sender not found for the specified id sender.");
            }

        } catch (SQLException e) {
            System.err.println("Error handling Sender update: " + e.getMessage());
        }
        MenuSender();
    }

    public static void DeleteSender() throws SQLException {
        String idSender;
        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Delete a Sender : ");
            System.out.print("Id Sender : ");
            idSender = scan.nextLine();

            if(idSender == null)
                System.out.println("Invalid information");

        } while (idSender == null);

        try {
            SenderRepository senderRepository = new SenderRepository();
            boolean isDelete = senderRepository.delete(idSender);

            if (isDelete) {
                System.out.println("Sender deleted");
            } else {
                System.out.println("Sender not found for the specified id sender.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Sender retrieval: " + e.getMessage());
        }
        MenuSender();
    }

    public static void GetAllSender() throws SQLException {
        System.out.println("\n\n");
        try {
            SenderRepository senderRepository = new SenderRepository();
            List<Sender> allSender = senderRepository.getAll();

            if (!allSender.isEmpty()) {
                System.out.println("All Sender :");
                for (Sender sender : allSender) {
                    System.out.println(sender);
                }
            } else {
                System.out.println("No sender found.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Sender retrieval: " + e.getMessage());
        }

        MenuSender();
    }
}