package com.algo.jolt.SXL_algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class GraphTheoryAlgo {

}

// 797. 所有可能的路径
class Solution_797_leetcode {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        path.add(0); // 无论什么路径都从起点 0 开始走
        dfs(graph, 0, graph.length - 1);
        return ans;
    }

    private void dfs(int[][] graph, int x, int target) { // x 当前节点下标，target 目标节点下标
        if (x == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int next : graph[x]) {
            path.add(next);
            dfs(graph, next, target);
            path.remove(path.size() - 1);
        }
    }
}

// 797. 所有可能的路径 ACM 邻接矩阵版
class Solution_797_ACM {
    static List<Integer> path = new ArrayList<>(); // 必须静态
    static List<List<Integer>> ans = new ArrayList<>();

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // n，节点数
        int m = sc.nextInt(); // m，边数
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            graph[s][t] = 1;
        }
        path.add(1);
        dfs(graph, 1, n);
        if (ans.isEmpty()) {
            System.out.println("-1");
        }
        for (List<Integer> p : ans) {
            for (int i = 0; i < p.size() - 1; i++) {
                System.out.print(p.get(i) + " ");
            }
            System.out.println(p.get(p.size() - 1));
        }
    }

    private static void dfs(int[][] graph, int x, int n) { // 必须静态
        if (x == n) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 1; i <= n; i++) { // 目标节点 n，最后要走到 n
            if (graph[x][i] == 1) {
                path.add(i);
                dfs(graph, i, n);
                path.remove(path.size() - 1);
            }
        }
    }
}

// 797. 所有可能的路径 ACM 邻接表版
class Solution_797_ACM_adjacency_list {
    static List<Integer> path = new ArrayList<>(); // 必须静态
    static List<List<Integer>> ans = new ArrayList<>();

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // n，节点数
        int m = sc.nextInt(); // m，边数
        List<LinkedList<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<>()); // 语法特性，不需要写参数 i，能直接添加到最后一个位置上，形成了一个有 n 个链表的数组
        }
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            graph.get(s).add(t);
        }
        path.add(1);
        dfs(graph, 1, n);
        if (ans.isEmpty()) {
            System.out.println("-1");
        }
        for (List<Integer> p : ans) {
            for (int i = 0; i < p.size() - 1; i++) {
                System.out.print(p.get(i) + " ");
            }
            System.out.println(p.get(p.size() - 1));
        }
    }

    private static void dfs(List<LinkedList<Integer>> graph, int x, int n) { // 必须静态
        if (x == n) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i : graph.get(x)) { // 目标节点 n，最后要走到 n
            path.add(i);
            dfs(graph, i, n);
            path.remove(path.size() - 1);
        }
    }
}

//BFS
class bfs_demo {
    private static int[][] DIRECTION = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    private void bfs(int[][] grid, int startX, int startY) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return;
        }
        int rowCount = grid.length;
        int colCount = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rowCount][colCount];
        queue.offer(new int[] { startX, startY });
        visited[startX][startY] = true;
        while (!queue.isEmpty()) {
            int[] curPt = queue.poll();
            int curx = curPt[0];
            int cury = curPt[1];
            for (int i = 0; i < DIRECTION.length; i++) {
                int newx = curx + DIRECTION[i][0];
                int newy = cury + DIRECTION[i][1];
                if (newx >= 0 && newx < rowCount && newy >= 0 && newy < colCount && !visited[newx][newy]) {
                    queue.offer(new int[] { newx, newy });
                    visited[newx][newy] = true;
                }
            }
        }

    }
}

// 200. 岛屿数量
class Solution_200_bfs {
    private static int[][] DIRECTION = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    boolean[][] visited;

    public int numIslands(char[][] grid) {
        int rowCount = grid.length;
        int colCount = grid[0].length;
        int ans = 0;
        visited = new boolean[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    ans++;
                    bfs(grid, i, j);
                }
            }
        }
        return ans;
    }

    private void bfs(char[][] grid, int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>(); // 在 BFS 方法里再创建 Queue
        queue.offer(new int[] { x, y });
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll(); //注意取数组元素的方法
            int curx = current[0];
            int cury = current[1];

            for (int[] dir : DIRECTION) { // 遍历四个方向
                int newx = curx + dir[0];
                int newy = cury + dir[1];

                if (newx >= 0 && newx < grid.length &&
                        newy >= 0 && newy < grid[0].length &&
                        !visited[newx][newy] &&
                        grid[newx][newy] == '1') {
                    queue.offer(new int[] { newx, newy });
                    visited[newx][newy] = true;
                }
            }
        }
    }
}
