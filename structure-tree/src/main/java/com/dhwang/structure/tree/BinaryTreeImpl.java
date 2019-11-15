package com.dhwang.structure.tree;

import java.util.List;

/**
 *
 *
 * @author : joe
 * create at:  2019-11-13  15:23
 */
public class BinaryTreeImpl implements BinaryTree {


    @Override
    public TreeNode find(TreeNode root, Integer value) {
        return null;
    }

    @Override
    public Boolean insert(TreeNode root, Integer value) {
        return null;
    }

    @Override
    public Boolean delete(TreeNode root, Integer value) {
        return null;
    }

    @Override
    public TreeNode buildTree(List<Integer> valueList) {
        TreeNode root;
        if(null == valueList || valueList.size() == 0)
        {
            return null;
        }
        else if(valueList.size() == 1)
        {
            root = new TreeNode(valueList.get(0));
            return root;
        }




        return null;
    }
}