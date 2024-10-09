package programmers.Practice.Level2;

import java.util.ArrayList;
import java.util.List;

public class LineUpMethod {
    private static int[] solution(int n, long k) {
        List<Integer> arr = new ArrayList<>();
        for (int i = 1; i<= n; i++) {
            arr.add(i);
        }
        int[] result = new int[n];
        long[] factorial = new long[n];
        factorial[0] = 1;
        factorial[1] = 1;
        for (int i = 2; i < n; i++) {
            factorial[i] = i * factorial[i - 1];
        }
        int idx = 0;
        k--;
        while (n > 0) {
            int share = (int) (k / factorial[n - 1]);
            result[idx] = arr.get(share);
            arr.remove(share);
            k %= factorial[n - 1];
            n--;
            idx++;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 3;
        int k = 5;
        int[] answer = solution(n, k);
        for (int id : answer) {
            System.out.print(id + " ");
        }
    }
}
