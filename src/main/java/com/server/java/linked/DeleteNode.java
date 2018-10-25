package com.server.java.linked;

/**
 * @author qiwenshuai
 * @note 删除链表中倒数第n个位置的节点
 * @since 18-10-24 20:13 by jdk 1.8
 */
public class DeleteNode {
    // 1-2-3-4-5 　假设我要删除倒数第二个节点.需要考虑边界
    private static ListNode deleteNode(ListNode node, int n) {
        //为空
        if (node == null) {
            return null;
        }
        //　节点长度不足的情况需要考虑
        //统计链表的长度
        int count = 0;
        int deleteCount;
        int indexCount=0;
        ListNode  myNode = new ListNode(0);
        myNode.next=node;
        ListNode deleteNode= node;
        ListNode current = node;
        while (current != null) {
            ++count;
            current = current.next;
        }
        //统计出来count
        if (count - n < 0) {
            return node;
        } else {
            //正数要删除的位置
            deleteCount =count-n+1;
        }
        //1-2-3-4-5  --- 要删除第4个

         while(true){
             ++indexCount;
             if(deleteCount-indexCount>1){
                 //没有到规定的count
                 deleteNode=deleteNode.next;
             }else if (indexCount+1==deleteCount){
                 //达到规定的count　我要进行删除
                 deleteNode.next=deleteNode.next.next;
                 break;
             }
         }
         return myNode.next;
    }


    public static void main(String[] args) {
        int [] a = {1,2,3,4,5};
        ListNode listNode =ListNode.buildListNode(a);
        ListNode listNode1 = deleteNode(listNode, 2);
        System.out.println(listNode1);
    }


}
