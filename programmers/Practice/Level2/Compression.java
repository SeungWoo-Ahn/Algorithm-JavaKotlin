package programmers.Practice.Level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Compression {
    private static HashMap<String, Integer> makeDictionary() {
        HashMap<String, Integer> dic = new HashMap<>();
        for (int idx = 1; idx <= 26; idx++) {
            char c = (char) ((idx - 1) + 'A');
            String key = Character.toString(c);
            dic.put(key, idx);
        }
        return dic;
    }

    private static int[] solution(String msg) {
        List<Integer> result = new ArrayList<>();
        HashMap<String, Integer> dic = makeDictionary();
        int dicIdx = dic.size() + 1;
        int st = 0;
        int en;
        while (st < msg.length()) {
            en = st + 1;
            while (en <= msg.length()) {
                String key = msg.substring(st, en);
                if (dic.containsKey(key)) {
                    en++;
                } else break;
            }
            String w = msg.substring(st, en - 1);
            result.add(dic.get(w));
            if (en <= msg.length()) {
                String wc = msg.substring(st, en);
                dic.put(wc, dicIdx++);
            }
            st = en - 1;
        }
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        String msg = "TOBEORNOTTOBEORTOBEORNOT";
        int[] answer = solution(msg);
        for (int i: answer) {
            System.out.print(i + " ");
        }
    }
}
