package com.wandou.demo.leetcode;

import org.junit.Test;

/**
 * @author liming
 * @date 2020/10/12
 * @description 2. 两数相加【中等】
 * <p>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 */
public class L2_AddTwoNumbers {

    @Test
    public void test() {
        ListNode head1 = new ListNode(2, null);
        ListNode tail1;
        head1.next = new ListNode(4, null);
        tail1 = head1.next;
        tail1.next = new ListNode(3, null);

        ListNode head2 = new ListNode(5, null);
        ListNode tail2;
        head2.next = new ListNode(6, null);
        tail2 = head2.next;
        tail2.next = new ListNode(4, null);

        ListNode target = addTwoNumbers(head1, head2);
        System.out.println("target = " + target);
        while (target != null) {
            System.out.println(target.val);
            target = target.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = null;
        ListNode tail = null;
        while (l1 != null || l2 != null) {
            int n1 = l1 == null ? 0 : l1.val;
            int n2 = l2 == null ? 0 : l2.val;
            int temp = n1 + n2 + carry;
            carry = 0;
            if (temp >= 10) {
                carry = 1;
                temp = temp - 10;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            if (head == null) {
                head = tail = new ListNode(temp, null);
            } else {
                tail.next = new ListNode(temp, null);
                tail = tail.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry, null);
        }
        return head;
    }


    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
