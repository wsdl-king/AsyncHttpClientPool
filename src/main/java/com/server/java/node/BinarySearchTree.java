package com.server.java.node;

/**
 * @author qiwenshuai
 * @note
 * @since 18-11-16 14:15 by jdk 1.8
 */
public class BinarySearchTree {
    public Node tree;

    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (data < p.data) p = p.left;
            else if (data > p.data) p = p.right;
            else return p;
        }
        return null;
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public   void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
            return;
        }

        Node p = tree;
        while (p != null) {
            if (data > p.data) {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            } else { // data < p.data
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }
    }


    public static void main(String[] args) {
        Node node =new Node(2);
        node.left=new Node(1) ;
        node.right=new Node(3);
        BinarySearchTree binarySearchTree= new BinarySearchTree();
        binarySearchTree.tree=node;
        binarySearchTree.insert(3);
    }
}
