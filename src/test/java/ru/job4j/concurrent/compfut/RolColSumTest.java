package ru.job4j.concurrent.compfut;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RolColSumTest {

    @Test
    public void when3x3MatrixSum() {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] rsl = RolColSum.sum(matrix);
        assertThat(24, is(rsl[2].getRowSum()));
        assertThat(18, is(rsl[2].getColSum()));
    }

    @Test
    public void when3x3MatrixAsync() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] rsl = RolColSum.asyncSum(matrix);
        assertThat(24, is(rsl[2].getRowSum()));
        assertThat(18, is(rsl[2].getColSum()));
    }
}