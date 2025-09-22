package com.madhanika.sales;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages sales records with functionality to add, retrieve, and analyze sales data.
 */
public class SalesManager {

    private List<Sale> sales;

    /**
     * Default constructor that initializes an empty sales list
     */
    public SalesManager() {
        this.sales = new ArrayList<>();
    }

    /**
     * Adds a new sale to the sales list
     * @param sale The sale to add
     * @return true if sale was added successfully
     */
    public boolean addSale(Sale sale) {
        if (sale != null) {
            return sales.add(sale);
        }
        return false;
    }

    /**
     * Returns all sales in the system
     * @return List of all sales
     */
    public List<Sale> getAllSales() {
        return new ArrayList<>(sales);
    }

    /**
     * Returns the total number of sales
     * @return Number of sales
     */
    public int getTotalSalesCount() {
        return sales.size();
    }

    /**
     * Calculates total revenue from all sales
     * @return Total revenue as double
     */
    public double getTotalRevenue() {
        return sales.stream()
                   .mapToDouble(Sale::getTotalPrice)
                   .sum();
    }
}
