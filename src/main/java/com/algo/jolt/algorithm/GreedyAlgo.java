package com.algo.jolt.algorithm;

import java.util.Arrays;

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
}
