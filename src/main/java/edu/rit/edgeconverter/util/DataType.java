package edu.rit.edgeconverter.util;

public enum DataType {
    VARCHAR("Varchar", 0, 1),
    BOOLEAN("Boolean", 1),
    INTEGER("Integer", 2),
    DOUBLE("Double", 3);

    private final String value;
    private final int index;
    private final int defaultLength;

    private DataType(String value, int index) {
        this(value, index, -1); // -1 is for data types without length
    }

    private DataType(String value, int index, int defaultLength) {
        this.value = value;
        this.index = index;
        this.defaultLength = defaultLength;
    }

    public String getValue() {
        return this.value;
    }

    public int getIndex() {
        return this.index;
    }

    public int getDefaultLength() {
        return this.defaultLength;
    }

}
