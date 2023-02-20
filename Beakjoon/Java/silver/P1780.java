/**
 *
 * URL : https://www.acmicpc.net/problem/1780
 *
 * 종이의 개수
 *
 * 문제
 * N×N크기의 행렬로 표현되는 종이가 있다. 종이의 각 칸에는 -1, 0, 1 중 하나가 저장되어 있다. 우리는 이 행렬을 다음과 같은 규칙에 따라 적절한 크기로 자르려고 한다.
 *
 * 만약 종이가 모두 같은 수로 되어 있다면 이 종이를 그대로 사용한다.
 * (1)이 아닌 경우에는 종이를 같은 크기의 종이 9개로 자르고, 각각의 잘린 종이에 대해서 (1)의 과정을 반복한다.
 * 이와 같이 종이를 잘랐을 때, -1로만 채워진 종이의 개수, 0으로만 채워진 종이의 개수, 1로만 채워진 종이의 개수를 구해내는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 37, N은 3k 꼴)이 주어진다. 다음 N개의 줄에는 N개의 정수로 행렬이 주어진다.
 *
 * 출력
 * 첫째 줄에 -1로만 채워진 종이의 개수를, 둘째 줄에 0으로만 채워진 종이의 개수를, 셋째 줄에 1로만 채워진 종이의 개수를 출력한다.
 *
 * 예제 입력 1
 * 9
 * 0 0 0 1 1 1 -1 -1 -1
 * 0 0 0 1 1 1 -1 -1 -1
 * 0 0 0 1 1 1 -1 -1 -1
 * 1 1 1 0 0 0 0 0 0
 * 1 1 1 0 0 0 0 0 0
 * 1 1 1 0 0 0 0 0 0
 * 0 1 -1 0 1 -1 0 1 -1
 * 0 -1 1 0 1 -1 0 1 -1
 * 0 1 -1 1 0 -1 0 1 -1
 * 예제 출력 1
 * 10
 * 12
 * 11
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P1780 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[][] paper;
    int[] number = new int[3];

    public static void main(String[] args) throws IOException {
        new P1780().solution();
    }

    public void solution() throws IOException {
        int N = Integer.parseInt(br.readLine());
        paper = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        binarySearch(0, 0, N);

        bw.write(number[0] + System.lineSeparator());
        bw.write(number[1] + System.lineSeparator());
        bw.write(number[2] + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    public void binarySearch(int x, int y, int n) {
        if (check(x, y, n)) {
            number[paper[x][y] + 1]++;
        } else {
            int div = n / 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    binarySearch(x + div * i, y + div * j, div);
                }
            }
        }
    }

    public boolean check(int x, int y, int n) {
        int num = paper[x][y];
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (num != paper[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
