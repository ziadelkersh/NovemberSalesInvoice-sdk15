
package com.SalesInvc.Controller;

import com.SalesInvc.Model.InvoiceClass;
import com.SalesInvc.Model.InvoiceLine;
import com.SalesInvc.Model.TableModelForDisplayInvoices;
import com.SalesInvc.Model.TableModelForDisplayLines;
import com.SalesInvc.View.DialogInvoice;
import com.SalesInvc.View.DialogLine;
import com.SalesInvc.View.FrameforSalesInvoice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InvoiceController implements ActionListener  , ListSelectionListener{
    private FrameforSalesInvoice InvcFrame;
    private DialogInvoice dialoginvoice;
    private DialogLine dialogline;
    public InvoiceController (FrameforSalesInvoice InvcFrame){
        this.InvcFrame = InvcFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String CommandAction = e.getActionCommand();
        System.out.println("this is an action :" + CommandAction);
        switch (CommandAction){
            case "Load File":
                fileLoad();
                break;
            case "Save File":
                fileSave();
                break;
            case "Create New Invoice":
                newInvoiceCreation();
                break;
            case "Delete Invoice":
                invoiceDeletion();
                break;
            case "Create Item":
                itemCreation();
                break;
            case "Delete Item":
                itemDeletion();
                break;
            case "createInvoiceCancel":
                CancelCreationInvoice();
                break;
            case "createInvoiceOK":
                OKCreationInvoice();
                break;
            case "createLineCancel":
                CancelCreationLine();
                break;
            case "createLineOK":
                OKCreationLine();
                break;
        }
    }
     @Override
    public void valueChanged(ListSelectionEvent e) {
        int IndxSelected =InvcFrame.getTableOfInvoice().getSelectedRow();
        if(IndxSelected >-1)
        {
         System.out.println("Row Selected is " + IndxSelected);
         InvoiceClass ActualInvoiceClass = InvcFrame.getInvoices().get(IndxSelected);
         InvcFrame.getInvoiceLabelNum().setText("Num is "+ActualInvoiceClass.getNum());
         InvcFrame.getInvoiceLabelDate().setText(ActualInvoiceClass.getDate());
         InvcFrame.getInvoiceLabelCustName().setText("Customer is "+ActualInvoiceClass.getCustomer());
         InvcFrame.getSumTotalInvoice().setText("Total Invoice "+ActualInvoiceClass.getTotalInvoicePrise());
         TableModelForDisplayLines tablemodelfordisplaylines = new TableModelForDisplayLines(ActualInvoiceClass.getInvoiceLine());
         InvcFrame.getTableOfItemsPerInvoice().setModel(tablemodelfordisplaylines);
         tablemodelfordisplaylines.fireTableDataChanged();
        }
    }

    private void fileLoad() {
        JFileChooser Fchoose = new JFileChooser();
        try{
       int Approved = Fchoose.showOpenDialog(InvcFrame);
       if(Approved == JFileChooser.APPROVE_OPTION)
       {
           File chosenFile = Fchoose.getSelectedFile();
           Path chosenheaderpass =Paths.get(chosenFile.getAbsolutePath());
           List<String> LinesHeader = Files.readAllLines(chosenheaderpass);
           System.out.println("Read Invoices");
           ArrayList<InvoiceClass> InvoiceArr = new ArrayList<>();
           for (String LinesHead : LinesHeader )
           {
               try{
               String[] HeaderItemsSplit =LinesHead.split(",");
               int numOfInvoice = Integer.parseInt(HeaderItemsSplit[0]);
               String dateOfInvoice = HeaderItemsSplit[1];
               String customerOwnerOfInvoice = HeaderItemsSplit[2];
               
               InvoiceClass InvcClass = new InvoiceClass(numOfInvoice,dateOfInvoice,customerOwnerOfInvoice);
               InvoiceArr.add(InvcClass);
               } catch(Exception excep){
                   excep.printStackTrace();
       JOptionPane.showMessageDialog(InvcFrame, "Wrong Format for reading lines in load file , please use CSV format", "Error", JOptionPane.ERROR_MESSAGE);
                   
               }
           }
           Approved = Fchoose.showOpenDialog(InvcFrame);
           if(Approved == JFileChooser.APPROVE_OPTION)
           {
               File FileLine = Fchoose.getSelectedFile();
               Path PathLine = Paths.get(FileLine.getAbsolutePath());
               List<String> LinesForEachLine = Files.readAllLines(PathLine);
               System.out.println("Read Lines of Invoices");
               for (String LineForEachLine :LinesForEachLine )
               {
                   try{
                   String LinesPartsSplit[] = LineForEachLine.split(",");
                   int numOfInvoice = Integer.parseInt(LinesPartsSplit[0]);
                   String NameOfItem = LinesPartsSplit[1];
                   double PriceOfItem = Double.parseDouble(LinesPartsSplit[2]);
                   int NumOfItems = Integer.parseInt(LinesPartsSplit[3]);
                   InvoiceClass Invc = null;
                   for(InvoiceClass InvcClass: InvoiceArr)
                   {
                       if(InvcClass.getNum()== numOfInvoice)
                       {
                           Invc = InvcClass;
                           break;
                       }
                   }
                   InvoiceLine invoiceline = new InvoiceLine(numOfInvoice,NameOfItem,PriceOfItem,NumOfItems,Invc );
                   Invc.getInvoiceLine().add(invoiceline);
                   }catch(Exception excep){
                   excep.printStackTrace();
       JOptionPane.showMessageDialog(InvcFrame, "Wrong Format for reading lines in load file , please use CSV format", "Error", JOptionPane.ERROR_MESSAGE);
                   
               }
               }
               System.out.println("get Invoice Lines");
           }
           InvcFrame.setInvoices(InvoiceArr);
           TableModelForDisplayInvoices tablemodelfordisplayinvoices = new TableModelForDisplayInvoices(InvoiceArr);
           InvcFrame.setTablemodelfordisplayinvoices(tablemodelfordisplayinvoices);
           InvcFrame.getTableOfInvoice().setModel(tablemodelfordisplayinvoices);
           InvcFrame.getTablemodelfordisplayinvoices().fireTableDataChanged();
       }
       }catch(IOException excep)
       {excep.printStackTrace();
       JOptionPane.showMessageDialog(InvcFrame, "Wrong Format for Loading File , please use CSV format", "Error", JOptionPane.ERROR_MESSAGE);
       }
       
    }

    private void fileSave() {
        ArrayList<InvoiceClass> invoices = InvcFrame.getInvoices();
        String headersforsave ="";
        String linesforsave = "";
        for (InvoiceClass invc : invoices)
        {
            String invoiceCSV = invc.getinvoiceAsCSV();
            headersforsave += invoiceCSV;
            headersforsave += "\n";
            
            for (InvoiceLine invoiceline : invc.getInvoiceLine())
            {
                String lineCSV = invoiceline.getlineAsCSV();
                linesforsave += lineCSV;
                linesforsave += "\n";
                
            }
            
        }
        try{
        JFileChooser Fchoose = new JFileChooser();
        int saveApproved =Fchoose.showSaveDialog(InvcFrame);
        if (saveApproved == JFileChooser.APPROVE_OPTION)
        {
            File Fileinvoice = Fchoose.getSelectedFile();
            FileWriter fileWriterinvoice = new FileWriter(Fileinvoice);
            fileWriterinvoice.write(headersforsave);
            fileWriterinvoice.flush();
            fileWriterinvoice.close();
            saveApproved= Fchoose.showSaveDialog(InvcFrame);
            if (saveApproved == JFileChooser.APPROVE_OPTION)
            {
               File Fileline = Fchoose.getSelectedFile();
                FileWriter fileWriterline = new FileWriter(Fileline);
            fileWriterline.write(linesforsave);
            fileWriterline.flush();
            fileWriterline.close();
            }
            
        }
        } catch (IOException excep){
            
        }
        
        
        
    }

    private void newInvoiceCreation() {
        dialoginvoice = new DialogInvoice(InvcFrame);
        dialoginvoice.setVisible(true);
           }

    private void invoiceDeletion() {
     int RowSelect = InvcFrame.getTableOfInvoice().getSelectedRow();
     if (RowSelect > -1)
     {
         InvcFrame.getInvoices().remove(RowSelect);
         InvcFrame.getTablemodelfordisplayinvoices().fireTableDataChanged();
     }
    }

    private void itemCreation() {
        dialogline = new DialogLine (InvcFrame);
        dialogline.setVisible(true);
        
       
    }

    private void itemDeletion() {


int RowSelect = InvcFrame.getTableOfInvoice().getSelectedRow();
int RowofItemSelect = InvcFrame.getTableOfItemsPerInvoice().getSelectedRow();
if (RowofItemSelect > -1 && RowSelect>-1)
{
    TableModelForDisplayLines tablemodelfordisplaylines = (TableModelForDisplayLines) InvcFrame.getTableOfItemsPerInvoice().getModel();
    tablemodelfordisplaylines.getInvoiceLine().remove(RowofItemSelect);
    tablemodelfordisplaylines.fireTableDataChanged();
    InvcFrame.getTablemodelfordisplayinvoices().fireTableDataChanged();

    
     }
    }

    private void CancelCreationInvoice() {
      dialoginvoice.setVisible(false);
      dialoginvoice.dispose();
      dialoginvoice = null;
    }

    private void OKCreationInvoice() {
        DateFormat formatOfDate = new SimpleDateFormat("DD/MM/YYYY");
        String dateOfInvoice =dialoginvoice.getDateOfInvoiceField().getText();
        String customerOwnerOfInvoice = dialoginvoice.getCustomerOwnerOfInvoiceField().getText();
        int numOfNewInvoice = InvcFrame.newInvoiceNum();
        try{
            String[] dateSplitted = dateOfInvoice.split("/");
            if(dateSplitted.length< 3)
            {
                JOptionPane.showMessageDialog(InvcFrame, "Wrong Format for the Date", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                int daynum = Integer.parseInt(dateSplitted[0]);
                int monthnum= Integer.parseInt(dateSplitted[1]);
                int yearnum = Integer.parseInt(dateSplitted[2]);
                if (daynum >31 || monthnum > 12 || yearnum >2022 || yearnum<1000)
                {
                    JOptionPane.showMessageDialog(InvcFrame, "Wrong Format for the Date", "Error", JOptionPane.ERROR_MESSAGE); 
                }
                else {
                    InvoiceClass invoiceclass = new InvoiceClass(numOfNewInvoice , dateOfInvoice , customerOwnerOfInvoice);
        InvcFrame.getInvoices().add(invoiceclass);
        InvcFrame.getTablemodelfordisplayinvoices().fireTableDataChanged();
        dialoginvoice.setVisible(false);
      dialoginvoice.dispose();
      dialoginvoice = null;
                }
            }
            
        } catch(Exception excep)
        {
            JOptionPane.showMessageDialog(InvcFrame, "Wrong Format for the Date", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
        
    }

    private void CancelCreationLine() {
        dialogline.setVisible(false);
        dialogline.dispose();
        dialogline = null;
    }

    private void OKCreationLine() {
        String nameOfItem     = dialogline.getNameOfItemField().getText();
        String countAsaString = dialogline.getCountOfItemField().getText();
        String priceAsAString = dialogline.getPriceOfItemField().getText();
        int countOfItem = Integer.parseInt(countAsaString);
        double priceAsANum = Double.parseDouble(priceAsAString);
        int invoiceChosen = InvcFrame.getTableOfInvoice().getSelectedRow();
        if (invoiceChosen != -1) {
           InvoiceClass invc = InvcFrame.getInvoices().get(invoiceChosen);
           InvoiceLine invoiceline = new InvoiceLine(nameOfItem, priceAsANum, countOfItem, invc);
           invc.getInvoiceLine().add(invoiceline);
           TableModelForDisplayLines tablemodelfordisplaylines = (TableModelForDisplayLines) InvcFrame.getTableOfItemsPerInvoice().getModel();
           tablemodelfordisplaylines.fireTableDataChanged();
           InvcFrame.getTablemodelfordisplayinvoices().fireTableDataChanged();
        }
        dialogline.setVisible(false);
        dialogline.dispose();
        dialogline = null;
      
    }

   
    
}
