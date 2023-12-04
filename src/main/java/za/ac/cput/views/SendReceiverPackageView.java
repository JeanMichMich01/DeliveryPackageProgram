package za.ac.cput.views;

import za.ac.cput.Main;
import za.ac.cput.domain.ShippingInfo;
import za.ac.cput.domain.SendReceiverPackage;
import za.ac.cput.factory.SendReceiverPackageFactory;
import za.ac.cput.repository.ShippingInfoRepository;
import za.ac.cput.repository.SendReceiverPackageRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SendReceiverPackageView {

    public static void MenuSendReceiverPackage() throws SQLException {
        System.out.println("\n\n");
        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println("Sender Receiver Package Menu :");
        System.out.println("1. Add a Sender Receiver Package");
        System.out.println("2. Search for a Sender Receiver Package");
        System.out.println("3. Update a Sender Receiver Package");
        System.out.println("4. Delete a Sender Receiver Package");
        System.out.println("5. Show all the Senders Receiver Package");
        System.out.println("6. Go back to the Main Menu");
        do{
            System.out.print("Enter your choice : ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    AddSendReceiverPackage();
                    break;
                case 2:
                    SearchSendReceiverPackage();
                    break;
                case 3:
                    UpdateSendReceiverPackage();
                    break;
                case 4:
                    DeleteSendReceiverPackage();
                    break;
                case 5:
                    GetAllSendReceiverPackage();
                    break;
                case 6:
                    Main.MainMenu();
                    break;
                default:
                    System.out.println("\nInvalid choice !");
                    choice = 0;
            }
        } while (choice == 0);
    }

    public static void AddSendReceiverPackage() throws SQLException {
        System.out.println("\n\n");
        SendReceiverPackage sendReceiverPackage;

        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Add a Sender Receiver Package : ");
            System.out.print("Id sender : ");
            String idSender = scan.nextLine();
            System.out.print("Id receiver : ");
            String idReceiver = scan.nextLine();
            System.out.print("Id package : ");
            int idPackage = scan.nextInt();
            scan.nextLine();
            System.out.print("Send date: ");
            String sendDate = scan.nextLine();

            ShippingInfoRepository shippingInfoRepository= new ShippingInfoRepository();
            ShippingInfo shippingInfo = shippingInfoRepository.read(sendDate);

            sendReceiverPackage = SendReceiverPackageFactory.createSendReceiverPackage(idSender, idReceiver, idPackage, shippingInfo);
            if(sendReceiverPackage == null)
                System.out.println("Invalid information");

        } while (sendReceiverPackage == null);

        try {
            SendReceiverPackageRepository sendReceiverPackageRepository = new SendReceiverPackageRepository();

            sendReceiverPackageRepository.create(sendReceiverPackage);

            System.out.println("Post Office created successfully: " + sendReceiverPackage);

        } catch (SQLException e) {
            System.err.println("Error handling Post Office creation: " + e.getMessage());
        }
        MenuSendReceiverPackage();
    }

    public static void SearchSendReceiverPackage() throws SQLException {
        int idPackage;
        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Search for a Sender Receiver Package : ");
            System.out.print("Id Package : ");
            idPackage = scan.nextInt();
            scan.nextLine();

            if(idPackage <= 0)
                System.out.println("Invalid information");

        } while (idPackage <=0 );

        try {
            SendReceiverPackageRepository sendReceiverPackageRepository = new SendReceiverPackageRepository();
            SendReceiverPackage sendReceiverPackage = sendReceiverPackageRepository.read(idPackage);

            if (sendReceiverPackage != null) {
                System.out.println("Sender Receiver Package found: " + sendReceiverPackage);
            } else {
                System.out.println("Sender Receiver Package not found for the specified id.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Post Office retrieval: " + e.getMessage());
        }
        MenuSendReceiverPackage();
    }

    public static void UpdateSendReceiverPackage() throws SQLException {
        SendReceiverPackage newSenderReceiverPackage;
        int idPackage;
        try {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Sender Receiver Package to Update : ");
            System.out.print("Id Package : ");
            idPackage = scan.nextInt();
            scan.nextLine();

            SendReceiverPackageRepository sendReceiverPackageRepository = new SendReceiverPackageRepository();
            SendReceiverPackage existingSendReceiverPackage = sendReceiverPackageRepository.read(idPackage);

            System.out.println(existingSendReceiverPackage);

            if (existingSendReceiverPackage != null) {

                do {
                    System.out.println("\n\n");
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("Update Sender Receiver Package : ");
                    System.out.print("Id Sender : ");
                    String idSender = scan2.nextLine();
                    System.out.print("Id Receiver : ");
                    String idReceiver = scan2.nextLine();


                    newSenderReceiverPackage = SendReceiverPackageFactory.createSendReceiverPackage(idSender, idReceiver,idPackage, existingSendReceiverPackage.getShippingInfo());
                    if(newSenderReceiverPackage == null)
                        System.out.println("Invalid information");

                } while (newSenderReceiverPackage == null);

                sendReceiverPackageRepository.update(newSenderReceiverPackage);

                System.out.println("Sender Receiver Package updated successfully: " + newSenderReceiverPackage);
            } else {
                System.out.println("Sender Receiver Package not found for the specified id.");
            }

        } catch (SQLException e) {
            System.err.println("Error handling Post Office update: " + e.getMessage());
        }
        MenuSendReceiverPackage();
    }

    public static void DeleteSendReceiverPackage() throws SQLException {
        int idPackage;
        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Delete a Sender Receiver Package  : ");
            System.out.print("Id Package: ");
            idPackage = scan.nextInt();
            scan.nextLine();

            if(idPackage <= 0)
                System.out.println("Invalid information");

        } while (idPackage <=0 );

        try {
            SendReceiverPackageRepository sendReceiverPackageRepository = new SendReceiverPackageRepository();
            boolean isDelete = sendReceiverPackageRepository.delete(idPackage);

            if (isDelete) {
                System.out.println("Sender Receiver Package deleted");
            } else {
                System.out.println("Sender Receiver Package not found for the specified id.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Post Office retrieval: " + e.getMessage());
        }
        MenuSendReceiverPackage();
    }

    public static void GetAllSendReceiverPackage() throws SQLException {
        System.out.println("\n\n");
        try {
            SendReceiverPackageRepository sendReceiverPackageRepository = new SendReceiverPackageRepository();
            List<SendReceiverPackage> allSenderReceiverPackage = sendReceiverPackageRepository.getAll();

            if (!allSenderReceiverPackage.isEmpty()) {
                System.out.println("All Sender Receiver Packages:");
                for (SendReceiverPackage sendReceiverPackage : allSenderReceiverPackage) {
                    System.out.println(sendReceiverPackage);
                }
            } else {
                System.out.println("No Sender Receiver Packages found.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Post Offices retrieval: " + e.getMessage());
        }
        MenuSendReceiverPackage();
    }
}
