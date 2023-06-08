/**
 *
 * URL : https://www.acmicpc.net/problem/24228
 *
 * 젓가락
 *
 * 문제
 * 젓가락통에
 * $N$ 종류의 젓가락이 종류별로 충분히 많이 들어있다. 당신은 이 젓가락통에서 무작위로 젓가락을 뽑아서
 * $R$개의 짝을 맞춰야 한다. 최악의 경우 몇 개의 젓가락을 뽑아야 하는가?
 *
 * 입력
 * 두 개의 정수
 * $N, R$이 주어진다.
 * $(1 ≤ N,R ≤ 10^{18})$ 
 *
 * 출력
 * 최악의 경우 뽑아야 하는 젓가락의 개수를 출력한다.
 *
 * 예제 입력 1
 * 2 1
 * 예제 출력 1
 * 3
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P24228 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  public static void main(String[] args) throws IOException {
      new P24228().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    long N = Long.parseLong(st.nextToken());
    long R = Long.parseLong(st.nextToken());

    bw.write((N + (2 * R) - 1) + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }
}
