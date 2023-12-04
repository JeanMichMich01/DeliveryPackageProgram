package za.ac.cput;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.PostOffice;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.PostOfficeFactory;
import za.ac.cput.repository.AddressRepository;
import za.ac.cput.repository.PostOfficeRepository;
import za.ac.cput.util.Server;
import za.ac.cput.views.AddressView;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Server.connect();
        MainMenu();


    }

    public static void MainMenu() throws SQLException {
        System.out.println("\n\n");
        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println("Main Menu :");
        System.out.println("1. Drop off a package");
        System.out.println("2. Retrieve a package");
        System.out.println("3. Package tracking");
        System.out.println("4. Edit all information (admin)");
        System.out.println("5. Exit the program");
        do{
            System.out.print("Enter your choice : ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    break;
                case 4:
                    AdminMenu();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid choice !");
                    choice = 0;
            }
        } while (choice == 0);
    }

    public static void AdminMenu() throws SQLException {
        System.out.println("\n\n");
        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println("Admin Menu :");
        System.out.println("1. Access to Address");
        System.out.println("2. Access to PostOffice");
        System.out.println("3. Access to Package");
        System.out.println("4. Access to SendReceiver");
        System.out.println("5. Access to ShoppingInfo");
        System.out.println("6. Access to Receiver");
        System.out.println("7. Access to Sender");
        System.out.println("8. Go back to the Main Menu");
        do{
            System.out.print("Enter your choice : ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    AddressView.MenuAddress();
                    break;
                case 2:
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    break;
                case 5:
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    break;
                case 7:
                    System.out.println();
                    break;
                case 8:
                    MainMenu();
                    break;
                default:
                    System.out.println("\nInvalid choice !");
                    choice = 0;
            }
        } while (choice == 0);
    }

}






