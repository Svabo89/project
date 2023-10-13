package edu.rit.edgeconverter.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CreateDDLButtonListener implements ActionListener {

    private File outputDir;

    private String sqlString;

    public static final String CANCELLED = "CANCELLED";

    private void setOutputDir(){
        //TODO: Create IO class with static methods in util package,
        // add method to it and call it from there (remove this method from this class)
    }

    private void getOutputClasses(){
        //TODO: Create IO class with static methods in util package,
        // add method to it and call it from there (remove this method from this class)
    }

    private String getSQLStatements(){
        //TODO: Create CreateDDL class with static methods in util package,
        // add method to it and call it from there (remove this method from this class)
        return "";
    }

    private void writeSQL(String sqlString){
        //TODO: Create CreateDDL class with static methods in util package,
        // add method to it and call it from there (remove this method from this class)
    }
    public void actionPerformed(ActionEvent ae) {
        while (outputDir == null) {
            JOptionPane.showMessageDialog(null, "You have not selected a path that contains valid output definition files yet.\nPlease select a path now.");
            setOutputDir();
        }
        getOutputClasses(); //in case outputDir was set before a file was loaded and EdgeTable/EdgeField objects created
        sqlString = getSQLStatements();
        if (sqlString.equals(CANCELLED)) {
            return;
        }
        writeSQL(sqlString);
    }
}
