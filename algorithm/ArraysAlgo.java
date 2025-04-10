package com.algo.jolt.algorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArraysAlgo {
    public static void main(String[] args) {
        int[] arr = new int[] { -1, 0, 3, 5, 9, 12 };
        log.info("二分查找结果: {}", search(arr, 9));
    }

    // 704 二分查找
    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            } else if (nums[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }
}
