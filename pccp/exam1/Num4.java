package pccp.exam1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Num4 {
    private static class Program implements Comparable<Program> {
        final int score;
        final int start;
        final int time;

        public Program(int score, int start, int time) {
            this.score = score;
            this.start = start;
            this.time = time;
        }

        @Override
        public int compareTo(Program o) {
            if (score != o.score) {
                return score - o.score;
            }
            return start - o.start;
        }
    }

    private static class CompareByStart implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            if (o1.start != o2.start) {
                return o1.start - o2.start;
            }
            return o1.score - o2.score;
        }
    }

    private static long[] solution(int[][] program) {
        PriorityQueue<Program> readyQ = new PriorityQueue<>(new CompareByStart()); // 대기 큐: 1. 시작 시간, 2. 점수 순서대로
        PriorityQueue<Program> processQ = new PriorityQueue<>(); // 실행 큐: 1. 점수, 2. 시작 시간 순서대로
        for (int[] info: program) {
            Program p = new Program(info[0], info[1], info[2]);
            readyQ.add(p);
        }
        long[] result = new long[11];
        long time = 0L;
        processQ.add(readyQ.poll()); // 맨 처음 실행할 프로그램 적재
        while (!processQ.isEmpty()) {
            Program p = processQ.poll();
            if (p.start >= time) { // 프로그램 시작 시간이 현재 시간 이상인 경우 -> 대기 시간이 없었음
                time = p.start + p.time;
            } else { // 대기 시간이 있었음
                long wait = time - p.start;
                result[p.score] += wait;
                time += p.time;
            }
            // 실행 큐가 비었고, 가장 빠른 시작 시간이 현재 시간 초과인 경우 -> 다음 시작 시간과 같은 프로그램 모두 적재
            if (processQ.isEmpty() && !readyQ.isEmpty() && readyQ.peek().start > time) {
                int nextStart = readyQ.peek().start;
                while (!readyQ.isEmpty() && readyQ.peek().start == nextStart) {
                    processQ.add(readyQ.poll());
                }
            } else { // 시작 시간이 현재 시간보다 작은 프로그램 모두 적재
                while (!readyQ.isEmpty() && readyQ.peek().start <= time) {
                    processQ.add(readyQ.poll());
                }
            }
        }
        result[0] = time;
        return result;
    }

    public static void main(String[] args) {
        int[][] program = {
            {2, 0, 10},
            {1, 5, 5},
            {3, 5, 3},
            {3, 12, 2}
        };
        long[] answer = solution(program);
        System.out.print(Arrays.toString(answer));
    }
}
