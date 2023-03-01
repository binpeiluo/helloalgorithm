package com.luo.sevendays.day1.linked;

/**
 * 单向链表
 */
public class SingleLinkedList {
    private Node head;
    private int size;
    public SingleLinkedList(){
        head=new Node(null,null);
    }
    //增
    public void addToTail(Object data){
        Node node=new Node(data);
        Node temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=node;
        size++;
    }

    //增
    public void insert(Object data,int index){
        checkIndex(index);
        Node node=new Node(data);
        Node temp=head;
        int i=0;
        while(i++<index){
            temp=temp.next;
        }
        node.next=temp.next;
        temp.next=node;
        size++;
    }

    //删
    public boolean remove(Object data){
        Node temp=head;
        while(temp.next!=null&&temp.next.data!=data){
            temp=temp.next;
        }
        if(temp.next==null)
            return false;
        if(temp.next.data==data){
            temp.next=temp.next.next;
            size--;
            return true;
        }
        return false;
    }

    //删
    public boolean remove(int index){
        checkIndex(index);
        Node temp=head;
        int i=0;
        while(i++<index){
            temp=temp.next;
        }
        temp.next=temp.next.next;
        size--;
        return true;
    }

    //查
    public Object get(int index){
        checkIndex(index);
        Node temp=head.next;
        int i=0;
        while(i--<index){
            temp=temp.next;
        }
        return temp.data;
    }
    //查
    public int find(Object data){
        Node temp=head;
        int index=0;
        while(temp.next!=null&&temp.next.data!=data){
            temp=temp.next;
            index++;
        }
        if(temp.next==null)
            return -1;
        else
            return index;
    }

    //改
    public boolean replace(Object src,Object dest){
        Node temp=head.next;
        while(temp!=null&&temp.data!=src&&temp.next!=null){
            temp=temp.next;
        }
        if(temp!=null&&temp.data==src){
            temp.data=dest;
            return true;
        }
        return false;
    }

    private void checkIndex(int index){
        if(index<0||index>=size)
            throw new IndexOutOfBoundsException("角标越界,"+index);
    }
    //打印
    public void display(){
        Node temp=head;
        System.out.print("size="+size+"\tcontent==");
        while(temp!=null){
            System.out.print("-->"+temp.data);
            temp=temp.next;
        }
        System.out.println();
    }

    /**
     * 实现单链表的反转
     * @return
     */
    public static Node reverse(Node node){
        if(node==null||node.next==null)
            return node;
        Node temp=node;
        Node res=null;
        while(temp!=null){
            Node next=temp.next;
            temp.next=res;
            res=temp;
            temp=next;
        }
        return res;
    }

    /**
     * 查找出链表的中间节点
     * @param node
     * @return
     */
    public static Node findMiddle(Node node){
        if(node==null||node.next==null){
            return node;
        }
        Node slow=node,fast=node;
        while(fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }

    public static void display(Node node){
        Node temp=node;
        while(temp!=null){
            System.out.print("-->"+temp.data);
            temp=temp.next;
        }
        System.out.println();
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
        SingleLinkedList list=new SingleLinkedList();
        list.addToTail(2);
        list.addToTail(3);
        list.addToTail(4);
        list.addToTail(5);
        list.addToTail(6);
        list.display();
        int i = list.find(2);
        System.out.println("index=="+i);
        list.remove(0);
        list.remove(2);
        list.display();
        list.replace(2,1);
        list.display();
        list.insert(0,0);
        list.insert(1,1);
        list.insert(10,4);
        list.display();
        list.remove(Integer.valueOf(10));
        list.display();

        System.out.println("测试链表反转");
        Node reverse = reverse(list.head.next);
        display(reverse);

        Node middle = findMiddle(reverse);
        System.out.println("中间节点:"+middle);
    }
}
