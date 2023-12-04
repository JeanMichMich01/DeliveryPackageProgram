package za.ac.cput.views;

import za.ac.cput.Main;
import za.ac.cput.domain.Address;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.repository.AddressRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AddressView {

    public static void MenuAddress() throws SQLException {
        System.out.println("\n\n");
        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println("Address Menu :");
        System.out.println("1. Add a address");
        System.out.println("2. Search for a address");
        System.out.println("3. Update a address");
        System.out.println("4. Delete a address");
        System.out.println("5. Show all the addresses");
        System.out.println("6. Go back to the Main Menu");
        do{
            System.out.print("Enter your choice : ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    AddAddress();
                    break;
                case 2:
                    SearchAddress();
                    break;
                case 3:
                    UpdateAddress();
                    break;
                case 4:
                    DeleteAddress();
                    break;
                case 5:
                    GetAllAddress();
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

    public static void AddAddress() throws SQLException {
        System.out.println("\n\n");
        Address address;

        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Add a new Address : ");
            System.out.print("Street Number : ");
            int numberStreet = scan.nextInt();
            scan.nextLine();
            System.out.print("Street Name : ");
            String nameStreet = scan.nextLine();
            System.out.print("Zip Code : ");
            String zipCode = scan.nextLine();
            System.out.print("City Name : ");
            String nameCity = scan.nextLine();

            address = AddressFactory.createAddress(numberStreet, nameStreet, zipCode, nameCity);
            if(address == null)
                System.out.println("Invalid information");

        } while (address == null);

        try {
            AddressRepository addressRepository = new AddressRepository();

            addressRepository.create(address);

            System.out.println("Address created successfully: " + address);

        } catch (SQLException e) {
            System.err.println("Error handling Address creation: " + e.getMessage());
        }
        MenuAddress();
    }

    public static void SearchAddress() throws SQLException {
        String nameCity;
        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Search for a Address : ");
            System.out.print("City Name : ");
            nameCity = scan.nextLine();

            if(nameCity == null)
                System.out.println("Invalid information");

        } while (nameCity == null);

        try {
            AddressRepository addressRepository = new AddressRepository();
            Address address = addressRepository.read(nameCity);

            if (address != null) {
                System.out.println("Address found: " + address);
            } else {
                System.out.println("Address not found for the specified city name.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Address retrieval: " + e.getMessage());
        }
        MenuAddress();
    }

    public static void UpdateAddress() throws SQLException {
        Address newAddress;
        String nameCity;
        try {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Address to Update : ");
            System.out.print("City Name : ");
            nameCity = scan.nextLine();

            AddressRepository addressRepository = new AddressRepository();
            Address existingAddress = addressRepository.read(nameCity);

            System.out.println(existingAddress);

            if (existingAddress != null) {

                do {
                    System.out.println("\n\n");
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("Add a new Address : ");
                    System.out.print("Street Number : ");
                    int numberStreet = scan2.nextInt();
                    scan2.nextLine();
                    System.out.print("Street Name : ");
                    String nameStreet = scan2.nextLine();
                    System.out.print("Zip Code : ");
                    String zipCode = scan2.nextLine();

                    newAddress = AddressFactory.createAddress(numberStreet, nameStreet, zipCode, nameCity);
                    if(newAddress == null)
                        System.out.println("Invalid information");

                } while (newAddress == null);

                // Call the update method to update the Address in the database
                addressRepository.update(newAddress);

                System.out.println("Address updated successfully: " + newAddress);
            } else {
                System.out.println("Address not found for the specified city name.");
            }

        } catch (SQLException e) {
            System.err.println("Error handling Address update: " + e.getMessage());
        }
        MenuAddress();
    }

    public static void DeleteAddress() throws SQLException {
        String nameCity;
        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Delete a Address : ");
            System.out.print("City Name : ");
            nameCity = scan.nextLine();

            if(nameCity == null)
                System.out.println("Invalid information");

        } while (nameCity == null);

        try {
            AddressRepository addressRepository = new AddressRepository();
            boolean isDelete = addressRepository.delete(nameCity);

            if (isDelete) {
                System.out.println("Address deleted");
            } else {
                System.out.println("Address not found for the specified city name.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Address retrieval: " + e.getMessage());
        }
        MenuAddress();
    }

    public static void GetAllAddress() throws SQLException {
        System.out.println("\n\n");
        try {
            AddressRepository addressRepository = new AddressRepository();
            List<Address> allAddresses = addressRepository.getAll();

            if (!allAddresses.isEmpty()) {
                System.out.println("All Addresses:");
                for (Address address : allAddresses) {
                    System.out.println(address);
                }
            } else {
                System.out.println("No addresses found.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Address retrieval: " + e.getMessage());
        }

        MenuAddress();
    }
}
