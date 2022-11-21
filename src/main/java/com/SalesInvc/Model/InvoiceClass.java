
package com.SalesInvc.Model;

import java.util.ArrayList;

public class InvoiceClass {
    private int num;
    private String date;
    private String customer;
    private ArrayList<InvoiceLine> InvoiceLines;
    private double TotalInvoicePrise;

    public InvoiceClass(int num, String date, String customer) {
        this.num = num;
        this.date = date;
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<InvoiceLine> getInvoiceLine() {
        if (InvoiceLines == null)
        {
            InvoiceLines = new ArrayList<>();
        }
        return InvoiceLines;
    }

    public double getTotalInvoicePrise() {
        double TotalPrice =0.0;
        for (InvoiceLine InvoiceLine : getInvoiceLine())
        {
            TotalPrice += InvoiceLine.getTotalLinePrise();
        }
        return TotalPrice;
    }

    public void setTotalInvoicePrise(double TotalInvoicePrise) {
        this.TotalInvoicePrise = TotalInvoicePrise;
    }
    public String getinvoiceAsCSV(){
        return num + "," + date + "," + customer;
    }
    
    
    
    
}
