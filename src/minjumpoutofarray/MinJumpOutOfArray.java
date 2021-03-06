package minjumpoutofarray;


/***
 *
 Minimum number of jumps to reach end

 Given an array of integers where each element represents the max number of steps that can be made forward from that element. Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). If an element is 0, then cannot move through that element.

 Example:

 Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
 Output: 3 (1-> 3 -> 8 ->9)

 First element is 1, so can only go to 3. Second element is 3, so can make at most 3 steps eg to 5 or 8 or 9.
 */
public class MinJumpOutOfArray {

    Integer[] jumpsToExit;
    int[] input;

    public MinJumpOutOfArray(int[] input) {
        this.input = input;
        jumpsToExit = new Integer[input.length];
    }

    public static void main(String args[]) {
        int[] input = new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        MinJumpOutOfArray main = new MinJumpOutOfArray(input);
        System.out.println(main.getJumpsToExitFrom(0));
    }

    private int getJumpsToExitFrom(int startPos) {
        if (jumpsToExit[startPos] != null) {
            return jumpsToExit[startPos];
        } else if (startPos + input[startPos] >= input.length) {
            jumpsToExit[startPos] = 1;
            return jumpsToExit[startPos];
        }

        int minJump = Integer.MAX_VALUE;
        int nextPos, jumpsToEnd;
        for (int currentJump = 1; currentJump <= input[startPos]; currentJump++) {
            nextPos = startPos + currentJump;
            jumpsToEnd = getJumpsToExitFrom(nextPos);
            if (jumpsToEnd < minJump) {
                minJump = jumpsToEnd;
            }
        }

        jumpsToExit[startPos] = 1 + minJump;
        return jumpsToExit[startPos];
    }

}