package programmers.BruthForce;

import java.util.ArrayList;
import java.util.List;

public class VowelDictionary {

    static String[] vowels = {"A", "E", "I", "O", "U"};
    static List<String> dictionary = new ArrayList<>();
    /**
     * @param word 길이 1~5인 'A', 'E', 'I', 'O', 'U' 로만 이루어진 문자
     * @return word 가 사전순으로 몇 번째인지
     * 사전에는 알파벳 모음으로 만들 수 있는 길이 5 이하의 모든 단어 수록
     */
    static int solution(String word) {
        makeDictionary("", 0);
        int answer = 0;
        for(int i = 0; i < dictionary.size(); i++) {
            if(dictionary.get(i).equals(word)) {
                answer = i;
                break;
            }
        }
        return answer;
    }

    static void makeDictionary(String str, int len) {
        dictionary.add(str);
        if(len == 5) return;
        for(int i = 0; i < 5; i++) {
            makeDictionary(str + vowels[i], len + 1);
        }
    }

    public static void main(String[] args) {
        String word = "I";
        System.out.println(solution(word));
    }
}
