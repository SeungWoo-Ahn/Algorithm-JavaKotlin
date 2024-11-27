package pccp.exam1;

import java.util.Arrays;

public class Num1 {
    private static int toIdx(char ch) {
        return ch - 'a';
    }

    private static char toChar(int idx) {
        return (char) (idx + 'a');
    }

    private static String solution(String input_string) {
        boolean[] isAlone = new boolean[26];
        int[] pre = new int[26];
        Arrays.fill(pre, -1);
        for (int i = 0; i < input_string.length(); i++) {
            char alpha = input_string.charAt(i);
            int idx = toIdx(alpha);
            if (!isAlone[idx] && pre[idx] >= 0 && pre[idx] != i - 1) {
                isAlone[idx] = true;
            }
            pre[idx] = i;
        }
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < 26; idx++) {
            if (isAlone[idx]) {
                char alpha = toChar(idx);
                sb.append(alpha);
            }
        }
        String result = sb.toString();
        if (result.isEmpty()) {
            return "N";
        }
        return result;
    }

    public static void main(String[] args) {
        String input_string = "edeaaabbccd";
        String answer = solution(input_string);
        System.out.print(answer);
    }
}
