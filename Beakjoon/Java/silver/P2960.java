/**
 *
 * URL : https://www.acmicpc.net/problem/2960
 *
 * 에라토스테네스의 체
 *
 * 문제
 * 에라토스테네스의 체는 N보다 작거나 같은 모든 소수를 찾는 유명한 알고리즘이다.
 *
 * 이 알고리즘은 다음과 같다.
 *
 * 2부터 N까지 모든 정수를 적는다.
 * 아직 지우지 않은 수 중 가장 작은 수를 찾는다. 이것을 P라고 하고, 이 수는 소수이다.
 * P를 지우고, 아직 지우지 않은 P의 배수를 크기 순서대로 지운다.
 * 아직 모든 수를 지우지 않았다면, 다시 2번 단계로 간다.
 * N, K가 주어졌을 때, K번째 지우는 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N과 K가 주어진다. (1 ≤ K < N, max(1, K) < N ≤ 1000)
 *
 * 출력
 * 첫째 줄에 K번째 지워진 수를 출력한다.
 *
 * 예제 입력 1
 * 7 3
 * 예제 출력 1
 * 6
 * 예제 입력 2
 * 15 12
 * 예제 출력 2
 * 7
 * 예제 입력 3
 * 10 7
 * 예제 출력 3
 * 9
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P2960 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    int[] array;

    public static void main(String[] args) throws IOException {
        new P2960().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        array = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            array[i] = i;
        }

        bw.write(search(N, K) + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    public int search(int N, int K) {
        int count = 0;

        for (int i = 2; i <= N; i++) {
            for (int j = i; j <= N; j += i) {
                if (array[j] != 0) {
                    count++;
                    if (count == K) {
                        return array[j];
                    }
                    array[j] = 0;
                }
            }
        }

        return 0;
    }
}
