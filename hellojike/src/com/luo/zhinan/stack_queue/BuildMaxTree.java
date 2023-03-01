package com.luo.zhinan.stack_queue;

import com.luo.util.CommonUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 构造数组的maxTree
 * 数组没有重复元素, maxTree是一颗二叉树, 每个节点元素大于两个子元素的大小
 * 构造的时间复杂度以及空间复杂度必须为O(n)以内
 */
public class BuildMaxTree {

    public static class Node{
        public int value;
        public Node left, right;
        public Node(int value){
            this.value=value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }


    // 想想构造二叉树, 一般都是某个节点为根节点, 两边选择子节点
    // 遍历到某个节点, 寻找左边第一个大于它的元素, 与右边第一个大于它的元素. 取两者的较小值为父节点
    // 如果某个节点两边没有比它大的节点, 那么此元素是根节点

    // 如果证明以上是正确的呢?
    // 1. 肯定是一棵树
    // 2. 树的一侧最多只有一个节点, 即是二叉树
    // 证明这两点, 说明该方法是有效的

    // 那如何快速找到两侧首个大于该元素的元素呢?
    // 使用栈
    public Node buildMaxTree(int[] a){
        int len=a.length;
        // 两侧最小栈
        Stack<Integer> leftStack=new Stack<>(), rightStack=new Stack<>();
        int[] leftA=new int[len], rightA=new int[len];
        for(int i = 0; i < len; i++) {
            while(!leftStack.isEmpty()&&a[i]>leftStack.peek()){
                leftStack.pop();
            }
            leftA[i] = leftStack.isEmpty()?-1:leftStack.peek();
            leftStack.push(a[i]);

            while(!rightStack.isEmpty()&&a[len-1-i]>rightStack.peek()){
                rightStack.pop();
            }
            rightA[len-1-i]=rightStack.isEmpty()?-1:rightStack.peek();
            rightStack.push(a[len-1-i]);
        }
        Node result = null;
        Map<Integer, Node> nodeMap=new HashMap<>();
        for(int i = 0; i < len; i++) {
            nodeMap.put(a[i], new Node(a[i]));
        }
        for (int i = 0; i < len; i++) {
            int left=leftA[i], right=rightA[i];
            if(left==-1&&right==-1){
                result = nodeMap.get(a[i]);
            }else if(left==-1){
                if(nodeMap.get(right).left==null){
                    nodeMap.get(right).left=nodeMap.get(a[i]);
                }else{
                    nodeMap.get(right).right=nodeMap.get(a[i]);
                }
            }else if(right==-1){
                if(nodeMap.get(left).left==null){
                    nodeMap.get(left).left=nodeMap.get(a[i]);
                }else{
                    nodeMap.get(left).right=nodeMap.get(a[i]);
                }
            }else{
                int min=left<right?left:right;
                if(nodeMap.get(min).left==null){
                    nodeMap.get(min).left=nodeMap.get(a[i]);
                }else{
                    nodeMap.get(min).right=nodeMap.get(a[i]);
                }
            }
        }
        return result;
    }



    public static void main(String[] args) {
        BuildMaxTree service = new BuildMaxTree();
        int[] a = {7,3,5,2};
        Node node = service.buildMaxTree(a);
        System.out.println(node.toString());
    }
}
