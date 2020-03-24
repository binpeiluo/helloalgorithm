package com.luo.labuladong.dataconstruct;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 使用队列实现栈或是使用栈实现队列
 */
public class QueueStack {
    /**
     * 队列API
     */
    interface IQueue<T>{
        void push(T val);
        T pop();
        boolean empty();
        T peek();
    }

    /**
     * 使用栈实现队列
     */
    static class MyQueue implements IQueue<Integer> {

//        用于储存
        Stack<Integer> s1;
//        用于倒序获取队列头部
        Stack<Integer> s2;

        MyQueue(){
//            底层使用先进后出的栈实现,只能使用入栈和弹出等基本操作
            this.s1=new Stack<>();
            this.s2=new Stack<>();
        }

        @Override
        public void push(Integer val) {
            s1.push(val);
        }

        @Override
        public Integer pop() {
            peek();
            return s2.pop();
        }

        @Override
        public boolean empty() {
            return s1.empty()&&s2.empty();
        }

        @Override
        public Integer peek() {
            if(s2.empty()){
                while(!s1.empty()){
                    s2.push(s1.pop());
                }
            }
            return s2.peek();
        }
    }

    interface IStack<T>{
        void push(T val);
        T pop();
        T peek();
        boolean empty();
    }

    static class MyStack implements IStack<Integer>{

        Queue<Integer> q;
//        使用变脸储存栈顶,不然需要peek复杂度为O(n)
        Integer topEle;

        MyStack(){
//            内部使用队列实现,只能使用入队或者出队操作
//            在java中是使用 offer入队,poll出队 (不会抛异常)
//                            add入队,remove出队(空会抛异常)
            q=new LinkedList<>();
        }

        @Override
        public void push(Integer val) {
            q.offer(val);
            topEle=val;
        }

        @Override
        public Integer pop() {
            int size = q.size();
            while(size>2){
                q.offer(q.poll());
            }
            topEle = q.peek();
            q.offer(q.poll());
            return q.poll();
        }

        @Override
        public Integer peek() {
            return topEle;
        }

        @Override
        public boolean empty() {
            return q.isEmpty();
        }
    }




    private static void testQueue(){
        MyQueue queue=new MyQueue();
        for (int i = 0; i < 5; i++) {
            queue.push(i);
        }
        Integer pop = queue.pop();
        System.out.println("pop ="+pop);
        pop = queue.pop();
        System.out.println("pop ="+pop);
        queue.push(5);
        queue.push(6);
        Integer peek = queue.peek();
        System.out.println("peek ="+peek);
        while(!queue.empty()){
            pop= queue.pop();
            System.out.println("pop ="+pop);
        }
    }

    public static void main(String[] args){
        testQueue();
    }
}
