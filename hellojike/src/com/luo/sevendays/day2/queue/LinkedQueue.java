package com.luo.sevendays.day2.queue;

/**
 * 使用链表实现队列
 */
public class LinkedQueue<E> {
    static final int DEFAULT_LEN=10;
    int size;
    int capacity;
    Node head;
    Node tail;

    LinkedQueue(){
        this.capacity=DEFAULT_LEN;
    }
    LinkedQueue(int len){
        this.capacity=len;
    }

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

    public boolean enqueue(E ele){
        if(size==capacity)
            return false;
        Node node=new Node(ele,null);
        if(head==null||tail==null){
            head=tail=node;
        }else{
            tail.next=node;
            tail=node;
        }
        size++;
        return true;
    }
    public E dequeue(){
        if(size==0)
            return null;
        Node n=head;
        head=head.next;
        if(head==null)
            tail=head;
        size--;
        return (E) n.data;
    }
    public void display(){
        System.out.println("链表队列:size="+size+",head="+head+",tail="+tail);
        Node temp=head;
        while(temp!=null){
            System.out.print(" ->\t"+temp.data);
            temp=temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        LinkedQueue<String> queue=new LinkedQueue<>(10);
        for(int i=0,len=14;i<len;i++){
            String data="data "+i;
            boolean push = queue.enqueue(data);
            System.out.println("enqueue "+data+" , "+push);
            queue.display();
        }
        for(int i=0,len=14;i<len;i++){
            String pop = queue.dequeue();
            System.out.println("dequeue=="+pop);
            queue.display();
        }

    }
}
