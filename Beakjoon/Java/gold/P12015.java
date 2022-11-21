/**
 *
 * URL : https://www.acmicpc.net/problem/12015
 *
 * 가장 긴 증가하는 부분 수열 2
 *
 * 문제
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
 *
 * 예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.
 *
 * 입력
 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.
 *
 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000,000)
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12015 {

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N];
        int LIS = 0;
        for (int i = 0; i < N; i++) {
            int index = binarySearch(arr[i], 0, LIS, LIS + 1);
            if (index == -1) {
                dp[LIS++] = arr[i];
            } else {
                dp[index] = arr[i];
            }
        }

        System.out.println(LIS);
    }

    private static int binarySearch(int n, int start, int end, int size) {
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
