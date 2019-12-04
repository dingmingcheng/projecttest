package com.dmc;

/**
 * 输入两个链表，找出它们的第一个公共结点。
 */


public class problem4 {
    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) {

    }
    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        int length1 = 0, length2 = 0;
        ListNode node1 = pHead1;
        ListNode node2 = pHead2;
        while (node1 != null) {
            node1 = node1.next;
            length1 ++;
        }
        while (node2 != null) {
            node2 = node2.next;
            length2 ++;
        }
        int balance = Math.abs(length1 - length2);
        node1 = pHead1;
        node2 = pHead2;
        if (length1 > length2) {
            while (balance-- > 0) {
                node1 = node1.next;
            }
        } else {
            while (balance-- > 0) {
                node2 = node2.next;
            }
        }
        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }
}
