package programmers.Practice.Level3;

public class OptimalMatrixMultiplication {
    private static int[][] cache;

    private static int dp(int st, int en, int[][] matrix) {
        if (en - st == 1) {
            return 0;
        }
        if (cache[st][en] > 0) {
            return cache[st][en];
        }
        int result = Integer.MAX_VALUE;
        for (int mid = st + 1; mid < en; mid++) {
            int left = dp(st, mid, matrix);
            int right = dp(mid, en, matrix);
            int current = matrix[st][0] * matrix[mid][0] * matrix[en - 1][1];
            result = Math.min(result, left + right + current);
        }
        cache[st][en] = result;
        return result;
    }

    private static int solution(int[][] matrix) {
        int n = matrix.length;
        cache = new int[n + 1][n + 1];
        return dp(0, n, matrix);
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {5, 3},
            {3, 10},
            {10, 6}
        };
        int answer = solution(matrix);
        System.out.print(answer);
    }
}
