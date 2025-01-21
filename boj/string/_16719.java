package boj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class _16719 {
    private static String input;
    private static boolean[] visited;
    private static final StringBuilder result = new StringBuilder();

    private static void recur(int left, int right) {
        if (left > right) return;
        int idx = left;
        for (int i = left; i <= right; i++) {
            if (input.charAt(idx) > input.charAt(i)) {
                idx = i;
            }
        }
        visited[idx] = true;
        for (int i = 0; i < input.length(); i++) {
            if (visited[i]) {
                result.append(input.charAt(i));
            }
        }
        result.append('\n');
        recur(idx + 1, right);
        recur(left, idx - 1);
    }

    private static String solution(String s) {
        input = s;
        visited = new boolean[input.length()];
        recur(0, input.length() - 1);
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String answer = solution(s);
        System.out.print(answer);
    }
}
