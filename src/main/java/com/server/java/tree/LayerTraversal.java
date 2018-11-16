package com.server.java.tree;

import java.util.*;

/**
 * @author qiwenshuai
 * @note
 * @since 18-11-15 18:05 by jdk 1.8
 */
public class LayerTraversal {

    //需求一
    //思路一
    public static void layerPrint1(BinTree.Node root) {


        if (root == null) {
            throw new IllegalArgumentException();
        }

        Queue<BinTree.Node> queue = new ArrayDeque<>();
        int currentSize = 1;
        int nextSize = 0;

        queue.add(root);
        while (!queue.isEmpty()) {
            BinTree.Node temp = queue.poll();
            //不是空
            System.out.print(temp.val + " ");
            if (temp.left != null) {
                queue.offer(temp.left);
                nextSize++;
            }

            if (temp.right != null) {
                queue.offer(temp.right);
                nextSize++;
            }
            currentSize--;

            if (currentSize == 0) {
                //结束当前层的循环
                System.out.println();
                currentSize = nextSize;
                nextSize = 0;
            }

        }
    }

    //需求一
    //思路二
    public static void layerPrint2(BinTree.Node root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        Queue<BinTree.Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            //不为空
            int size = queue.size(); //关键

            for (int i = 0; i < size; i++) {
                BinTree.Node poll = queue.poll();
                System.out.print(poll.val+" ");
                if(poll.left!=null){
                    queue.offer(poll.left);
                }
                if(poll.right!=null){
                    queue.offer(poll.right);
                }
            }
            System.out.println();
        }
    }

    //需求二
    //思路一
    public static List<List<Integer>> getList1(BinTree.Node root) {
        if (root == null) {
            throw new IllegalArgumentException("invalid parameters");
        }

        //创建队列，存储二叉树节点
        Queue<BinTree.Node> queue = new ArrayDeque<>();
        //把根节点存到队列尾
        queue.offer(root);

        //当前层待遍历节点个数
        int toBeVisit = 1;
        //下一层节点个数
        int nextLevel = 0;

        //创建结果集合
        List<List<Integer>> result = new ArrayList<>();

        //创建集合，存储每一行的节点值
        List<Integer> list = new LinkedList<>();

        while (!queue.isEmpty()) {
            BinTree.Node temp = queue.poll();
            list.add(temp.val);

            if (temp.left != null) {
                nextLevel++;
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                nextLevel++;
                queue.offer(temp.right);
            }

            //待遍历节点数减一
            toBeVisit--;

            //当待遍历节点数为0，代表当前行已经遍历完
            if (toBeVisit == 0) {
                //把当前list集合中的元素存到result中，注意不能直接result.add(list)，因为后面要清空list集合，result也会受到影响
                //所以可以创建新的集合，存储list集合元素，然后添加到result中
                List<Integer> l = new LinkedList<>();
                l.addAll(list);

                result.add(l);

                toBeVisit = nextLevel;
                nextLevel = 0;
                list.clear();//清空共有的List集合
            }
        }

        return result;
    }

    //需求二u
    //思路二
    public static List<List<Integer>> getList2(BinTree.Node root) {
        if (root == null) {
            throw new IllegalArgumentException("invalid parameters");
        }

        //创建结果集合
        List<List<Integer>> result = new ArrayList<>();

        //创建队列，存储二叉树节点
        Queue<BinTree.Node> queue = new ArrayDeque<>();
        //把根节添加到队列尾
        queue.offer(root);

        //当队列中不为空
        while (!queue.isEmpty()) {
            //创建List集合存储当前层节点值
            List<Integer> list = new LinkedList<>();

            //记录当前层的节点个数
            int size = queue.size();
            BinTree.Node temp;

            for (int i = 0; i < size; i++) {
                temp = queue.poll();
                list.add(temp.val);

                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }

            result.add(list);
        }

        return result;
    }

    public static void main(String[] args) {
        String[] str = {
                "1", "2", "3", "4", "#", "#", "7"
        };

        //构建二叉树
        BinTree bt = new BinTree(str);
        bt.createBinTree();

        //层次遍历
        System.out.println("逐层打印：");
        layerPrint1(bt.getRoot());

        System.out.println("逐层打印2：");
        layerPrint2(bt.getRoot());

        System.out.println("二叉树分层集合：");
        System.out.println(getList1(bt.getRoot()));
        System.out.println("二叉树分层集合：");
        System.out.println(getList2(bt.getRoot()));


    }
}
