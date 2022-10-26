/**
 *
 * URL : https://www.acmicpc.net/problem/13251
 *
 * 조약돌 꺼내기
 *
 * 문제
 * 효빈이의 비밀 박스에는 조약돌이 N개 들어있다. 조약돌의 색상은 1부터 M까지 중의 하나이다.
 *
 * 비밀 박스에서 조약돌을 랜덤하게 K개 뽑았을 때, 뽑은 조약돌이 모두 같은 색일 확률을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 M (1 ≤ M ≤ 50)이 주어진다.
 *
 * 둘째 줄에는 각 색상의 조약돌이 몇 개 있는지 주어진다. 각 색상의 조약돌 개수는 1보다 크거나 같고 50보다 작거나 같은 자연수이다.
 *
 * 셋째 줄에는 K가 주어진다. (1 ≤ K ≤ N)
 *
 * 출력
 * 첫째 줄에 뽑은 조약돌이 모두 같은 색일 확률을 출력한다. 정답과의 절대/상대 오차는 10-9까지 허용한다.
 *
 * 예제 입력 1
 * 1
 * 13
 * 8
 * 예제 출력 1
 * 1.0
 * 예제 입력 2
 * 2
 * 5 7
 * 1
 * 예제 출력 2
 * 1.0
 * 예제 입력 3
 * 3
 * 5 6 7
 * 2
 * 예제 출력 3
 * 0.3006535947712418
 * 예제 입력 4
 * 5
 * 12 2 34 13 17
 * 4
 * 예제 출력 4
 * 0.035028830818304504
 */

package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P13251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] stones = new int[N];
        int T = 0;
        for (int i = 0; i < N; i++) {
            stones[i] = Integer.parseInt(st.nextToken());
            T += stones[i];
        }

        int K = Integer.parseInt(br.readLine());
        double[] probability = new double[50];  // 조건에서 최대 50개라고 지정함
        double answer = 0.0;
        for (int i = 0; i < N; i++) {
            if (stones[i] >= K) {
                probability[i] = 1.0;
                for (int j = 0; j < K; j++) {
                    probability[i] *= (double) (stones[i] - j) / (T - j);
                }
            }
            answer += probability[i];
        }
        System.out.println(answer);
    }
}
