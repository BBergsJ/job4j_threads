package ru.job4j.concurrent.compfut;

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
    }

    public static Sums[] sum(int[][] matrix) {
        return null;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        return null;
    }
}