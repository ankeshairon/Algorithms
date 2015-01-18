package mathbased;

public class TrailingZeroesInFactorial {
    public static void main(String[] args) {
        System.out.println(findTrailingZeroesInFactorial(100));
    }

    private static int findTrailingZeroesInFactorial(int n) {
        if (n < 0)
            return 0;

        int count = 0, r;

        for (int p5 = 1; ; p5++) {
            r = (int) (n / Math.pow(5, p5));
            count += r;
            if (r == 0)
                break;
        }
        return count;
    }

}
