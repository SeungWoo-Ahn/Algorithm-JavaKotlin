package pccp.exam1;

import java.util.Arrays;

public class Num3 {
    private static final int[] cnt = new int[16];

    // 4^n 을 15승까지 채움
    private static void fillCnt() {
        cnt[1] = 4;
        for (int i = 2; i < cnt.length; i++) {
            cnt[i] = cnt[i - 1] << 2;
        }
    }

    /**
     * n 세대의 가지수를 4등분했을 때,
     * 1. 첫 번째 -> 무조건 RR
     * 2. 마지막 -> 무조건 rr
     * 3. 두, 세번째 -> n,p 바꿔서 재귀 탐색
     * 4. 1세대까지 올라가면 -> Rr
     */
    private static String getValue(int n, int p) {
        if (n == 1) {
            return "Rr";
        }
        int part = cnt[n - 1] >> 2;
        if (p <= part) {
            return "RR";
        } else if (p > part * 3) {
            return "rr";
        } else if (p <= (part << 1)) {
            return getValue(n - 1, p - part);
        } else {
            return getValue(n - 1, p - (p << 1));
        }
    }

    private static String[] solution(int[][] queries) {
        fillCnt();
        String[] result = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int n = queries[i][0];
            int p = queries[i][1];
            result[i] = getValue(n, p);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] queries = {
            {3, 1},
            {2, 3},
            {3, 9}
        };
        String[] answer = solution(queries);
        System.out.print(Arrays.toString(answer));
    }
}
