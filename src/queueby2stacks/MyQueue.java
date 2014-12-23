package queueby2stacks;

import java.util.Stack;

/**
 * Implement a MyQueue class which implements a queue using two stacks.
 */
public class MyQueue<T> {

    public static void main(String[] args) {
        MyQueue<Integer> q = new MyQueue<>();
        for (int i = 0; i < 5; i++) {
            q.offer(i);
        }
        for (int i = 0; i < 6; i++) {
            System.out.println(q.poll());
        }
    }

    Stack<T> in;
    Stack<T> out;

    public MyQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    public void offer(T data) {
        in.push(data);
    }

    public T poll() {
        if (out.empty() && !in.empty()) {
            transferAll(in, out);
        }
        return out.empty() ? null : out.pop();
    }

    private void transferAll(Stack<T> src, Stack<T> dest) {
        while (!src.empty())
            dest.push(src.pop());
    }
}
