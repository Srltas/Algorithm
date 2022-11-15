/**
 *
 * URL : https://www.acmicpc.net/problem/9658
 *
 * 돌 게임 4
 *
 * 문제
 * 돌 게임은 두 명이서 즐기는 재밌는 게임이다.
 *
 * 탁자 위에 돌 N개가 있다. 상근이와 창영이는 턴을 번갈아가면서 돌을 가져가며, 돌은 1개, 3개 또는 4개 가져갈 수 있다. 마지막 돌을 가져가는 사람이 게임을 지게 된다.
 *
 * 두 사람이 완벽하게 게임을 했을 때, 이기는 사람을 구하는 프로그램을 작성하시오. 게임은 상근이가 먼저 시작한다.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. (1 ≤ N ≤ 1000)
 *
 * 출력
 * 상근이가 게임을 이기면 SK를, 창영이가 게임을 이기면 CY을 출력한다.
 *
 * 예제 입력 1
 * 6
 * 예제 출력 1
 * SK
 */

package silver;

import java.io.*;

public class P9658 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        boolean[] DP = new boolean[1001];
        DP[2] = true;
        DP[4] = true;

        for (int i = 5; i <= N; i++) {
            if (DP[i - 1] && DP[i - 3] && DP[i - 4]) {
                DP[i] = false;
            } else {
                DP[i] = true;
            }
        }

        bw.write(DP[N] ? "SK" : "CY");
        bw.flush();
        bw.close();
        br.close();
    }
}
