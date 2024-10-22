/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import za.ac.tut.kiddie.Kiddie;

/**
 *
 * @author MemaniV
 */
public class KiddiesCreche extends JFrame {
    //declare panels
    private JPanel namePnl;
    private JPanel genderPnl;
    private JPanel btnsPnl;
    private JPanel kiddiesTxtAreaPnl;
    private JPanel combinedPnl;
    private JPanel mainPnl;
    
    //declare labels
    private JLabel nameLbl;
    private JLabel genderLbl;
    
    //declare textfield
    private JTextField nameTxtFld;
    
    //declare radio buttons
    private JRadioButton maleRadBtn;
    private JRadioButton femaleRadBtn;
    
    //declare a brutton group
    private ButtonGroup genderBtnGrp;  

    //declare buttons
    private JButton registerKiddieBtn;
    private JButton displayKiddiesBtn;
    
    //declare text area
    private JTextArea kiddiesTxtArea;
    
    //declare scrollpane
    private JScrollPane scrollableTxtArea;
    
    //declare arraylist
    private ArrayList <Kiddie> kiddies;
    
    public KiddiesCreche(){
        //create dynamic list
        this.kiddies = new ArrayList<>();
        
        //set up the frame
        setTitle("CRECHE 4 YOUR KIDDIE");
        setSize(600, 900);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.yellow);
        
        //create panels
        namePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPnl  = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnsPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        kiddiesTxtAreaPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        combinedPnl = new JPanel(new GridLayout(3,1,1,1));
        mainPnl = new JPanel(new BorderLayout());
        
        //create labels
        nameLbl = new JLabel("Name: ");
        genderLbl = new JLabel("Gender: ");
        
        //create textfield
        nameTxtFld = new JTextField(10);
        
        //create radio buttons
        maleRadBtn = new JRadioButton("Male");
        femaleRadBtn= new JRadioButton("Female");
    
        //make one radio button selectable at a time
        genderBtnGrp = new ButtonGroup();  
        genderBtnGrp.add(maleRadBtn);
        genderBtnGrp.add(femaleRadBtn);
        
        //create a buttons
        registerKiddieBtn = new JButton("Register kiddie");
        displayKiddiesBtn = new JButton("Display kiddies");
        
        //add action listeners to buttons
        registerKiddieBtn.addActionListener(new RegisterKiddieBtnHandler());       
        displayKiddiesBtn.addActionListener(new DisplayKiddiesBtnHandler()); 
        
        //create a text area and make it uneditable
        kiddiesTxtArea = new JTextArea(10,35);
        kiddiesTxtArea.setEditable(false);
        
        //make the text area scrollable
        scrollableTxtArea = new JScrollPane(kiddiesTxtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        //add components to panels
        namePnl.add(nameLbl);
        namePnl.add(nameTxtFld);
        
        genderPnl.add(genderLbl);
        genderPnl.add(maleRadBtn);
        genderPnl.add(femaleRadBtn);
                
        btnsPnl.add(registerKiddieBtn);
        btnsPnl.add(displayKiddiesBtn);
        
        kiddiesTxtAreaPnl.add(scrollableTxtArea);
        
        combinedPnl.add(namePnl);
        combinedPnl.add(genderPnl);
        combinedPnl.add(btnsPnl);
        
        mainPnl.add(combinedPnl, BorderLayout.CENTER);
        mainPnl.add(kiddiesTxtAreaPnl, BorderLayout.SOUTH);
        
        //add the main panel to the frame
        add(mainPnl);
        
        //pack the components on the frame
        pack();
        
        //set the frame visible
        setVisible(true);
    }
    
    //inner class for handling events coming from the register button
    private class RegisterKiddieBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String name = nameTxtFld.getText();
            String gender = "Male";
            
            if(femaleRadBtn.isSelected()){
                gender = "Female";
            }
            
            Kiddie kiddie = new Kiddie(name, gender);
            kiddies.add(kiddie);
            
            nameTxtFld.setText("");
            genderBtnGrp.clearSelection();
        }
    }
    
    //inner class for handling events coming from the display button
    private class DisplayKiddiesBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String name, gender, regKiddie = "";
            
            for(Kiddie kiddie:kiddies){
                name = kiddie.getName();
                gender = kiddie.getGender();
                regKiddie = regKiddie + "\nName: " + name + "\nGender: " + gender + "\n";
            }
            
            kiddiesTxtArea.setText(regKiddie);
        }
    }    
       
}
