/**
 *
 * URL : https://www.acmicpc.net/problem/2138
 *
 * 전구와 스위치
 *
 * 문제
 * N개의 스위치와 N개의 전구가 있다. 각각의 전구는 켜져 있는 상태와 꺼져 있는 상태 중 하나의 상태를 가진다. i(1 < i < N)번 스위치를 누르면 i-1, i, i+1의 세 개의 전구의 상태가 바뀐다. 즉, 꺼져 있는 전구는 켜지고, 켜져 있는 전구는 꺼지게 된다. 1번 스위치를 눌렀을 경우에는 1, 2번 전구의 상태가 바뀌고, N번 스위치를 눌렀을 경우에는 N-1, N번 전구의 상태가 바뀐다.
 *
 * N개의 전구들의 현재 상태와 우리가 만들고자 하는 상태가 주어졌을 때, 그 상태를 만들기 위해 스위치를 최소 몇 번 누르면 되는지 알아내는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 자연수 N(2 ≤ N ≤ 100,000)이 주어진다. 다음 줄에는 전구들의 현재 상태를 나타내는 숫자 N개가 공백 없이 주어진다. 그 다음 줄에는 우리가 만들고자 하는 전구들의 상태를 나타내는 숫자 N개가 공백 없이 주어진다. 0은 켜져 있는 상태, 1은 꺼져 있는 상태를 의미한다.
 *
 * 출력
 * 첫째 줄에 답을 출력한다. 불가능한 경우에는 -1을 출력한다.
 *
 * 예제 입력 1
 * 3
 * 000
 * 010
 * 예제 출력 1
 * 3
 */

package gold;

import java.io.*;

public class P2138 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  int N;
  boolean[] goal;
  int result = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
      new P2138().solution();
  }

  public void solution() throws IOException {
    N = Integer.parseInt(br.readLine());
    boolean[] currentA = new boolean[N];
    boolean[] currentB = new boolean[N];
    goal = new boolean[N];

    char[] currArray = br.readLine().toCharArray();
    char[] goalArray = br.readLine().toCharArray();

    for (int i = 0; i < N; i++) {
      currentA[i] = currArray[i] == '1';
      currentB[i] = currArray[i] == '1';
      goal[i] = goalArray[i] == '1';
    }

    // 첫 번째 전구 사용 X
    greedy(1, 0, currentA);

    // 첫 번째 전구 사용 O
    greedy(1, 1, turnOnOff(0, currentB));

    bw.write((result == Integer.MAX_VALUE ? -1 : result) + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private void greedy(int index, int count, boolean[] current) {
    if (index == N) {
      if (current[index - 1] == goal[index - 1]) {
        result = Math.min(result, count);
      }
      return;
    }

    if (current[index - 1] != goal[index - 1]) {
      greedy(index + 1, count + 1, turnOnOff(index, current));
    } else {
      greedy(index + 1, count, current);
    }
  }

  private boolean[] turnOnOff(int index, boolean[] current) {
    for (int i = index - 1; i <= index + 1; i++) {
      if (i >= 0 && i < N) {
        current[i] = !current[i];
      }
    }
    return current;
  }
}
