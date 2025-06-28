package com.algo.jolt.SXL_algorithm;

import java.util.Scanner;

public class DynamicProgrammingAlgo {

    // 416. 分割等和子集
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                if (dp[j] == target) { // 剪枝，如果已经相等就结束
                    return true;
                }
            }
        }
        return dp[target] == target;
    }

    // 198. 打家劫舍
    public int rob_dp_len(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        } else if (len == 1) {
            return nums[0];
        }
        int[] dp = new int[len];
        // 因为递推时要用前 2 个值，所以初始化他俩
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len - 1];
    }

    public int rob_dp_3(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        } else if (len == 1) {
            return nums[0];
        } else if (len == 2) {
            return Math.max(nums[0], nums[1]); // 不加这句，在此思路下无法处理 只有 2 个元素的 case
        }
        int[] dp = new int[3];
        // 因为递推时要用前 2 个值，所以初始化他俩
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        // 滚动数组
        for (int i = 2; i < len; i++) {
            dp[2] = Math.max(dp[1], dp[0] + nums[i]);
            dp[0] = dp[1];
            dp[1] = dp[2];
        }
        return dp[2];
    }

    public int rob_dp_2(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        } else if (len == 1) {
            return nums[0];
        } else if (len == 2) {
            return Math.max(nums[0], nums[1]); // 不加这句，在此思路下无法处理 只有 2 个元素的 case
        }
        // 数组只用 2 格，存滚动值。ans 放在单独元素
        int[] dp = new int[2];
        int ans = 0;
        // 因为递推时要用前 2 个值，所以初始化他俩
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        // 滚动数组
        for (int i = 2; i < len; i++) {
            ans = Math.max(dp[1], dp[0] + nums[i]);
            dp[0] = dp[1];
            dp[1] = ans;
        }
        return ans;
    }

    // 213. 打家劫舍 II
    public int rob_2(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        } else if (len == 1) {
            return nums[0];
        }
        // 分别计算不偷第一个房子和不偷最后一个房子的情况
        return Math.max(robAction(nums, 0, len - 1), robAction(nums, 1, len));
    }

    private int robAction(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }
        int[] dp = new int[2];
        dp[0] = nums[start];
        if (start + 1 < end) {
            dp[1] = Math.max(nums[start], nums[start + 1]);
        } else {
            dp[1] = dp[0];
        }

        for (int i = start + 2; i < end; i++) {
            int temp = Math.max(dp[1], dp[0] + nums[i]);
            dp[0] = dp[1];
            dp[1] = temp;
        }
        return dp[1];
    }

    // 474. 一和零
    public int findMaxForm(String[] strs, int m, int n) {
        int zeroCount, oneCount;
        int[][] dp = new int[m + 1][n + 1]; // 有 0 的一列，所以 数组长度需要 + 1
        for (String str : strs) { // 外层遍历字符串（物品）
            zeroCount = 0;
            oneCount = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    zeroCount++;
                } else {
                    oneCount++;
                }
            }
            // 内层遍历容量，两个维度无先后顺序之分
            // 注意遍历最后一格是zeroCount和oneCount，不要走到0
            for (int i = m; i >= zeroCount; i--) {
                for (int j = n; j >= oneCount; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroCount][j - oneCount] + 1);
                }
            }
        }
        return dp[m][n];
    }

    // 121. 买卖股票的最佳时机（只买卖一次）
    // 法 1：完整长度
    public int maxProfit_lenArray(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0]; // 0列表示今天持有；1 列表示今天不持有
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]); // 昨天就持有 or 今天买入
            dp[i][1] = Math.max(dp[i - 1][1], prices[i] + dp[i - 1][0]); // 昨天就没有 or 今天卖出拿钱
        }
        return dp[len - 1][1];
    }
    
    // 法 2：滚动数组，长度为 2
    public int maxProfit_len2(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[2][2];
        dp[0][0] = -prices[0]; // 0列表示今天持有；1 列表示今天不持有
        for (int i = 1; i < len; i++) {
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], -prices[i]); // 昨天就持有 or 今天买入
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], prices[i] + dp[(i - 1) % 2][0]); // 昨天就没有 or 今天卖出拿钱
        }
        return dp[(len - 1) % 2][1]; // 最终不应该还拿着股票，拿着没用，不会回本，所以一定返回的是[1]
    }

    // 309. 买卖股票的最佳时机（买卖多次，含冷冻期）
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int[][] dp = new int[2][4];
        // 初始化状态
        dp[0][0] = -prices[0];

        for (int i = 1; i < len; i++) {
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0],
                    Math.max(dp[(i - 1) % 2][1] - prices[i], dp[(i - 1) % 2][3] - prices[i]));
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], dp[(i - 1) % 2][3]);
            dp[i % 2][2] = dp[(i - 1) % 2][0] + prices[i];
            dp[i % 2][3] = dp[(i - 1) % 2][2];
        }
        return Math.max(dp[(len - 1) % 2][1], Math.max(dp[(len - 1) % 2][2], dp[(len - 1) % 2][3]));
    }
}

// 最普通的 0-1 背包问题，使用二维数组
class ZeroOneKnapsack_matrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int objectCount = sc.nextInt();
        int space = sc.nextInt();
        int[] weight = new int[objectCount];
        int[] value = new int[objectCount];
        for (int i = 0; i < objectCount; i++) {
            weight[i] = sc.nextInt();
        }
        for (int i = 0; i < objectCount; i++) {
            value[i] = sc.nextInt();
        }
        int dp[][] = new int[objectCount][space + 1]; // 空间要多一列，因为有第 0 列
        // Java 初始化默认为 0，可以不初始化
        for (int i = 0; i < weight[0]; i++) { // 0 行，不够放第一个物品时
            dp[0][i] = 0;
        }
        for (int i = weight[0]; i <= space; i++) { // 0 行，够放第一个物品时
            dp[0][i] = value[0];
        }
        for (int i = 1; i < objectCount; i++) { // 0 列
            dp[i][0] = 0;
        }
        for (int i = 1; i < objectCount; i++) {
            for (int j = 0; j <= space; j++) {
                if (j >= weight[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[objectCount - 1][space]);
    }
}

// 0-1 背包问题，使用一维数组
class ZeroOneKnapsack_array {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int objectCount = sc.nextInt();
        int space = sc.nextInt();
        int[] weight = new int[objectCount];
        int[] value = new int[objectCount];
        for (int i = 0; i < objectCount; i++) {
            weight[i] = sc.nextInt();
        }
        for (int i = 0; i < objectCount; i++) {
            value[i] = sc.nextInt();
        }
        int dp[] = new int[space + 1]; // 空间要多一列，因为有第 0 列
        // Java 初始化默认为 0，无需初始化
        for (int i = 0; i < objectCount; i++) {
            for (int j = space; j >= weight[i]; j--) { // 比 weight[i] 小的列别去了，放不下
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        System.out.println(dp[space]);
    }
}
