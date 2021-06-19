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
            }
            for (int l = 0; l < matrix[0].length; l++) {
                sumCol += matrix[l][i];
            }
            Sums enterSum = new Sums();
            enterSum.setRowSum(sumRow);
            enterSum.setColSum(sumCol);
            sums[i] = enterSum;
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        int n = matrix.length;
        Sums[] sums = new Sums[n * 2];

        return sums;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] test =  RolColSum.sum(matrix);

        for (Sums s : test) {
            System.out.println(s);
        }
    }
}