package edu.rit.edgeconverter.listeners;

import edu.rit.edgeconverter.model.Field;
import edu.rit.edgeconverter.util.DataType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonListener implements ActionListener {
    private Field field;
    private TextField tfVarcharLength;
    private Button setVarcharLengthButton;
    private TextField tfDefaultValue;
    private boolean dataSaved;
    public RadioButtonListener(Field field, TextField tfVarcharLength, Button setVarcharLengthButton, TextField tfDefaultValue, boolean dataSaved){
        this.field = field;
        this.tfVarcharLength = tfVarcharLength;
        this.setVarcharLengthButton = setVarcharLengthButton;
        this.tfDefaultValue = tfDefaultValue;
        this.dataSaved = dataSaved;
    }

    public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();
        for (DataType dataType: DataType.values()) {
            if(button.getName().equals(dataType.getValue())){
                field.setDataType(dataType);
                break;
            }
        }
        if(button.getName().equals(DataType.VARCHAR.getValue())){
            tfVarcharLength.setText(Integer.toString(DataType.VARCHAR.getDefaultLength()));
            setVarcharLengthButton.setEnabled(true);
        } else {
            tfVarcharLength.setText("");
            setVarcharLengthButton.setEnabled(false);
        }
        tfDefaultValue.setText("");
        field.setDefaultValue("");
        dataSaved = false;
    }
}
