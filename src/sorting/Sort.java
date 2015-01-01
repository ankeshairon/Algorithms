package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sort {
    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        for (int i = 0; i < 10; i++) {
            a[i] = 10 - i;
        }
        bubbleSort(a);
        insertionSort(a);
        List<Integer> l = mergeSort(Arrays.asList(a));
        System.out.println(l);
        quickSort(a);
        System.out.println(Arrays.toString(a));

    }

    private static void quickSort(Integer[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(Integer[] a, int s, int e) {
        if (s >= e  || e >= a.length)
            return;
        int p = (s + e) / 2;
        int l = s;
        swap(a, p, e);
        p = e;
        for (int i = s; i < e; i++) {
            if (a[i] < a[p]) {
                swap(a, i, l);
                ++l;
            }
        }
        swap(a, l, p);
        p = l;
        quickSort(a, s, p - 1);
        quickSort(a, p + 1, e);
    }

    private static void swap(Integer[] a, int i, int j) {
        Integer t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static List<Integer> mergeSort(List<Integer> l) {
        if (l.size() <= 1)
            return l;
        int mid = l.size() / 2;
        List<Integer> l1 = mergeSort(l.subList(0, mid));
        List<Integer> l2 = mergeSort(l.subList(mid, l.size()));
        return merge(l1, l2);
    }

    private static List<Integer> merge(List<Integer> l1, List<Integer> l2) {
        List<Integer> l3 = new ArrayList<>(l1.size() + l2.size() + 1);
        int i = 0, j = 0, imax = l1.size(), jmax = l2.size();
        for (; i < imax && j < jmax; ) {
            if (l1.get(i) < l2.get(j))
                l3.add(l1.get(i++));
            else
                l3.add(l2.get(j++));
        }
        while (i < imax)
            l3.add(l1.get(i++));
        while (j < jmax)
            l3.add(l2.get(j++));
        return l3;
    }


    private static void insertionSort(Integer[] a) {
        int t;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i; j < a.length; j++) {
                if (a[j] < a[i]) {
                    t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }
        }
    }

    private static void bubbleSort(Integer[] a) {
        boolean swapped;
        int t;
        for (int i = 0; i < a.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                    swapped = true;
                }
            }
            if (!swapped)
                return;
        }
    }


}
