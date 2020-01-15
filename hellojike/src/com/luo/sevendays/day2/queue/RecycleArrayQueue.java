package com.luo.sevendays.day2.queue;

/**
 * 循环队列
 * 使用数组实现
 */
public class RecycleArrayQueue<E> {
    static final int DEFAULT_LEN=10;
    int size;
    int capacity;
    Object[] data;
    int headIndex;
    int tailIndex;
    RecycleArrayQueue(){
        this.capacity=DEFAULT_LEN;
        data=new Object[capacity];
    }
    RecycleArrayQueue(int len){
        this.capacity=len;
        data=new Object[capacity];
    }

    //增
    public boolean enqueue(E ele){
        if(size==capacity)
            return false;
        data[tailIndex]=ele;
        tailIndex=(tailIndex+1)%capacity;
        size++;
        return true;
    }
    //弹
    public E dequeue(){
        if(size==0)
            return null;
        E res= (E) data[headIndex];
        data[headIndex]=null;
        headIndex=(headIndex+1)%capacity;
        size--;
        return res;
    }

    public void display(){
        System.out.println(" 数组队列:size="+size);
        for(int i=0;i<capacity;i++){
            System.out.print("\t"+data[i]);
        }
        System.out.println();
    }

    public static void main(String[] args){
        RecycleArrayQueue<String> queue=new RecycleArrayQueue<String>();
        for(int i=0,len=17;i<len;i++){
            String data="value"+i;
            boolean push = queue.enqueue(data);
            System.out.println("enqueue "+data+" ,result="+push);
            queue.display();
        }
        for(int i=0,len=7;i<len;i++){
            String pop = queue.dequeue();
            System.out.println("dequeue "+pop);
            queue.display();
        }

        for(int i=0,len=9;i<len;i++){
            String data="second"+i;
            boolean push = queue.enqueue(data);
            System.out.println("enqueue "+data+" ,result="+push);
            queue.display();
        }
        queue.dequeue();
        queue.display();
    }
}
