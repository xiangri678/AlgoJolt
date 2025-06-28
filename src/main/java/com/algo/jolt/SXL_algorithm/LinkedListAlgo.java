package com.algo.jolt.SXL_algorithm;

class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }
    
    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode nxt) {
        this.val = val;
        this.next = nxt;
    }
}
public class LinkedListAlgo {
    // 203. 移除链表元素
    public ListNode removeElements_dummyNode(ListNode head, int val) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next; // 如果删空了，这就能返回 null；但return head 会返回 head 的指针！
    }

    public ListNode removeElements_recursive(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        head.next = removeElements_recursive(head.next, val);
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }
    }
    
    // 707. 设计链表
    class MyLinkedList_OneWay {
        private ListNode head;
        private int count;

        class ListNode {
            int val;
            ListNode next;

            ListNode(int val) {
                this.val = val;
            }

            ListNode(int val, ListNode nxt) {
                this.val = val;
                this.next = nxt;
            }
        }

        public MyLinkedList_OneWay() {
            this.count = 0;
            this.head = new ListNode(0); // 为避免其他操作反复判断链表是否为空，索性创建一个空节点
        }

        public int get(int index) {
            if (index > count - 1) {
                return -1;
            } else {
                ListNode p = head;
                for (int i = 0; i <= index; i++) {
                    p = p.next;
                }
                return p.val;
            }
        }

        public void addAtHead(int val) {
            ListNode newNode = new ListNode(val);
            newNode.next = head.next;
            head.next = newNode;
            count++;
        }

        public void addAtTail(int val) {
            ListNode newNode = new ListNode(val);
            ListNode p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = newNode;
            count++;
        }

        public void addAtIndex(int index, int val) {
            if (index <= count) {
                ListNode newNode = new ListNode(val);
                ListNode p = head;
                for (int i = 0; i < index; i++) {
                    p = p.next;
                }
                newNode.next = p.next;
                p.next = newNode;
                count++;
            }
        }

        public void deleteAtIndex(int index) {
            if (index < count) {
                ListNode p = head;
                for (int i = 0; i < index; i++) {
                    p = p.next;
                }
                p.next = p.next.next;
                count--;
            }
        }

        public void printLinkList() {
            ListNode p = head;
            while (p != null) {
                System.out.print(p.val + " -> ");
                p = p.next;
            }
            System.out.print("\n");
        }
    }

    class MyLinkedList_TwoWay {
        public MyLinkedList_TwoWay() {
            
        }
    }

    /**
     * Your MyLinkedList object will be instantiated and called as such:
     * MyLinkedList obj = new MyLinkedList();
     * int param_1 = obj.get(index);
     * obj.addAtHead(val);
     * obj.addAtTail(val);
     * obj.addAtIndex(index,val);
     * obj.deleteAtIndex(index);
     */

    // 206.反转链表
    public ListNode reverseList_for(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode nxt = null;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
    
    public ListNode reverseList_recursive(ListNode head) {
        return reverse(head, null);
    }

    private ListNode reverse(ListNode cur, ListNode pre) {
        if (cur == null) {
            return pre;
        }
        ListNode nxt = cur.next;
        cur.next = pre;
        pre = cur;
        cur = nxt;
        return reverse(cur, pre);
    }

    // 232.用栈实现队列
    
}
