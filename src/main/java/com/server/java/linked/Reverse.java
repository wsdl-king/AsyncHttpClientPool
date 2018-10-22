package com.server.java.linked;

/**
 * @author qiwenshuai
 * @note 实现链表反转
 * @since 18-10-22 18:55 by jdk 1.8
 */
public class Reverse {
    //1,2,3,4,5
    private static ListNode reverse(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return listNode;
        }
        ListNode prev = null;
        ListNode node = listNode;
        ListNode node2 = listNode;
        while (node.next != null) {
            node = node.next;
            ListNode next = listNode.next;
            node2.next = prev;
            prev = node2;
            listNode = next;
            node2 = node;
        }
        ListNode listNode1 = new ListNode(node.val);
        listNode1.next = prev;
        return listNode1;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6};
        ListNode listNode = ListNode.buildListNode(a);
        ListNode reverse = reverse(listNode);
        System.out.println(reverse);
    }
}
