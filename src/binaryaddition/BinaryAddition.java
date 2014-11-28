package binaryaddition;

/**
 * Given an array a which contains the powers of 2 added up to create an integer k.
 * e.g. if a = {2,4,5},  then k = 2^2 + 2^4 + 2^5  where a^b is a raised to the power b
 *
 * Calculate the number of bits set in 3*k
 */
public class BinaryAddition {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3};

        StringBuilder k = new StringBuilder();
        for (int i = a[a.length - 1], pos = a.length - 1; i >= 0; i--) {
            if (pos > -1 && i == a[pos]) {
                k.append(1);
                --pos;
            } else {
                k.append(0);
            }
        }
        StringBuilder k2 = new StringBuilder(k).append(0);
        k.insert(0, 0);

//        StringBuilder k3 = new StringBuilder();
        boolean carry = false;
        int bitCount = 0;

        for (int i = k.length() - 1; i >= 0; i--) {
            if (k.charAt(i) == '0' && k2.charAt(i) == '0') {
                if (carry) {
                    ++bitCount;
//                    k3.insert(0, 1);
                    carry = false;
//                } else {
//                    k3.insert(0, 0);
                }
            } else if (k.charAt(i) == '1' && k2.charAt(i) == '1') {
                if (carry) {
                    ++bitCount;
//                    k3.insert(0, 1);
                } else {
//                    k3.insert(0, 0);
                    carry = true;
                }
            } else {
                if (carry) {
//                    k3.insert(0, 0);
                } else {
                    ++bitCount;
//                    k3.insert(0, 1);
                }
            }
        }
        if (carry) {
            ++bitCount;
//            k3.insert(0, 1);
        }
        System.out.println(bitCount);
    }
}
