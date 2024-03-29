package com.dhwang.structure.tree;

import java.util.Arrays;

/**
 * @author : joe
 * create at:  2019-11-22  09:55
 */
public class BinaryTreeTest {

    public static void main(String[] args) {
        BinaryTree binaryTree = new RedBlackTreeImpl();

        TreeNode root = binaryTree.buildTree(Arrays.asList(9,5,4,10,8,3,12,16,15));

        System.out.println(root.getValue() + " " + root.getRed());

        binaryTree.iterator(root);

        System.out.println();

//        binaryTree.delete(root, 12);
//        System.out.println();
//        System.out.println(root.getValue());
//        binaryTree.iterator(root);
//
//        binaryTree.delete(root, 37);
//        System.out.println();
//        System.out.println(root.getValue());
//        binaryTree.iterator(root);

//        root = binaryTree.delete(root, 9);
//        System.out.println();
//        System.out.println(root.getValue());
//        binaryTree.iterator(root);
//
//        binaryTree.insert(root, 9);
//
//        System.out.println();
//        System.out.println(root.getValue());
//        binaryTree.iterator(root);

    }

}