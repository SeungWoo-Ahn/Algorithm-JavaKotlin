package programmers.Practice.Level3;

public class ChuseokTraffic {
    private static class Work {
        int from;
        int to;

        public Work(int to, int time) {
            this.from = to - time + 1;
            this.to = to;
        }
    }

    private static Work toWork(String line) {
        int hh = Integer.parseInt(line.substring(11, 13));
        int mm = Integer.parseInt(line.substring(14, 16));
        double sss = Double.parseDouble(line.substring(17, 23));
        double t = Double.parseDouble(line.substring(24, line.length() - 1));
        int to = hh * 60 * 60 * 1_000 + mm * 60 * 1_000 + (int) (sss * 1_000);
        int time = (int) (t * 1_000);
        return new Work(to, time);
    }

    private static int solution(String[] lines) {
        Work[] works = new Work[lines.length];
        for (int i = 0; i < lines.length; i++) {
            works[i] = toWork(lines[i]);
        }
        int result = 0;
        for (int i = 0; i < works.length; i++) {
            int cnt = 1;
            for (int j = i + 1; j < works.length; j++) {
                if (works[i].to + 1_000 > works[j].from) {
                    cnt++;
                }
            }
            result = Math.max(result, cnt);
        }
        return result;
    }

    public static void main(String[] args) {
        String[] lines = {
            "2016-09-15 20:59:57.421 0.351s",
            "2016-09-15 20:59:58.233 1.181s",
            "2016-09-15 20:59:58.299 0.8s",
            "2016-09-15 20:59:58.688 1.041s",
            "2016-09-15 20:59:59.591 1.412s",
            "2016-09-15 21:00:00.464 1.466s",
            "2016-09-15 21:00:00.741 1.581s",
            "2016-09-15 21:00:00.748 2.31s",
            "2016-09-15 21:00:00.966 0.381s",
            "2016-09-15 21:00:02.066 2.62s"
        };
        int answer = solution(lines);
        System.out.print(answer);
    }
}
