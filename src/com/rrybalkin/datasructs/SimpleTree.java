package com.rrybalkin.datasructs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class realizes data structure - Tree
 */
public class SimpleTree<T> {
	/**
	 *  Leafs of Tree in type Map: Key - T data, Value - Node<T> object
	 */
    private Map<T, Node<T>> leafs;

    public SimpleTree() {
    	leafs = new HashMap<>();
    }
    
    /**
     * Method finds roots of Tree
     * 	- root is taken as Node<T> without parent Node<T>
     * @return list of roots
     */
    public List<Node<T>> getRoots() {
    	List<Node<T>> roots = new ArrayList<Node<T>>();
    	for (T leaf : leafs.keySet()) {
    		Node<T> node = leafs.get(leaf);
    		if (!node.existParent())
    			roots.add(node);
    	}
    	
    	return roots;
    }
    
    /**
     * Method adds new Node<T> leaf to Tree
     * @param leaf - Node<T> object
     * @param parent - parent Node<T> object
     */
    public void addLeaf(T leaf, T parent) {
    	Node<T> node;
    	Node<T> parentNode;
    	
    	if (leafs.containsKey(leaf)) {
    		// leaf already exists
    		if (leafs.containsKey(parent)) {
    			node = leafs.get(leaf);
    			parentNode = leafs.get(parent);
    			node.setParent(parentNode);
    		} else {
        		parentNode = new Node<>(parent);
        		node = leafs.get(leaf);
        		node.setParent(parentNode);
    		}
    	} else if (leafs.containsKey(parent)) {
    		// leaf doesn't exist, parent already exists
    		parentNode = leafs.get(parent);
    		node = new Node<>(leaf, parentNode);
    	} else {
    		// leaf and parent doesn't exist
    		parentNode = new Node<>(parent);
    		node = new Node<>(leaf, parentNode);
    	}
    	
    	parentNode.addChild(node);
    	
    	if (!leafs.containsKey(node.data)) leafs.put(leaf, node);
    	if (!leafs.containsKey(parentNode.data)) leafs.put(parent, parentNode);
    }

    /**
     * Inner class describes object Node
     * 	it has data, link to parent node, children nodes
     * @param <T> - Object's type in the tree.
     */
    public static class Node<T> {
        private T data;
        private Node<T> parent;
        private List<Node<T>> children;
        
        public Node(T data) {
        	this(data, null);
        }
        
        public Node(T data, Node<T> parent) {
        	this.data = data;
        	this.parent = parent;
        	this.children = new ArrayList<>();
        }
        
        public T getData() {
        	return this.data;
        }
        
        public Node<T> getParent() {
        	return this.parent;
        }
        
        public void setParent(Node<T> parent) {
        	this.parent = parent;
        }
        
        public void addChild(Node<T> child) {
        	this.children.add(child);
        }
        
        public List<Node<T>> getChildren() {
        	return this.children;
        }
        
        public boolean existParent() {
        	return (this.parent != null);
        }
     }
}