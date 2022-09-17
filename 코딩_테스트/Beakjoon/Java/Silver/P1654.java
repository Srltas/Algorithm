package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] A = new int[K];
        long max = 0;
        for (int i = 0; i < K; i++) {
            A[i] = Integer.parseInt(br.readLine());

            if (max < A[i]) {
                max = A[i];
            }
        }

        max++;

        long min = 0;
        long mid = 0;
        // min = 0, max 는 입력받은 LAN선 중 가장 긴 길이를 갖는다.
        while (min < max) {
            mid = (max + min) / 2;

            long count = 0;

            // 구한 중간 길이로 잘라서 총 몇 개가 만들어지는지 구한다.
            for (int i = 0; i < K; i++) {
                count += (A[i] / mid);
            }

            if (count < N) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        System.out.println(min - 1);
    }
}
