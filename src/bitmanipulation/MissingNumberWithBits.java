package bitmanipulation;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class MissingNumberWithBits {
    public static void main(String[] args) {
        List<BitSet> bitSets = new ArrayList<>();
        for (long no = 0; no < 100; no++) {
            bitSets.add(BitSet.valueOf(new long[]{no}));
        }
        bitSets.remove(29);
        System.out.println(new MissingNumberWithBits().findMissing(bitSets));

    }

    public int findMissing(List<BitSet> l) {
        List<BitSet> one, zero;
        BitSet n = new BitSet(Integer.SIZE);

        for (int col = 0; col <= Integer.SIZE - 1 && l.size() != 1; col++) {
            one = new ArrayList<>();
            zero = new ArrayList<>();

            for (BitSet b : l) {
                if (b.get(col))
                    one.add(b);
                else
                    zero.add(b);
            }

            if (one.size() >= zero.size()) {
                l = zero;
            } else {
                l = one;
                n.set(col);
            }
        }
        return (int) n.toLongArray()[0];
    }
}
