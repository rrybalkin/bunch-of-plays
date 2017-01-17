package com.rrybalkin.datasructs;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Roman Rybalkin
 * 10.10.15
 */
public class BinaryTreeTest {

    @Test
    public void testBinaryTree() {
        BinaryTree<Integer, String> bTree = new BinaryTree<>();
        bTree.put(1, "1");
        bTree.put(29, "29");
        bTree.put(101, "101");
        bTree.put(14, "14");
        bTree.put(28, "28");
        bTree.put(3, "3");
        bTree.put(400, "400");
        bTree.put(10231, "fasf");
        bTree.put(-102, "daa");
        bTree.put(0, "zero");

        Assert.assertEquals("14", bTree.get(14));

        System.out.println(bTree);
    }
}
