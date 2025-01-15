package boj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1969 {
    private static String solution(int m, String[] dnaList) {
        char[] dnaSort = {'A', 'C', 'G', 'T'};
        int[][] cnt = new int[m][dnaSort.length];
        int distance = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (String dna: dnaList) {
                char ch = dna.charAt(i);
                int idx;
                if (ch == dnaSort[0]) idx = 0;
                else if (ch == dnaSort[1]) idx = 1;
                else if (ch == dnaSort[2]) idx = 2;
                else idx = 3;
                cnt[i][idx]++;
            }
            int max = 0;
            int maxIdx = 0;
            for (int j = 0; j < dnaSort.length; j++) {
                if (cnt[i][j] > max) {
                    max = cnt[i][j];
                    maxIdx = j;
                }
            }
            for (int j = 0; j < dnaSort.length; j++) {
                if (j == maxIdx) continue;
                distance += cnt[i][j];
            }
            sb.append(dnaSort[maxIdx]);
        }
        sb.append('\n').append(distance);
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[] dnaList = new String[n];
        for (int i = 0; i < n; i++) {
            dnaList[i] = br.readLine();
        }
        String answer = solution(m, dnaList);
        System.out.print(answer);
    }
}
