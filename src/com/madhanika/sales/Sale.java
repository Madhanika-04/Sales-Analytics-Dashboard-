package com.madhanika.sales;
import java.time.LocalDate;

/**
 * Represents a sales record with product details, quantity, pricing, and region information.
 * Includes validation to ensure data integrity.
 */
public class Sale {

    // Private fields for encapsulation
    private String productName;
    private int quantity;
    private double pricePerUnit;
    private String region;
    private LocalDate date;

    /**
     * Parameterized constructor with validation
     * @param productName Name of the product (cannot be null or empty)
     * @param quantity Quantity sold (must be positive)
     * @param pricePerUnit Price per unit (must be positive)
     * @param region Sales region (cannot be null or empty)
     * @param date Date of sale (cannot be null)
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Sale(String productName, int quantity, double pricePerUnit, String region, LocalDate date) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (pricePerUnit <= 0) {
            throw new IllegalArgumentException("Price per unit must be positive");
        }
        if (region == null || region.trim().isEmpty()) {
            throw new IllegalArgumentException("Region cannot be null or empty");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }

        this.productName = productName.trim();
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.region = region.trim();
        this.date = date;
    }

    // getter function for all variables declared (to access private variable)
    public String getProductName() {
        return productName;
    }

    public String getRegion() {
        return region;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTotalPrice() {
        return quantity * pricePerUnit;
    }

    /**
     * Returns a formatted string representation of the sale
     * @return String representation of the sale
     */
    @Override
    public String toString() {
        return String.format("Sale{productName='%s', quantity=%d, pricePerUnit=%.2f, region='%s', date=%s, total=%.2f}",
                           productName, quantity, pricePerUnit, region, date, getTotalPrice());
    }

    /**
     * Checks if this sale equals another object
     * @param obj Object to compare with
     * @return true if objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Sale sale = (Sale) obj;

        if (quantity != sale.quantity) return false;
        if (Double.compare(sale.pricePerUnit, pricePerUnit) != 0) return false;
        if (!productName.equals(sale.productName)) return false;
        if (!region.equals(sale.region)) return false;
        return date.equals(sale.date);
    }

    /**
     * Returns hash code for this sale
     * @return hash code
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = productName.hashCode();
        result = 31 * result + quantity;
        temp = Double.doubleToLongBits(pricePerUnit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + region.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
