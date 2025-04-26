package com.algo.jolt.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.Map;

@Slf4j

public class HashAlgo {
    public static void main(String[] args) {
        HashAlgo ha = new HashAlgo();
        // log.info("isAnagram"+ha.isAnagram("anagram", "nqgmara"));
        System.out.println(Arrays.toString(ha.intersection_ArrayList(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 })));
    }
    
    public boolean isAnagram(String s, String t) {
        int[] dict = new int[26];
        for (int i = 0; i < s.length(); i++) {
            dict[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            dict[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < dict.length; i++) {
            if (dict[i] != 0) {
                return false;
            }
        }
        return true;
    }
    
    // 349. 两个数组的交集
    public int[] intersection_HashSet(int[] nums1, int[] nums2) {
        Set<Integer> tmpHashSet = new HashSet<>();
        Set<Integer> ansHashSet = new HashSet<>();
        for (int i : nums1) {
            tmpHashSet.add(i);
        }
        for (int i : nums2) {
            if (tmpHashSet.contains(i)) {
                ansHashSet.add(i);
            }
        }
        // 法 1：创建一个新数组，返回
        int[] ansArray = new int[ansHashSet.size()];
        int j = 0;
        for (int i : ansHashSet) {
            ansArray[j++] = i;
        }
        // return ansArray;
        // 法 2：直接转换数据类型，返回
        return ansHashSet.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] intersection_Array(int[] nums1, int[] nums2) {
        int n = 1002; // 力扣用例说明数组长度最大 1000，所以本题这样够用了
        int[] tmp1 = new int[n];
        int[] tmp2 = new int[n];
        for (int i : nums1) {
            tmp1[i]++;
            System.out.println("tmp1"+i);
        }
        int count = 0;
        for (int i : nums2) {
            if (tmp1[i] > 0 && tmp2[i]!=1) {
                tmp2[i] = 1;
                count++;
                System.out.println("tmp2"+i);
            }
        }
        int[] ans = new int[count];
        int j=0;
        for (int i = 0; i < n; i++) {
            if (tmp2[i] == 1) {
                ans[j++] = i;
            }
        }
        return ans;
    }

    public int[] intersection_ArrayList(int[] nums1, int[] nums2) {
        int n = 1002;
        int[] tmp1 = new int[n];
        int[] tmp2 = new int[n];
        for (int i : nums1) {
            tmp1[i]++;
        }
        for (int i : nums2) {
            tmp2[i]++;
        }
        List<Integer> ansAL = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (tmp1[i] > 0 && tmp2[i] > 0) {
                ansAL.add(i);
            }
        }
        int[] ans = new int[ansAL.size()];
        int j = 0;
        for (int i : ansAL) {
            ans[j++] = i;
        }
        return ans;
    }
    
    // 202. 快乐数
    public boolean isHappy(int n) {
        Set<Integer> record = new HashSet<>();
        while (n != 1 && !record.contains(n)) {
            record.add(n); // 如果无限循环了怎么办？不会吗？
            n = getNextNumber(n);
            System.out.println(n);
        }
        return n == 1;
    }

    private int getNextNumber(int n) {
        int ans = 0;
        while (n > 0) {
            ans += Math.pow((n % 10), 2);
            n /= 10;
        }
        return ans;
    }

    // 1. 两数之和
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[] {};
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[] {};
    }

}
