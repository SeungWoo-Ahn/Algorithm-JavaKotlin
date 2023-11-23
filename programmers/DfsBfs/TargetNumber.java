package programmers.DfsBfs;

import java.util.Stack;

public class TargetNumber {

    static int answer = 0;

    /**
     * @param numbers 음이 아닌 정수들
     * @param target 타겟 넘버
     * @return 순서를 바꾸지 않고 number 를 더하거나 빼서 타겟 넘버를 만드는 경우의 수
     */
    static int solution(int[] numbers, int target) {
        dfsStack(numbers, target);
        return answer;
    }

    static void dfs(int index, int target, int sum, int[] numbers) {
        if(index == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }
        dfs(index + 1, target, sum + numbers[index], numbers);
        dfs(index + 1, target, sum - numbers[index], numbers);
    }

    static Stack<Pair> stack = new Stack<>();
    static void dfsStack(int[] numbers, int target) {
        stack.push(new Pair(0, numbers[0]));
        stack.push(new Pair(0, numbers[0] * -1));

        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            if(pair.index == numbers.length - 1) {
                if(pair.sum == target) {
                    answer++;
                }
                continue;
            }

            int nextIndex = pair.index + 1;
            if(nextIndex >= numbers.length) continue;
            stack.add(new Pair(nextIndex, pair.sum + numbers[nextIndex]));
            stack.add(new Pair(nextIndex, pair.sum - numbers[nextIndex]));
        }
    }

    static class Pair {
        int index;
        int sum;
        Pair(int index, int sum) {
            this.index = index;
            this.sum = sum;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(solution(numbers, target));
    }
}
