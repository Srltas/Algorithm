package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P18111 {
    public static void main(String[] args) throws IOException {        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int min = 0;
        int max = 0;
        int[][] A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, A[i][j]);
                max = Math.max(max, A[i][j]);
            }
        }

        int shortTime = Integer.MAX_VALUE;
        int floor = 0;
        for (int f = min; f <= max; f++) {
            int bag = B;
            int time = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (A[i][j] > f) {
                        time += (A[i][j] - f) * 2;
                        bag += (A[i][j] - f);
                    } else if (A[i][j] < f) {
                        time += (f - A[i][j]);
                        bag -= (f - A[i][j]);
                    }
                }
            }

            if (bag >= 0 && time <= shortTime) {
                shortTime = time;
                floor = f;
            }
        }

        System.out.println(shortTime + " " + floor);
    }
}

