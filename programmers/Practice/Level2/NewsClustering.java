package programmers.Practice.Level2;

import java.util.HashMap;

public class NewsClustering {
    private static final int VAL = 65536;
    private static int size1 = 0;
    private static int size2 = 0;

    private static boolean isAlpha(char ch) {
        int code = ch - 'A';
        return (0 <= code && code <= 25) || (32 <= code && code <= 57);
    }

    private static HashMap<String, Integer> makeGroup(String str, int num) {
        HashMap<String, Integer> group = new HashMap<>();
        StringBuilder sb;
        int idx = 0;
        int cnt = 0;
        while (idx < str.length() - 1) {
            char cur = str.charAt(idx);
            if (isAlpha(cur)) {
                char nxt = str.charAt(idx + 1);
                if (isAlpha(nxt)) {
                    sb = new StringBuilder();
                    sb.append(Character.toUpperCase(cur));
                    sb.append(Character.toUpperCase(nxt));
                    String key = sb.toString();
                    group.put(key, group.getOrDefault(key, 0) + 1);
                    idx++;
                    cnt++;
                } else {
                    idx += 2;
                }
            } else {
                idx++;
            }
        }
        if (num == 1) {
            size1 = cnt;
        } else {
            size2 = cnt;
        }
        return group;
    }

    static int solution(String str1, String str2) {
        HashMap<String, Integer> group1 = makeGroup(str1, 1);
        HashMap<String, Integer> group2 = makeGroup(str2, 2);
        if (size1 == 0 && size2 == 0) {
            return VAL;
        }
        int child = 0;
        for (String key: group1.keySet()) {
            if (group2.containsKey(key)) {
                child += Math.min(group1.get(key), group2.get(key));
            }
        }
        int parent = child + (size1 - child) + (size2 - child);
        return child * VAL / parent;
    }

    public static void main(String[] args) {
        String str1 = "FRANCE";
        String str2 = "french";
        int answer = solution(str1, str2);
        System.out.print(answer);
    }
}
