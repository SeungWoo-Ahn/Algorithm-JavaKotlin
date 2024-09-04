package programmers.Practice.Level3;

public class PedestrianHeaven {
    private static final int MOD = 20_170_805;

    private static int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m][n][2];
        for (int x = 1; x < m; x++) {
            if (cityMap[x][0] == 1) break;
            dp[x][0][1] = 1;
        }
        for (int y = 1; y < n; y++) {
            if (cityMap[0][y] == 1) break;
            dp[0][y][0] = 1;
        }
        for (int x = 1; x < m; x++) {
            for (int y = 1; y < n; y++) {
                if (cityMap[x][y] == 1) continue;
                switch (cityMap[x][y - 1]) {
                    case 0: dp[x][y][0] = (dp[x][y - 1][0] + dp[x][y - 1][1]) % MOD;
                            break;
                    case 2: dp[x][y][0] = dp[x][y - 1][0];
                            break;
                }
                switch (cityMap[x - 1][y]) {
                    case 0: dp[x][y][1] = (dp[x - 1][y][0] + dp[x - 1][y][1]) % MOD;
                            break;
                    case 2: dp[x][y][1] = dp[x - 1][y][1];
                            break;
                }
            }
        }
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 6;
        int[][] cityMap = {
            {0, 2, 0, 0, 0, 2},
            {0, 0, 2, 0, 1, 0},
            {1, 0, 0, 2, 2, 0}
        };
        int answer = solution(m, n, cityMap);
        System.out.print(answer);
    }
}
