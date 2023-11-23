package programmers.DfsBfs;

public class TargetNumber {

    static int answer = 0;

    /**
     * @param numbers 음이 아닌 정수들
     * @param target 타겟 넘버
     * @return 순서를 바꾸지 않고 number 를 더하거나 빼서 타겟 넘버를 만드는 경우의 수
     */
    static int solution(int[] numbers, int target) {
        dfs(0, target, 0, numbers);
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

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(solution(numbers, target));
    }
}
