package programmers.Practice.Level2;

public class FindBiggestSquare {
    private static int solution(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[][] dp = new int[n][m];
        boolean oneExist = false;
        for (int x = 0; x < n; x++) {
            dp[x][0] = board[x][0];
            oneExist = board[x][0] == 1;
        }
        for (int y = 0; y < m; y++) {
            dp[0][y] = board[0][y];
            oneExist = board[0][y] == 1;
        }
        int maxSize = oneExist ? 1: 0;
        for (int x = 1; x < n; x++) {
            for (int y = 1; y < m; y++) {
                if (board[x][y] == 1) {
                    dp[x][y] = Math.min(dp[x - 1][y], Math.min(dp[x][y - 1], dp[x - 1][y - 1])) + 1;
                    if (dp[x][y] > maxSize) {
                        maxSize = dp[x][y];
                    }
                }
            }
        }
        return maxSize * maxSize;
    }

    public static void main(String[] args) {
        int[][] board = {
            {0,1,1,1},
            {1,1,1,1},
            {1,1,1,1},
            {0,0,1,0}
        };
        int answer = solution(board);
        System.out.print(answer);
    }
}
