package com.algo.jolt.SXL_algorithm;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Deque;

public class StackQueueAlgo {
    public static void main(String[] args) {

    }
    
    // 20. 有效的括号
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.peek() != c) {
                return false;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}

// 232. 用栈实现队列
class MyQueue {
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public MyQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();

    }

    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        dumpStackIn();
        return stackOut.pop();
    }

    public int peek() {
        dumpStackIn();
        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    private void dumpStackIn() {
        if (!stackOut.isEmpty()) {
            return;
        } else {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

// 225. 用队列实现栈
class MyStack_OneQueue {

    Queue<Integer> queue;

    public MyStack_OneQueue() {
        queue = new LinkedList<>();
    }

    // 在 push 时维护队列中元素的顺序，按照栈序排列，这样其他操作都可以直接进行
    public void push(int x) {
        queue.offer(x);
        // 把其他元素都弹出再重新插入队列，保持栈序
        int size = queue.size();
        while (size-- > 1) {
            queue.add(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

class MyStack_OneDeque {
    Deque<Integer> queue;

    public MyStack_OneDeque() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.offerLast(x);
    }

    public int pop() {
        return queue.pollLast();
    }

    public int top() {
        return queue.peekLast();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

class MyStack_TwoQueue {
    Queue<Integer> q1;
    Queue<Integer> q2;

    public MyStack_TwoQueue() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        int size = q1.size();
        while (size-- > 0) {
            q2.offer(q1.poll());
        }
        q1.offer(x);
        size = q2.size();
        while (size-- > 0) {
            q1.offer(q2.poll());
        }
    }

    public int pop() {
        return q1.poll();
    }

    public int top() {
        return q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

