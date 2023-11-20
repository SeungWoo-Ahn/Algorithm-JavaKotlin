package programmers.String;

public class DescendingOrder {

    static int ALPHA_SIZE = 26;

    static String solution(String s) {
        int[] smallLetters = new int[ALPHA_SIZE];
        int[] bigLetters = new int[ALPHA_SIZE];
        char[] inputLetters = s.toCharArray();
        for(int i=0; i<inputLetters.length; i++) {
            boolean isSmallLetter = inputLetters[i] - 'a' >= 0;
            int letterIdx = isSmallLetter ? inputLetters[i] - 'a' : inputLetters[i] - 'A';
            if(isSmallLetter) {
                smallLetters[letterIdx]++;
            } else {
                bigLetters[letterIdx]++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=ALPHA_SIZE-1; i>=0; i--) {
            int size = smallLetters[i];
            while (size >0) {
                sb.append((char) (i + 'a'));
                size--;
            }
        }
        for(int i=ALPHA_SIZE-1; i>=0; i--) {
            int size = bigLetters[i];
            while (size >0) {
                sb.append((char) (i + 'A'));
                size--;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution("Zbcdefg"));
    }
}
