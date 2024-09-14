package programmers.Practice.Level2;

public class NRadixGame {
    private static int nextTurn(int turn, int size) {
        return (turn + 1) % size;
    }

    private static String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int myTurn = p - 1;
        int turn = 0;
        int num = 0;
        while (sb.length() < t) {
            String numStr = Integer.toString(num, n).toUpperCase();
            for (int i = 0; i < numStr.length(); i++) {
                if (turn == myTurn) {
                    char ch = numStr.charAt(i);
                    sb.append(ch);
                    if (sb.length() == t) {
                        break;
                    }
                }
                turn = nextTurn(turn, m);
            }
            num++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int n = 16;
        int t = 16;
        int m = 2;
        int p = 1;
        String answer = solution(n, t, m, p);
        System.out.print(answer);
    }
}
