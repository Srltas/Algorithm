/**
 *
 * https://www.acmicpc.net/problem/2910
 *
 * 빈도 정렬
 *
 * 문제
 * 위대한 해커 창영이는 모든 암호를 깨는 방법을 발견했다. 그 방법은 빈도를 조사하는 것이다.
 *
 * 창영이는 말할 수 없는 방법을 이용해서 현우가 강산이에게 보내는 메시지를 획득했다. 이 메시지는 숫자 N개로 이루어진 수열이고, 숫자는 모두 C보다 작거나 같다. 창영이는 이 숫자를 자주 등장하는 빈도순대로 정렬하려고 한다.
 *
 * 만약, 수열의 두 수 X와 Y가 있을 때, X가 Y보다 수열에서 많이 등장하는 경우에는 X가 Y보다 앞에 있어야 한다. 만약, 등장하는 횟수가 같다면, 먼저 나온 것이 앞에 있어야 한다.
 *
 * 이렇게 정렬하는 방법을 빈도 정렬이라고 한다.
 *
 * 수열이 주어졌을 때, 빈도 정렬을 하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 메시지의 길이 N과 C가 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ C ≤ 1,000,000,000)
 *
 * 둘째 줄에 메시지 수열이 주어진다.
 *
 * 출력
 * 첫째 줄에 입력으로 주어진 수열을 빈도 정렬한 다음 출력한다.
 *
 * 예제 입력 1
 * 5 2
 * 2 1 2 1 2
 * 예제 출력 1
 * 2 2 2 1 1
 */
package silver;

import java.io.*;
import java.util.*;

public class P2910 {
  public static void main(String[] args) throws IOException {
    new P2910().solution();
  }

  public void solution() throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int C = Integer.parseInt(st.nextToken());

      st = new StringTokenizer(br.readLine());
      Map<Integer, Number> numbers = new HashMap<>();
      for (int i = 0; i < N; i++) {
        int num = Integer.parseInt(st.nextToken());
        Number number = numbers.getOrDefault(num, new Number(num, i, 0));
        number.count++;
        numbers.put(num, number);
      }

      List<Number> list = new ArrayList<>(numbers.values());
      list.sort(null);

      for (Number number : list) {
        for (int i = 0; i < number.count; i++) {
          bw.write(number.num + " ");
        }
      }
      bw.flush();
    }
  }

  static class Number implements Comparable<Number> {
    int num;
    final int firstAppearance;
    int count;

    public Number(int num, int firstAppearance, int count) {
      this.num = num;
      this.firstAppearance = firstAppearance;
      this.count = count;
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) return true;
      if (object == null || getClass() != object.getClass()) return false;
      Number number = (Number) object;
      return num == number.num;
    }

    @Override
    public int hashCode() {
      return Objects.hash(num);
    }

    @Override
    public int compareTo(Number o) {
      if (this.count == o.count) {
        return this.firstAppearance - o.firstAppearance;
      }
      return o.count - this.count;
    }
  }
}
