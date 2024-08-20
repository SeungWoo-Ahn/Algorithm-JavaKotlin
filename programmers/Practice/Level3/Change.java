package programmers.Practice.Level3;

class Change {
    public static int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int m : money) {
            for (int j = 1; j <= n; j++) {
                if (j >= m) {
                    dp[j] = (dp[j] + dp[j - m]) % 1_000_000_007;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 5;
        int[] money = {1, 2, 5};
        int answer = solution(n, money);
        System.out.print(answer);
    }
}
