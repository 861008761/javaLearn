package com.mylovin.algo.leetcode0002;

class ListNode {
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

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
        int high = 0;
        int low = 0;
        ListNode result = new ListNode();
        ListNode temp = result;
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                low = (high + l1.val + l2.val) % 10;
                high = (high + l1.val + l2.val) / 10;

                l1 = l1.next;
                l2 = l2.next;

                if (temp.next == null) {
                    temp.next = new ListNode(low);
                    temp = temp.next;
                }
            } else if (l1 != null) {
                while (l1 != null) {
                    low = (high + l1.val) % 10;
                    high = (high + l1.val) / 10;

                    l1 = l1.next;

                    if (temp.next == null) {
                        temp.next = new ListNode(low);
                        temp = temp.next;
                    }
                }
            } else if (l2 != null) {
                while (l2 != null) {
                    low = (high + l2.val) % 10;
                    high = (high + l2.val) / 10;

                    l2 = l2.next;

                    if (temp.next == null) {
                        temp.next = new ListNode(low);
                        temp = temp.next;
                    }
                }
            }
        }
        if (high == 1) {
            temp.next = new ListNode(1);
        }
        return result.next;
        */
        int high = 0;
        int low = 0;
        ListNode result = l1;
        ListNode temp = result;
        ListNode last = null;
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                low = (high + l1.val + l2.val) % 10;
                high = (high + l1.val + l2.val) / 10;

                l1 = l1.next;
                l2 = l2.next;

                temp.val = low;
                last = temp;
                temp = temp.next;
            } else if (l1 != null) {
                while (l1 != null) {
                    low = (high + l1.val) % 10;
                    high = (high + l1.val) / 10;

                    l1 = l1.next;

                    temp.val = low;
                    last = temp;
                    temp = temp.next;
                }
            } else if (l2 != null) {
                while (l2 != null) {
                    low = (high + l2.val) % 10;
                    high = (high + l2.val) / 10;

                    last.next = l2;
                    temp = l2;
                    l2 = l2.next;

                    temp.val = low;
                    last = temp;
                    temp = temp.next;
                }
            }
        }
        if (high == 1) {
            last.next = new ListNode(1);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode l1 = new ListNode(2);
        ListNode l11 = new ListNode(4);
        ListNode l12 = new ListNode(9);
        l1.next = l11;
        l11.next = l12;

        ListNode l2 = new ListNode(5);
        ListNode l21 = new ListNode(6);
        ListNode l22 = new ListNode(4);
        ListNode l23 = new ListNode(9);
        l2.next = l21;
        l21.next = l22;
        l22.next = l23;

        ListNode rst = s.addTwoNumbers(l1, l2);
        while (rst != null) {
            System.out.println(rst.val);
            rst = rst.next;
        }
    }
}
