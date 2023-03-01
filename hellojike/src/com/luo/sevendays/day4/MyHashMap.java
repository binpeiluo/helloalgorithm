package com.luo.sevendays.day4;

import java.lang.reflect.Array;

/**
 * 实现一个基于链表法解决hash冲突的散列表
 */
public class MyHashMap<K,V> {
    static class Node<K,V>{
        K key;
        V value;
        Node hnext;//散列表中的后一个元素
        Node prev;//双向链表中的前一个元素
        Node next;//双向链表中的后一个元素
        public Node(K key, V value, Node hnext) {
            this.key = key;
            this.value = value;
            this.hnext = hnext;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
    private Node<K,V>[] nodes;
    private int capacity;
    private int size;
    private Node head;
    private Node tail;

    MyHashMap(int capacity){
        this.capacity=capacity;
        nodes= (Node<K, V>[]) Array.newInstance(Node.class,this.capacity);
    }

    public boolean put(K key,V value){
        Node<K,V> node=new Node<>(key,value,null);
        if(head==null||tail==null)
            head=tail=node;
        int index=key.hashCode()&capacity-1;
        if(nodes[index]==null)
            nodes[index]=node;
        else{
            Node temp=nodes[index];
            while(temp.hnext !=null){
                temp=temp.hnext;
            }
            temp.hnext =node;
        }
        size++;
        return true;
    }

    public boolean removeKey(K key){
        int index=key.hashCode()&capacity-1;
        if(nodes[index]==null)
            return false;
        Node n=nodes[index];
        Node p=null;
        while(n!=null&&!n.key.equals(key)){
            p=n;
            n=n.hnext;
        }
        if(n==null)
            return false;
        else{
            if(n==nodes[index])
                nodes[index]=n.hnext;
            else
                p.hnext =p.hnext.hnext;
            size--;
            return true;
        }
    }
    public void display(){
        System.out.println("哈希表打印");
        for(int i=0;i<capacity;i++){
            System.out.print("桶"+i+" :");
            displayNode(nodes[i]);
        }
    }
    private void displayNode(Node n){
        while(n!=null){
            System.out.print("\t->"+n.toString());
            n=n.hnext;
        }
        System.out.print("\t-> null");
        System.out.println();
    }

    public static void main(String[] args){
        MyHashMap<String,String> map=new MyHashMap<>(10);
        for(int i=0;i<30;i++){
            map.put("key_"+i,"value_"+i);
            map.display();
        }
        System.out.println("开始删除...");
        for(int i=0;i<40;i++){
            boolean b = map.removeKey("key_" + i);
            System.out.println(b?"删除成功":"删除失败");
            map.display();
        }
    }
}
