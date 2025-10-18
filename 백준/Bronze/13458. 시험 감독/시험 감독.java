import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            long B = Long.parseLong(st.nextToken());
            long C = Long.parseLong(st.nextToken());

            long total = 0L;
            for (int a : arr) {
                long t = (long)a - B;
                if (t <= 0) {
                    total += 1;
                } else {
                    total += 1 + (t + C - 1) / C;
                }
            }
            System.out.print(total);
        }
    }
}