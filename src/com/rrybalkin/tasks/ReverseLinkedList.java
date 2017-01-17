package com.rrybalkin.tasks;

/**
 * Created by Roman Rybalkin
 * 10.10.16
 */
public class ReverseLinkedList {

    public static void main(String... args) {
        Node head = getHead();
        printLinkedList(head);
        //reverse(head);
        Node newHead = reverse(head);
        printLinkedList(newHead);
    }

    public static Node reverse(Node n) {
        if (n.next != null) {
            Node nextNode = reverse(n.next);
            nextNode.next = n;
            return n;
        }
        return n;
    }

    public static Node getHead() {
        Node head = null;
        Node prev = null;
        for (int i = 0; i < 10; i++) {
            Node node = new Node();
            node.value = i+1;
            if (head == null) {
                head = node;
                prev = head;
            } else {
                prev.next = node;
                prev = node;
            }
        }
        return head;
    }

    public static void printLinkedList(Node head) {
        Node next = head;
        while (next != null) {
            System.out.print(next + " ");
            next = next.next;
        }
        System.out.print("\n");
    }
}

class Node {
    Node next;
    int value;

    @Override
    public String toString() {
        return "Node[" + value + "]";
    }
}