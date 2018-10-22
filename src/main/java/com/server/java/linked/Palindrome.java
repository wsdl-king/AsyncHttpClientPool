package com.server.java.linked;

/**
 * @author qiwenshuai
 * @note 判断是否回文
 * @since 18-10-22 13:58 by jdk 1.8
 */
public class Palindrome {

    //abba
    private static boolean isPalindrome(ListNode listNode) {
        ListNode prev = null;
        ListNode slow = listNode;
        ListNode fast = listNode;
        //边界判断
        if (listNode == null || listNode.next == null) {
            return true;
        }
        while (fast != null && fast.next != null) {
            //移动快指针
            fast = fast.next.next;
            //开辟空间,保存慢指针节点,用于最后的慢指针赋值。
            ListNode next = slow.next;
            // 反转节点链接到慢节点
            slow.next = prev;
            //重新赋值反转节点
            prev = slow;
            //移动慢指针
            slow = next;
        }
        if (fast != null) {
            //奇数需要下移一位慢节点
            slow = slow.next;
        }
        while (slow != null) {
            if (slow.val != prev.val) {
                return false;
            }
            prev = prev.next;
            slow = slow.next;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] dd = {1, 2, 3,4,2, 1};
        ListNode listNode = ListNode.buildListNode(dd);
        boolean palindrome = isPalindrome(listNode);
        System.out.println(palindrome);
    }


}
