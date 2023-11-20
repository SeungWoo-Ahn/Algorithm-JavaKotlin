package programmers.String;

import java.util.Arrays;
import java.util.Comparator;

public class SortStringsIWant {

    /**
     * 1. 각 string의 n번째 index 글자를 기준으로 정렬
     * 2. 위가 같다면 사전순으로 정렬
     */
    static class ComparatorIWant implements Comparator<String> {
        int n;
        public ComparatorIWant(int n){
            this.n = n;
        }
        @Override
        public int compare(String o1, String o2) {
            if(o1.charAt(n) == o2.charAt(n)) {
                return o1.compareTo(o2);
            }
            return o1.charAt(n) - o2.charAt(n);
        }
    }

    static String[] solution(String[] strings, int n) {
        Arrays.sort(strings, new ComparatorIWant(n));
        return strings;
    }

    public static void main(String[] args) {
        String[] input = {"sun", "bed", "car"};
        String[] output = solution(input, 1);
        for(String s: output) {
            System.out.print(s+" ");
        }
    }
}
