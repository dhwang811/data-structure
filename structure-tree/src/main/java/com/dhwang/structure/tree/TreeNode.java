package com.dhwang.structure.tree;

/**
 * 树节点
 *
 * @author : joe
 * create at:  2019-11-13  11:05
 */
public class TreeNode {

    private TreeNode parentNode;

    private TreeNode leftNode;

    private TreeNode rightNode;

    private Integer value;

    private Boolean isRed;

    public TreeNode(){

    }

    public TreeNode(Integer value)
    {
        this.value = value;
    }

    public Boolean getRed() {
        return isRed == null ? true : isRed;
    }

    public void setRed(Boolean red) {
        isRed = red;
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}