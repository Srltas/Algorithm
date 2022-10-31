/**
 *
 * URL : https://www.acmicpc.net/problem/9252
 *
 * LCS 2
 *
 * 문제
 * LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
 *
 * 예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.
 *
 * 입력
 * 첫째 줄과 둘째 줄에 두 문자열이 주어진다. 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.
 *
 * 출력
 * 첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를, 둘째 줄에 LCS를 출력한다.
 *
 * LCS가 여러 가지인 경우에는 아무거나 출력하고, LCS의 길이가 0인 경우에는 둘째 줄을 출력하지 않는다.
 *
 * 예제 입력 1
 * ACAYKP
 * CAPCAK
 * 예제 출력 1
 * 4
 * ACAK
 */

package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P9252 {

    private static long[][] DP;
    private static char[] A;
    private static char[] B;
    private static List<Character> path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();
        path = new ArrayList<>();
        DP = new long[A.length + 1][B.length + 1];

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    DP[i][j] = DP[i - 1][j - 1] + 1;
                } else {
                    DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]);
                }
            }
        }

        System.out.println(DP[A.length][B.length]);

        getText(A.length, B.length);
        Collections.reverse(path);
        for (Character character : path) {
            System.out.print(character);
        }
        System.out.println();
    }

    private static void getText(int r, int c) {
        if (r == 0 || c == 0) {
            return;
        }

        // 문자가 같으면 path에 기록 후 왼쪽 위로 이동
        if (A[r - 1] == B[c - 1]) {
            path.add(A[r - 1]);
            getText(r - 1, c - 1);
        } else {
            // 문자가 다르면 왼쪽과 위 중 큰 수로 이동
            if (DP[r - 1][c] > DP[r][c - 1]) {
                getText(r - 1, c);
            } else {
                getText(r, c - 1);
            }
        }
    }
}
