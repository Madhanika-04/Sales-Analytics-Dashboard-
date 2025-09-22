
package com.madhanika.sales;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    private static SalesManager salesManager = new SalesManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Sales Tracker Analyzer ===");
        boolean running = true;

        while (running) {
            showMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    addSale();
                    break;
                case 2:
                    viewAllSales();
                    break;
                case 3:
                    viewTotalRevenue();
                    break;
                case 4:
                    viewTopSellingProducts();
                    break;
                case 5:
                    filterSalesByRegion();
                    break;
                case 6:
                    System.out.println("Thank you for using Sales Tracker Analyzer!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Add Sale");
        System.out.println("2. View All Sales");
        System.out.println("3. View Total Revenue");
        System.out.println("4. View Top Selling Products");
        System.out.println("5. Filter Sales by Region");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void addSale() {
        System.out.println("\n=== Add New Sale ===");
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter price per unit: ");
        double pricePerUnit = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter region: ");
        String region = scanner.nextLine();

        System.out.print("Enter date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();

        try {
            LocalDate date = LocalDate.parse(dateString);
            Sale sale = new Sale(productName, quantity, pricePerUnit, region, date);
            if (salesManager.addSale(sale)) {
                System.out.println("Sale added successfully!");
            } else {
                System.out.println("Failed to add sale.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
