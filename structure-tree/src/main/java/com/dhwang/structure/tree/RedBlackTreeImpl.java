package com.dhwang.structure.tree;

import java.util.List;

/**
 * 红黑树
 *
 * @author : joe
 * create at:  2019-11-22  14:39
 */
public class RedBlackTreeImpl implements BinaryTree {

    @Override
    public TreeNode find(TreeNode root, Integer value) {
        if(null == root || null == value)
        {
            return null;
        }
        TreeNode cp = root;
        while (null != cp)
        {
            if(value.equals(cp.getValue()))
            {
                return cp;
            }
            else if(value > cp.getValue())
            {
                cp = cp.getRightNode();
            }
            else
            {
                cp = cp.getLeftNode();
            }
        }
        return null;
    }

    @Override
    public TreeNode insert(TreeNode root, Integer value) {
        if(null == value)
        {
            // 插入节点为空不做处理
            return root;
        }
        if(null == root)
        {
            // 根节点为空，将插入节点作为根节点
            root = new TreeNode(value);
            root.setRed(false);
            return root;
        }
        boolean isLeft = true;
        TreeNode p = root;
        TreeNode pp = null;
        while (null != p)
        {
            pp = p;
            Integer pVal = p.getValue();
            if(value.equals(pVal))
            {
                // 插入节点在树中已经存在，不在做插入
                return root;
            }
            else if(pVal > value)
            {
                p = pp.getLeftNode();
                isLeft = true;
            }
            else
            {
                p = pp.getRightNode();
                isLeft = false;
            }
        }
        TreeNode newNode = new TreeNode(value);
        newNode.setParentNode(pp);
        if(isLeft)
        {
            pp.setLeftNode(newNode);
        }
        else
        {
            pp.setRightNode(newNode);
        }
        root = balanceTreeInsert(root, newNode);
        return root;
    }

    private TreeNode balanceTreeInsert(TreeNode root, TreeNode x) {
        TreeNode xp, xpp, xppl, xppr;
        while(true)
        {
            xp = x.getParentNode();
            if(null == xp)
            {
                x.setRed(false);
                return x;
            }
            else if(!xp.getRed() || null == xp.getParentNode())
            {
                return root;
            }
            xpp = xp.getParentNode();
            xppl = xpp.getLeftNode();
            xppr = xpp.getRightNode();
            if(xp.equals(xppl))
            {
                if(null != xppr && xppr.getRed())
                {
                    xppl.setRed(false);
                    xppr.setRed(false);
                    xpp.setRed(true);
                    x = xpp;
                }
                else
                {
                    if(x.equals(xp.getRightNode()))
                    {
                        x = xp;
                        root = rotateLeft(root, x);
                        xpp = (xp = x.getParentNode()) == null ? null : xp.getParentNode();
                    }
                    if(null != xp)
                    {
                        xp.setRed(false);
                        if(null != xpp)
                        {
                            xpp.setRed(true);
                            root = rotateRight(root, xpp);
                        }
                    }
                }
            }
            else
            {
                if(null != xppl && xppl.getRed())
                {
                    xppl.setRed(false);
                    xppr.setRed(false);
                    xpp.setRed(true);
                    x = xpp;
                }
                else
                {
                    if(x.equals(xp.getLeftNode()))
                    {
                        x = xp;
                        root = rotateRight(root, x);
                        xpp = (xp = x.getParentNode()) == null ? null : xp.getParentNode();
                    }
                    if(null != xp)
                    {
                        xp.setRed(false);
                        if(null != xpp)
                        {
                            xpp.setRed(true);
                            root = rotateLeft(root, xpp);
                        }
                    }
                }
            }
        }
    }

