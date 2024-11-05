package programmers.Practice.Level2;

import java.util.LinkedList;
import java.util.Queue;

public class OilDrilling {
    private static class Node {
        private final int x;
        private final int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private static int n, m;
    private static int[] oilInfo;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static final int OIL = 1;

    private static void initProps(int[][] land) {
        n = land.length;
        m = land[0].length;
        oilInfo = new int[m + 1];
    }

    private static void findOilGroups(int[][] land) {
        boolean[][] visited = new boolean[n][m];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (!visited[x][y] && land[x][y] == OIL) {
                    findOilGroup(x, y, land, visited);
                }
            }
        }
    }

    private static void findOilGroup(int sx, int sy, int[][] land, boolean[][] visited) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(sx, sy));
        visited[sx][sy] = true;
        int minRow = m;
        int maxRow = 0;
        int oilAmount = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            minRow = Math.min(minRow, cur.getY());
            maxRow = Math.max(maxRow, cur.getY());
            oilAmount++;
            for (int i = 0; i < 4; i++) {
                int nx = cur.getX() + dx[i];
                int ny = cur.getY() + dy[i];
                if (outOfBoundary(nx, ny) || visited[nx][ny]) continue;
                if (land[nx][ny] == OIL) {
                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny));
                }
            }
        }
        oilInfo[minRow] += oilAmount;
        oilInfo[maxRow + 1] -= oilAmount;
    }

    private static boolean outOfBoundary(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    private static int findMaxOilAmount() {
        int maxAmount = oilInfo[0];
        for (int i = 1; i < oilInfo.length; i++) {
            oilInfo[i] += oilInfo[i - 1];
            maxAmount = Math.max(maxAmount, oilInfo[i]);
        }
        return maxAmount;
    }

    private static int solution(int[][] land) {
        initProps(land);
        findOilGroups(land);
        return findMaxOilAmount();
    }

    public static void main(String[] args) {
        int[][] land = {
            {1, 0, 1, 0, 1, 1},
            {1, 0, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1}
        };
        int answer = solution(land);
        System.out.print(answer);
    }
}
