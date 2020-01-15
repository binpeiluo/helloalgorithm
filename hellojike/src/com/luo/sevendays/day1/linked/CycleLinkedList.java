package com.luo.sevendays.day1.linked;

public class CycleLinkedList {

    private Node head;
    private Node tail;
    private int size;
    public CycleLinkedList(){
        head=tail=new Node(null,null);
        head.next=tail;
    }

    //增 头
    public void addToHead(Object data){
        Node node=new Node(data);
        if(head.next==head){//空表
            node.next=head;
            head.next=node;
            tail=node;
        }else{
            node.next=head.next;
            head.next=node;
        }
        size++;
    }

    //增 尾
    public void addToTail(Object data){
        Node node=new Node(data);
        node.next=tail.next;
        tail.next=node;
        tail=node;
        size++;
    }

    //增 位置
    public void insert(int index,Object data){
        checkIndex(index);
        Node temp=head;
        int i=0;
        while(i<index){
            temp=temp.next;
            i++;
        }
        Node node=new Node(data);
        node.next=temp.next;
        temp.next=node;
        size++;
    }
    //删 头
    public Object removeHead(){
        if(size==0)
            return null;
        Object data = head.next.data;
        head.next=head.next.next;
        return data;
    }
    //删 尾
    public Object removeTail(){
        if(size==0)
            return null;
        Object data=tail.data;
        Node temp=head;
        while(temp.next!=tail){
            temp=temp.next;
        }
        temp.next=tail.next;
        tail=temp;
        return data;
    }


    public int size(){
        return size;
    }

    private void checkIndex(int index){
        if(index<0||index>=size)
            throw new IndexOutOfBoundsException("角标越界,"+index);
    }


    //打印
    public void display(){
        Node temp=head.next;
        System.out.print("循环链表长度:"+size+"\tcontent: "+head.data);
        while(temp!=head&&temp.next!=null){
            System.out.print("-->"+temp.data);
            temp=temp.next;
        }
        System.out.println();
        System.out.println("head=="+head+" , tail="+tail);
    }


    public class Node{
        Node next;
        Object data;
        Node(Object data){
            this(data,null);
        }
        Node(Object data,Node next){
            this.next=next;
            this.data=data;
        }

        @Override
        public String toString() {
            return "[data="+data+"]";
        }
    }

    public static void main(String[] args){
        CycleLinkedList list=new CycleLinkedList();
        list.addToHead(1);
        list.addToHead(2);
        list.addToHead(3);
        list.addToHead(4);
        list.addToTail(5);
        list.addToTail(6);
        list.addToTail(7);
        list.addToTail(8);
        list.display();
        list.insert(0,10);
        list.display();
        list.insert(1,9);
        list.display();
        list.insert(list.size-1,9);
        list.display();
        for(int i=0;i<3;i++){
            int o = (int) list.removeHead();
            System.out.println("removeHead=="+o);
        }
        list.display();
        for(int i=0;i<3;i++){
            int o = (int) list.removeTail();
            System.out.println("removeTail="+o);
        }
        list.display();


    }
}
