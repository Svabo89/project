package edu.rit.edgeconverter.model;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector {
    private int numConnector, endPoint1, endPoint2;
    private String endStyle1, endStyle2;
    private boolean isEP1Field, isEP2Field, isEP1Table, isEP2Table;
    private final Logger logger = Logger.getLogger(Field.class.getName());


    public Connector(String inputString){
        // TODO: change the "|"
        try{
            StringTokenizer st = new StringTokenizer(inputString, "|");
            numConnector = Integer.parseInt(st.nextToken());
            endPoint1 = Integer.parseInt(st.nextToken());
            endPoint2 = Integer.parseInt(st.nextToken());
            endStyle1 = st.nextToken();
            endStyle2 = st.nextToken();
        } catch(NullPointerException | NumberFormatException | NoSuchElementException e){
            logger.log(Level.WARNING, "Error parsing input string.", e);
            numConnector = 0;
            endPoint1 = 0;
            endPoint2 = 0;
            endStyle1 = "";
            endStyle2 = "";
        }

        isEP1Field = false;
        isEP2Field = false;
        isEP1Table = false;
        isEP2Table = false;
    }

    public int getNumConnector() {
        return numConnector;
    }

    public void setNumConnector(int numConnector) {
        this.numConnector = numConnector;
    }

    public int getEndPoint1() {
        return endPoint1;
    }

    public void setEndPoint1(int endPoint1) {
        this.endPoint1 = endPoint1;
    }

    public int getEndPoint2() {
        return endPoint2;
    }

    public void setEndPoint2(int endPoint2) {
        this.endPoint2 = endPoint2;
    }

    public String getEndStyle1() {
        return endStyle1;
    }

    public void setEndStyle1(String endStyle1) {
        this.endStyle1 = endStyle1;
    }

    public String getEndStyle2() {
        return endStyle2;
    }

    public void setEndStyle2(String endStyle2) {
        this.endStyle2 = endStyle2;
    }

    public boolean isEP1Field() {
        return isEP1Field;
    }

    public void setIsEP1Field(boolean field1) {
        isEP1Field = field1;
    }

    public boolean isEP2Field() {
        return isEP2Field;
    }

    public void setIsEP2Field(boolean field2) {
        isEP2Field = field2;
    }

    public boolean isEP1Table() {
        return isEP1Table;
    }

    public void setIsEP1Table(boolean table1) {
        isEP1Table = table1;
    }

    public boolean isEP2Table() {
        return isEP2Table;
    }

    public void setIsEP2Table(boolean table2) {
        isEP2Table = table2;
    }
}
