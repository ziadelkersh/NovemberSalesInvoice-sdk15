
package com.SalesInvc.View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class DialogInvoice extends JDialog {
    private JTextField customerOwnerOfInvoiceField;
    private JTextField DateOfInvoiceField;
    private JLabel customerOwnerOfInvoiceLbl;
    private JLabel DateOfInvoiceLbl;
    private JButton okAsABtn;
    private JButton cancelAsABtn;
    
    public DialogInvoice(FrameforSalesInvoice InvcFrame)
    {
        customerOwnerOfInvoiceLbl =new JLabel("Customer Owner of Invoice:");
        customerOwnerOfInvoiceField= new JTextField(20);
        DateOfInvoiceLbl = new JLabel("Date of Invoice:");
       DateOfInvoiceField = new JTextField(20);
        okAsABtn = new JButton("OK");
        cancelAsABtn = new JButton("Cancel");
        
        okAsABtn.setActionCommand("createInvoiceOK");
        cancelAsABtn.setActionCommand("createInvoiceCancel");
        
        okAsABtn.addActionListener(InvcFrame.getInvcContrl());
        cancelAsABtn.addActionListener(InvcFrame.getInvcContrl());
        setLayout(new GridLayout(4, 2));
        
        add(DateOfInvoiceLbl);
        add(DateOfInvoiceField);
        add(customerOwnerOfInvoiceLbl);
        add(customerOwnerOfInvoiceField);
        add(okAsABtn);
        add(cancelAsABtn);
        
        pack();
    }

    public JTextField getCustomerOwnerOfInvoiceField() {
        return customerOwnerOfInvoiceField;
    }

    public void setCustomerOwnerOfInvoiceField(JTextField customerOwnerOfInvoiceField) {
        this.customerOwnerOfInvoiceField = customerOwnerOfInvoiceField;
    }

    public JTextField getDateOfInvoiceField() {
        return DateOfInvoiceField;
    }


    public JLabel getCustomerOwnerOfInvoiceLbl() {
        return customerOwnerOfInvoiceLbl;
    }

    public void setCustomerOwnerOfInvoiceLbl(JLabel customerOwnerOfInvoiceLbl) {
        this.customerOwnerOfInvoiceLbl = customerOwnerOfInvoiceLbl;
    }

    public JLabel getDateOfInvoiceLbl() {
        return DateOfInvoiceLbl;
    }

    public void setDateOfInvoiceLbl(JLabel DateOfInvoiceLbl) {
        this.DateOfInvoiceLbl = DateOfInvoiceLbl;
    }

    public JButton getOkAsABtn() {
        return okAsABtn;
    }

    public void setOkAsABtn(JButton okAsABtn) {
        this.okAsABtn = okAsABtn;
    }

    public JButton getCancelAsABtn() {
        return cancelAsABtn;
    }

    public void setCancelAsABtn(JButton cancelAsABtn) {
        this.cancelAsABtn = cancelAsABtn;
    }
   
    
}
