package programmers.Practice.Level2;

public class JumpBlink {
    private static int solution(int n) {
        int result = 0;
        while (n > 0) {
            if (n % 2 != 0) {
                result++;
            }
            n /= 2;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5000;
        int answer = solution(n);
        System.out.print(answer);
    }
}
