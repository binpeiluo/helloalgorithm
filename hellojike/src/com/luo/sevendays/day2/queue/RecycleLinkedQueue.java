package com.luo.sevendays.day2.queue;

/**
 * 循环队列
 * 使用链表实现
 */
public class RecycleLinkedQueue<E> {
    static class Node<E>{
        E value;
        Node next;
        Node(E value,Node next){
            this.value=value;
            this.next=next;
        }
    }
    static final int DEFAULT_LEN=10;
    int size;
    int capacity;
    Node head;
    Node tail;

    RecycleLinkedQueue(){
        this.capacity=DEFAULT_LEN;
    }
    RecycleLinkedQueue(int len){
        this.capacity=len;
    }

    public boolean push(E ele){
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
    public E pop(){
        return null;
    }

}
