package queueandstacks.setofstacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real life,
 * we would likely start a new stack when the previous stack exceeds some threshold. Implement a data
 * structure SetOfStacks that mimics this. SetOfStacks should be composed of several stacks, and should
 * create a new stack once the previous one exceeds capacity. SetOfStacks.push() and SetOfStacks.pop()
 * should behave identically to a single stack (that is, pop() should return the same values as it would if there
 * were just a single stack).
 */
public class SetOfStacks {

    final int SIZE = 3;
    List<Stack> stacks;

    public SetOfStacks() {
        stacks = new ArrayList<>();
    }

    public void push(Object data) {
        Stack s;
        if (stacks.isEmpty() || lastStack().size() == SIZE) {
            s = new Stack();
            stacks.add(s);
        } else {
            s = lastStack();
        }
        s.push(data);
    }

    public Object pop() {
        Object data = lastStack().pop();
        if (lastStack().size() == 0) {
            stacks.remove(lastStack());
        }
        return data;
    }

    private Stack lastStack() {
        return stacks.get(stacks.size() - 1);
    }

    public static void main(String[] args) {
        SetOfStacks setOfStacks = new SetOfStacks();
        for (int i = 0; i < 15; i++) {
            setOfStacks.push(i);
        }
        for (int i = 0; i < 15; i++) {
            System.out.println(setOfStacks.pop());
        }
        System.out.println();
    }
}
