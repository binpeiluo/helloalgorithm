package com.luo.sevendays.day2.queue;

/**
 * 使用数组实现一个队列
 */
public class ArrayQueue<E> {
    static final int DEFAULT_LEN=10;
    int size;
    int capacity;
    Object[] data;
    ArrayQueue(){
        capacity=DEFAULT_LEN;
        data=new Object[capacity];
    }
    ArrayQueue(int len){
        capacity=len;
        data=new Object[capacity];
    }
    public boolean enqueue(E ele){
        if(size==capacity)
            return false;
        data[size++]=ele;
        return true;
    }
    public E dequeue(){
        if(size==0)
            return null;
        E res= (E) data[0];
        size--;
        System.arraycopy(data,1,data,0,size);
        data[size]=null;
        return res;
    }
    public void display(){
        System.out.println("数组队列:size="+size);
        for(int i=0;i<capacity;i++){
            System.out.print("\t"+data[i]);
        }
        System.out.println();
    }

    public static void main(String[] args){
        ArrayQueue<String> queue=new ArrayQueue<>(10);
        for(int i=0,len=16;i<len;i++){
            String data="data " + i;
            boolean push = queue.enqueue(data);
            System.out.println("element="+data+",enqueue "+push);
            queue.display();
        }
        for(int i=0,len=16;i<len;i++){
            String pop = queue.dequeue();
            System.out.println("dequeue="+pop);
            queue.display();
        }
    }
}
