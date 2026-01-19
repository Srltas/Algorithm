import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            int[] A = new int[5];
            int[] B = new int[5];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int aCount = Integer.parseInt(st.nextToken());
            for (int i = 0; i < aCount; i++) {
                A[Integer.parseInt(st.nextToken())]++;
            }

            st = new StringTokenizer(br.readLine());
            int bCount = Integer.parseInt(st.nextToken());
            for (int i = 0; i < bCount; i++) {
                int shape = Integer.parseInt(st.nextToken());
                B[shape]++;
            }

            if (A[4] > B[4]) {
                sb.append("A\n");
            } else if (A[4] < B[4]) {
                sb.append("B\n");
            }

            else if (A[3] > B[3]) {
                sb.append("A\n");
            } else if (A[3] < B[3]) {
                sb.append("B\n");
            }

            else if (A[2] > B[2]) {
                sb.append("A\n");
            } else if (A[2] < B[2]) {
                sb.append("B\n");
            }

            else if (A[1] > B[1]) {
                sb.append("A\n");
            } else if (A[1] < B[1]) {
                sb.append("B\n");
            }

            else {
                sb.append("D\n");
            }
        }
        System.out.println(sb);
    }
}
