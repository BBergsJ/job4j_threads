package ru.job4j.concurrent.compfut;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {


    public static class Sums {
        private int rowSum;
        private int colSum;
        /* Getter and Setter */

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public String toString() {
            return "Sums{"
                    + "rowSum=" + rowSum
                    + ", colSum=" + colSum
                    + '}';
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int sumRow = 0;
            int sumCol = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                sumRow += matrix[i][j];
                sumCol += matrix[j][i];
            }
            Sums enterSum = new Sums();
            enterSum.setRowSum(sumRow);
            enterSum.setColSum(sumCol);
            sums[i] = enterSum;
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            map.put(i, getSum(i, matrix));
        }
        for (Integer key : map.keySet()) {
            sums[key] = map.get(key).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> getSum(int i, int[][] matrix) {
        return CompletableFuture.supplyAsync(() -> {
            int sumRow = 0;
            int sumCol = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                sumRow += matrix[i][j];
                sumCol += matrix[j][i];
            }
            Sums enterSum = new Sums();
            enterSum.setRowSum(sumRow);
            enterSum.setColSum(sumCol);
            return enterSum;
        });
    }
}