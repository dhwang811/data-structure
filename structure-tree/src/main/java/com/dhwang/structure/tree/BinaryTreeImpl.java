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

        if(null == root || null == value)
        {
            return null;
        }
        TreeNode p = root;
        while(null != p)
        {
            Integer pVal = p.getValue();
            if(value.equals(pVal))
            {
                //插入节点已存在，返回已存在节点
                return p;
            }
            else if(value > pVal)
            {
                p = p.getRightNode();
            }
            else
            {
                p = p.getLeftNode();
            }
        }
        return null;
    }

    @Override
    public TreeNode insert(TreeNode root, Integer value) {
        if(null == root || null == value)
        {
            return null;
        }
        TreeNode p = root, cp;
        boolean isLeft;
        while(null != p)
        {
            cp = p;
            Integer pVal = cp.getValue();
            if(value.equals(pVal))
            {
                //插入节点已存在，返回已存在节点
                return cp;
            }
            else if(value > pVal)
            {
                p = cp.getRightNode();
                isLeft = false;
            }
            else
            {
                p = cp.getLeftNode();
                isLeft = true;
            }
            if(null == p)
            {
                //插入到当前节点下
                TreeNode treeNode = new TreeNode(value);
                treeNode.setParentNode(cp);
                if(isLeft)
                {
                    cp.setLeftNode(treeNode);
                }
                else
                {
                    cp.setRightNode(treeNode);
                }
            }
        }
        return null;
    }

    @Override
    public TreeNode delete(TreeNode root, Integer value) {
        TreeNode delNode = find(root, value);
        if(null == delNode)
        {
            // 未查询到要删除节点
            return root;
        }
        TreeNode pNode = delNode.getParentNode();
        TreeNode lNode = delNode.getLeftNode();
        TreeNode rNode = delNode.getRightNode();
        if(null == lNode && null == rNode)
        {
            if(null == pNode)
            {
                // 删除节点为根节点
                return null;
            }
            else
            {
                // 删除节点为叶节点
                if(delNode.equals(pNode.getLeftNode()))
                {
                    pNode.setLeftNode(null);
                }
                else
                {
                    pNode.setRightNode(null);
                }
                delNode.setParentNode(null);
            }
        }
        else if(null == lNode || null == rNode)
        {
            //删除节点只有左子节点或右子节点
            if(null != lNode)
            {
                lNode.setParentNode(pNode);
                if(null != pNode)
                {
                    if(delNode.equals(pNode.getLeftNode()))
                    {
                        pNode.setLeftNode(lNode);
                    }
                    else
                    {
                        pNode.setRightNode(lNode);
                    }
                }
                else
                {
                    root = delNode.getLeftNode();
                }

                delNode.setLeftNode(null);
            }
            else
            {
                rNode.setParentNode(pNode);
                if(null != pNode)
                {
                    if(delNode.equals(pNode.getRightNode()))
                    {
                        pNode.setLeftNode(rNode);
                    }
                    else
                    {
                        pNode.setRightNode(rNode);
                    }
                }
                else
                {
                    root = delNode.getRightNode();
                }
                delNode.setRightNode(null);
            }
            delNode.setParentNode(null);
        } else
        {
            // 删除节点包含左右子节点，查找当前节点后继节点替换删除节点
            TreeNode nextNode = getNextNode(delNode);
            TreeNode pNextNode = nextNode.getParentNode();
            TreeNode rNextNode = nextNode.getRightNode();
            nextNode.setParentNode(pNode);
            nextNode.setLeftNode(lNode);

            if(!pNextNode.equals(delNode))
            {
                //后继节点的父节点不是待删除节点
                nextNode.setRightNode(rNode);
                pNextNode.setLeftNode(rNextNode);
            }

            if(null != pNode)
            {
                if(delNode.equals(pNode.getRightNode()))
                {
                    pNode.setRightNode(nextNode);
                }
                else
                {
                    pNode.setLeftNode(nextNode);
                }
            }
            else
            {
                root = nextNode;
            }
            delNode.setParentNode(null);
            delNode.setLeftNode(null);
            delNode.setRightNode(null);
        }
        return root;
    }

    private TreeNode getNextNode(TreeNode delNode) {
        TreeNode p = delNode.getRightNode();
        TreeNode cp = null;
        while (null != p)
        {
            cp = p;
            p = cp.getLeftNode();
        }
        return cp;
    }

    @Override
    public TreeNode buildTree(List<Integer> valueList) {
        TreeNode root;
        if(null == valueList || valueList.size() == 0)
        {
            return null;
        }
        root = new TreeNode(valueList.get(0));
        for(int i = 1; i < valueList.size(); i ++)
        {
            Integer currentVal = valueList.get(i);
            insert(root, currentVal);
        }
        return root;
    }

    @Override
    public void iterator(TreeNode root) {

        if(null == root)
        {
            return;
        }
        iterator(root.getLeftNode());
        System.out.print(root.getValue() + " ");
        iterator(root.getRightNode());
    }
}