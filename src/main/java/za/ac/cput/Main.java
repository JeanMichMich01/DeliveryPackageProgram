package za.ac.cput;

import za.ac.cput.util.Server;
import za.ac.cput.views.*;

import java.sql.SQLException;
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
        System.out.println("1. Access to Address");
        System.out.println("2. Access to PostOffice");
        System.out.println("3. Access to Package");
        System.out.println("4. Access to SendReceiverPackage");
        System.out.println("5. Access to ShippingInfo");
        System.out.println("6. Access to Receiver");
        System.out.println("7. Access to Sender");
        System.out.println("8. Exit");
        do{
            System.out.print("Enter your choice : ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    AddressView.MenuAddress();
                    break;
                case 2:
                    PostOfficeView.MenuPostOffice();
                    break;
                case 3:
                    PackageView.MenuPackage();
                    break;
                case 4:
                    SendReceiverPackageView.MenuSendReceiverPackage();
                    break;
                case 5:
                    ShippingInfoView.MenuShippingInfo();
                    break;
                case 6:
                    ReceiverView.MenuReceiver();
                    break;
                case 7:
                    SenderView.MenuSender();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid choice !");
                    choice = 0;
            }
        } while (choice == 0);
    }

}






