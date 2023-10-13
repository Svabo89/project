package edu.rit.edgeconverter.model;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Table {
    private int numFigure;
    private String tableName;
    private List<Integer> nativeFieldsList;
    private List<Integer> relatedTablesList;
    private int[] nativeFields, relatedTables, relatedFields;
    private final Logger logger = Logger.getLogger(Field.class.getName());


    public Table(String inputString) {
        // TODO: change the "|"
        try{
            StringTokenizer st = new StringTokenizer(inputString, "|");
            numFigure = Integer.parseInt(st.nextToken());
            tableName = st.nextToken();
        } catch(NullPointerException | NumberFormatException | NoSuchElementException e){
            logger.log(Level.WARNING, "Error parsing input string.", e);
            numFigure = 0;
            tableName = "";
        }

        relatedTablesList = new ArrayList<>();
        nativeFieldsList = new ArrayList<>();
    }

    public int getNumFigure() {
        return numFigure;
    }

    public String getTableName() {
        return tableName;
    }

    public List<Integer> getAllNativeFields() {
        return nativeFieldsList;
    }

    public List<Integer> getAllRelatedTables() {
        return relatedTablesList;
    }

    public void setAllRelatedTables(List<Integer> relatedTablesList) {
        this.relatedTablesList = relatedTablesList;
    }

    public int[] getNativeFields() {
        return nativeFields;
    }

    public void setNativeFields(int[] nativeFields) {
        this.nativeFields = nativeFields;
    }

    public void addNativeField(int value) { nativeFieldsList.add(value); }

    public int[] getRelatedTables() {
        return relatedTables;
    }

    public void setRelatedTables(int[] relatedTables) {
        this.relatedTables = relatedTables;
    }

    public void addRelatedTable(int relatedTable) {
        relatedTablesList.add(relatedTable);
    }

    public int[] getRelatedFields() {
        return relatedFields;
    }

    public void setRelatedFields(int[] relatedFields) {
        this.relatedFields = relatedFields;
    }

    public void setRelatedField(int index, int relatedValue) {
        relatedFields[index] = relatedValue;
    }


    public void moveFieldUp(int index) {
        // TODO: method for moving field closer to beginning of the list
    }

    public void moveFieldDown(int index) {
        // TODO: method for moving field closer to the end of the list
    }

    public void makeArrays() { //convert the ArrayLists into int[]
        Integer[] temp;
        temp = (Integer[]) nativeFieldsList.toArray(new Integer[nativeFieldsList.size()]);
        nativeFields = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            nativeFields[i] = temp[i];
        }

        temp = (Integer[]) relatedTablesList.toArray(new Integer[relatedTablesList.size()]);
        relatedTables = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            relatedTables[i] = temp[i];
        }

        relatedFields = new int[nativeFields.length];
        Arrays.fill(relatedFields, 0);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Table: ").append(numFigure).append("\r\n");
        sb.append("{\r\n");
        sb.append("TableName: ").append(tableName).append("\r\n");
        sb.append("NativeFields: ");
        for (int i = 0; i < nativeFields.length; i++) {
            sb.append(nativeFields[i]);
            if (i < (nativeFields.length - 1)) {
                sb.append("|");
            }
        }
        sb.append("\r\nRelatedTables: ");
        for (int i = 0; i < relatedTables.length; i++) {
            sb.append(relatedTables[i]);
            if (i < (relatedTables.length - 1)) {
                sb.append("|");
            }
        }
        sb.append("\r\nRelatedFields: ");
        for (int i = 0; i < relatedFields.length; i++) {
            sb.append(relatedFields[i]);
            if (i < (relatedFields.length - 1)) {
                sb.append("|");
            }
        }
        sb.append("\r\n}\r\n");

        return sb.toString();


    }
}