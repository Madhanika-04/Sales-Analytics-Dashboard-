
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
                    viewAllRegions();
                    break;
                case 7:
                    viewAllProducts();
                    break;
                case 8:
                    clearAllSales();
                    break;
                case 9:
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
        System.out.println("6. View All Regions");
        System.out.println("7. View All Products");
        System.out.println("8. Clear All Sales");
        System.out.println("9. Exit");
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

    private static void viewAllSales() {
        System.out.println("\n=== All Sales ===");
        var sales = salesManager.getAllSales();

        if (sales.isEmpty()) {
            System.out.println("No sales found.");
            return;
        }

        System.out.println("Total sales: " + salesManager.getTotalSalesCount());
        System.out.println("----------------------------------------");

        for (int i = 0; i < sales.size(); i++) {
            Sale sale = sales.get(i);
            System.out.println((i + 1) + ". " + sale.toString());
        }
    }

    private static void viewTotalRevenue() {
        System.out.println("\n=== Total Revenue ===");
        double totalRevenue = salesManager.getTotalRevenue();
        int totalSales = salesManager.getTotalSalesCount();

        System.out.println("Total number of sales: " + totalSales);
        System.out.println("Total revenue: $" + String.format("%.2f", totalRevenue));

        if (totalSales > 0) {
            double averageRevenue = totalRevenue / totalSales;
            System.out.println("Average revenue per sale: $" + String.format("%.2f", averageRevenue));
        }
    }

    private static void viewTopSellingProducts() {
        System.out.println("\n=== Top Selling Products ===");
        var productQuantities = salesManager.getTopSellingProducts();

        if (productQuantities.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        System.out.println("Product sales quantities:");
        System.out.println("----------------------------------------");

        int rank = 1;
        for (var entry : productQuantities.entrySet()) {
            System.out.println(rank + ". " + entry.getKey() + " - " + entry.getValue() + " units");
            rank++;
        }
    }

    private static void filterSalesByRegion() {
        System.out.println("\n=== Filter Sales by Region ===");
        System.out.print("Enter region to filter: ");
        String region = scanner.nextLine();

        var filteredSales = salesManager.getSalesByRegion(region);
        double regionRevenue = salesManager.getRevenueByRegion(region);

        if (filteredSales.isEmpty()) {
            System.out.println("No sales found for region: " + region);
            return;
        }

        System.out.println("Sales in region: " + region);
        System.out.println("Number of sales: " + filteredSales.size());
        System.out.println("Total revenue: $" + String.format("%.2f", regionRevenue));
        System.out.println("----------------------------------------");

        for (int i = 0; i < filteredSales.size(); i++) {
            Sale sale = filteredSales.get(i);
            System.out.println((i + 1) + ". " + sale.toString());
        }
    }

    private static void viewAllRegions() {
        System.out.println("\n=== All Regions ===");
        var regions = salesManager.getAllRegions();

        if (regions.isEmpty()) {
            System.out.println("No regions found.");
            return;
        }

        System.out.println("Available regions:");
        System.out.println("----------------------------------------");

        for (int i = 0; i < regions.size(); i++) {
            String region = regions.get(i);
            double regionRevenue = salesManager.getRevenueByRegion(region);
            int regionSales = salesManager.getSalesByRegion(region).size();
            System.out.println((i + 1) + ". " + region + " - " + regionSales + " sales, $" + String.format("%.2f", regionRevenue));
        }
    }

    private static void viewAllProducts() {
        System.out.println("\n=== All Products ===");
        var products = salesManager.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        System.out.println("Available products:");
        System.out.println("----------------------------------------");

        for (int i = 0; i < products.size(); i++) {
            String product = products.get(i);
            var productQuantities = salesManager.getTopSellingProducts();
            int quantity = productQuantities.getOrDefault(product, 0);
            System.out.println((i + 1) + ". " + product + " - " + quantity + " units sold");
        }
    }

    private static void clearAllSales() {
        System.out.println("\n=== Clear All Sales ===");
        System.out.print("Are you sure you want to clear all sales? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y")) {
            salesManager.clearAllSales();
            System.out.println("All sales have been cleared.");
        } else {
            System.out.println("Operation cancelled.");
        }
    }
}
