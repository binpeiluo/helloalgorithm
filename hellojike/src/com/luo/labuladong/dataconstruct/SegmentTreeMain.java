package com.luo.labuladong.dataconstruct;

public class SegmentTreeMain {

    static class Node {
        int left; // l是区间左边界
        int right; // r是区间右边界
        int sum; // sum是区间元素和
        int lazy; // lazy是懒惰标记

        public Node(int left, int right, int sum, int lazy) {
            this.left = left;
            this.right = right;
            this.sum = sum;
            this.lazy = lazy;
        }
    }

    static int n = 10; // n是元素个数
    static int[] array = {0, 1, 5, 1, 3, 4, 2, 0, 9, 0, 9};
    // array是原序列(第一个0是占array[0]位的)
    static Node[] tree = new Node[4 * n]; // tree是线段树

    public static void main(String[] args) {
        initTree();
        build(1, 10, 1); // 利用build函数建树
        System.out.println("操作1：[2，5]的区间和是：" + query(2, 5, 1));
        // 利用query函数搜索区间和
        modify(5, 9, 1); // 利用modify函数实现单点修改（元素5从4改为9）
        System.out.println("操作2：元素5从4改为9，此时[2，5]的区间和是：" + query(2, 5, 1));
        modifySegment(3, 4, 3, 1);
        // 利用modifySegment函数将[3，4]每个元素增加3
        System.out.println("操作3：区间[3，4]每个元素+3，此时[2，5]的区间和是：" + query(2, 5, 1));
    }

    private static void initTree() {
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node(0, 0, 0, 0);
        }
    }

    private static void updateNode(int num) { // num是当前节点序号
        tree[num].sum = tree[num * 2].sum + tree[num * 2 + 1].sum;
    }

    /**
     * 构造线段树数组角标元素num的左边界left和右边界right
     * 并且计算该线段的值sum
     * @param left     线段树数组元素角标num的左边界
     * @param right     线段树数组元素角标num的右边界
     * @param num   构造的线段树角标
     */
    private static void build(int left, int right, int num) { // 建树
        tree[num].left = left;
        tree[num].right = right;
        if (left == right) { // left = r说明到达叶子节点
            tree[num].sum = array[left];
            return;
        }
        int mid = (left + right) / 2;
        build(left, mid, num * 2); // 递归左儿子
        build(mid + 1, right, num * 2 + 1); // 递归右儿子

//        直接写成这样子应该更好理解一点 前边已经计算好子元素的sum,这里计算根元素的sum
//        tree[num].sum = tree[num * 2].sum + tree[num * 2 + 1].sum;
        updateNode(num);

    }

    static void modify(int i, int value, int num) { // 把元素i修改为值value
        if (tree[num].left == tree[num].right) { // 到达叶子节点
            tree[num].sum = value;
            return;
        }
        int mid = (tree[num].left + tree[num].right) / 2;
        if (i <= mid) {
            modify(i, value, num * 2); // 递归左儿子
        } else {
            modify(i, value, num * 2 + 1); // 递归右儿子
        }
        updateNode(num);
    }

    private static void modifySegment(int left, int right, int value, int num) { // [left,right]每一项都增加value
        if (tree[num].left == left && tree[num].right == right) { // 找到当前区间
            tree[num].sum += (right - left + 1) * value; // right-left+1是区间元素个数
            tree[num].lazy += value;
            return;
        }
        int mid = (tree[num].left + tree[num].right) / 2;
        if (right <= mid) { // 在左区间
            modifySegment(left, right, value, num * 2);
        } else if (left > mid) { // 在右区间
            modifySegment(left, right, value, num * 2 + 1);
        } else { // 分成2块
            modifySegment(left, mid, value, num * 2);
            modifySegment(mid + 1, right, value, num * 2 + 1);
        }
        updateNode(num);
    }

    private static void pushDown(int num) {
        if (tree[num].left == tree[num].right) { // 叶节点不用下传标记
            tree[num].lazy = 0; // 清空当前标记
            return;
        }
        tree[num * 2].lazy += tree[num].lazy; // 下传左儿子的懒惰标记
        tree[num * 2 + 1].lazy += tree[num].lazy; // 下传右儿子的懒惰标记

        tree[num * 2].sum += (tree[num * 2].right - tree[num * 2].left + 1) * tree[num].lazy; // 更新左儿子的值
        tree[num * 2 + 1].sum += (tree[num * 2 + 1].right - tree[num * 2 + 1].left + 1) * tree[num].lazy; // 更新右儿子的值
        tree[num].lazy = 0; // 清空当前节点的懒惰标记
    }

    private static int query(int left, int right, int num) {
        if (tree[num].lazy != 0) { // 下传懒惰标记
            pushDown(num);
        }
        if (tree[num].left == left && tree[num].right == right) { // 找到当前区间
            return tree[num].sum;
        }
        int mid = (tree[num].left + tree[num].right) / 2;
        if (right <= mid) { // 在左区间
            return query(left, right, num * 2);
        }
        if (left > mid) { // 在右区间
            return query(left, right, num * 2 + 1);
        }
        return query(left, mid, num * 2) + query(mid + 1, right, num * 2 + 1); // 分成2块
    }

}
