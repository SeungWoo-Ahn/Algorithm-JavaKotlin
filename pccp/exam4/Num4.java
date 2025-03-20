package pccp.exam4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Num4 {
    private static class Info {
        String a;
        String sign;
        String b;
        String c;

        public Info(String expression) {
            String[] exp = expression.split(" ");
            this.a = exp[0];
            this.sign = exp[1];
            this.b = exp[2];
            if (exp[4].equals("X")) {
                this.c = null;
            } else {
                this.c = exp[4];
            }
        }

        public int getMinRadix() {
            int aMaxNum = getMaxNum(a);
            int bMaxNum = getMaxNum(b);
            int maxNum = Math.max(aMaxNum, bMaxNum);
            if (c != null) {
                int cMaxNum = getMaxNum(c);
                maxNum = Math.max(maxNum, cMaxNum);
            }
            return maxNum + 1;
        }

        private int getMaxNum(String s) {
            int maxNum = 0;
            for (int i = 0; i < s.length(); i++) {
                int num = s.charAt(i) - '0';
                if (num > maxNum) {
                    maxNum = num;
                }
            }
            return maxNum;
        }

        @Override
        public String toString() {
            return a + " " + sign + " " + b + " = " + c;
        }
    }

    private static String calc(Info info, int radix) {
        int left = Integer.parseInt(info.a, radix);
        int right = Integer.parseInt(info.b, radix);
        int answer;
        if (info.sign.equals("+")) {
            answer = left + right;
        } else {
            answer = left - right;
        }
        return Integer.toString(answer, radix);
    }

    private static List<Integer> getCandidates(int minRadix, List<Info> infos) {
        List<Integer> candidates = new ArrayList<>();
        for (int radix = minRadix; radix < 10; radix++) {
            boolean isPossible = true;
            for (Info info : infos) {
                String answer = calc(info, radix);
                if (!answer.equals(info.c)) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) {
                candidates.add(radix);
            }
        }
        return candidates;
    }

    private static void setC(List<Info> infos, List<Integer> candidates) {
        if (candidates.size() == 1) {
            int radix = candidates.getFirst();
            for (Info info : infos) {
                info.c = calc(info, radix);
            }
        } else {
            for (Info info : infos) {
                Set<String> answerSet = new HashSet<>();
                String possible = "";
                for (int radix : candidates) {
                    String answer = calc(info, radix);
                    answerSet.add(answer);
                    possible = answer;
                }
                if (answerSet.size() == 1) {
                    info.c = possible;
                } else {
                    info.c = "?";
                }
            }
        }
    }

    private static String[] solution(String[] expressions) {
        List<Info> knownInfos = new ArrayList<>();
        List<Info> unknownInfos = new ArrayList<>();
        int minRadix = 0;
        for (String expression : expressions) {
            Info info = new Info(expression);
            minRadix = Math.max(minRadix, info.getMinRadix());
            if (info.c == null) {
                unknownInfos.add(info);
            } else {
                knownInfos.add(info);
            }
        }
        List<Integer> candidates = getCandidates(minRadix, knownInfos);
        setC(unknownInfos, candidates);
        String[] result = new String[unknownInfos.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = unknownInfos.get(i).toString();
        }
        return result;
    }

    public static void main(String[] args) {
        String[] expressions = {"2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "5 - 5 = X"};
        String[] answer = solution(expressions);
        for (String a : answer) {
            System.out.print(a + " ");
        }
    }
}
