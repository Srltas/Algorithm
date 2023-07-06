/**
 *
 * URL : https://www.acmicpc.net/problem/13171
 *
 * A
 *
 * 문제
 * 음이 아닌 두 정수 A, X 가 있을 때 AX을 구하는 방법을 생각해보자. 물론 이 수는 매우 클 수 있기에, 1,000,000,007 (= 109 + 7)로 나눈 나머지를 구할 것이다. a mod x를 a를 x로 나눴을 때의 나머지라고 표현하면,
 *
 * (a × b) mod x = {(a mod x) × (b mod x)} mod x
 *
 * 가 성립하기 때문에, 어떤 두 정수를 1,000,000,007로 나눈 나머지만 알고 있어도 그 두 정수의 곱을 1,000,000,007로 나눈 나머지를 쉽게 계산할 수 있다.
 *
 * 본 문제로 돌아가서, 그렇다면 이제 A를 X 번 곱하면 AX을 쉽게 구할 수 있을 것 같아 보인다. 그러나 안타깝게도 X가 상당히 커서 64비트 정수의 범위에 있다면 A를 하나하나씩 곱하는 방식으로는 상상할 수 없을 정도로 긴 시간이 흘러야 답을 찾을 수 있을 것이다. 그래서 다음과 같이 곱셈의 횟수를 줄이는 방법을 사용한다.
 *
 * 먼저 A1, A2, A4, A8, ...을 순서대로 계산한다. 각 수는 이전에 있는 수를 제곱함으로써 계산할 수 있고, 지수가 X 를 딱 넘지 않을 시점까지만 계산하면 충분할 것이다. X가 64비트 정수의 범위에 있으므로 계산하는 수는 64개보다 작을 것이다.
 * 이제 X 를 이진수로 나타내 보자. 예를 들어 X를 11로 두면, X = 11 = 1 + 2 + 8이다. 그런데 지수법칙에 의해, A11 = A1+2+8 = A1 × A2 × A8이 성립한다. 이를 통해 1번 단계에서 미리 계산해 놓았던 수 몇 개만 곱해서 AX 을 계산할 수 있음을 알 수 있다.
 * 즉, 차례로 A를 곱해 나간다면 시간이 X에 비례하게 걸리겠지만, 위의 방법을 이용하면 시간이 log(X)에 비례하게 걸리게 된다. AX를 구하는 프로그램을 작성하라.
 *
 * 입력
 * 첫 번째 줄에는 정수 A(1 ≤ A ≤ 1018)이 주어진다.
 *
 * 두 번째 줄에는 정수 X(1 ≤ X ≤ 1018)가 주어진다.
 *
 * 출력
 * AX을 출력한다. 이 수는 매우 커질 수 있으므로 1,000,000,007로 나눈 나머지를 출력해야 한다.
 *
 * 예제 입력 1
 * 3
 * 3
 * 예제 출력 1
 * 27
 * 예제 입력 2
 * 100
 * 100
 * 예제 출력 2
 * 424090053
 */

package silver;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class P13171 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  private final BigInteger MOD = new BigInteger("1000000007");

  public static void main(String[] args) throws IOException {
      new P13171().solution();
  }

  public void solution() throws IOException {
    BigInteger A = new BigInteger(br.readLine());
    BigInteger X = new BigInteger(br.readLine());

    bw.write(DFS(A, X) + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private BigInteger DFS(BigInteger a, BigInteger x) {
    if (x == BigInteger.ZERO) {
      return BigInteger.ONE;
    }

    BigInteger value = DFS(a, x.divide(BigInteger.TWO));

    if (x.remainder(BigInteger.TWO) == BigInteger.ZERO) {
      return value.multiply(value).remainder(MOD);
    } else {
      return value.multiply(value).multiply(a).remainder(MOD);
    }
  }
}
