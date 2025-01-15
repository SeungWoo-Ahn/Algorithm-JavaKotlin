package boj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2999 {
    private static int getR(int length) {
        int r = 1;
        for (int i = 1; i * i <= length; i++) {
            if (length % i == 0) {
                r = i;
            }
        }
        return r;
    }

    private static String solution(String message) {
        int r = getR(message.length());
        int c = message.length() / r;
        char[][] table = new char[r][c];
        int idx = 0;
        for (int j = 0; j < c; j++) {
            for (int i = 0; i < r; i++) {
                table[i][j] = message.charAt(idx);
                idx++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sb.append(table[i][j]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String message = br.readLine();
        String answer = solution(message);
        System.out.print(answer);
    }
}
