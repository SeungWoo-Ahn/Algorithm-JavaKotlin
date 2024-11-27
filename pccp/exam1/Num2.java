package pccp.exam1;

public class Num2 {
    private static int result = 0;

    private static void dfs(int depth, int sum, int[][] ability, boolean[] visited) {
        if (depth == ability[0].length) {
            if (sum > result) {
                result = sum;
            }
            return;
        }
        for (int i = 0; i < ability.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(depth + 1, sum + ability[i][depth], ability, visited);
            visited[i] = false;
        }
    }

    private static int solution(int[][] ability) {
        boolean[] visited = new boolean[ability.length];
        dfs(0, 0, ability, visited);
        return result;
    }

    public static void main(String[] args) {
        int[][] ability = {
            {40, 10, 10},
            {20, 5, 0},
            {30, 30, 30},
            {70, 0, 70},
            {100, 100, 100}
        };
        int answer = solution(ability);
        System.out.print(answer);
    }
}
