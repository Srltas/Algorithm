/**
 *
 * URL : https://www.acmicpc.net/problem/7795
 *
 * 먹을 것인가 먹힐 것인가
 *
 * 문제
 * 심해에는 두 종류의 생명체 A와 B가 존재한다. A는 B를 먹는다. A는 자기보다 크기가 작은 먹이만 먹을 수 있다. 예를 들어, A의 크기가 {8, 1, 7, 3, 1}이고, B의 크기가 {3, 6, 1}인 경우에 A가 B를 먹을 수 있는 쌍의 개수는 7가지가 있다. 8-3, 8-6, 8-1, 7-3, 7-6, 7-1, 3-1.
 *
 *
 *
 * 두 생명체 A와 B의 크기가 주어졌을 때, A의 크기가 B보다 큰 쌍이 몇 개나 있는지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스의 첫째 줄에는 A의 수 N과 B의 수 M이 주어진다. 둘째 줄에는 A의 크기가 모두 주어지며, 셋째 줄에는 B의 크기가 모두 주어진다. 크기는 양의 정수이다. (1 ≤ N, M ≤ 20,000)
 *
 * 출력
 * 각 테스트 케이스마다, A가 B보다 큰 쌍의 개수를 출력한다.
 *
 * 예제 입력 1
 * 2
 * 5 3
 * 8 1 7 3 1
 * 3 6 1
 * 3 4
 * 2 13 7
 * 103 11 290 215
 * 예제 출력 1
 * 7
 * 1
 */

package silver;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P7795 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] A;
    int[] B;

    public static void main(String[] args) throws IOException {
        new P7795().solution();
    }

    public void solution() throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            A = new int[N + 1];
            B = new int[M + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(B, 1, M + 1);

            int ans = 0;
            for (int i = 1; i <= N; i++) {
                ans += binarySearch(1, M, A[i]);
            }
            bw.write(ans + System.lineSeparator());
        }
        bw.flush();

        bw.close();
        br.close();
    }

    public int binarySearch(int start, int end, int target) {
        int ret = start - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (B[mid] < target) {
                ret = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ret;
    }
}
