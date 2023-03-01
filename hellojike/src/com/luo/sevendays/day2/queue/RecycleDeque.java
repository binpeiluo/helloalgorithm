package com.luo.sevendays.day2.queue;

/**
 * 设计循环双端队列
 */
public class RecycleDeque<E> {

    int capacity;
    Object[] data;
    int size;
    int front;
    int rear;

    /** Initialize your data structure here.
     * Set the size of the deque to be k.
     * */
    public RecycleDeque(int k) {
        this.capacity=k;
        this.data=new Object[capacity];
    }

    /** Adds an item at the front of Deque.
     * Return true if the operation is successful.
     * */
    public boolean insertFront(E value) {
        //如果队列满，插入失败
        if(rear==front && size == capacity)
            return  false;
        else {
            front = (front + capacity -1)% capacity;
            data[front] = value;
            size++;
            return true;
        }
    }

    /** Adds an item at the rear of Deque.
     * Return true if the operation is successful.
     * */
    public boolean insertLast(E value) {
        if(rear==front && size == capacity)
            return  false;//如果队列满，插入失败
        else {
            data[rear] = value;
            rear = (rear+1)%capacity;
            size++;
            return true;
        }
    }

    /** Deletes an item from the front of Deque.
     * Return true if the operation is successful.
     * */
    public boolean deleteFront() {
        if( rear == front && size == 0)
            return false;
        else {
            data[front]=null;
            front = (front+1) % capacity;
            size--;
            return true;
        }
    }

    /** Deletes an item from the rear of Deque.
     * Return true if the operation is successful.
     * */
    public boolean deleteLast() {
        if( rear == front && size == 0)
            return false;
        else {
            rear = (rear - 1 + capacity) % capacity;
            data[rear]=null;
            size--;
            return true;
        }
    }

    /** Get the front item from the deque. */
    public E getFront() {
        if((rear == front) && size==0) return null;
        else {
            E frontE = (E) data[front];
            return frontE;
        }
    }

    /** Get the last item from the deque. */
    public E getRear() {
        if((rear == front) && size==0)
            return null;
        else {
            E rearE = (E) data[(rear-1+capacity)%capacity];
            return rearE;
        }
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return (rear == front) && size==0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return rear==front && size == capacity;
    }

    public void display(){
        System.out.println("循环双端队列:size="+size);
        for(int i=0;i<capacity;i++){
            System.out.print("\t"+data[i]);
        }
        System.out.println();
    }

    public static void main(String[] args){
        RecycleDeque<Integer> deque=new RecycleDeque<>(10);
        for(int i=0;i<3;i++){
            deque.insertFront(i);
            deque.display();
        }
        for(int i=3;i<6;i++){
            deque.insertLast(i);
            deque.display();
        }
    }
}
