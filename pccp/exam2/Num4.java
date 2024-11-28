package pccp.exam2;

import java.util.LinkedList;
import java.util.Queue;

public class Num4 {
    private static class Node {
        final int x;
        final int y;
        final int cnt;
        final int use;

        public Node(int x, int y, int cnt, int use) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.use = use;
        }
    }

    private static boolean outOfBoundary(int m, int n, int x, int y) {
        return x < 1 || x > m || y < 1 || y > n;
    }

    private static int solution(int n, int m, int[][] hole) {
        Queue<Node> q = new LinkedList<>();
        boolean[][][] visited = new boolean[m + 1][n + 1][2];
        boolean[][] isHole = new boolean[m + 1][n + 1];
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        for (int[] h: hole) {
            isHole[h[1]][h[0]] = true;
        }
        q.add(new Node(1, 1, 0, 0));
        visited[1][1][0] = true;
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.x == m && node.y == n) {
                return node.cnt;
            }
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (outOfBoundary(m, n, nx, ny) || isHole[nx][ny] || visited[nx][ny][node.use]) continue;
                visited[nx][ny][node.use] = true;
                q.add(new Node(nx, ny, node.cnt + 1, node.use));
            }
            if (node.use == 0) {
                for (int i = 0; i < 4; i++) {
                    int nx = node.x + dx[i] * 2;
                    int ny = node.y + dy[i] * 2;
                    if (outOfBoundary(m, n, nx, ny) || isHole[nx][ny] || visited[nx][ny][1]) continue;
                    visited[nx][ny][1] = true;
                    q.add(new Node(nx, ny, node.cnt + 1, 1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 4;
        int m = 4;
        int[][] hole = {
            {2, 3},
            {3, 3}
        };
        int answer = solution(n, m, hole);
        System.out.print(answer);
    }
}
