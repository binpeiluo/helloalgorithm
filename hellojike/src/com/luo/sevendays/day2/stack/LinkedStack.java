package com.luo.sevendays.day2.stack;

/**
 * 使用链表实现一个栈
 */
public class LinkedStack<E> {
    public static final int DEFAULT_LEN=10;
    int capacity;
    int size;
    Node current;
    static class Node<E>{
        E data;
        Node next;
        Node(E data,Node next){
            this.data=data;
            this.next=next;
        }
        @Override
        public String toString() {
            return " [data="+data+"] ";
        }
    }
    LinkedStack(){
        capacity=DEFAULT_LEN;
    }
    LinkedStack(int len){
        capacity=len;
    }

    public boolean push(E ele){
        if(capacity==size)
            return false;
        Node node=new Node(ele,null);
        if(current==null)
            current=node;
        else{
            node.next=current;
            current=node;
        }
        size++;
        return true;
    }
    public E pop(){
        if(size==0)
            return null;
        Node res=current;
        current=current.next;
        size--;
        return (E) res.data;
    }
    public E peek(){
        return (E) current.data;
    }
    public void display(){
        System.out.println("链表栈 :size="+size);
        Node temp=current;
        while(temp!=null){
            System.out.print("->\t"+temp.data);
            temp=temp.next;
        }
        System.out.println();

    }

    public static void main(String[] args){
        LinkedStack<String> stack=new LinkedStack<>(10);
        for(int i=0,len=15;i<len;i++){
            String data="data"+i;
            boolean push = stack.push(data);
            System.out.println("enqueue "+data+" , "+push);
            stack.display();
        }
        for(int i=0,len=15;i<len;i++){
            String pop = stack.pop();
            System.out.println("dequeue=="+pop);
            stack.display();
        }
    }
}
