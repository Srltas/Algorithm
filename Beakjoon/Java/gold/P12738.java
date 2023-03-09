/**
 *
 * URL : https://www.acmicpc.net/problem/12738
 *
 * 가장 긴 증가하는 부분 수열 3
 *
 * 문제
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
 *
 * 예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.
 *
 * 입력
 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.
 *
 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (-1,000,000,000 ≤ Ai ≤ 1,000,000,000)
 *
 * 출력
 * 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
 *
 * 예제 입력 1
 * 6
 * 10 20 10 30 20 50
 * 예제 출력 1
 * 4
 */

package gold;

import java.io.*;
import java.util.StringTokenizer;

public class P12738 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] array;
    int[] dp;

    public static void main(String[] args) throws IOException {
        new P12738().solution();
    }

    public void solution() throws IOException {
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = Integer.MIN_VALUE;
        }

        int LIS = 0;
        for (int i = 0; i < N; i++) {
            int index = binarySearch(array[i], 0, LIS, LIS + 1);
            if (index == -1) {
                dp[LIS++] = array[i];
            } else {
                dp[index] = array[i];
            }
        }

        bw.write(LIS + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    public int binarySearch(int n, int start, int end, int size) {
        int res = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (n <= dp[mid]) {
                res = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        if (start == size) {
            return -1;
        } else {
            return res;
        }
    }
}
