package com.rrybalkin.tasks;

/**
 * Created by Roman Rybalkin
 * 05.10.16
 */
public class MatrixRotate {

    public static void main(String[] args) {
        int[][] sample = new int[3][3];
        sample[0][0] = 1;
        sample[0][1] = 2;
        sample[0][2] = 3;
        sample[1][0] = 4;
        sample[1][1] = 5;
        sample[1][2] = 6;
        sample[2][0] = 7;
        sample[2][1] = 8;
        sample[2][2] = 9;

        System.out.println("Source matrix: ");
        printMatrix(sample);

        System.out.println("Rotated90 to right matrix:");
        printMatrix(rotate90right(sample));

        System.out.println("Rotated90 to left matrix:");
        printMatrix(rotate90left(sample));
    }

    public static int[][] rotate90right(int[][] matrix) {
        int N = matrix.length;
        int[][] rotated = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                int k, m;
                if (i == 0) {
                    m = N-1;
                } else if (i == N-1) {
                    m = 0;
                } else {
                    m = i;
                }
                k = j;
                rotated[k][m] = matrix[i][j];
            }
        return rotated;
    }

    public static int[][] rotate90left(int[][] matrix) {
        int N = matrix.length;
        int[][] rotated = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                int k, m;
                if (j == 0) {
                    k = N-1;
                } else if (j == N-1) {
                    k = 0;
                } else {
                    k = j;
                }
                m = i;
                rotated[k][m] = matrix[i][j];
            }
        return rotated;
    }

    public static void printMatrix(int[][] matrix) {
        int N = matrix.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
