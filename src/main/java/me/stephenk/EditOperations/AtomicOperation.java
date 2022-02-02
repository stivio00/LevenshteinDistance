package me.stephenk.EditOperations;

public record AtomicOperation(OperationType type, int index, String value) {

    public static AtomicOperation newInsert(int index, String value){
        return new AtomicOperation(OperationType.insert, index, value);
    }
    public static AtomicOperation newInsert(int index, char valueChar){
        String value = String.valueOf(valueChar);
        return new AtomicOperation(OperationType.insert, index, value);
    }

    public static AtomicOperation newDelete(int index, String value){
        return new AtomicOperation(OperationType.delete, index, value);
    }

    public static AtomicOperation newDelete(int index, char valueChar){
        String value = String.valueOf(valueChar);
        return new AtomicOperation(OperationType.delete, index, value);
    }

    public static AtomicOperation newSubstitution(int index, String value){
        return new AtomicOperation(OperationType.substitution, index, value);
    }

    public static AtomicOperation newSubstitution(int index, char valueChar){
        String value = String.valueOf(valueChar);
        return new AtomicOperation(OperationType.substitution, index, value);
    }
}
