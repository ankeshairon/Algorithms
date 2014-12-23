package imagerotation;

import java.util.Arrays;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 *
 * Approach - Swap rows and then take transpose
 */
public class Rotate90_2 {
    public static void main(String[] args) {
        int[][] a = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        print2DArray(a);

        int[] temp;
        for (int i = 0, l = (a.length - 1) / 2; i <= l; i++) {
            temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
        System.out.println();
        print2DArray(a);
        int t;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a[i].length; j++) {
                t = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = t;
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
