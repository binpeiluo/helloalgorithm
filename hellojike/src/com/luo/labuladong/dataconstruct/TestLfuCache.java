package com.luo.labuladong.dataconstruct;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LFU
 */
@SuppressWarnings("Duplicates")
public class TestLfuCache {

    static class LfuCache{
        /** 储存键值对*/
        private Map<Integer,Node> map;
        /** 储存每个访问次序的链表*/
        private Map<Integer,DoubleList> freq2Key;
        private Map<Integer,Integer> key2Freq;
        private int capacity;
        private int count;
        LfuCache(int capacity){
            this.capacity=capacity;
            this.count=0;
            map=new HashMap<>();
            freq2Key=new HashMap<>();
            key2Freq=new HashMap<>();
        }
        public int get(int key){
            Node node = map.get(key);
            if(node==null){
                return -1;
            }else{
                this.visitNode(node);
                return node.value;
            }
        }

        public void put(int key,int value){
            if(capacity==0){
//                绝了  还有这案例
                return;
            }
            Node node = map.get(key);
            if(node==null){
                if(capacity==count){
                    this.removeOldestNode();
                }
                this.addNode(new Node(key,value));
            }else{
                node.value=value;
                visitNode(node);
            }
        }

        /** 新添加节点*/
        private void addNode(Node node){
            int defaultFreq=1;
//            访问频次到列表
            DoubleList orDefault = freq2Key.getOrDefault(defaultFreq, new DoubleList());
            orDefault.addLast(node);
            freq2Key.put(defaultFreq,orDefault);
//            键访问次数
            key2Freq.put(node.key,defaultFreq);
            map.put(node.key,node);
            count++;
        }

        private void visitNode(Node node){
            Integer freq = key2Freq.get(node.key);
//            处理原来相同访问频率的链表 从中删除
            DoubleList freqList = freq2Key.get(freq);
            freqList.removeNode(node);
            if(freqList.count()==0){
                freq2Key.remove(freq);
            }
//            将之提升到另一个链表中
            freq+=1;
            DoubleList orDefault = freq2Key.getOrDefault(freq, new DoubleList());
            orDefault.addLast(node);
            freq2Key.put(freq,orDefault);

//            修改频率
            key2Freq.put(node.key,freq);
        }

        private Node removeOldestNode(){
//            找到最小的频率
            Integer minFreq = freq2Key.keySet().stream().min(Comparator.naturalOrder()).get();
            DoubleList doubleList = freq2Key.get(minFreq);
//            移除最小频率链表的头结点
            Node node = doubleList.removeFirst();
            if(doubleList.count()==0){
                freq2Key.remove(minFreq);
            }
//            同时移除频率map
            key2Freq.remove(node.key);
//            同时删除键值对
            map.remove(node.key);
            count--;
            return node;
        }

        private void printNode(){
            System.out.println("-----begin freq2Key");
            String collect = freq2Key.entrySet().stream().map(entry -> {
                return "频率:" + entry.getKey() + " 链表:" + entry.getValue().toString();
            }).collect(Collectors.joining("\r\n"));
            System.out.println(collect);


            System.out.println("-----begin key2Freq");
            System.out.println(key2Freq.toString());


        }

    }

    static class Node{
        private int key,value;
        private Node next,prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    static class DoubleList{
        private Node head,tail;
        private int count;
        DoubleList(){
            head=new Node(-1,-1);
            tail=new Node(-1,-1);
            head.next=tail;
            tail.prev=head;
            this.count=0;
        }
        public void addLast(Node n){
            n.next=tail;
            n.prev=tail.prev;
            tail.prev.next=n;
            tail.prev=n;
            this.count++;
        }
        public Node removeFirst(){
            if(head.next==tail){
                return null;
            }
            Node node=head.next;
            removeNode(node);
            return node;
        }
        private void removeNode(Node n){
            n.prev.next=n.next;
            n.next.prev=n.prev;
            n.next=null;
            n.prev=null;
            this.count--;
        }
        public int count(){
            return this.count;
        }

        @Override
        public String toString() {
            return printNode();
        }

        private String printNode(){
            Node tmp=head.next;
            String result="{";
            while(tmp!=tail){
                result+=(" -> "+tmp.key);
                tmp=tmp.next;
            }
            result+="}";
            return result;
        }
    }


    public static void main(String[] args){
//        testDemo1();

//        testDemo2();

//        testDemo3();

        testDemo4();
    }

    private static void testDemo4(){
//        ["LFUCache","put","put","get","get","put","get","get","get"]
//        [[2],[2,1],[3,2],[3],[2],[4,3],[2],[3],[4]]

        LfuCache lfuCache=new LfuCache(2);

        lfuCache.put(2,1);
        lfuCache.printNode();

        lfuCache.put(3,2);
        lfuCache.printNode();

        int i = lfuCache.get(3);
        System.out.println(i);
        lfuCache.printNode();

        i = lfuCache.get(2);
        System.out.println(i);
        lfuCache.printNode();

        lfuCache.put(4,3);
        lfuCache.printNode();

        i = lfuCache.get(2);
        System.out.println(i);
        lfuCache.printNode();

        i = lfuCache.get(3);
        System.out.println(i);
        lfuCache.printNode();

        i = lfuCache.get(4);
        System.out.println(i);
        lfuCache.printNode();
    }

    private static void testDemo3(){
//        ["LFUCache","put","get"]
//        [[0],[0,0],[0]]

        LfuCache lfuCache=new LfuCache(0);

        lfuCache.put(0,0);
        lfuCache.printNode();

        int i = lfuCache.get(0);
        System.out.println(i);
        lfuCache.printNode();

    }

    private static void testDemo2(){
//        ["LFUCache","put","put","put","put","get"]
//        [[2],[3,1],[2,1],[2,2],[4,4],[2]]

        LfuCache lfuCache=new LfuCache(2);

        lfuCache.put(3,1);
        lfuCache.printNode();

        lfuCache.put(2,1);
        lfuCache.printNode();

        lfuCache.put(2,2);
        lfuCache.printNode();

        lfuCache.put(4,4);
        lfuCache.printNode();

        int i = lfuCache.get(2);
        System.out.println(i);
        lfuCache.printNode();
    }

    private static void testDemo1(){

//          ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
//          [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]

        LfuCache lfuCache=new LfuCache(2);
        lfuCache.put(1,1);
        lfuCache.printNode();

        lfuCache.put(2,2);
        lfuCache.printNode();

        int i = lfuCache.get(1);
        System.out.println(i);
        lfuCache.printNode();

        lfuCache.put(3,3);
        lfuCache.printNode();

        i = lfuCache.get(2);
        System.out.println(i);
        lfuCache.printNode();

        i = lfuCache.get(3);
        System.out.println(i);
        lfuCache.printNode();

        lfuCache.put(4,4);
        lfuCache.printNode();

        i = lfuCache.get(1);
        System.out.println(i);
        lfuCache.printNode();

        i = lfuCache.get(3);
        System.out.println(i);
        lfuCache.printNode();

        i = lfuCache.get(4);
        System.out.println(i);
        lfuCache.printNode();

    }
}
