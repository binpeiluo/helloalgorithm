package com.luo.labuladong.dataconstruct;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 实现LRU缓存
 */
public class TestLruCache {

    static class Node{
        int key, val;
        Node prev,next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * 双向链表
     * 提供删除指定节点,删除头结点,插入尾节点方法
     */
    static class DoubleList{
        private Node head,tail;
        private int size;
        DoubleList(){
            head=new Node(-1,-1);
            tail=new Node(-1,-1);
            head.next=tail;
            tail.prev=head;
            this.size=0;
        }

        public void remove(Node node){
            node.prev.next=node.next;
            node.next.prev=node.prev;
            size--;
        }

        public Node removeHead(){
            Node n = head.next;
            if(n==tail){
                return null;
            }
            remove(n);
            return n;
        }

        public void addTail(Node node){
            node.prev=tail.prev;
            node.next=tail;
            tail.prev.next=node;
            tail.prev=node;
            size++;
        }
        public int size(){
            return size;
        }
    }

    static class LruCache{
        DoubleList list;
        Map<Integer,Node> map;
        int capacity;
        LruCache(int capacity){
            list=new DoubleList();
            map=new HashMap<>();
            this.capacity=capacity;
        }
        public void put(int key,int value){
            if(map.containsKey(key)){
                deleteKey(key);
                addRecently(new Node(key,value));
            }else{
                if(list.size()==capacity){
                    Node node = list.removeHead();
                    map.remove(node.key);
                }
                addRecently(new Node(key,value));
            }
        }
        public int get(int key){
            if(!map.containsKey(key)){
                return -1;
            }else{
                Node node = map.get(key);
                makeRecently(node);
                return node.val;
            }
        }

        private void deleteKey(int key){
            Node node = map.remove(key);
            list.remove(node);
        }
        private void makeRecently(Node node){
            list.remove(node);
            list.addTail(node);
        }
        private void addRecently(Node node){
            list.addTail(node);
            map.put(node.key,node);
        }
    }

    static class LruCacheEasy{
        int capacity;
        LinkedHashMap<Integer,Integer> map;
        LruCacheEasy(int capacity){
            this.capacity=capacity;
            map=new LinkedHashMap<>();
        }
        public void put(int key,int val){
            if(map.containsKey(key)){
                map.remove(key);
                map.put(key,val);
            }else{
                if(map.size()==capacity){
                    Integer head = map.keySet().iterator().next();
                    map.remove(head);
                }
                map.put(key,val);
            }
        }

        public int get(int key){
            if(map.containsKey(key)){
                int value = map.remove(key);
                map.put(key,value);
                return value;
            }else{
                return -1;
            }
        }
    }

    static class LruCacheEasy2{
        int capacity;
        LinkedHashMap<Integer,Integer> map;
        LruCacheEasy2(int capacity){
            this.capacity=capacity;
            map=new LinkedHashMap<>(16,0.75f,true);
        }

        public void put(int key,int value){
            if(!map.containsKey(key)&&map.size()==capacity){
                int head = map.keySet().iterator().next();
                map.remove(head);
            }
            map.put(key,value);
        }
        public int get(int key){
            if(map.containsKey(key)){
                return map.get(key);
            }else{
                return -1;
            }
        }
    }

    public static void main(String[] args){
//        LruCache cache=new LruCache(2);

        LruCacheEasy cache=new LruCacheEasy(2);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4

    }
}
