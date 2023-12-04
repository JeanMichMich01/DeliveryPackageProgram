package za.ac.cput.views;

import za.ac.cput.Main;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.PostOffice;
import za.ac.cput.factory.PostOfficeFactory;
import za.ac.cput.repository.AddressRepository;
import za.ac.cput.repository.PostOfficeRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PostOfficeView {

    public static void MenuPostOffice() throws SQLException {
        System.out.println("\n\n");
        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println("Post Office Menu :");
        System.out.println("1. Add a post office");
        System.out.println("2. Search for a post office");
        System.out.println("3. Update a post office");
        System.out.println("4. Delete a post office");
        System.out.println("5. Show all the post offices");
        System.out.println("6. Go back to the Main Menu");
        do{
            System.out.print("Enter your choice : ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    AddPostOffice();
                    break;
                case 2:
                    SearchPostOffice();
                    break;
                case 3:
                    UpdatePostOffice();
                    break;
                case 4:
                    DeletePostOffice();
                    break;
                case 5:
                    GetAllPostOffice();
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

    public static void AddPostOffice() throws SQLException {
        System.out.println("\n\n");
        PostOffice postOffice;

        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Add a new Post Office : ");
            System.out.print("Name Post Office : ");
            String namePostOffice = scan.nextLine();
            System.out.print("Max Capacity : ");
            int maxCapacity = scan.nextInt();
            scan.nextLine();
            System.out.print("City Name : ");
            String nameCity = scan.nextLine();

            AddressRepository addressRepository = new AddressRepository();
            Address address = addressRepository.read(nameCity);

            postOffice = PostOfficeFactory.createPostOffice(namePostOffice, maxCapacity, address);
            if(postOffice == null)
                System.out.println("Invalid information");

        } while (postOffice == null);

        try {
            PostOfficeRepository postOfficeRepository = new PostOfficeRepository();

            postOfficeRepository.create(postOffice);

            System.out.println("Post Office created successfully: " + postOffice);

        } catch (SQLException e) {
            System.err.println("Error handling Post Office creation: " + e.getMessage());
        }
        MenuPostOffice();
    }

    public static void SearchPostOffice() throws SQLException {
        int idPostOffice;
        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Search for a Post Office : ");
            System.out.print("Id Post Office : ");
            idPostOffice = scan.nextInt();
            scan.nextLine();

            if(idPostOffice <= 0)
                System.out.println("Invalid information");

        } while (idPostOffice <=0 );

        try {
            PostOfficeRepository postOfficeRepository = new PostOfficeRepository();
            PostOffice postOffice = postOfficeRepository.read(String.valueOf(idPostOffice));

            if (postOffice != null) {
                System.out.println("Post Office found: " + postOffice);
            } else {
                System.out.println("Post office not found for the specified id.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Post Office retrieval: " + e.getMessage());
        }
        MenuPostOffice();
    }

    public static void UpdatePostOffice() throws SQLException {
        PostOffice newPostOffice;
        int idPostOffice;
        try {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Post Office to Update : ");
            System.out.print("Id Post Office : ");
            idPostOffice = scan.nextInt();
            scan.nextLine();

            PostOfficeRepository postOfficeRepository = new PostOfficeRepository();
            PostOffice existingPostOffice = postOfficeRepository.read(String.valueOf(idPostOffice));

            System.out.println(existingPostOffice);

            if (existingPostOffice != null) {

                do {
                    System.out.println("\n\n");
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("Update Post Office : ");
                    System.out.print("Name post Office : ");
                    String namePostOffice = scan2.nextLine();
                    System.out.print("Max Capacity : ");
                    int maxCapacity = scan2.nextInt();
                    scan2.nextLine();


                    newPostOffice = PostOfficeFactory.createPostOffice(idPostOffice, namePostOffice, maxCapacity, existingPostOffice.getAddress());
                    if(newPostOffice == null)
                        System.out.println("Invalid information");

                } while (newPostOffice == null);

                postOfficeRepository.update(newPostOffice);

                System.out.println("Post Office updated successfully: " + newPostOffice);
            } else {
                System.out.println("Post Office not found for the specified id.");
            }

        } catch (SQLException e) {
            System.err.println("Error handling Post Office update: " + e.getMessage());
        }
        MenuPostOffice();
    }

    public static void DeletePostOffice() throws SQLException {
        int idPostOffice;
        do {
            System.out.println("\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Delete a Post Office : ");
            System.out.print("Id Post Office : ");
            idPostOffice = scan.nextInt();
            scan.nextLine();

            if(idPostOffice <= 0)
                System.out.println("Invalid information");

        } while (idPostOffice <=0 );

        try {
            PostOfficeRepository postOfficeRepository = new PostOfficeRepository();
            boolean isDelete = postOfficeRepository.delete(String.valueOf(idPostOffice));

            if (isDelete) {
                System.out.println("Post Office deleted");
            } else {
                System.out.println("Post Office not found for the specified id.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Post Office retrieval: " + e.getMessage());
        }
        MenuPostOffice();
    }

    public static void GetAllPostOffice() throws SQLException {
        System.out.println("\n\n");
        try {
            PostOfficeRepository postOfficeRepository = new PostOfficeRepository();
            List<PostOffice> allPostOffice = postOfficeRepository.getAll();

            if (!allPostOffice.isEmpty()) {
                System.out.println("All Post Offices:");
                for (PostOffice postOffice : allPostOffice) {
                    System.out.println(postOffice);
                }
            } else {
                System.out.println("No Post Offices found.");
            }
        } catch (SQLException e) {
            System.err.println("Error handling Post Offices retrieval: " + e.getMessage());
        }

        MenuPostOffice();
    }
}
