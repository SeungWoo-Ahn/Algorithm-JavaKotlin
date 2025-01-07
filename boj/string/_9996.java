package boj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _9996 {
    private static boolean isMatch(String prefix, String suffix, String fileName) {
        if (prefix.length() + suffix.length() > fileName.length()) {
            return false;
        }
        int prefixIdx = fileName.indexOf(prefix);
        int suffixIdx = fileName.lastIndexOf(suffix);
        if (prefixIdx < 0 || suffixIdx < 0) {
            return false;
        }
        return prefixIdx == 0 && (suffixIdx + suffix.length()) == fileName.length();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String pattern = br.readLine();
        int starIdx = pattern.indexOf('*');
        String prefix = pattern.substring(0, starIdx);
        String suffix = pattern.substring(starIdx + 1);
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            String fileName = br.readLine();
            boolean isMatched = isMatch(prefix, suffix, fileName);
            String result = isMatched ? "DA" : "NE";
            sb.append(result).append('\n');
        }
        System.out.print(sb);
    }
}
