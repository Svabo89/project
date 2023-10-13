package edu.rit.edgeconverter.ddlCreators;

import edu.rit.edgeconverter.model.Field;
import edu.rit.edgeconverter.model.Table;
import edu.rit.edgeconverter.view.ConverterGUI;

public class MySQLDDLCreator extends DDLCreator{
    protected String databaseName;
    protected StringBuffer sb;
    public MySQLDDLCreator(Table[] inputTables, Field[] inputFields){
        initalize();
        sb=new StringBuffer();
    }
    public MySQLDDLCreator() {

    }

    @Override
    public void createDDL() {
        //ConverterGUI.setReadSuccess(true);
        databaseName = getDatabaseName();
        sb.append("CREATE DATABASE " + databaseName + ";\r\n");
        sb.append("USE " + databaseName + ";\r\n");
        for (int boundCount = 0; boundCount <= maxBound; boundCount++) { //process table in order from least dependent (least number of bound tables) to most dependent
            for (int tableCount = 0; tableCount < numBoundTables.length; tableCount++) { //step through list of tables
                if (numBoundTables[tableCount] == boundCount) { //
                    sb.append("CREATE TABLE " + tables[tableCount].getTableName() + " (\r\n");
                    int[] nativeFields = tables[tableCount].getNativeFields();
                    int[] relatedFields = tables[tableCount].getRelatedFields();
                    boolean[] primaryKey = new boolean[nativeFields.length];
                    int numPrimaryKey = 0;
                    int numForeignKey = 0;
                    for (int nativeFieldCount = 0; nativeFieldCount < nativeFields.length; nativeFieldCount++) { //print out the fields
                        Field currentField = getField(nativeFields[nativeFieldCount]);
                        sb.append("\t" + currentField.getName() + " " + currentField.getDataType());
                        if (currentField.getDataType().getIndex() == 0) { //varchar
                            sb.append("(" + currentField.getVarcharValue() + ")"); //append varchar length in () if data type is varchar
                        }
                        if (currentField.getDisallowNull()) {
                            sb.append(" NOT NULL");
                        }
                        if (!currentField.getDefaultValue().equals("")) {
                            if (currentField.getDataType().getIndex() == 1) { //boolean data type
                                sb.append(" DEFAULT " + convertStrBooleanToInt(currentField.getDefaultValue()));
                            } else { //any other data type
                                sb.append(" DEFAULT " + currentField.getDefaultValue());
                            }
                        }
                        if (currentField.isPrimaryKey()) {
                            primaryKey[nativeFieldCount] = true;
                            numPrimaryKey++;
                        } else {
                            primaryKey[nativeFieldCount] = false;
                        }
                        if (currentField.getFieldBound() != 0) {
                            numForeignKey++;
                        }
                        sb.append(",\r\n"); //end of field
                    }
                    if (numPrimaryKey > 0) { //table has primary key(s)
                        sb.append("CONSTRAINT " + tables[tableCount].getTableName() + "_PK PRIMARY KEY (");
                        for (int i = 0; i < primaryKey.length; i++) {
                            if (primaryKey[i]) {
                                sb.append(getField(nativeFields[i]).getName());
                                numPrimaryKey--;
                                if (numPrimaryKey > 0) {
                                    sb.append(", ");
                                }
                            }
                        }
                        sb.append(")");
                        if (numForeignKey > 0) {
                            sb.append(",");
                        }
                        sb.append("\r\n");
                    }
                    if (numForeignKey > 0) { //table has foreign keys
                        int currentFK = 1;
                        for (int i = 0; i < relatedFields.length; i++) {
                            if (relatedFields[i] != 0) {
                                sb.append("CONSTRAINT " + tables[tableCount].getTableName() + "_FK" + currentFK +
                                        " FOREIGN KEY(" + getField(nativeFields[i]).getName() + ") REFERENCES " +
                                        getTable(getField(nativeFields[i]).getTableBound()).getTableName() + "(" + getField(relatedFields[i]).getName() + ")");
                                if (currentFK < numForeignKey) {
                                    sb.append(",\r\n");
                                }
                                currentFK++;
                            }
                        }
                        sb.append("\r\n");
                    }
                    sb.append(");\r\n\r\n"); //end of table
                }
            }
        }
    }
    protected int convertStrBooleanToInt(String input) { //MySQL uses '1' and '0' for boolean types
        if (input.equals("true")) {
            return 1;
        } else {
            return 0;
        }
    }
    public String generateDatabaseName() { //prompts user for database name
        String dbNameDefault = "MySQLDB";
        databaseName="";
        return databaseName;
    }
    @Override
    public String getDatabaseName() {
        return databaseName;
    }

    @Override
    public String getSQLString() {
        createDDL();
        return sb.toString();
    }

    @Override
    public void initalize() {
        numBoundTables = new int[tables.length];
        maxBound = 0;
        sb = new StringBuffer();

        for (int i = 0; i < tables.length; i++) { //step through list of tables
            int numBound = 0; //initialize counter for number of bound tables
            int[] relatedFields = tables[i].getRelatedFields();
            for (int j = 0; j < relatedFields.length; j++) { //step through related fields list
                if (relatedFields[j] != 0) {
                    numBound++; //count the number of non-zero related fields
                }
            }
            numBoundTables[i] = numBound;
            if (numBound > maxBound) {
                maxBound = numBound;
            }
        }
    }

    @Override
    public String getProductName() {
        return "MySQL";
    }
}
