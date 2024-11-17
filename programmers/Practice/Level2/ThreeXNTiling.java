package programmers.Practice.Level2;

public class ThreeXNTiling {
    private static final int MOD = 1_000_000_007;

    private static int solution(int n) {
        if (n % 2 != 0) {
            return 0;
        }
        int unit = n >> 1;
        long[] dp = new long[unit + 1];
        dp[1] = 3L;
        dp[2] = 11L;
        for (int i = 3; i <= unit; i++) {
            dp[i] = (dp[i - 1] * 4 - dp[i - 2]) % MOD;
            if (dp[i] < 0) {
                dp[i] += MOD;
            }
        }
        return (int) dp[unit];
    }

    public static void main(String[] args) {
        int n = 4;
        int answer = solution(n);
        System.out.print(answer);
    }
}
