package pccp.exam3;

public class Num4 {
    private static final boolean[][] isWall = new boolean[5][5];
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static boolean[][][] visited = new boolean[5][5][2];
    private static int n, m, rex, rey, bex, bey;
    private static int result = Integer.MAX_VALUE;

    private static  void backtracking(int rx, int ry, int bx, int by, int turn, int depth, boolean redStart) {
        if (isBothEnd(rx, ry, bx, by)) {
            int cnt = depth;
            if (redStart && turn == 1) cnt++;
            else if (!redStart && turn == 0) cnt++;
            result = Math.min(result, cnt / 2);
            return;
        }
        int nextTurn = (turn + 1) % 2;
        if (turn == 0) {
            if (isRedEnd(rx, ry)) {
                backtracking(rx, ry, bx, by, nextTurn, depth + 1, redStart);
            } else {
                for (int i = 0; i < 4; i++) {
                    int nx = rx + dx[i];
                    int ny = ry + dy[i];
                    if (cantMove(nx, ny, bx, by, turn)) continue;
                    visited[nx][ny][turn] = true;
                    backtracking(nx, ny, bx, by, nextTurn, depth + 1, redStart);
                    visited[nx][ny][turn] = false;
                }
            }
        } else {
            if (isBlueEnd(bx, by)) {
                backtracking(rx, ry, bx, by, nextTurn, depth + 1, redStart);
            } else {
                for (int i = 0; i < 4; i++) {
                    int nx = bx + dx[i];
                    int ny = by + dy[i];
                    if (cantMove(nx, ny, rx, ry, turn)) continue;
                    visited[nx][ny][turn] = true;
                    backtracking(rx, ry, nx, ny, nextTurn, depth + 1, redStart);
                    visited[nx][ny][turn] = false;
                }
            }
        }
    }

    private static boolean cantMove(int x, int y, int ox, int oy, int turn) {
        return outOfBoundary(x, y) || (x == ox && y == oy) || isWall[x][y] || visited[x][y][turn];
    }

    private static boolean outOfBoundary(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    private static boolean isBothEnd(int rx, int ry, int bx, int by) {
        return isRedEnd(rx, ry) && isBlueEnd(bx, by);
    }

    private static boolean isRedEnd(int x, int y) {
        return x == rex && y == rey;
    }

    private static boolean isBlueEnd(int x, int y) {
        return x == bex && y == bey;
    }

    private static int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        int rsx = 0, rsy = 0, bsx = 0, bsy = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                switch (maze[x][y]) {
                    case 1:
                        rsx = x;
                        rsy = y;
                        break;
                    case 2:
                        bsx = x;
                        bsy = y;
                        break;
                    case 3:
                        rex = x;
                        rey = y;
                        break;
                    case 4:
                        bex = x;
                        bey = y;
                        break;
                    case 5:
                        isWall[x][y] = true;
                        break;
                }
            }
        }
        int redFirst, blueFirst;
        visited[rsx][rsy][0] = true;
        visited[bsx][bsy][1] = true;
        backtracking(rsx, rsy, bsx, bsy, 0, 0, true);
        redFirst = result;

        visited = new boolean[5][5][2];
        visited[rsx][rsy][0] = true;
        visited[bsx][bsy][1] = true;
        backtracking(rsx, rsy, bsx, bsy, 1, 0, false);
        blueFirst = result;

        int min = Math.min(redFirst, blueFirst);
        if (min == Integer.MAX_VALUE) {
            return 0;
        }
        return min;
    }

    public static void main(String[] args) {
        int[][] maze = {
            {1, 0, 2},
            {0, 0, 0},
            {5, 0, 5},
            {4, 0, 3}
        };
        int answer = solution(maze);
        System.out.print(answer);
    }
}
