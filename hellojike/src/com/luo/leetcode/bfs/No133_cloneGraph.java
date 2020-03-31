package com.luo.leetcode.bfs;

import java.util.*;

/**
 * 133. 克隆图
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * <p>
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 */
public class No133_cloneGraph {
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * 使用dfs 递归遍历实现
     *
     * 时间复杂度:   O(n*n)  子问题规模,子问题大小
     * 空间复杂度:   O(n)
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if(node==null)
            return null;
        Map<Node,Node> map=new HashMap<>();
        Node newNode = helper(node,map);
        return newNode;
    }

    private Node helper(Node node,Map<Node,Node> map){
        if(map.containsKey(node))
            return map.get(node);
        Node clone=new Node(node.val,new ArrayList<>());
        map.put(node,clone);
        for (Node c:node.neighbors)
            clone.neighbors.add(helper(c,map));
        return clone;
    }

    /**
     * 使用bfs实现
     * @param node
     * @return
     */
    public Node cloneGraph2(Node node){
        if(node==null)
            return null;
        Node clone=new Node(node.val,new ArrayList<>());
        Map<Node,Node> map=new HashMap<>();
        map.put(node,clone);
        Queue<Node> queue=new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            Node poll = queue.poll();
            for (Node c:poll.neighbors){
//                对于每个节点的子节点,加入当前map没有储存映射对象,则创建新对象
                if(!map.containsKey(c)){
                    map.put(c,new Node(c.val,new ArrayList<>()));
                    queue.offer(c);
                }
                map.get(poll).neighbors.add(map.get(c));
            }
        }
        return clone;
    }



}
