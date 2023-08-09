/**
 *
 * URL : https://www.acmicpc.net/problem/19941
 *
 * 햄버거 분배
 *
 * 문제
 * 기다란 벤치 모양의 식탁에 사람들과 햄버거가 아래와 같이 단위 간격으로 놓여 있다. 사람들은 자신의 위치에서 거리가
 * $K$ 이하인 햄버거를 먹을 수 있다.
 *
 * 햄버거	사람	햄버거	사람	햄버거	사람	햄버거	햄버거	사람	사람	햄버거	사람
 * 1	2	3	4	5	6	7	8	9	10	11	12
 * 위의 상태에서
 * $K = 1$인 경우를 생각해보자. 이 경우 모든 사람은 자신과 인접한 햄버거만 먹을 수 있다. 10번의 위치에 있는 사람은 11번 위치에 있는 햄버거를 먹을 수 있다. 이 경우 다음과 같이 최대 5명의 사람이 햄버거를 먹을 수 있다.
 *
 * 2번 위치에 있는 사람: 1번 위치에 있는 햄버거
 * 4번 위치에 있는 사람: 5번 위치에 있는 햄버거
 * 6번 위치에 있는 사람: 7번 위치에 있는 햄버거
 * 9번 위치에 있는 사람: 8번 위치에 있는 햄버거
 * 10번 위치에 있는 사람: 11번 위치에 있는 햄버거
 * 12번 위치에 있는 사람: 먹을 수 있는 햄버거가 없음
 *
 * $K = 2$인 경우에는 6명 모두가 햄버거를 먹을 수 있다.
 *
 * 2번 위치에 있는 사람: 1번 위치에 있는 햄버거
 * 4번 위치에 있는 사람: 3번 위치에 있는 햄버거
 * 6번 위치에 있는 사람: 5번 위치에 있는 햄버거
 * 9번 위치에 있는 사람: 7번 위치에 있는 햄버거
 * 10번 위치에 있는 사람: 8번 위치에 있는 햄버거
 * 12번 위치에 있는 사람: 11번 위치에 있는 햄버거
 * 식탁의 길이
 * $N$, 햄버거를 선택할 수 있는 거리
 * $K$, 사람과 햄버거의 위치가 주어졌을 때, 햄버거를 먹을 수 있는 사람의 최대 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫 줄에 두 정수
 * $N$과
 * $K$가 있다. 그리고 다음 줄에 사람과 햄버거의 위치가 문자 P(사람)와 H(햄버거)로 이루어지는 길이
 * $N$인 문자열로 주어진다.
 *
 * 출력
 * 첫 줄에 햄버거를 먹을 수 있는 최대 사람 수를 나타낸다.
 *
 * 제한
 *
 * $1 \le N \le 20,000$ 
 *
 * $1 \le K \le 10$ 
 * 예제 입력 1
 * 20 1
 * HHPHPPHHPPHPPPHPHPHP
 * 예제 출력 1
 * 8
 * 예제 입력 2
 * 20 2
 * HHHHHPPPPPHPHPHPHHHP
 * 예제 출력 2
 * 7
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P19941 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  public static void main(String[] args) throws IOException {
      new P19941().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    String str = br.readLine();
    char[] list = new char[N];
    boolean[] eat = new boolean[N];
    for (int i = 0; i < N; i++) {
      list[i] = str.charAt(i);
    }

    int result = 0;
    for (int i = 0; i < N; i++) {
      if (list[i] == 'P') {
        int head = Math.max(i - K, 0);
        int tail = Math.min(i + K, N - 1);
        for (int j = head; j <= tail; j++) {
          if (list[j] == 'H' && !eat[j]) {
            eat[j] = true;
            result++;
            break;
          }
        }
      }
    }
    bw.write(result + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }
}
