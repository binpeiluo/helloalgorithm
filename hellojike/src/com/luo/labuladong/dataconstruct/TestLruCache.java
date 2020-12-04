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

    static class DoubleList{
        /*双指针 保存链表头结点和尾节点*/
        private Node head,tail;
        private int count;
        DoubleList(){
            this.head=new Node(-1,-1);
            this.tail=new Node(-1,-1);
            this.count=0;
        }
        /**在尾节点添加节点 其他节点被移动到最前面时*/
        public void addTail(Node t){
            t.next=tail;
            t.prev=tail.prev;
            tail.prev.next=t;
            tail.prev=t;
            this.count++;
        }
        /**在链表中删除某节点 该节点一定存在*/
        private Node removeNode(Node n){
            n.prev.next=n.next;
            n.next.prev=n.prev;
            n.prev=null;
            n.next=null;
            this.count--;
            return n;
        }
        /**此时需要抛弃头结点*/
        public Node removeFirst(){
            if(head.next==tail){
                return null;
            }else{
                return removeNode(head.next);
            }

        }
        /**此时 节点被访问 需要移动到最后边*/
        public void moveToTail(Node n){
            removeNode(n);
            addTail(n);
        }
    }


    static class LruCacheEasy{
        private Map<Integer,Node> map=new HashMap<>();
        private DoubleList list=new DoubleList();
        private int capacity;
        LruCacheEasy(int capacity){
            this.capacity=capacity;
        }
        public void put(int key,int val){
            Node node = map.get(key);
            if(node==null){
//                map中不存在
                if(this.count()==this.capacity){
                    this.removeNode();
                }
                addNode(node);
            }else{
                visitNode(node);
            }
        }

        public int get(int key){
            Node node = map.get(key);
            if(node==null){
                return -1;
            }else{
                return node.val;
            }
        }

        /** 此时访问到节点 需要将元素移动到链表尾部*/
        private void visitNode(Node n){
            list.moveToTail(n);
        }
        private void removeNode(){
            Node node = list.removeFirst();
            map.remove(node.key);
        }
        private void addNode(Node n){
            map.put(n.key,n);
            list.addTail(n);
        }
        private int count(){
            return list.count;
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
