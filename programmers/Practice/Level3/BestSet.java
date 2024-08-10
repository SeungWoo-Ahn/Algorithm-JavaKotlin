package programmers.Practice.Level3;

import java.util.Arrays;

class BestSet {
    static int[] solution(int n, int s) {
        int[] answer;
        if (n > s) {
            answer = new int[1];
            answer[0] = -1;
            return answer;
        }
        int base = s / n;
        int offsetCnt = s % n;
        answer = new int[n];
        for (int i = 0; i < n - offsetCnt; i++) {
            answer[i] = base;
        }
        for (int i = n - offsetCnt; i < n; i++) {
            answer[i] = base + 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        int n = 2;
        int s = 9;
        int[] answer = solution(n, s);
        System.out.println(Arrays.toString(answer));
    }
}


