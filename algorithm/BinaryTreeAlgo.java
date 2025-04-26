package com.algo.jolt.algorithm;

import com.algo.jolt.model.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Deque;

public class BinaryTreeAlgo {
    // 102. 二叉树的层序遍历
    // 递归法
    public List<List<Integer>> levelOrderRecursive(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        levelTraversal(root, 0, ans);
        return ans;
    }
    
    public void levelTraversal(TreeNode node, int depth, List<List<Integer>> ans) {
        if (node == null) {
            return;
        }
        depth++;
        if (ans.size() < depth) { //层级增加时，创建新一层的数组
            List<Integer> thisLevel = new ArrayList<Integer>();
            ans.add(thisLevel);
        }
        ans.get(depth - 1).add(node.val);
        levelTraversal(node.left, depth, ans);
        levelTraversal(node.right, depth, ans);
    }
    
    // 非递归，Queue 法
    public List<List<Integer>> levelOrderQueue(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (root == null)
            return ans;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> thisLevel = new ArrayList<Integer>();
            int len = queue.size();
            while (len-- > 0) {
                TreeNode tmp = queue.poll();
                thisLevel.add(tmp.val);
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
            ans.add(thisLevel);
        }
        return ans;
    }
    
    // 107. 二叉树的层序遍历 II
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            List<Integer> thisLevel = new ArrayList<>();
            while (len-- > 0) {
                TreeNode tmp = queue.poll();
                thisLevel.add(tmp.val);
                if (tmp.left != null) {
                    queue.add(tmp.left);
                }
                if (tmp.right != null) {
                    queue.add(tmp.right);
                }
            }
            ans.addFirst(thisLevel);
        }
        return ans;
    }

    // 199. 二叉树的右视图
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode tmp = queue.pollFirst();
                if (tmp.left != null) {
                    queue.offerLast(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offerLast(tmp.right);
                }
                if (i == len - 1) {
                    ans.add(tmp.val);
                }
            }
        }
        return ans;
    }
    
}
