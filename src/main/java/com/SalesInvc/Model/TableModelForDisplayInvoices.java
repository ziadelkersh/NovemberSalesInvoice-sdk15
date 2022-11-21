
package com.SalesInvc.Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class TableModelForDisplayInvoices extends AbstractTableModel {
   private ArrayList<InvoiceClass> invoices;
   private String[] Tablecolumns ={"NumOfInvoice" , "DateOfInvoice" , "CustomerOwnerOfInvoice" ,"TotalPriceOfInvoice"};

    public TableModelForDisplayInvoices(ArrayList<InvoiceClass> invoices) {
        this.invoices = invoices;
    }

    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
       return Tablecolumns.length;
    }

    @Override
    public String getColumnName(int Tcolumn) {
        return Tablecolumns[Tcolumn];
    }

    
    @Override
    public Object getValueAt(int rowIndx, int columnIndx) {
       InvoiceClass invcClass = invoices.get(rowIndx);
       switch(columnIndx){
           case 0: return invcClass.getNum();
           case 1: return invcClass.getDate();
           case 2: return invcClass.getCustomer();
           case 3: return invcClass.getTotalInvoicePrise();
           default : return "";
       }
    }
}
