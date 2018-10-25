package com.server.java.linked;

/**
 * @author qiwenshuai
 * @note
 * @since 18-10-22 13:58 by jdk 1.8
 */

public class ListNode {
    public Integer val;
    public ListNode next;

    public ListNode(Integer val) {
        this.val = val;
    }


    public static ListNode buildListNode(int[] input) {
        ListNode first = null, last = null, newNode;
        if (input.length > 0) {
            for (int anInput : input) {
                newNode = new ListNode(anInput);
                newNode.next = null;
                if (first == null) {
                    first = newNode;
                    last = newNode;
                } else {
                    last.next = newNode;
                    last = newNode;
                }

            }
        }
        return first;
    }
}