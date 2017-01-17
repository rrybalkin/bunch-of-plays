package com.rrybalkin.datasructs;

import java.util.Objects;

public class BinaryTree<T extends Comparable<T>, D> {
    private Node<T, D> root;

    public void put(T key, D value) {
        Objects.requireNonNull(key, "key requires not null");
        Node<T, D> n = new Node<>(key, value);
        if (root == null) {
            root = n;
        } else {
            Node<T, D> cur = root;
            while (true) {
                if (key.compareTo(cur.getKey()) < 0) {
                    // left side
                    if (cur.getLeft() == null) {
                        cur.setLeft(n);
                        break;
                    } else {
                        cur = cur.getLeft();
                    }
                } else if (key.compareTo(cur.getKey()) > 0) {
                    // right side
                    if (cur.getRight() == null) {
                        cur.setRight(n);
                        break;
                    } else {
                        cur = cur.getRight();
                    }
                } else if (key.compareTo(cur.getKey()) == 0) {
                    cur.setData(value);
                    break;
                }
            }
        }
    }

    public D get(T key) {
        Objects.requireNonNull(key, "key requires not null");
        Node<T, D> cur = root;
        while (true) {
            if (cur == null) return null;
            if (key.compareTo(cur.getKey()) < 0) {
                // left side
                cur = cur.getLeft();
            } else if (key.compareTo(cur.getKey()) > 0) {
                // right side
                cur = cur.getRight();
            } else if (key.compareTo(cur.getKey()) == 0) {
                return cur.getData();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        nodeToString(s, 0, root);
        return s.toString();
    }

    private void nodeToString(StringBuilder s, int shifts, Node node) {
        if (node == null || node.isLeaf()) {
            return;
        }
        s.append("\n");
        for (int i = 0; i < shifts; i++) {
            s.append(" ");
        }
        s.append("[").append(node.getKey()).
                append(": l->").append(node.getLeft()).
                append(", r->").append(node.getRight());
        nodeToString(s, ++shifts, node.getLeft());
        nodeToString(s, shifts, node.getRight());
    }
}

class Node<T, D> {
    private T key;
    private D data;
    private Node<T, D> left;
    private Node<T, D> right;

    public Node(T key, D data) {
        this.key = key;
        this.data = data;
    }

    public void setLeft(Node<T, D> left) {
        this.left = left;
    }

    public Node<T, D> getLeft() {
        return left;
    }

    public void setRight(Node<T, D> right) {
        this.right = right;
    }

    public Node<T, D> getRight() {
        return right;
    }

    public T getKey() {
        return key;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?, ?> node = (Node<?, ?>) o;

        return key.equals(node.key);

    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return "[" + key + "-" + data + "]";
    }
}
