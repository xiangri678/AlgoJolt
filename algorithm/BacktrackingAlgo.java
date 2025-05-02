package com.algo.jolt.algorithm;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;

public class BacktrackingAlgo {

}

// 77. 组合
class Solution77 {
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        backTracking(n, k, 1);
        return ans;
    }

    private void backTracking(int n, int k, int startIdx) {
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIdx; i <= n - (k - path.size()) + 1; i++) { // 手动算一个 case，搞清楚 i 的边界、<=
            path.add(i);
            backTracking(n, k, i + 1);
            path.removeLast();
        }
    }
}

// 216. 组合总会
class Solution216 {
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backTracking(n, k, 0, 1);
        return ans;
    }

    private void backTracking(int targetSum, int k, int sum, int startIdx) {
        if (path.size() == k && targetSum == sum) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (sum > targetSum) {
            return;
        }
        for (int i = startIdx; i <= 9 - (k - path.size()) + 1; i++) {
            sum += i;
            path.add(i);
            backTracking(targetSum, k, sum, i + 1);
            sum -= i;
            path.removeLast();
        }
    }
}

// 40. 组合总和 II
// 使用 i 直接判断法
class Solution_40_i {
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backTracking(candidates, target, 0, 0);
        return ans;
    }

    private void backTracking(int[] candidates, int target, int sum, int startIdx) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIdx; i < candidates.length && sum + candidates[i] <= target; i++) {
            if (i > startIdx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            sum += candidates[i];
            backTracking(candidates, target, sum, i + 1);
            sum -= candidates[i];
            path.removeLast();
        }

    }
}

// 使用 used 数组法
class Solution_40_used {
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        used = new boolean[candidates.length];
        Arrays.fill(used, false);
        backTracking(candidates, target, 0, 0);
        return ans;
    }

    private void backTracking(int[] candidates, int target, int sum, int startIdx) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIdx; i < candidates.length && sum + candidates[i] <= target; i++) {
            if (i > 0 && used[i - 1] == false && candidates[i] == candidates[i - 1]) {
                continue;
            }
            used[i] = true;
            path.add(candidates[i]);
            sum += candidates[i];
            backTracking(candidates, target, sum, i + 1);
            used[i] = false;
            sum -= candidates[i];
            path.removeLast();
        }

    }
}

// 131. 分割回文串
class Solution_131_notDP {
    List<List<String>> ans = new ArrayList<>();
    List<String> curAns = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backTracking(s, 0);
        return ans;
    }

    private void backTracking(String s, int startIdx) {
        if (startIdx >= s.length()) {
            ans.add(new ArrayList<>(curAns)); // 新建一个副本
            return;
        }
        for (int i = startIdx; i < s.length(); i++) {
            if (computePalindrome(s, startIdx, i)) {
                curAns.add(s.substring(startIdx, i + 1));
                backTracking(s, i + 1);
                curAns.remove(curAns.size() - 1);
            }
        }
    }

    private boolean computePalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}

class Solution_131_DP {
    // TODO: DP 计算回文串的方法还没写，学学 DP 再写~
}

// 78. 子集
class Solution_78 {
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backTracking(0, nums);
        return ans;
    }

    private void backTracking(int startIdx, int[] nums) {
        // 因为本题要的是全集合，所以无需剪枝
        // 无需边界条件判断，因为 fori 循环能够自动停止，不会走超出去
        ans.add(new ArrayList<>(path));
        for (int i = startIdx; i < nums.length; i++) {
            path.add(nums[i]);
            backTracking(i + 1, nums);
            path.removeLast();
        }
    }
}

// 90. 子集 II
class Solution_90 {
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backTracking(nums, 0);
        return ans;
    }

    private void backTracking(int[] nums, int startIdx) {
        ans.add(new ArrayList<>(path));
        for (int i = startIdx; i < nums.length; i++) {
            if (i > startIdx && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backTracking(nums, i + 1);
            path.removeLast();
        }
    }
}

// 491. 非递减子序列
class Solution_491 {
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backTracking(nums, 0);
        return ans;
    }

    private void backTracking(int[] nums, int startIdx) {
        if (path.size() > 1) {
            ans.add(new ArrayList<>(path));
        }
        // 用 3 种数据结构，3 种实现方式
        int[] ar = new int[201];
        // HashSet<Integer> hs = new HashSet<>();
        // HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = startIdx; i < nums.length; i++) {
            if ((!path.isEmpty() && nums[i] < path.getLast()) || ar[nums[i] + 100] == 1) {
            // if ((!path.isEmpty() && nums[i] < path.getLast()) || hs.contains(nums[i])) {
            // if ((!path.isEmpty() && nums[i] < path.getLast()) || hm.containsKey(nums[i])) {
                continue;
            }
            ar[nums[i] + 100] = 1;
            // hs.add(nums[i]);
            // hm.put(nums[i], 1);
            path.add(nums[i]);
            backTracking(nums, i + 1);
            path.removeLast();
        }
    }
}

// 46. 全排列
class Solution_46_used {
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backTracking(nums);
        return ans;
    }

    private void backTracking(int[] nums) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == true) {
                continue;
            } else {
                path.add(nums[i]);
                used[i] = true;
                backTracking(nums);
                path.removeLast();
                used[i] = false;
            }
        }
    }
}

class Solution_46_path {
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        backTracking(nums);
        return ans;
    }

    private void backTracking(int[] nums) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) { // 不用 used 数组，直接判断有无就行了
                continue;
            } else {
                path.add(nums[i]);
                backTracking(nums);
                path.removeLast();
            }
        }
    }
}

// 47. 全排列 2
class Solution_47 {
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backTracking(nums);
        return ans;
    }

    private void backTracking(int[] nums) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && used[i - 1] == false && nums[i - 1] == nums[i]) {
                continue;
            }
            if (used[i] == false) { // 因为没有 startIdx，所以需要检查当前元素是否用过
                path.add(nums[i]);
                used[i] = true;
                backTracking(nums);
                path.removeLast();
                used[i] = false;
            }
        }
    }
}