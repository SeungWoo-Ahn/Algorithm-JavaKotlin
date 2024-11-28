package pccp.exam2;

import java.util.PriorityQueue;

public class Num2 {
    private static int solution(int[] ability, int number) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int a: ability) {
            pq.add(a);
        }
        while (number-- > 0) {
            int first = pq.poll();
            int second = pq.poll();
            int sum = first + second;
            pq.add(sum);
            pq.add(sum);
        }
        int sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] ability = {10, 3, 7, 2};
        int number = 2;
        int answer = solution(ability, number);
        System.out.print(answer);
    }
}
