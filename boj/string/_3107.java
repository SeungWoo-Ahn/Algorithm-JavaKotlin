package boj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _3107 {
    private static final char COLON = ':';

    private static String solution(String ip) {
        if (ip.contains("::")) {
            return withDoubleColon(ip);
        } else {
            return withoutDoubleColon(ip.split(":"));
        }
    }

    private static String withDoubleColon(String ip) {
        int doubleColonIdx = ip.indexOf("::");
        String[] groups = new String[8];
        if (doubleColonIdx > 0) {
            String[] leftGroups = ip.substring(0, doubleColonIdx).split(":");
            for (int i = 0; i < leftGroups.length; i++) {
                groups[i] = leftGroups[i];
            }
        }
        if (doubleColonIdx + 2 < ip.length()) {
            String[] rightGroups = ip.substring(doubleColonIdx + 2).split(":");
            int lenDiff = groups.length - rightGroups.length;
            for (int i = 0; i < rightGroups.length; i++) {
                groups[i + lenDiff] = rightGroups[i];
            }
        }
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] == null) {
                groups[i] = "";
            }
        }
        return withoutDoubleColon(groups);
    }

    private static String withoutDoubleColon(String[] groups) {
        for (int i = 0; i < groups.length; i++) {
            String group = groups[i];
            int zeroCnt = 4 - group.length();
            groups[i] = "0".repeat(zeroCnt) + group;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < groups.length; i++) {
            result.append(groups[i]);
            if (i != groups.length - 1) {
                result.append(COLON);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ip = br.readLine();
        String answer = solution(ip);
        System.out.print(answer);
    }
}
