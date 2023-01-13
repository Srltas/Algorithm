/**
 *
 * URL : https://www.acmicpc.net/problem/2167
 *
 * 2차원 배열의 합
 *
 * 문제
 * 2차원 배열이 주어졌을 때 (i, j) 위치부터 (x, y) 위치까지에 저장되어 있는 수들의 합을 구하는 프로그램을 작성하시오. 배열의 (i, j) 위치는 i행 j열을 나타낸다.
 *
 * 입력
 * 첫째 줄에 배열의 크기 N, M(1 ≤ N, M ≤ 300)이 주어진다. 다음 N개의 줄에는 M개의 정수로 배열이 주어진다. 배열에 포함되어 있는 수는 절댓값이 10,000보다 작거나 같은 정수이다. 그 다음 줄에는 합을 구할 부분의 개수 K(1 ≤ K ≤ 10,000)가 주어진다. 다음 K개의 줄에는 네 개의 정수로 i, j, x, y가 주어진다(1 ≤ i ≤ x ≤ N, 1 ≤ j ≤ y ≤ M).
 *
 * 출력
 * K개의 줄에 순서대로 배열의 합을 출력한다. 배열의 합은 231-1보다 작거나 같다.
 *
 * 예제 입력 1
 * 2 3
 * 1 2 4
 * 8 16 32
 * 3
 * 1 1 2 3
 * 1 2 1 2
 * 1 3 2 3
 * 예제 출력 1
 * 63
 * 2
 * 36
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P2167 {
    public static void main(String[] args) throws IOException {
        new P2167().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] array = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            int startI = Integer.parseInt(st.nextToken());
            int startJ = Integer.parseInt(st.nextToken());
            int endI = Integer.parseInt(st.nextToken());
            int endJ = Integer.parseInt(st.nextToken());

            int num = 0;
            for (int i = startI; i <= endI; i++) {
                for (int j = startJ; j <= endJ; j++) {
                    num += array[i][j];
                }
            }

            bw.write(num + System.lineSeparator());
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
