package com.algo.jolt.algorithm;

import java.util.Arrays;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArraysAlgo {
    public static void main(String[] args) {
        int[] arr = new int[] { -1, 0, 3, 5, 9, 12 };
        var aa = new ArraysAlgo();
        // log.info("二分查找结果: {}", aa.search(arr, 9));
        // log.info("移除元素结果: {}", aa.removeElement_slowFastPointers(arr, 9));
        // log.info("有序数组平方" + Arrays.toString(aa.sortedSquares(arr)));
        // log.info("null"+ aa.minSubArrayLen(2, new int[]{2,3,1,2,4,3}));
        // aa.generateMatrix(4);
        aa.Qujianhe();
    }

    // 704 二分查找
    public int search(int[] nums, int target) {
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

    // 27 移除元素
    public int removeElement_slowFastPointers(int[] nums, int val) {
        int slowIdx = 0;
        for (int fastIdx = 0; fastIdx < nums.length; fastIdx++) {
            if (nums[fastIdx] != val) {
                nums[slowIdx] = nums[fastIdx];
                slowIdx++;
            }
        }
        return slowIdx;
    }

    public int removeElement_twoWayPointers(int[] nums, int val) {
        int l = 0, r = nums.length - 1;
        while (r >= 0 && nums[r] == val) { // 有等号
            r--;
        }
        while (l <= r) { // 有等号
            if (nums[l] == val) {
                nums[l] = nums[r];
                r--;
            }
            l++;
            while (r >= 0 && nums[r] == val) { // 有等号
                r--;
            }
        }
        return l;
    }

    // 977.有序数组的平方
    public int[] sortedSquares(int[] nums) {
        int n=nums.length;
        int[] result = new int[n];
        int l = 0, r = n - 1, index = n - 1;
        while (l<=r) {
            if (nums[l]*nums[l] <= nums[r]*nums[r]) {
                result[index--] = nums[r] * nums[r--];
            } else {
                result[index--] = nums[l] * nums[l++];
            }
        }
        return result;
    }

    // 209.长度最小的子数组
    public int minSubArrayLen(int target, int[] nums) {
        int inWindowSum = 0;
        int windowStartIdx = 0;
        int minWindowLen = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            inWindowSum += nums[right];
            while (inWindowSum >= target) {
                minWindowLen = Math.min(minWindowLen, right - windowStartIdx + 1);
                inWindowSum -= nums[windowStartIdx++]; // idx++，不能忘
            }
        }
        return minWindowLen == Integer.MAX_VALUE ? 0 : minWindowLen;
    }

    // 59. 螺旋矩阵 II
    public int[][] generateMatrix(int n) {
        int count = 0;
        int startx = 0, starty = 0; // 本轮起点位置
        int loop = 1; // 总轮数
        int i = 0, j = 0; // 当前位置
        int offset = 1; // 距离边缘的距离，初始为 1，每轮+1
        int[][] result = new int[n][n];
        while (loop <= n / 2) {
            for (j = starty; j < n - offset; j++) {
                result[startx][j] = count++; // 注意这轮的行号应该是 startX，i 此时 = 0，尚未重新赋值
            }
            for (i = startx; i < n - offset; i++) {
                result[i][j] = count++;
            }
            for (; j > starty; j--) {
                result[i][j] = count++;
            }
            for (; i > startx; i--) {
                result[i][j] = count++;
            }
            startx++;
            starty++;
            offset++;
            loop++;
        }
        if (n % 2 != 0) {
            result[startx][starty] = count;
        }

        // 为便于观察输出，和题目无关
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.print("\n");
        }
        return result;
    }

    // 卡码网 58. 区间和 （ACM 模式，自己读输入，写输出）
    public void Qujianhe() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n]; //数组
        int[] preSums = new int[n]; //前缀和
        int preSum = 0;
        int l, r;
        // 读入整个数组
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            preSum += nums[i];
            preSums[i] = preSum;
        }
        while (sc.hasNextInt()) {
            l = sc.nextInt();
            r = sc.nextInt();
            if (l == 0) {
                System.out.println(preSums[r]);
            } else {
                System.out.println(preSums[r] - preSums[l - 1]); // 注意左边界是 l-1，否则 l 的话就把区间的第一个值减掉了
            }
        }
        sc.close();
    }

    //TODO: 44. 开发商购买土地(卡码网，没做)

}
