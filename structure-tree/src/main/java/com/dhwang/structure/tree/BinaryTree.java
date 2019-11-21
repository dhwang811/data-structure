package com.dhwang.structure.tree;

import java.util.List;

/**
 * @author : joe
 * create at:  2019-11-12  18:30
 */
public interface BinaryTree {

    /**
     * 根据value查找treeNode
     *
     * @param root
     * @param value
     * @return
     */
    TreeNode find(TreeNode root, Integer value);


    /**
     * 插入数据
     *
     * @param root
     * @param value
     * @return
     */
    TreeNode insert(TreeNode root, Integer value);

    /**
     * 删除数据
     *
     * @param root
     * @param value
     * @return
     */
    Boolean delete(TreeNode root, Integer value);

    /**
     * 构建树，返回根节点
     *
     * @param valueList
     * @return
     */
    TreeNode buildTree(List<Integer> valueList);

}