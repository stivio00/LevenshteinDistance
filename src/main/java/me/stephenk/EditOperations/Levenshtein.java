package me.stephenk.EditOperations;

import java.util.ArrayList;
import java.util.List;

final public class Levenshtein {

    public static int min3(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static int[][] matrix(String s1, String s2) {
        int rows = s1.length() + 1;
        int columns = s2.length() + 1;

        int[][] matrix = new int[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (Math.min(row, column) == 0) {
                    matrix[row][column] = Math.max(row, column);
                    continue;
                }
                int value = s1.charAt(row - 1) == s2.charAt(column - 1) ? 0 : 1;
                matrix[row][column] = min3(
                        matrix[row - 1][column] + 1,
                        matrix[row][column - 1] + 1,
                        matrix[row - 1][column - 1] + value
                );
            }
        }

        return matrix;
    }

    public static int distance(String s1, String s2) {
        var levenshteinMatrix = matrix(s1, s2);
        return levenshteinMatrix[s1.length()][s2.length()];
    }

    public static List<AtomicOperation> atomicOperations(String s1, String s2) {
        int row = s1.length() + 1;
        int column = s2.length() + 1;
        int[][] matrix = matrix(s1, s2);
        List<AtomicOperation> atomics = new ArrayList<>();

        while (row > 0 && column > 0) {
            if( matrix[row][column] ==  matrix[row-1][column-1]){
                row --;
                column--;
            } else if ( matrix[row][column]  >  matrix[row-1][column-1]) {
                atomics.add(new AtomicOperation(OperationType.substitution, row, String.valueOf(s1.charAt(row-1))));
                row --;
                column--;
            }else if(matrix[row][column]  >  matrix[row-1][column]) {
                atomics.add(new AtomicOperation(OperationType.insert, row, String.valueOf(s1.charAt(row-1))));
                row --;
            }else if(matrix[row][column]  >  matrix[row][column-1]) {
                atomics.add(new AtomicOperation(OperationType.delete, row, String.valueOf(s2.charAt(column-1))));
                column --;}
            else{
                throw new IllegalStateException("Malformed Levenshtein matrix.");
            }
        }

        return atomics;
    }

    public static List<Operation> operationsList(String s1, String s2) {
        var atomicOps = atomicOperations(s1, s2);
        List<Operation> operations = new ArrayList<>();

        return operations;
    }
}
