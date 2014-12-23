package imagerotation;

import java.util.Arrays;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 * <p>
 * Approach - Rotate directly
 */
public class Rotate90_1 {
    public static void main(String[] args) {
        int[][] a = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        print2DArray(a);

        int n = a.length - 1;
        int temp;
        for (int i = 0; i < n - i; i++) {
            for (int j = i; j < n - i; j++) {
                temp = a[i][j];
                a[i][j] = a[n - j][i];
                a[n - j][i] = a[n - i][n - j];
                a[n - i][n - j] = a[j][n - i];
                a[j][n - i] = temp;
            }
        }

        System.out.println();
        print2DArray(a);
    }

    private static void print2DArray(int[][] as) {
        for (int[] a : as) {
            System.out.println(Arrays.toString(a));
        }
    }
}
