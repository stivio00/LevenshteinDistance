package me.stephenk.EditOperations;

public class Operation {
    private OperationType type;
    private int index;
    private int length;
    private String value;

    public Operation() {
    }

    public Operation(OperationType type, int index, int length, String value) {
        this.type = type;
        this.index = index;
        this.length = length;
        this.value = value;
    }

    public static Operation fromAtomic(AtomicOperation atomic) {
        return new Operation(
                atomic.type(),
                atomic.index(),
                1,
                atomic.value()
        );
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
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


    public void addAtomic(AtomicOperation atomic) {
        if (atomic.type() != type){
            throw  new IllegalArgumentException("Atomic operation is not the same in this operation.");
        }

        value += atomic.value();
        length += 1;
    }

    public boolean isAppendable(AtomicOperation atomic){
        return type == atomic.type() && (index + length) == atomic.index();
    }
}
