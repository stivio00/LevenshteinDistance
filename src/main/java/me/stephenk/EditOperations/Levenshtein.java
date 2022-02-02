package me.stephenk.EditOperations;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

final public class Levenshtein {

    public static int min3(int a, int b, int c) {
        return min(min(a, b), c);
    }

    public static float min3(float a, float b, float c) {
        return min(min(a, b), c);
    }

    public static float[][] matrix(String s1, String s2) {
        return matrix(s1, s2, 1.0f, 1.0f, 1.0f);
    }

    public static float[][] matrix(String s1, String s2,
                                   float cost_insert,
                                   float cost_delete,
                                   float cost_subst) {

        int rows = s1.length() + 1;
        int columns = s2.length() + 1;

        float[][] matrix = new float[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (min(row, column) == 0) {
                    matrix[row][column] = max(row, column);
                    continue;
                }

                float value = s1.charAt(row - 1) == s2.charAt(column - 1)
                        ? 0.0f : cost_subst;
                matrix[row][column] = min3(
                        matrix[row - 1][column] + cost_insert,
                        matrix[row][column - 1] + cost_delete,
                        matrix[row - 1][column - 1] + value
                );
            }
        }
        return matrix;
    }

    public static float distance(String s1, String s2) {
        var levenshteinMatrix = matrix(s1, s2);
        return levenshteinMatrix[s1.length()][s2.length()];
    }

    public static List<AtomicOperation> atomicOperations(String s1, String s2) {
        int row = s1.length() + 1;
        int column = s2.length() + 1;
        float[][] matrix = matrix(s1, s2);
        List<AtomicOperation> atomics = new ArrayList<>();

        while (row > 0 && column > 0) {
            if (matrix[row][column] == matrix[row - 1][column - 1]) {
                row--;
                column--;
            } else if (matrix[row][column] > matrix[row - 1][column - 1]) {
                atomics.add(AtomicOperation.newSubstitution(row, s1.charAt(row - 1)));
                row--;
                column--;
            } else if (matrix[row][column] > matrix[row - 1][column]) {
                atomics.add(AtomicOperation.newInsert(row, s1.charAt(row - 1)));
                row--;
            } else if (matrix[row][column] > matrix[row][column - 1]) {
                atomics.add(AtomicOperation.newDelete(row, s2.charAt(column - 1)));
                column--;
            } else {
                throw new IllegalStateException("Malformed Levenshtein matrix.");
            }
        }

        return atomics;
    }

    public static List<Operation> operationsList(String s1, String s2) {
        var atomicOps = atomicOperations(s1, s2);
        List<Operation> operations = new ArrayList<>();
        for (AtomicOperation atomic : atomicOps) {
            if (operations.size() == 0) {
                operations.add(Operation.fromAtomic(atomic));
                continue;
            }

            var lastOp = operations.get(operations.size() - 1);
            if (lastOp.isAppendable(atomic)) {
                lastOp.addAtomic(atomic);
            } else {
                operations.add(Operation.fromAtomic(atomic));
            }
        }
        return operations;
    }
}
