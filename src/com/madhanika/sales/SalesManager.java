package com.madhanika.sales;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class SalesManager {

    private List<Sale> sales;

    public SalesManager() {
        this.sales = new ArrayList<>();
    }

    public boolean addSale(Sale sale) {
        if (sale != null) {
            sales.add(sale);
            return true;
        }
        return false;
    }

    public List<Sale> getAllSales() {
        List<Sale> result = new ArrayList<>();
        for (Sale sale : sales) {
            result.add(sale);
        }
        return result;
    }

    public int getTotalSalesCount() {
        return sales.size();
    }

    public double getTotalRevenue() {
        double total = 0.0;
        for (Sale sale : sales) {
            total += sale.getTotalPrice();
        }
        return total;
    }

    public List<Sale> getSalesByRegion(String region) {
        List<Sale> result = new ArrayList<>();
        for (Sale sale : sales) {
            if (sale.getRegion().equalsIgnoreCase(region)) {
                result.add(sale);
            }
        }
        return result;
    }

    public double getRevenueByRegion(String region) {
        double total = 0.0;
        for (Sale sale : sales) {
            if (sale.getRegion().equalsIgnoreCase(region)) {
                total += sale.getTotalPrice();
            }
        }
        return total;
    }

    public Map<String, Integer> getTopSellingProducts() {
        Map<String, Integer> productQuantities = new HashMap<>();

        for (Sale sale : sales) {
            String productName = sale.getProductName();
            int quantity = sale.getQuantity();
            if (productQuantities.containsKey(productName)) {
                productQuantities.put(productName, productQuantities.get(productName) + quantity);
            } else {
                productQuantities.put(productName, quantity);
            }
        }

        return productQuantities;
    }

    public Map<String, Double> getProductRevenue() {
        Map<String, Double> productRevenue = new HashMap<>();

        for (Sale sale : sales) {
            String productName = sale.getProductName();
            double revenue = sale.getTotalPrice();
            if (productRevenue.containsKey(productName)) {
                productRevenue.put(productName, productRevenue.get(productName) + revenue);
            } else {
                productRevenue.put(productName, revenue);
            }
        }

        return productRevenue;
    }
}
