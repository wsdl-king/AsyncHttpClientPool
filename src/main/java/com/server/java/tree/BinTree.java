package com.server.java.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * @author qiwenshuai
 * @note
 * @since 18-11-15 18:04 by jdk 1.8
 */
public class BinTree {
    //表示二叉树的字符串数组
    private String[] str;
    //表示二叉树的List集合
    private List<Node> list;

    //构造函数
    //传入字符串数组
    BinTree(String[] str) {
        this.str = str;
    }

    //二叉树节点内部类
    class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;

            this.left = null;
            this.right = null;
        }
    }

    //创建二叉树
    public void createBinTree() {
        list = new LinkedList<>();
        for (String s : str) {
            if (!("#".equals(s))) {
                list.add(new Node(Integer.parseInt(s)));
            } else {
                list.add(null);
            }

        }


        for (int index = 0; index < str.length / 2 - 1; index++) {
            list.get(index).left=list.get(index*2+1);
            list.get(index).right=list.get(index*2+2);
        }

        //单独处理最后一个点位，如果不这样，那么在上面会越界
        int lastIndex=str.length/2-1;
        list.get(lastIndex).left=list.get(lastIndex*2+1);

        if(str.length%2!=0){
            list.get(lastIndex).right=list.get(lastIndex*2+2);
        }
    }


    //获取二叉树根
    public Node getRoot() {
        if (list != null) {
            return list.get(0);
        }

        System.out.println("请先创建二叉树");
        return null;
    }

    //获取二叉树的深度，即最大深度
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        //将深度初始化为1
        int depth = 1;

        //求左儿子的最大深度
        int leftMaxDepth = maxDepth(root.left);
        //求右儿子的最大深度
        int rightMaxDepth = maxDepth(root.right);

        return Math.max(leftMaxDepth, rightMaxDepth) + depth;
    }

    //获取二叉树的最小深度
    public int minDepth(Node root) {
        if (root == null) {
            return 0;
        }

        //深度初始化为1
        int depth = 1;

        //求左儿子的最小深度
        int leftMinDepth = minDepth(root.left);
        //求右儿子的最小深度
        int rightMinDepth = minDepth(root.right);

        //如果左儿子树或者右儿子树最小深度是0，那么就返回另一个长度
        if (leftMinDepth == 0) {
            return rightMinDepth + depth;
        }
        if (rightMinDepth == 0) {
            return leftMinDepth + depth;
        }

        return Math.min(leftMinDepth, rightMinDepth) + depth;
    }
}
