package programmers.Practice.Level2;

import java.util.LinkedList;
import java.util.Queue;

public class ColoringBook {
    private static final int BLANK = 0;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    private static int bfs(int m, int n, int sx, int sy, int[][] picture, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;
        int areaSize = 0;
        int target = picture[sx][sy];
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            areaSize++;
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (outOfBoundary(m, n, nx, ny) || visited[nx][ny]) continue;
                if (picture[nx][ny] == target) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        return areaSize;
    }

    private static boolean outOfBoundary(int m, int n, int x, int y) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }

    private static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        boolean[][] visited = new boolean[m][n];
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (picture[x][y] != BLANK && !visited[x][y]) {
                    int areaSize = bfs(m, n, x, y, picture, visited);
                    if (areaSize > maxSizeOfOneArea) {
                        maxSizeOfOneArea = areaSize;
                    }
                    numberOfArea++;
                }
            }
        }
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public static void main(String[] args) {
        int m = 6;
        int n = 4;
        int[][] picture = {
            {1, 1, 1, 0},
            {1, 2, 2, 0},
            {1, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 3},
            {0, 0, 0, 3}
        };
        int[] answer = solution(m, n, picture);
        System.out.print(answer[0] + ", " + answer[1]);
    }
}
