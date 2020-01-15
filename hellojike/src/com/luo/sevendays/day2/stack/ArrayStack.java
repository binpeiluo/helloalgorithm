package com.luo.sevendays.day2.stack;

/**
 * 使用数组实现一个顺序栈
 */
public class ArrayStack<E> {
    private static int DEFAULT_LEN=10;
    int size;
    int capacity;
    Object[] data;
    ArrayStack(){
        this.capacity=DEFAULT_LEN;
        data=new Object[capacity];
    }
    ArrayStack(int len){
        this.capacity=len;
        data=new Object[capacity];
    }
    //增
    public boolean push(E ele){
        if(size==capacity)
            return false;
        data[size++]=ele;
        return true;
    }
    //弹
    public E pop(){
        if(size==0)
            return null;
        E res= (E) data[--size];
        data[size]=null;
        return res;
    }
    //取 不弹出
    public E peek(){
        if(size==0)
            return null;
        return (E) data[size-1];
    }

    public void display(){
        System.out.println("数组栈 :size="+size);
        for(int i=0;i<capacity;i++){
            System.out.print("\t"+data[i]);
        }
        System.out.println();
    }

    public static void main(String[] args){
        ArrayStack<String> stack=new ArrayStack<String>();
        for(int i=0,len=15;i<len;i++){
            String data="data" + i;
            boolean push = stack.push(data);
            System.out.println("element="+data+",enqueue "+push);
            stack.display();
        }
        for(int i=0,len=15;i<15;i++){
            String pop = stack.pop();
            System.out.println("dequeue="+pop);
            stack.display();
        }
    }
}
