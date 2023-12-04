package za.ac.cput.views;

import za.ac.cput.Main;
import za.ac.cput.domain.StatuePackage;
import za.ac.cput.domain.Package;
import za.ac.cput.factory.PackageFactory;
import za.ac.cput.repository.PackageRepository;
import java.lang.Enum;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PackageView {
    public static void MenuPackage() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Package Menu:");
        System.out.println("1. Create a package");
        System.out.println("2. Read a package");
        System.out.println("3. Update a package");
        System.out.println("4. Delete a package");
        System.out.println("5. View all packages");
        System.out.println("6. Go back to the Main Menu");
        do{
            System.out.print("Choose an option : ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createPackage();
                    break;
                case 2:
                    readPackage();
                    break;
                case 3:
                    updatePackage();
                    break;
                case 4:
                    deletePackage();
                    break;
                case 5:
                    getAllPackages();
                    break;
                case 6:
                    Main.MainMenu();
                    break;
                default:
                    System.out.println("\nInvalid choice !");
                    choice=0;
            }
        } while (choice==0);
    }

    private static void createPackage() throws SQLException {
        System.out.println("\n\n");
        Package pack;
        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Add a new Package : ");
            System.out.print("Weight : ");
            int weight = scan.nextInt();
            System.out.print("Fragile or not : ");
            boolean fragile = scan.nextBoolean();

            pack = PackageFactory.createPackage(weight, fragile);
            if(pack == null)
                System.out.println("Invalid information");

        } while (pack == null);

        try {
            PackageRepository packRepository = new PackageRepository();

            packRepository.create(pack);

            System.out.println("Package created successfully");

        } catch (SQLException e) {
            System.err.println("Error handling package creation: " + e.getMessage());
        }
        MenuPackage();
    }

    private static void readPackage() throws SQLException {
        int idPackage;
        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Search for a package : ");
            System.out.print("Id Package : ");
            idPackage = scan.nextInt();

            if(idPackage < 1)
                System.out.println("Invalid information");

        } while (idPackage < 1);

        try {
            PackageRepository packRepository = new PackageRepository();
            Package pack = packRepository.read(idPackage);

            if (pack != null) {
                System.out.println("Package found: " + pack);
            } else {
                System.out.println("Package not found for the specified Id package.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling package retrieval: " + e.getMessage());
        }
        MenuPackage();
    }

    private static void updatePackage() throws SQLException {
        Package newPackage = null;
        int idPackage;
        try {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Package to Update: ");
            System.out.print("Id Package : ");
            idPackage = scan.nextInt();

            PackageRepository packRepository = new PackageRepository();
            Package existingPackage = packRepository.read(idPackage);

            System.out.println(existingPackage);

            if (existingPackage != null) {

                do {
                    System.out.println("\n\n");
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("Update package : ");
                    System.out.print("Weight : ");
                    int weight = scan2.nextInt();
                    scan2.nextLine();
                    System.out.print("Fragile or not : ");
                    boolean fragile = scan2.nextBoolean();
                    System.out.print("Status (InTransit, Arrived, Picked): ");
                    String statusInput = scan2.next();
                    StatuePackage status = StatuePackage.valueOf(statusInput);


                    newPackage = PackageFactory.createPackage(idPackage, weight, fragile, status);
                    if(newPackage == null)
                        System.out.println("Invalid information");

                } while (newPackage == null);
                packRepository.update(newPackage);

                System.out.println("Package updated successfully: " + newPackage);
            } else {
                System.out.println("Package not found for the specified Id package.");
            }

        } catch (SQLException e) {
            System.err.println("Error handling package update: " + e.getMessage());
        }
        MenuPackage();
    }

    private static void deletePackage() throws SQLException {
        int idPackage;
        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Delete a package : ");
            System.out.print("Id package : ");
            idPackage = scan.nextInt();

            if(idPackage < 1)
                System.out.println("Invalid information");

        } while (idPackage < 1);

        try {
            PackageRepository packRepository = new PackageRepository();
            boolean isDelete = packRepository.delete(idPackage);

            if (isDelete) {
                System.out.println("Package deleted");
            } else {
                System.out.println("Package not found for the specified Id package.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling package retrieval: " + e.getMessage());
        }
        MenuPackage();
    }

    private static void getAllPackages() throws SQLException {
        System.out.println("\n\n");
        try {
            PackageRepository packRepository = new PackageRepository();
            List<Package> allPackages = packRepository.getAll();

            if (!allPackages.isEmpty()) {
                System.out.println("All Packages:");
                for (Package pack : allPackages) {
                    System.out.println(pack);
                }
            } else {
                System.out.println("No packages found.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling package retrieval: " + e.getMessage());
        }

        MenuPackage();
    }
}