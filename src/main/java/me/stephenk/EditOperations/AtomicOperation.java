package me.stephenk.EditOperations;

public record AtomicOperation(OperationType type, int index, String value) {

    public static AtomicOperation newInsert(int index, String value){
        return new AtomicOperation(OperationType.insert, index, value);
    }

    public static AtomicOperation newDelete(int index, String value){
        return new AtomicOperation(OperationType.delete, index, value);
    }

    public static AtomicOperation newSubstitution(int index, String value){
        return new AtomicOperation(OperationType.substitution, index, value);
    }
}
