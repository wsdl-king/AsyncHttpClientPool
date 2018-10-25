package com.server.java.linked;
/**
 * @author qiwenshuai
 * @note 寻找链表中间节点
 * @since 18-10-25 11:42 by jdk 1.8
 */
public class FindMidListNode {

    //1-2-3-4-5-6
    private static ListNode findMidNode(ListNode node) {
        //考虑边界
        if (node == null || node.next == null) {
            return node;
        }
        ListNode slow = node;
        ListNode fast = node;
        while ( fast!=null &&fast.next != null) {
            fast = fast.next.next;
            ListNode next = node.next;
            node=node.next;
            slow = next;
        }

        return slow;
    }


    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 6};
        ListNode listNode = ListNode.buildListNode(a);
        ListNode midNode = findMidNode(listNode);
        System.out.println(midNode);
    }
}
