package programmers.Practice.Level2;

import org.jetbrains.annotations.NotNull;
import java.util.Arrays;

public class SortFileName {
    private static class File implements Comparable<File> {
        private final int id;
        private final String head;
        private final String number;

        public File(int id, String head, String number) {
            this.id = id;
            this.head = head;
            this.number = number;
        }

        @Override
        public int compareTo(@NotNull File o) {
            if (!this.head.equals(o.head)) {
                return this.head.compareTo(o.head);
            }
            if (this.getNumber() != o.getNumber()) {
                return this.getNumber() - o.getNumber();
            }
            return this.id - o.id;
        }

        private int getNumber() {
            return Integer.parseInt(number);
        }
    }

    private static boolean isNumber(char ch) {
        int code = ch - '0';
        return 0 <= code && code <= 9;
    }

    private static File createFile(int id, String fileName) {
        int st = 0;
        int en = 0;
        while (en < fileName.length() && !isNumber(fileName.charAt(en))) {
            en++;
        }
        String head = fileName.substring(st, en).toLowerCase();
        st = en;
        while (en < fileName.length() && isNumber(fileName.charAt(en))) {
            en++;
        }
        String number = fileName.substring(st, en);
        return new File(id, head, number);
    }

    private static String[] solution(String[] files) {
        File[] fileList = new File[files.length];
        for (int i = 0; i < files.length; i++) {
            fileList[i] = createFile(i, files[i]);
        }
        Arrays.sort(fileList);
        String[] result = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            result[i] = files[fileList[i].id];
        }
        return result;
    }

    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] answer = solution(files);
        for (String fileName: answer) {
            System.out.print(fileName + " ");
        }
    }
}
