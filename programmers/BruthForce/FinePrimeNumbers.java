package programmers.BruthForce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinePrimeNumbers {

    /**
     *
     * @param numbers: 길이가 1이상 7이하 문자열, 0~9의 숫자로만 이루어짐
     * @return 각 문자열을 조합하여 만들 수 있는 소수의 수를 반환
     */
    static int solution(String numbers) {
        int answer = 0;

        // 1. 조합 가능한 가장 큰 수를 찾는다
        int maxNumber = findMaxCombinationNumber(numbers);

        // 2-1. maxNumber 까지의 소수를 확인한다.
        // 2-2. numbers 의 조합의로 해당 소수를 만들 수 있는지 확인한다.
        int[] inputsNumberTable = makeNumberTable(numbers);
        for(int i=0; i<=maxNumber; i++) {
            if(isPrime(i)) {
                if(includesInNumbers(inputsNumberTable, i)) {
                    answer++;
                }
            }
        }

        return answer;
    }

    static int findMaxCombinationNumber(String numbers) {
        int len = numbers.length();
        String[] numberStrList = new String[len];
        for(int i=0; i<len; i++) {
            numberStrList[i] = String.valueOf(numbers.charAt(i));
        }
        Arrays.sort(numberStrList, ((o1, o2) -> (o2 + o1).compareTo(o1 + o2)));
        StringBuilder sb = new StringBuilder();
        for(String s: numberStrList) {
            sb.append(s);
        }
        return Integer.parseInt(sb.toString());
    }

    static boolean isPrime(int number) {
        if(number < 2) return false;
        if(number == 2) return true;
        for(int i=2; i<= Math.sqrt(number); i++) {
            if(number % i == 0) return false;
        }
        return true;
    }

    static boolean includesInNumbers(int[] inputsNumberTable ,int prime) {
        int[] primeNumberTable = makeNumberTable(Integer.toString(prime));
        for(int i=0; i<10; i++) {
            if(primeNumberTable[i] > inputsNumberTable[i]) {
                return false;
            }
        }
        return true;
    }

    static int[] makeNumberTable(String numberStr) {
        int[] table = new int[10];
        char[] numberCharArr = numberStr.toCharArray();
        for(char c: numberCharArr) {
            table[c - '0']++;
        }
        return table;
    }

    public static void main(String[] args) {
        System.out.println(solution("17"));
    }
}
