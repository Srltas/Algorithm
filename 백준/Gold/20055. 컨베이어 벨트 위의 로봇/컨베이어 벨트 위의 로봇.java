import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int N2 = 2 * N;

            boolean[] robot = new boolean[N];
            int[] belt = new int[N2];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N2; i++) {
                belt[i] = Integer.parseInt(st.nextToken());
            }

            int step = 0;
            int zero = 0;
            while (zero < K) {
                step++;

                System.arraycopy(robot, 0, robot, 1, N - 1);
                robot[0] = false;

                int last = belt[N2 - 1];
                System.arraycopy(belt, 0, belt, 1, N2 - 1);
                belt[0] = last;

                if (robot[N - 1]) robot[N - 1] = false;

                for (int i = N - 2; i >= 0; i--) {
                    if (robot[i] && !robot[i + 1] && belt[i + 1] > 0) {
                        robot[i] = false;
                        robot[i + 1] = true;
                        if (--belt[i + 1] == 0) zero++;
                    }
                }

                if (robot[N - 1]) robot[N - 1] = false;

                if (belt[0] > 0 && !robot[0]) {
                    robot[0] = true;
                    if (--belt[0] == 0) zero++;
                }
            }

            System.out.println(step);
        }
    }
}
