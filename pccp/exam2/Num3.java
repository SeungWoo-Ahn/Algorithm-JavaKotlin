package pccp.exam2;

public class Num3 {
    private static int solution(int[] menu, int[] order, int k) {
        int[][] times = new int[order.length][2];
        int time = 0;
        for (int i = 0; i < times.length; i++) {
            int s = i * k;
            int e = Math.max(s, time) + menu[order[i]];
            time = e;
            times[i][0] = s;
            times[i][1] = e;
        }
        int[] cafe = new int[time + 1];
        for (int[] t : times) {
            cafe[t[0]]++;
            cafe[t[1]]--;
        }
        int max = 1;
        for (int i = 1; i < cafe.length; i++) {
            cafe[i] += cafe[i - 1];
            if (cafe[i] > max) {
                max = cafe[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] menu = {5, 12, 30};
        int[] order = {1, 2, 0, 1};
        int k = 10;
        int answer = solution(menu, order, k);
        System.out.print(answer);
    }
}
