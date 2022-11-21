
package com.SalesInvc.View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


    
    public class DialogLine extends JDialog{
    private JTextField nameOfItemField;
    private JTextField countOfItemField;
    private JTextField priceOfItemField;
    private JLabel nameOfItemLbl;
    private JLabel countOfItemLbl;
    private JLabel priceOfItemLbl;
    private JButton okAsABtn;
    private JButton cancelAsABtn;
    
     
    
    public DialogLine(FrameforSalesInvoice InvcFrame)
    {
        nameOfItemField = new JTextField(20);
        nameOfItemLbl = new JLabel("Name Of Item");
        
        countOfItemField = new JTextField(20);
        countOfItemLbl = new JLabel("Count Of Item");
        
        priceOfItemField = new JTextField(20);
        priceOfItemLbl = new JLabel("Item Price");
        
        okAsABtn = new JButton("OK");
        cancelAsABtn = new JButton("Cancel");
        
         okAsABtn.setActionCommand("createLineOK");
        cancelAsABtn.setActionCommand("createLineCancel");
        
         okAsABtn.addActionListener(InvcFrame.getInvcContrl());
        cancelAsABtn.addActionListener(InvcFrame.getInvcContrl());
        setLayout(new GridLayout(4, 2));
        
        add(nameOfItemLbl);
        add(nameOfItemField);
        add(countOfItemLbl);
        add(countOfItemField);
        add(priceOfItemLbl);
        add(priceOfItemField);
        add(okAsABtn);
        add(cancelAsABtn);
        
        pack();
    }

    public JTextField getNameOfItemField() {
        return nameOfItemField;
    }

   

    public JTextField getCountOfItemField() {
        return countOfItemField;
    }

   

    public JTextField getPriceOfItemField() {
        return priceOfItemField;
    }

  

    public JLabel getNameOfItemLbl() {
        return nameOfItemLbl;
    }

    public void setNameOfItemLbl(JLabel nameOfItemLbl) {
        this.nameOfItemLbl = nameOfItemLbl;
    }

    public JLabel getCountOfItemLbl() {
        return countOfItemLbl;
    }

    public void setCountOfItemLbl(JLabel countOfItemLbl) {
        this.countOfItemLbl = countOfItemLbl;
    }

    public JLabel getPriceOfItemLbl() {
        return priceOfItemLbl;
    }

    public void setPriceOfItemLbl(JLabel priceOfItemLbl) {
        this.priceOfItemLbl = priceOfItemLbl;
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

