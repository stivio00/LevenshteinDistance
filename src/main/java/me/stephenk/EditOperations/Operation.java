package me.stephenk.EditOperations;

public class Operation {
    private Operation type;
    private int index;
    private int length;
    private String value;

    public Operation() {
    }

    public Operation(Operation type, int index, int length, String value) {
        this.type = type;
        this.index = index;
        this.length = length;
        this.value = value;
    }

    public Operation getType() {
        return type;
    }

    public void setType(Operation type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
