/**
 *
 * URL : https://www.acmicpc.net/problem/23843
 *
 * 콘센트
 *
 * 문제
 * 광재는 전자기기 대여사업을 시작했다. 퇴근하기 전에 다음날 손님들에게 빌려줄 N개의 전자기기를 충전하려 한다. 사용 가능한 콘센트는 M개가 있고, 성능은 모두 동일하다.
 *
 * 전자기기들은 한 번에 하나의 콘센트에서만 충전이 가능하고, 충전에 필요한 시간은 2k(0 ≤ k ≤ 15, k는 정수) 형태이다.
 *
 * 광재의 빠른 퇴근을 위해 모든 전자기기를 충전하기 위한 최소 시간이 얼마인지 알려주자.
 *
 * 입력
 * 첫째 줄에 전자기기의 개수 N과 콘센트의 개수 M이 주어진다. (1 ≤ N ≤ 10,000, 1 ≤ M ≤ 10)
 *
 * 둘째 줄에 충전에 필요한 시간 ti를 나타내는 N개의 정수가 주어진다. (20 ​≤ ti ≤ 215, ti = 2k (0 ≤ k ≤ 15, k는 정수))
 *
 * 출력
 * 충전에 필요한 최소 시간을 출력한다.
 *
 * 예제 입력 1
 * 5 2
 * 1 4 4 8 1
 * 예제 출력 1
 * 9
 */

package gold;

import java.io.*;
import java.util.*;

public class P23843 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  public static void main(String[] args) throws IOException {
      new P23843().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    Integer[] time = new Integer[N];
    for (int i = 0; i < N; i++) {
      time[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(time, Collections.reverseOrder());

    int index = 0;
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    while (index < N) {
      if (queue.size() < M) {
        queue.add(time[index++]);
        continue;
      }

      int temp = queue.poll() + time[index];
      queue.offer(temp);

      index++;
    }

    int result = 0;
    while (!queue.isEmpty()) {
      result = queue.poll();
    }

    bw.write(result + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }
}
