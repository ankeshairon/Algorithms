package sorting;

/**
 * Given a sorted array of n integers that has been rotated an unknown number of
 * times, give an O(log n) algorithm that finds an element in the array. You may assume
 * that the array was originally sorted in increasing order.
 * EXAMPLE:
 * Input: find 5 in array (15 16 19 20 25 1 3 4 5 7 10 14)
 * Output: 8 (the index of 5 in the array)
 */
public class SearchInSortedNRotated {

    public static void main(String[] args) {
        int[] a = new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        System.out.println(new SearchInSortedNRotated().find(a, 0, a.length - 1, 5));
    }

    public int find(int[] a, int s, int e, int n) {
        if (s >= e || e >= a.length)
            return -1;
        int mid = (s + e) / 2;
        if (a[mid] == n)
            return mid;
        if (a[s] > a[e])
            if (a[s] < n)
                return find(a, s, mid - 1, n);
            else
                return find(a, mid + 1, e, n);
        else
            if (n > a[mid])
                return find(a, mid + 1, e, n);
            else
                return find(a, s, mid - 1, n);
    }
}
