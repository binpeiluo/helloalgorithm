package com.luo.leetcode.dataconstruct;

import java.util.Stack;

/*
    155. 最小栈
    设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
    push(x) —— 将元素 x 推入栈中。
    pop() —— 删除栈顶的元素。
    top() —— 获取栈顶元素。
    getMin() —— 检索栈中的最小元素。
     
    示例:
    输入：
    ["MinStack","push","push","push","getMin","pop","top","getMin"]
    [[],[-2],[0],[-3],[],[],[],[]]
    输出：
    [null,null,null,null,-3,null,0,-2]

    解释：
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    minStack.getMin();   --> 返回 -3.
    minStack.pop();
    minStack.top();      --> 返回 0.
    minStack.getMin();   --> 返回 -2.
     
    提示：
    pop、top 和 getMin 操作总是在 非空栈 上调用。
*/
public class No155_MinStack {

    interface IMinStack{
        void push(int x);
        void pop();
        int top();
        int getMin();
    }

    /**
     * 实现思想:
     *      使用java的stack类实现,不同之处在于push时不仅将数组push还将push当前最小数push进去
     */
    static class MinStack implements IMinStack {

        private Stack<Integer> stack;
//        其实也没有必要使用这个变量。可以使用stack.peek获取当前栈元素的最小值
//        private Integer currMin;

        /** initialize your data structure here. */
        public MinStack() {
            stack=new Stack<>();
        }

        @Override
        public void push(int x) {
//            if(currMin==null)
//                currMin=x;
//            else
////                这里currMin取值为栈顶的最小值与当前压入值的较小值，
////                并不是currMin=min(currMin,x)
//                currMin=Math.min(stack.peek(),x);
//            stack.push(x);
//            stack.push(currMin);

            if(stack.isEmpty()){
                stack.push(x);
                stack.push(x);
            }else{
                int min=Math.min(stack.peek(),x);
                stack.push(x);
                stack.push(min);
            }
        }

        @Override
        public void pop() {
            stack.pop();
            stack.pop();
//            当栈为空时，需要维护currMin定义，currMin表示当前栈元素中的最小值。
//            if(stack.isEmpty())
//                currMin=null;
        }

        @Override
        public int top() {
            Integer min = stack.pop();
            Integer peek = stack.peek();
            stack.push(min);
            return peek;
        }

        @Override
        public int getMin() {
            return stack.peek();
        }
    }

    static class MinStack1 implements IMinStack{

        class Node{
            int val;
            int min;
            Node next;
            Node(int val,int min,Node next){
                this.val=val;
                this.min=min;
                this.next=next;
            }
        }

        Node head;

        public MinStack1(){

        }
        @Override
        public void push(int x) {
            if(head==null){
                head=new Node(x,x,null);
            }else{
                head=new Node(x,Math.min(head.min,x),head);
            }
        }

        @Override
        public void pop() {
            head=head.next;
        }

        @Override
        public int top() {
            return head.val;
        }

        @Override
        public int getMin() {
            return head.min;
        }
    }
    public static void main(String[] args){
        MinStack1 stack1=new MinStack1();
        stack1.push(-2);
        stack1.push(0);
        stack1.push(-3);
        stack1.push(-5);
        stack1.push(6);
        stack1.push(10);
        int min = stack1.getMin();
        System.out.println(min);
        stack1.pop();
        int top = stack1.top();
        System.out.println(top);
        min=stack1.getMin();
        System.out.println(min);

    }
}
