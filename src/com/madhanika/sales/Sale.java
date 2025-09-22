package com.madhanika.sales;
import java.time.LocalDate;
public class Sale {
//Declaration
private String productName;
private int Qty;
private Double priceperunit ;
private String Region;
private LocalDate date;
    //parameterized constructor
    public Sale(String Pname, int Qty, Double price, String region, LocalDate date){
    this.productName = Pname;
    this.Qty = Qty;
    this.priceperunit = price;
    this.Region = region;
    this.date = date;
    }
    // getter function for all variables declared (to access private variable)
    public String getProductName(){
        return productName;
    } public String getregion(){
        return Region;
    } public  int getquantity(){
        return Qty;
    }
    public Double getPriceperunit(){
        return priceperunit;
    }
    public LocalDate getDate(){
        return date;
    }
    public  Double getTotalPrice(){
        return Qty * priceperunit;
    }

}
