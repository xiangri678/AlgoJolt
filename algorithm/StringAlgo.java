package com.algo.jolt.algorithm;

import java.util.Scanner;

public class StringAlgo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        StringAlgo sa = new StringAlgo();
        System.out.println(sa.replaceNumber(s));
    }
    
    // 
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            s[l] ^= s[r];
            s[r] ^= s[l];
            s[l++] ^= s[r--];
        }
    }

    // 541. 反转字符串II
    public String reverseStr_1(String s, int k) {
        int n = s.length();
        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < n; i += 2 * k) {
            int firstK = (i + k > n) ? n : i + k;
            int secondK = (i + 2 * k > n) ? n : i + 2 * k;
            StringBuffer tmp = new StringBuffer();
            tmp.append(s.substring(i, firstK));
            ans.append(tmp.reverse());
            if (firstK < secondK) {
                ans.append(s.substring(firstK, secondK));
            }
        }
        return ans.toString();
    }

    public String reverseStr_2(String s, int k) {
        char[] ch = s.toCharArray();
        for (int i = 0; i < s.length(); i += 2 * k) {
            int start = i;
            int end = Math.min(s.length() - 1, start + k - 1);
            while (start < end) {
                ch[start] ^= ch[end];
                ch[end] ^= ch[start];
                ch[start++] ^= ch[end--];
            }
        }
        return new String(ch);
    }

    // 54. 替换数字（卡码网）
    public String replaceNumber(String s) {
        char[] charS = s.toCharArray();
        int oldLength = s.length();
        int newLength = s.length();
        for (int i = 0; i < oldLength; i++) {
            if (Character.isDigit(charS[i])) { // 判断字符串的一个元素是数字的方法
                newLength += 5;
            }
        }
        char[] ans = new char[newLength];
        System.arraycopy(charS, 0, ans, 0, oldLength); // 数组拷贝方法
        int oldEnd = oldLength - 1, newEnd = newLength - 1;
        while (oldEnd>=0) {
            if (!Character.isDigit(ans[oldEnd])) {
                ans[newEnd--] = ans[oldEnd--];
            } else {
                ans[newEnd--] = 'r';
                ans[newEnd--] = 'e';
                ans[newEnd--] = 'b';
                ans[newEnd--] = 'm';
                ans[newEnd--] = 'u';
                ans[newEnd--] = 'n';
                oldEnd--;
            }
        }
        return new String(ans);
    }

}
