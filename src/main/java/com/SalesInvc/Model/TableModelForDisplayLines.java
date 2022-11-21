
package com.SalesInvc.Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelForDisplayLines extends AbstractTableModel {
    
    private ArrayList<InvoiceLine> InvoiceArrLine;
 private String[] Tablecolumns ={"NumOfInvoice","NameOfItem" , "CostOfItem" , "CountOfItems" ,"TotalPriceOfITems"};

    public TableModelForDisplayLines(ArrayList<InvoiceLine> InvoiceArrLine) {
        this.InvoiceArrLine = InvoiceArrLine;
    }
    public ArrayList<InvoiceLine> getInvoiceLine() {
        return InvoiceArrLine;
    
}
 
    @Override
    public int getRowCount() {
        return  InvoiceArrLine.size();
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
        InvoiceLine invcLine = InvoiceArrLine.get(rowIndx);
        switch(columnIndx)
        {
           case 0: return invcLine.getInvc().getNum();
           case 1: return invcLine.getItem();
           case 2: return invcLine.getPrice();
           case 3: return invcLine.getCount();
           case 4: return invcLine.getTotalLinePrise();
           default : return "";
        }
    }
    
}
