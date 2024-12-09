package pccp.exam3;

import java.util.LinkedList;
import java.util.Queue;

public class Num2 {
    private static int n, m;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    private static int[] bfs(int sx, int sy, int[][] land, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;
        int amount = 0;
        int minY = sy;
        int maxY = sy;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            minY = Math.min(minY, cur[1]);
            maxY = Math.max(maxY, cur[1]);
            amount++;
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (outOfBoundary(nx, ny) || visited[nx][ny]) continue;
                if (land[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        return new int[]{amount, minY, maxY};
    }

    private static boolean outOfBoundary(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    private static int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        int[] oil = new int[m + 1];
        boolean[][] visited = new boolean[n][m];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (!visited[x][y] && land[x][y] == 1) {
                    int[] info = bfs(x, y, land, visited);
                    oil[info[1]] += info[0];
                    oil[info[2] + 1] -= info[0];
                }
            }
        }
        int result = oil[0];
        for (int i = 1; i < m; i++) {
            oil[i] += oil[i - 1];
            if (oil[i] > result) {
                result = oil[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] land = {
            {0, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0},
            {1, 1, 0, 0, 0, 1, 1, 0},
            {1, 1, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 0, 1, 1}
        };
        int answer = solution(land);
        System.out.print(answer);
    }
}
