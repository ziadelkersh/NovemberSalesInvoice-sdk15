
package com.SalesInvc.Model;


public class InvoiceLine {
    private int num;
    private String Item;
    private double price;
    private int count;
    private InvoiceClass invc;

    public InvoiceLine() {
    }


    public InvoiceLine(int num, String Item, double price, int count, InvoiceClass invc) {
        this.num = num;
        this.Item = Item;
        this.price = price;
        this.count = count;
        this.invc = invc;
    }

    public InvoiceLine(String Item, double price, int count, InvoiceClass invc) {
        this.Item = Item;
        this.price = price;
        this.count = count;
        this.invc = invc;
    }
    

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String Item) {
        this.Item = Item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "InvoiceLine{" + "num=" + num + ", Item=" + Item + ", price=" + price + ", count=" + count + '}';
    }
    
    public double getTotalLinePrise(){
        return price * count;
    }

    public InvoiceClass getInvc() {
        return invc;
    }
    public String getlineAsCSV() {
        return invc.getNum() + "," + Item + "," + price + "," + count;
    }
    
}
