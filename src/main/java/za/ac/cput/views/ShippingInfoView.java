package za.ac.cput.views;

import za.ac.cput.Main;
import za.ac.cput.domain.ShippingInfo;
import za.ac.cput.factory.ShippingInfoFactory;
import za.ac.cput.repository.ShippingInfoRepository;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ShippingInfoView {

    public static void MenuShippingInfo() throws SQLException {
        System.out.println("\n\n");
        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println("Address Menu :");
        System.out.println("1. Add an information of shipping");
        System.out.println("2. Search for an information of shipping");
        System.out.println("3. Update an information of shipping");
        System.out.println("4. Delete an information of shipping");
        System.out.println("5. Show all the informations of shipping");
        System.out.println("6. Go back to the Main Menu");
        do{
            System.out.print("Enter your choice : ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    AddShippingInfo();
                    break;
                case 2:
                    SearchShippingInfo();
                    break;
                case 3:
                    UpdateShippingInfo();
                    break;
                case 4:
                    DeleteShippingInfo();
                    break;
                case 5:
                    GetAllShippingInfo();
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

    public static void AddShippingInfo() throws SQLException {
        System.out.println("\n\n");
        ShippingInfo shippingInfo;

        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Add a new information shipping : ");
            System.out.print("Id office start: ");
            int idOfficeStart = scan.nextInt();
            scan.nextLine();
            System.out.print("Id office end : ");
            int idOfficeEnd = scan.nextInt();
            scan.nextLine();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date localDate= new Date();
            String dateSend = formatter.format(localDate);

            shippingInfo = ShippingInfoFactory.createShippingInfo(idOfficeStart, idOfficeEnd, dateSend);
            if(shippingInfo == null)
                System.out.println("Invalid information");

        } while (shippingInfo == null);

        try {
            ShippingInfoRepository shippingInfoRepository = new ShippingInfoRepository();

            shippingInfoRepository.create(shippingInfo);

            System.out.println("Information Shipping created successfully: " + shippingInfo);

        } catch (SQLException e) {
            System.err.println("Error handling Information Shipping creation: " + e.getMessage());
        }
        MenuShippingInfo();
    }

    public static void SearchShippingInfo() throws SQLException {
        String dateSend;
        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Search for a Information Shipping : ");
            System.out.println("Format date  is : dd/MM/yyyy HH:mm:ss");
            System.out.print("Send date : ");
            dateSend = scan.nextLine();

            if(dateSend == null)
                System.out.println("Invalid information");

        } while (dateSend == null);

        try {
            ShippingInfoRepository shippingInfoRepository = new ShippingInfoRepository();
            ShippingInfo shippingInfo = shippingInfoRepository.read(dateSend);

            if (shippingInfo != null) {
                System.out.println("Information shipping found: " + shippingInfo);
            } else {
                System.out.println("Information Shipping not found for the specified send date.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Address retrieval: " + e.getMessage());
        }
        MenuShippingInfo();
    }

    public static void UpdateShippingInfo() throws SQLException {
        ShippingInfo newShippingInfo;
        String sendDate;
        try {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Information Shipping to Update : ");
            System.out.println("Format of send date  is : dd/MM/yyyy HH:mm:ss ");
            System.out.print("Send date : ");
            sendDate = scan.nextLine();

            ShippingInfoRepository shippingInfoRepository = new ShippingInfoRepository();
            ShippingInfo existingShippingInfo = shippingInfoRepository.read(sendDate);

            System.out.println(existingShippingInfo);

            if (existingShippingInfo != null) {

                do {
                    System.out.println("\n\n");
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("Update Information Shipping : ");
                    System.out.print("Id office start : ");
                    int idOfficeStart = scan2.nextInt();
                    scan2.nextLine();
                    System.out.print("Id office end : ");
                    int idOfficeEnd = scan2.nextInt();
                    scan2.nextLine();

                    newShippingInfo = ShippingInfoFactory.createShippingInfo(idOfficeStart, idOfficeEnd, sendDate);
                    if(newShippingInfo == null)
                        System.out.println("Invalid information");

                } while (newShippingInfo == null);

                shippingInfoRepository.update(newShippingInfo);

                System.out.println("Information Shipping updated successfully: " + newShippingInfo);
            } else {
                System.out.println("Information Shipping not found for the specified city name.");
            }

        } catch (SQLException e) {
            System.err.println("Error handling Address update: " + e.getMessage());
        }
        MenuShippingInfo();
    }

    public static void DeleteShippingInfo() throws SQLException {
        String sendDate;
        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Delete a Information Shipping : ");
            System.out.println("Format date  is : dd/MM/yyyy HH:mm:ss");
            System.out.print("Send date : ");

            sendDate = scan.nextLine();

            if(sendDate== null)
                System.out.println("Invalid information");

        } while (sendDate == null);

        try {
            ShippingInfoRepository shippingInfoRepository = new ShippingInfoRepository();
            boolean isDelete = shippingInfoRepository.delete(sendDate);

            if (isDelete) {
                System.out.println("Information Shipping deleted");
            } else {
                System.out.println("Information Shipping not found for the specified send date.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Address retrieval: " + e.getMessage());
        }
        MenuShippingInfo();
    }

    public static void GetAllShippingInfo() throws SQLException {
        System.out.println("\n\n");
        try {
            ShippingInfoRepository shippingInfoRepository = new ShippingInfoRepository();
            List<ShippingInfo> allShippinginfos = shippingInfoRepository.getAll();

            if (!allShippinginfos.isEmpty()) {
                System.out.println("All Information Shipping:");
                for (ShippingInfo shippingInfo : allShippinginfos) {
                    System.out.println(shippingInfo);
                }
            } else {
                System.out.println("No Information Shipping found.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Information Shipping retrieval: " + e.getMessage());
        }

        MenuShippingInfo();
    }
}
