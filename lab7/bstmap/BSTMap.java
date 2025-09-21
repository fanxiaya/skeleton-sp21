package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    int size;
    BSTNode bstNode;

    private class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        BSTNode() {
            this.key = null;
            this.value = null;
        }


    }

    public BSTMap() {
        this.size = 0;
        this.bstNode = new BSTNode();
    }

    @Override
    public void clear() {
        this.size = 0;
        this.bstNode = new BSTNode();
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null || this.size() == 0) {
            return false;
        } else {
            return containsKeyHelper(this.bstNode, key);
        }


    }

    private boolean containsKeyHelper(BSTNode node, K key) {
        if (node == null) {
            return false;
        } else {
            if (key.compareTo(node.key) == 0) {
                return true;
            } else if (key.compareTo(node.key) < 0) {
                return containsKeyHelper(node.left, key);
            } else {
                return containsKeyHelper(node.right, key);
            }
        }


    }


    @Override
    public V get(K key) {
        if (key == null || this.size() == 0) {
            return null;
        } else {
            return getHelper(this.bstNode, key);
        }
    }

    private V getHelper(BSTNode node, K key) {
        if (node == null) {
            return null;
        } else {
            if (key.compareTo(node.key) == 0) {
                return node.value;
            } else if (key.compareTo(node.key) < 0) {
                return getHelper(node.left, key);
            } else {
                return getHelper(node.right, key);
            }
        }


    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        if (size() == 0) {
            this.bstNode = new BSTNode(key, value);
            size++;
            return;
        }
        this.bstNode = putHelper(this.bstNode, key, value);

        size++;
    }


    private BSTNode putHelper(BSTNode node, K key, V value) {
        if (node == null) {
            return new BSTNode(key, value);
        }

        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            node.left = putHelper(node.left, key, value);
        } else {
            node.right = putHelper(node.right, key, value);
        }
        return node;
    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {

    }

    private void printInOrderHelper(BSTNode node) {
        if (node == null) {
            System.out.println();
        } else {
            System.out.print(bstNode.key);
            System.out.println();
            printInOrderHelper(bstNode.left);
            printInOrderHelper(bstNode.right);

        }


    }


}