package com.algo.jolt.SXL_algorithm;

import java.util.*;

public class backtrackAlgo {
public static void main(String[] args) {
    Solution_332_backtrack_linkedlist solution = new Solution_332_backtrack_linkedlist();
    // 创建测试用例
    List<List<String>> tickets = new ArrayList<>();

    // 添加测试用例1 - 简单例子
    // tickets.add(Arrays.asList("JFK", "SFO"));
    // tickets.add(Arrays.asList("JFK", "ATL"));
    // tickets.add(Arrays.asList("SFO", "ATL"));
    // tickets.add(Arrays.asList("ATL", "JFK"));
    // tickets.add(Arrays.asList("ATL", "SFO"));

    // System.out.println("测试用例1结果: " + solution.findItinerary(tickets));

    // 清空测试用例为第二个测试
    tickets.clear();

    // 添加测试用例2 - 你提供的复杂例子
    tickets.add(Arrays.asList("JFK", "SFO"));
    tickets.add(Arrays.asList("JFK", "ATL"));
    tickets.add(Arrays.asList("SFO", "JFK"));
    tickets.add(Arrays.asList("ATL", "AAA"));
    tickets.add(Arrays.asList("AAA", "ATL"));
    tickets.add(Arrays.asList("ATL", "BBB"));
    tickets.add(Arrays.asList("BBB", "ATL"));
    tickets.add(Arrays.asList("ATL", "CCC"));
    tickets.add(Arrays.asList("CCC", "ATL"));
    tickets.add(Arrays.asList("ATL", "DDD"));
    tickets.add(Arrays.asList("DDD", "ATL"));
    tickets.add(Arrays.asList("ATL", "EEE"));
    tickets.add(Arrays.asList("EEE", "ATL"));
    tickets.add(Arrays.asList("ATL", "FFF"));
    tickets.add(Arrays.asList("FFF", "ATL"));
    tickets.add(Arrays.asList("ATL", "GGG"));
    tickets.add(Arrays.asList("GGG", "ATL"));
    tickets.add(Arrays.asList("ATL", "HHH"));
    tickets.add(Arrays.asList("HHH", "ATL"));
    tickets.add(Arrays.asList("ATL", "III"));
    tickets.add(Arrays.asList("III", "ATL"));
    tickets.add(Arrays.asList("ATL", "JJJ"));
    tickets.add(Arrays.asList("JJJ", "ATL"));
    tickets.add(Arrays.asList("ATL", "KKK"));
    tickets.add(Arrays.asList("KKK", "ATL"));
    tickets.add(Arrays.asList("ATL", "LLL"));
    tickets.add(Arrays.asList("LLL", "ATL"));
    tickets.add(Arrays.asList("ATL", "MMM"));
    tickets.add(Arrays.asList("MMM", "ATL"));
    tickets.add(Arrays.asList("ATL", "NNN"));
    tickets.add(Arrays.asList("NNN", "ATL"));

    String[] ans2 = { "JFK", "SFO", "JFK", "ATL", "AAA", "ATL", "BBB", "ATL", "CCC", "ATL", "DDD", "ATL", "EEE",
            "ATL", "FFF", "ATL", "GGG", "ATL", "HHH", "ATL", "III", "ATL", "JJJ", "ATL", "KKK", "ATL", "LLL", "ATL",
            "MMM", "ATL", "NNN", "ATL" };
    List<String> exp = Arrays.asList(ans2);
    // List<String> back = solution.findItinerary(tickets);
    // System.out.println("测试用例2结果: " + solution.findItinerary(tickets));
    if (exp.equals(solution.findItinerary(tickets))) {
        System.out.println("true");
    }
}
}

// 77. 组合
class Solution77 {
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack(n, k, 1);
        return ans;
    }

    private void backtrack(int n, int k, int startIdx) {
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIdx; i <= n - (k - path.size()) + 1; i++) { // 手动算一个 case，搞清楚 i 的边界、<=
            path.add(i);
            backtrack(n, k, i + 1);
            path.removeLast();
        }
    }
}

// 216. 组合总会
class Solution216 {
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtrack(n, k, 0, 1);
        return ans;
    }

    private void backtrack(int targetSum, int k, int sum, int startIdx) {
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
            backtrack(targetSum, k, sum, i + 1);
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
        backtrack(candidates, target, 0, 0);
        return ans;
    }

    private void backtrack(int[] candidates, int target, int sum, int startIdx) {
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
            backtrack(candidates, target, sum, i + 1);
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
        backtrack(candidates, target, 0, 0);
        return ans;
    }

    private void backtrack(int[] candidates, int target, int sum, int startIdx) {
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
            backtrack(candidates, target, sum, i + 1);
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
        backtrack(s, 0);
        return ans;
    }

    private void backtrack(String s, int startIdx) {
        if (startIdx >= s.length()) {
            ans.add(new ArrayList<>(curAns)); // 新建一个副本
            return;
        }
        for (int i = startIdx; i < s.length(); i++) {
            if (computePalindrome(s, startIdx, i)) {
                curAns.add(s.substring(startIdx, i + 1));
                backtrack(s, i + 1);
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
        backtrack(0, nums);
        return ans;
    }

    private void backtrack(int startIdx, int[] nums) {
        // 因为本题要的是全集合，所以无需剪枝
        // 无需边界条件判断，因为 fori 循环能够自动停止，不会走超出去
        ans.add(new ArrayList<>(path));
        for (int i = startIdx; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(i + 1, nums);
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
        backtrack(nums, 0);
        return ans;
    }

    private void backtrack(int[] nums, int startIdx) {
        ans.add(new ArrayList<>(path));
        for (int i = startIdx; i < nums.length; i++) {
            if (i > startIdx && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backtrack(nums, i + 1);
            path.removeLast();
        }
    }
}

// 491. 非递减子序列
class Solution_491 {
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtrack(nums, 0);
        return ans;
    }

    private void backtrack(int[] nums, int startIdx) {
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
            backtrack(nums, i + 1);
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
        backtrack(nums);
        return ans;
    }

    private void backtrack(int[] nums) {
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
                backtrack(nums);
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
        backtrack(nums);
        return ans;
    }

    private void backtrack(int[] nums) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) { // 不用 used 数组，直接判断有无就行了
                continue;
            } else {
                path.add(nums[i]);
                backtrack(nums);
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
        backtrack(nums);
        return ans;
    }

    private void backtrack(int[] nums) {
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
                backtrack(nums);
                path.removeLast();
                used[i] = false;
            }
        }
    }
}

