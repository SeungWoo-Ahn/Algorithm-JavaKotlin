package programmers.Practice.Level2;

public class TakeGroupPhoto {
    private static final char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private static int result = 0;

    private static void dfs(String s, String[] data) {
        if (s.length() == friends.length) {
            for (String d: data) {
                char first = d.charAt(0);
                char second = d.charAt(2);
                char sign = d.charAt(3);
                int num = d.charAt(4) - '0';
                int gap = Math.abs(s.indexOf(first) - s.indexOf(second)) - 1;
                if (!isValidate(sign, num, gap)) return;
            }
            result++;
            return;
        }
        for (char friend: friends) {
            if (s.indexOf(friend) < 0) {
                dfs(s + friend, data);
            }
        }
    }

    private static boolean isValidate(char sign, int num, int gap) {
        return (sign == '=' && num == gap) || (sign == '<' && gap < num) || (sign == '>' && gap > num);
    }

    private static int solution(int n, String[] data) {
        dfs("", data);
        return result;
    }

    public static void main(String[] args) {
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};
        int answer = solution(n, data);
        System.out.print(answer);
    }
}
