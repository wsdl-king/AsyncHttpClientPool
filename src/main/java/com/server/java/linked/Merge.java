package com.server.java.linked;

/**
 * @author qiwenshuai
 * @note 两个链表进行合并
 * @since 18-10-23 17:21 by jdk 1.8
 */
public class Merge {


    // 有序链表的合并
    private static ListNode merge2(ListNode listNode1,
                                   ListNode listNode2
    ) {
        ListNode head = new ListNode(0);
        ListNode currentNode= head;
        while(true){
            if(listNode1== null && listNode2==null){
                break;
                // 1不为空 2大于1 或者2为空 我就要链接1，然后移动1的指针
            }else if (listNode1!=null  &&( listNode2 ==null ||listNode2.val>listNode1.val) ){
                 currentNode.next=listNode1;
                 listNode1=listNode1.next;
            }else if (listNode1 == null || listNode1.val > listNode2.val){
                // 2不为空 1大于2 或者1为空 我就要链接2，然后移动2的指针
                currentNode.next=listNode2;
                listNode2=listNode2.next;
            }
            currentNode=currentNode.next;
        }
        return head.next;

    }


    //没有做到有序合并  只是奇偶合并,并且需要反转
    private static ListNode merge(ListNode listNode1, ListNode listNode2) {
        ListNode endNode = null;
        if (listNode2 == null) {
            return listNode1;
        }
        if (listNode1 == null) {
            return listNode2;
        }
        ListNode nodeNext1 = listNode1;
        ListNode nodeNext2 = listNode2;
        //去除边界
        while (listNode1 != null && listNode2 != null) {
            nodeNext1 = nodeNext1.next;
            nodeNext2 = nodeNext2.next;
            listNode1.next = endNode;
            //1->null
            endNode = listNode1;
            listNode2.next = endNode;
            //2-1-null
            endNode = listNode2;
            listNode1 = nodeNext1;
            listNode2 = nodeNext2;
        }
        return endNode;
    }

    private static ListNode reverse(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return listNode;
        }
        ListNode prev = null;
        ListNode node = listNode;
        ListNode node2 = listNode;
        while (node.next != null) {
            node = node.next;
            node2.next = prev;
            prev = node2;
            node2 = node;
        }
        ListNode listNode1 = new ListNode(node.val);
        listNode1.next = prev;
        return listNode1;
    }

    public static void main(String[] args) {
        int[] a1 = {1, 3, 5, 7};
        int[] a2 = {2, 4, 6, 8};
        ListNode listNode1 = ListNode.buildListNode(a1);
        ListNode listNode2 = ListNode.buildListNode(a2);

        ListNode listNode = merge2(listNode1, listNode2);
//        ListNode merge = merge(listNode1, listNode2);
//        ListNode reverse = reverse(merge);
        //8-7-6-5-4-3-2-1
        System.out.println(listNode);
    }
}
