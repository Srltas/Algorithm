import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        for (int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = new int[]{x, y};
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int[] curr = arr[i];
            int count = 0;
            for (int j = 0; j < N; j++) {
                int[] next = arr[j];
                if (next[0] > curr[0] && next[1] > curr[1]) count++;
            }
            sb.append(count + 1).append(" ");
        }
        System.out.println(sb);
    }
}
