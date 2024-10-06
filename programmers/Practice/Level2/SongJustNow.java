package programmers.Practice.Level2;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.PriorityQueue;

public class SongJustNow {
    private static class Music implements Comparable<Music> {
        private final int id;
        private final int playTime;
        private final String title;
        private final String melody;

        public Music(int id, int playTime, String title, String melody) {
            this.id = id;
            this.playTime = playTime;
            this.title = title;
            this.melody = melody;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public int compareTo(@NotNull Music o) {
            if (this.playTime != o.playTime) {
                return o.playTime - this.playTime;
            }
            return this.id - o.id;
        }

        public boolean check(String code) {
            return melody.contains(code);
        }
    }

    private static final HashMap<String, String> codeMap = new HashMap<>();

    private static void makeCodeMap() {
        String[] codes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "B#"};
        for (int i = 0; i < codes.length; i++) {
            codeMap.put(codes[i], Integer.toString(i, codes.length));
        }
    }

    private static Music infoToMusic(int id, String info) {
        String[] splitter = info.split(",");
        int playTime = getMinute(splitter[1]) - getMinute(splitter[0]);
        String code = convertCode(splitter[3]);
        String melody = getMelody(code, playTime);
        return new Music(id, playTime, splitter[2], melody);
    }

    private static int getMinute(String time) {
        String[] splitter = time.split(":");
        int hh = Integer.parseInt(splitter[0]);
        int mm = Integer.parseInt(splitter[1]);
        return hh * 60 + mm;
    }

    private static String convertCode(String code) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        while (i <= code.length()) {
            String key = code.substring(i - 1, i);
            if (i < code.length() && code.charAt(i) == '#') {
                key += "#";
                i += 2;
            } else {
                i++;
            }
            sb.append(codeMap.get(key));
        }
        return sb.toString();
    }

    public static String getMelody(String code, int playTime) {
        if (playTime < code.length()) {
            return code.substring(0, playTime);
        }
        StringBuilder sb = new StringBuilder();
        int share = playTime / code.length();
        int rest = playTime % code.length();
        sb.append(code.repeat(share));
        if (rest > 0) {
            sb.append(code, 0, rest);
        }
        return sb.toString();
    }

    private static String solution(String m, String[] musicInfos) {
        makeCodeMap();
        String code = convertCode(m);
        PriorityQueue<Music> pq = new PriorityQueue<>();
        for (int i = 0; i < musicInfos.length; i++) {
            Music music = infoToMusic(i, musicInfos[i]);
            if (music.check(code)) {
                pq.add(music);
            }
        }
        if (pq.isEmpty()) {
            return "(None)";
        }
        return pq.poll().getTitle();
    }

    public static void main(String[] args) {
        String m = "ABCDEFG";
        String[] musicInfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String answer = solution(m, musicInfos);
        System.out.print(answer);
    }
}