    private TreeNode rotateRight(TreeNode root, TreeNode x)
    {
        TreeNode xp = x.getParentNode();
        TreeNode xl = x.getLeftNode();
        if(null != xl)
        {
            TreeNode xlr = xl.getRightNode();
            if(null != xp && x.equals(xp.getLeftNode()))
            {
                xp.setLeftNode(xl);
            }
            else if(null != xp && x.equals(xp.getRightNode()))
            {
                xp.setRightNode(xl);
            }
            else if(null == xp)
            {
                xl.setRed(false);
                root = xl;
            }
            xl.setParentNode(xp);
            xl.setRightNode(x);
            x.setParentNode(xl);
            x.setLeftNode(xlr);
            if(null != xlr)
            {
                xlr.setParentNode(x);
            }
        }
        return root;
    }

    private TreeNode rotateLeft(TreeNode root, TreeNode x) {
        TreeNode xp = x.getParentNode();
        TreeNode xr = x.getRightNode();
        if(null != xr)
        {
            TreeNode xrl = xr.getLeftNode();
            if(null != xp && x.equals(xp.getLeftNode()))
            {
                xp.setLeftNode(xr);
            }
            else if(null != xp && x.equals(xp.getRightNode()))
            {
                xp.setRightNode(xr);
            }
            else if(null == xp)
            {
                xr.setRed(false);
                root = xr;
            }
            xr.setParentNode(xp);
            xr.setLeftNode(x);
            x.setParentNode(xr);
            x.setRightNode(xrl);
            if(null != xrl)
            {
                xrl.setParentNode(x);
            }
        }
        return root;
    }

    @Override
    public TreeNode delete(TreeNode root, Integer value) {

        TreeNode delNode = find(root, value);
        if(null == delNode)
        {
            // 待删除节点不存在，直接返回
            return root;
        }

        TreeNode x = delNode;
        TreeNode xp = delNode.getParentNode();
        TreeNode xl = delNode.getLeftNode();
        TreeNode xr = delNode.getRightNode();
        if(null == xp && null == xl && null == xr)
        {
            // 只有一个根节点，直接删除
            return null;
        }

        while (true)
        {
            if(null == xl && null == xr)
            {
                break;
            }

            if(null != xl && null == xr)
            {
                xl.setParentNode(xp);
                x.setParentNode(xl);
                x.setLeftNode(xl.getLeftNode());
                x.setRightNode(xl.getRightNode());
                xl.setLeftNode(x);
                Boolean isRed = x.getRed();
                x.setRed(xl.getRed());
                xl.setRed(isRed);
                if(null != xp)
                {
                    if(x.equals(xp.getLeftNode()))
                    {
                        xp.setLeftNode(xl);
                    }
                    else
                    {
                        xp.setRightNode(xl);
                    }
                }
            }


        }





        return null;
    }

    private void swapNode(TreeNode node1, TreeNode node2)
    {
        TreeNode pNode1 = node1.getParentNode();
        TreeNode lNode1 = node1.getLeftNode();
        TreeNode rNode1 = node1.getRightNode();
        Boolean colorNode1 = node1.getRed();


//        node1.setRed(node2.get);


        if(node2.getParentNode() == node1)
        {

        }


    }

    @Override
    public TreeNode buildTree(List<Integer> valueList) {
        if(null == valueList || valueList.size() == 0)
        {
            return null;
        }
        TreeNode root = null;
        for(int i = 0; i < valueList.size(); i ++)
        {
            root = insert(root, valueList.get(i));
        }
        return root;
    }

    @Override
    public void iterator(TreeNode root) {
        if(null == root){
            return;
        }
        iterator(root.getLeftNode());
        System.out.print("[value=" + root.getValue() + ",isRed=" + root.getRed() +
                ",left=" + getTreeVal(root.getLeftNode()) + ",right=" + getTreeVal(root.getRightNode()) + "] ");
        iterator(root.getRightNode());
    }

    private String getTreeVal(TreeNode node)
    {
        if(null == node)
        {
            return "NULL";
        }

        return String.valueOf(node.getValue());
    }
}