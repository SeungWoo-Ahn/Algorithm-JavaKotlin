package programmers.Practice.Level2;

public class VisitLength {
    private static int getCode(char ch) {
        return switch (ch) {
            case 'U' -> 0;
            case 'D' -> 1;
            case 'R' -> 2;
            case 'L' -> 3;
            default -> throw new UnsupportedOperationException();
        };
    }

    private static int getOppositeCode(int code) {
        return switch (code) {
            case 0 -> 1;
            case 1 -> 0;
            case 2 -> 3;
            case 3 -> 2;
            default -> throw new UnsupportedOperationException();
        };
    }

    private static boolean outOfBoundary(int x, int y) {
        return !(0 <= x && x <= 10) || !(0 <= y && y <= 10);
    }

    private static int solution(String dirs) {
        boolean[][][] visited = new boolean[11][11][4];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int curX = 5;
        int curY = 5;
        int result = 0;
        for (int i = 0; i < dirs.length(); i++) {
            char command = dirs.charAt(i);
            int code = getCode(command);
            int nx = curX + dx[code];
            int ny = curY + dy[code];
            if (outOfBoundary(nx, ny)) continue;
            if (!visited[curX][curY][code]) {
                visited[curX][curY][code] = true;
                visited[nx][ny][getOppositeCode(code)] = true;
                result++;
            }
            curX = nx;
            curY = ny;
        }
        return result;
    }

    public static void main(String[] args) {
        String dirs = "ULURRDLLU";
        int answer = solution(dirs);
        System.out.print(answer);
    }
}
