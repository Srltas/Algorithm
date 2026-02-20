import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] lamps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        lamps = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            lamps[i] = Integer.parseInt(st.nextToken());
        }

        int low = 1;
        int high = N;
        int answer = N;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (check(mid)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(answer);
    }

    static boolean check(int h) {
        int light = 0;
        for (int lamp : lamps) {
            if (lamp - h > light) {
                return false;
            }
            light = lamp + h;
        }
        return light >= N;
    }
}
