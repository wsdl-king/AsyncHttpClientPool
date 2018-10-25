package com.server.java.linked;

/**
 * @author qiwenshuai
 * @note 　单链表中环的检测
 *  1->2>3>4
 *      ↑↓
 *     6←5
 *     1.　是否存在环,创造两个节点,快慢节点，当快慢节点经过若干步然后相遇的时候,就说明有闭环。
 *     2.　若想知道环的长度,需要记录第一次相遇的位置,然后第二次相遇的位置与第一次相遇位置之间的差值就是环长度。
 *    3.　若想知道环的入口，则在首次相遇时候，生成一个头节点，并且头结点和相遇点每次前进一位，相遇部分就是环入口。
 * @since 18-10-25 13:39 by jdk 1.8
 */
public class FindRing {





    public void findRing (ListNode node){


    }



    public static void main(String[] args) {
        int [] a={1,2,3,4,5,6};
        ListNode listNode = ListNode.buildListNode(a);
        ListNode  currentNode =listNode;
        listNode.next.next.next.next.next.next=currentNode.next.next;
        /*
         *  假设构造这样一个环
         *  0->1->2>3>4
         *  ↑        ↓
         *  9←8←7←6←5
         */
        System.out.println(listNode);
        //进行环的检测

    }

}
