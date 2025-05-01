package programmers.Practice.Level3;

import java.util.Arrays;

public class WordPuzzle {
    private static int solution(String[] strs, String t) {
        int[] dp = new int[t.length() + 1];
        int max = 20_001;
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= t.length(); i++) {
            for (String str : strs) {
                if (i >= str.length() && t.startsWith(str, i - str.length())) {
                    dp[i] = Math.min(dp[i], dp[i - str.length()] + 1);
                }
            }
        }
        return dp[t.length()] == max ? -1 : dp[t.length()];
    }

    public static void main(String[] args) {
        String[] strs = {"ba","na","n","a"};
        String t = "banana";
        int answer = solution(strs, t);
        System.out.print(answer);
    }
}
