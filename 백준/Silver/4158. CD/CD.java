import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line == null) break;

            StringTokenizer st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            int[] sang = new int[N];
            for (int i = 0; i < N; i++) sang[i] = Integer.parseInt(br.readLine());

            int count = 0;
            for (int i = 0; i < M; i++) {
                int sunCard = Integer.parseInt(br.readLine());
                if (Arrays.binarySearch(sang, sunCard) >= 0) count++;
            }

            System.out.println(count);
        }
    }
}
