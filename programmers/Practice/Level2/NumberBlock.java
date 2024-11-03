package programmers.Practice.Level2;

public class NumberBlock {
    static int getBlockNum(int x) {
        if (x == 1) {
            return 0;
        }
        int max = 1;
        for (int mod = 2; mod * mod <= x; mod++) {
            if (x % mod == 0) {
                max = mod;
                if (x / mod <= 10_000_000) {
                    return x / mod;
                }
            }
        }
        return max;
    }

    static int[] solution(long begin, long end) {
        int size = (int) (end - begin) + 1;
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = getBlockNum((int) (begin + i));
        }
        return result;
    }

    public static void main(String[] args) {
        long begin = 1;
        long end = 10;
        int[] answer = solution(begin, end);
        for (int i: answer) {
            System.out.print(i + " ");
        }
    }
}