// 332. 重新安排行程
class Solution_332_dfs { //非常高效，可 AC
    Map<String, PriorityQueue<String>> adj = new HashMap<>();
    LinkedList<String> route = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        // 1. 构建邻接表，使用 PriorityQueue 保证字典序
        for (List<String> ticket : tickets) {
            adj.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }

        // 2. 从 "JFK" 开始 DFS
        dfs("JFK");

        // 3. 返回结果
        return route;
    }

    private void dfs(String airport) {
        PriorityQueue<String> targets = adj.get(airport);

        // 循环访问并移除字典序最小的目的地
        while (targets != null && !targets.isEmpty()) {
            String nextAirport = targets.poll(); // 取出并移除
            dfs(nextAirport); // 递归访问
        }

        // 当一个机场的所有出路都走完后，将其加入结果列表的头部
        route.addFirst(airport);
    }
}

class Solution_332_backtrack_linkedlist { //低效，只过 4 cases
    private LinkedList<String> ans;
    private LinkedList<String> path = new LinkedList<>();
    private boolean[] used;

    public List<String> findItinerary(List<List<String>> tickets) {
        Collections.sort(tickets, (a, b) -> a.get(1).compareTo(b.get(1)));
        used = new boolean[tickets.size()];
        path.add("JFK");
        backtrack(tickets);
        return ans;
    }

    private boolean backtrack(List<List<String>> tickets) {
        if (path.size() == tickets.size() + 1) {
            ans = new LinkedList<>(path);
            return true;
        }
        for (int i = 0; i < tickets.size(); i++) {
            if (used[i] == false && tickets.get(i).get(0).equals(path.getLast())) {
                path.add(tickets.get(i).get(1));
                used[i] = true;
                if (backtrack(tickets)) {
                    return true;
                }
                used[i] = false;
                path.removeLast();
            }
        }
        return false;
    }
}

class Solution_332_backtrack_deque { // 低效，只过 4 cases
    private Deque<String> ans = new LinkedList();
    private Map<String, Map<String, Integer>> adj = new HashMap<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> t : tickets) {
            Map<String, Integer> tmp;
            if (adj.containsKey(t.get(0))) {
                tmp = adj.get(t.get(0));
                tmp.put(t.get(1), tmp.getOrDefault(t.get(1), 0) + 1);
            } else {
                tmp = new TreeMap<>();
                tmp.put(t.get(1), 1);
            }
            adj.put(t.get(0), tmp);
        }
        ans.add("JFK");
        backtrack(tickets.size());
        return new ArrayList<>(ans);// 本来用的 Deque 不是 List，需转换为一致
    }

    private boolean backtrack(int ticketsNum) {
        if (ans.size() == ticketsNum + 1) {
            return true;
        }
        String destination = ans.getLast();
        if (!adj.containsKey(destination)) {
            return false;
        }
        for (Map.Entry<String, Integer> target : adj.get(destination).entrySet()) { // 啥意思
            int count = target.getValue();
            if (count > 0) {
                ans.add(target.getKey());
                target.setValue(count - 1);
                if (backtrack(ticketsNum)) {
                    return true;
                }
                ans.removeLast();
                target.setValue(count);
            }
        }
        return false;
    }
}

// 51. N 皇后
class Solution_51 {
    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessboard[i][j] = '.';
            }
        }
        backtrack(0, n, chessboard);
        return ans;
    }

    public void backtrack(int row, int n, char[][] chessboard) {
        if (row == n) {
            ans.add(recordAns(chessboard));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(row, col, n, chessboard)) { // 先判断有效性，有效再实际递归
                chessboard[row][col] = 'Q';
                backtrack(row + 1, n, chessboard);
                chessboard[row][col] = '.';
            }
        }
    }

    private List<String> recordAns(char[][] chessboard) {
        List<String> ans1 = new ArrayList<>();
        for (char[] cs : chessboard) {
            ans1.add(String.copyValueOf(cs));
        }
        return ans1;
    }

    private boolean isValid(int row, int col, int n, char[][] chessboard) {
        for (int i = 0; i < row; i++) {
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}

// 37. 解数独
class Solution_37 {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                for (char k = '1'; k <= '9'; k++) {
                    if (isValid(i, j, k, board)) {
                        board[i][j] = k;
                        if (backtrack(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean isValid(int row, int col, char k, char[][] board) {
        for (int l = 0; l < board.length; l++) {
            if (board[row][l] == k) {
                return false;
            }
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == k) {
                return false;
            }
        }
        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (!(i == row && j == col) && board[i][j] == k) {
                    return false;
                }
            }
        }
        return true;
    }
}