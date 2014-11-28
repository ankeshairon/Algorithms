package numberofdiscintersections;

import java.util.Arrays;

/**
 *

 Given an array A of N integers, we draw N discs in a 2D plane such that the I-th disc is centered on (0,I) and has a radius of A[I]. We say that the J-th disc and K-th disc intersect if J ≠ K and J-th and K-th discs have at least one common point.

 Write a function:

 class Solution { public int solution(int[] A); }

 that, given an array A describing N discs as explained above, returns the number of pairs of intersecting discs. For example, given N=6 and:

 A[0] = 1  A[1] = 5  A[2] = 2
 A[3] = 1  A[4] = 4  A[5] = 0

 intersecting discs appear in eleven pairs of elements:

 0 and 1,
 0 and 2,
 0 and 4,
 1 and 2,
 1 and 3,
 1 and 4,
 1 and 5,
 2 and 3,
 2 and 4,
 3 and 4,
 4 and 5.

 so the function should return 11.

 The function should return −1 if the number of intersecting pairs exceeds 10,000,000.

 Assume that:

 N is an integer within the range [0..100,000];
 each element of array A is an integer within the range [0..2147483647].

 Complexity:

 expected worst-case time complexity is O(N*log(N));
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).

 Elements of input arrays can be modified.

 */
class NumberOfDiscIntersections {
    public static void main(String[] args) {
        System.out.println(new NumberOfDiscIntersections().solution(new int[]{1,2,3}));
    }

    public int solution(int[] A) {
        Point[] points = new Point[2 * A.length];

        for (int i = 0; i < A.length; i++) {
            points[2 * i] = new Point(i - (long)A[i], true);
            points[2 * i + 1] = new Point(i + (long)A[i], false);
        }
        Arrays.sort(points);

        int multiplier = 0;
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            if (points[i].start) {
                count = count + multiplier;
                if (count > 10_000_000) {
                    return -1;
                }
                ++multiplier;
            } else {
                --multiplier;
            }
        }
        return count;
    }
}

class Point implements Comparable<Point> {
    Long value;
    Boolean start;

    public Point(Long value, boolean start) {
        this.value = value;
        this.start = start;
    }

    @Override
    public int compareTo(Point that) {
        int c = value.compareTo(that.value);
        if (c == 0) {
            c = -start.compareTo(that.start);
        }
        return c;
    }
}