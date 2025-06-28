package com.algo.jolt.SXL_algorithm;

import java.util.Arrays;
import java.util.stream.IntStream;

public class GreedyAlgo {
    // 455. 分发饼干
    public int findContentChildren_g(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int ans = 0;
        int idx = s.length - 1;
        for (int i = g.length - 1; i >= 0; i--) {
            if (idx >= 0 && g[i] <= s[idx]) {
                ans++;
                idx--;
            }
        }
        return ans;
    }

    public int findContentChildren_s(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int ans = 0;
        int idx = 0;
        for (int i = 0; i < s.length; i++) {
            if (idx < g.length && s[i] >= g[idx]) {
                ans++;
                idx++;
            }
        }
        return ans;
    }

    // 376. 摆动序列
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int preDiff = 0;
        int curDiff = 0;
        int ans = 1;
        for (int i = 1; i < nums.length; i++) {
            curDiff = nums[i] - nums[i - 1];
            if ((preDiff <= 0 && curDiff > 0) || (preDiff >= 0 && curDiff < 0)) {
                ans++;
                preDiff = curDiff;
            }
        }
        return ans;
    }

    // 53. 最大子数组和
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i];
            ans = Math.max(ans, cur);
            if (cur < 0) {
                cur = 0;
            }
        }
        return ans;
    }

    // 122. 买卖股票的最佳时机 II
    // 本题也有 DP 做法，没怎么巧妙
    public int maxProfit_greedy(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }

    // 55. 跳跃游戏
    public boolean canJump(int[] nums) {
        int cover = 0;
        int len = nums.length;
        if (len == 1) {
            return true;
        }
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(cover, i + nums[i]);
            if (cover >= len - 1) {
                return true;
            }
        }
        return false;
    }

    // 45. 跳跃游戏 2
    public int jump_simulateTrue(int[] nums) {
        int len = nums.length;
        int curDistance = 0;
        int nxtDistance = 0;
        int ans = 0;
        if (len == 1) {
            return 0;
        }
        for (int i = 0; i < len; i++) {
            nxtDistance = Math.max(i + nums[i], nxtDistance);
            if (i == curDistance) {
                ans++;
                curDistance = nxtDistance;
                if (nxtDistance >= len - 1) {
                    break;
                }
            }
        }
        return ans;
    }

    public int jump_unifyCode(int[] nums) {
        int len = nums.length;
        int curDistance = 0;
        int nxtDistance = 0;
        int ans = 0;
        for (int i = 0; i < len - 1; i++) { // 最远走到提前一格的位置，停止循环
            nxtDistance = Math.max(i + nums[i], nxtDistance);
            if (i == curDistance) {
                ans++; // 只要达到当前边界就+1，因为循环条件已经明确少走一格，留出了这里+1 的余地
                curDistance = nxtDistance;
            }
        }
        return ans;
    }

    // 1005. K 次取反后最大化的数组和
    public int largestSumAfterKNegations_array(int[] nums, int k) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 && k > 0) {
                nums[i] = Math.abs(nums[i]);
                k--;
            } else {
                break;
            }
        }
        if (k > 0 && k % 2 == 1) {
            Arrays.sort(nums); // 再次排序，因为之前排序后，原nums[0] 已经变大了
            nums[0] = -nums[0];
        }
        int ans = 0;
        for (int num : nums) {
            ans += num;
        }
        return ans;
    }

    public int largestSumAfterKNegations_stream(int[] nums, int k) {
        nums = IntStream.of(nums).boxed().sorted((n1, n2) -> Math.abs(n2) - Math.abs(n1)).mapToInt(Integer::intValue)
                .toArray();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
        }
        if (k > 0 && k % 2 == 1) {
            nums[nums.length - 1] = -nums[nums.length - 1];
        }
        return Arrays.stream(nums).sum();
    }

    // 134. 加油站
    // 暴力解法，可通过 37/39，超时
    public int canCompleteCircuit_(int[] gas, int[] cost) {
        int restGas, curPos;
        for (int i = 0; i < cost.length; i++) { // 外层 i 遍历起点
            restGas = gas[i] - cost[i];
            curPos = (i + 1) % cost.length;
            while (restGas > 0 && curPos != i) { // 内存 while 遍历本轮的每个位置
                restGas += gas[curPos] - cost[curPos];
                curPos = (curPos + 1) % cost.length;
            }
            if (curPos == i && restGas >= 0) {
                return i;
            }
        }
        return -1;
    }

    public int canCompleteCircuit_general_greedy(int[] gas, int[] cost) {
        int restGasMin = Integer.MAX_VALUE, curGas = 0;
        for (int i = 0; i < cost.length; i++) {
            curGas += gas[i] - cost[i];
            restGasMin = Math.min(curGas, restGasMin);
        }
        if (curGas < 0) { // curGas 累计了全程的剩余油量。若<0说明油不够，咋跑也不行
            return -1;
        }
        if (restGasMin >= 0) { // 默认从 0 出发。剩余油量最小值>0，说明全程有油，返回 0 就完事了
            return 0;
        }
        for (int i = cost.length - 1; i >= 0; i--) { // 因为从 0 开始往后出现了负数，所以从后往前遍历，找出能够使得全部非负的 i
            restGasMin += gas[i] - cost[i];
            if (restGasMin >= 0) {
                return i;
            }
        }
        return -1;
    }

    public int canCompleteCircuit_greedy(int[] gas, int[] cost) {
        int curGas = 0, totalGas = 0, startIdx = 0;
        for (int i = 0; i < cost.length; i++) {
            int rest = gas[i] - cost[i];
            curGas += rest;
            totalGas += rest;
            if (curGas < 0) { // 当前油量不足以到达下一个加油站, 则从下一个加油站开始
                curGas = 0;
                startIdx = i + 1;
            }
        }
        return totalGas < 0 ? -1 : startIdx;
    }

    // 135. 分发糖果
    public int candy(int[] ratings) {
        int ans = 0;
        int len = ratings.length;
        int[] candy = new int[len];
        candy[0] = 1;
        for (int i = 1; i < len; i++) {
            candy[i] = ratings[i] > ratings[i - 1] ? candy[i - 1] + 1 : 1;
        }
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
        }
        for (int c : candy) {
            ans += c;
        }
        return ans;
    }

    // 452. 用最少数量的箭引爆气球
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            return Integer.compare(a[0], b[0]);
        });
        int ans = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= points[i - 1][1]) {
                points[i][1] = Math.min(points[i][1], points[i - 1][1]); // 小技巧：无需一个新元素 curEnd 记录当前结束点。直接把值覆盖到points[i][1] ，下次比较就刚好还能用到这个值
            } else {
                ans++;
            }
        }
        return ans;
    }

    // 435. 无重叠区间
    public int eraseOverlapIntervals_sortRightEnd(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            return Integer.compare(a[1], b[1]); // 按右边界排序
        });
        int notOverlapCount = 1;
        int curEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= curEnd) { // 未重叠
                notOverlapCount++;
                curEnd = intervals[i][1];
            }
        }
        return intervals.length - notOverlapCount;
    }

    public int eraseOverlapIntervals_sortLeftEnd(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            return Integer.compare(a[0], b[0]); // 按左边界排序
        });
        int overlapCount = 0;
        int curEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= curEnd) { // 未重叠，更新边界
                curEnd = intervals[i][1];
            } else {
                curEnd = Math.min(curEnd, intervals[i][1]); // 重叠了，因为这次比较的是左边界，所以还需要更新右边界
                overlapCount++;
            }
        }
        return overlapCount;
    }


}
