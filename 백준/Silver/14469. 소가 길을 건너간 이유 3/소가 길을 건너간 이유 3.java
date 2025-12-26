import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            List<Cow> cows = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int arrive = Integer.parseInt(st.nextToken());
                int duration = Integer.parseInt(st.nextToken());
                cows.add(new Cow(arrive, duration));
            }

            Collections.sort(cows);

            int time = 0;
            for (Cow cow : cows) {
                if (time < cow.arriveTime) {
                    time = cow.arriveTime;
                }
                time += cow.duration;
            }
            System.out.println(time);
        }
    }

    static class Cow implements Comparable<Cow> {
        int arriveTime;
        int duration;

        public Cow(int arriveTime, int duration) {
            this.arriveTime = arriveTime;
            this.duration = duration;
        }

        @Override
        public int compareTo(Cow other) {
            if (this.arriveTime == other.arriveTime) {
                return this.duration - other.duration;
            }
            return this.arriveTime - other.arriveTime;
        }
    }
}
