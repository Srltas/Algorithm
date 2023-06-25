/**
 *
 * URL : https://www.acmicpc.net/problem/1339
 *
 * 단어 수학
 *
 * 문제
 * 민식이는 수학학원에서 단어 수학 문제를 푸는 숙제를 받았다.
 *
 * 단어 수학 문제는 N개의 단어로 이루어져 있으며, 각 단어는 알파벳 대문자로만 이루어져 있다. 이때, 각 알파벳 대문자를 0부터 9까지의 숫자 중 하나로 바꿔서 N개의 수를 합하는 문제이다. 같은 알파벳은 같은 숫자로 바꿔야 하며, 두 개 이상의 알파벳이 같은 숫자로 바뀌어지면 안 된다.
 *
 * 예를 들어, GCF + ACDEB를 계산한다고 할 때, A = 9, B = 4, C = 8, D = 6, E = 5, F = 3, G = 7로 결정한다면, 두 수의 합은 99437이 되어서 최대가 될 것이다.
 *
 * N개의 단어가 주어졌을 때, 그 수의 합을 최대로 만드는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 단어의 개수 N(1 ≤ N ≤ 10)이 주어진다. 둘째 줄부터 N개의 줄에 단어가 한 줄에 하나씩 주어진다. 단어는 알파벳 대문자로만 이루어져있다. 모든 단어에 포함되어 있는 알파벳은 최대 10개이고, 수의 최대 길이는 8이다. 서로 다른 문자는 서로 다른 숫자를 나타낸다.
 *
 * 출력
 * 첫째 줄에 주어진 단어의 합의 최댓값을 출력한다.
 *
 * 예제 입력 1
 * 2
 * AAA
 * AAA
 * 예제 출력 1
 * 1998
 * 예제 입력 2
 * 2
 * GCF
 * ACDEB
 * 예제 출력 2
 * 99437
 * 예제 입력 3
 * 10
 * A
 * B
 * C
 * D
 * E
 * F
 * G
 * H
 * I
 * J
 * 예제 출력 3
 * 45
 * 예제 입력 4
 * 2
 * AB
 * BA
 * 예제 출력 4
 * 187
 */

package gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1339 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  public static void main(String[] args) throws IOException {
      new P1339().solution();
  }

  public void solution() throws IOException {
    int N = Integer.parseInt(br.readLine());
    int[] alphabet = new int[26];

    for (int i = 0; i < N; i++) {
      char[] word = br.readLine().toCharArray();
      int value = (int) Math.pow(10, word.length - 1);

      for (int j = 0; j < word.length; j++) {
        alphabet[word[j] - 'A'] += value;
        value /= 10;
      }
    }

    Arrays.sort(alphabet);
    int result = 0;
    for (int i = 25; i >= 17; i--) {
      result += alphabet[i] * (i - 16);
    }
    bw.write(result + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }
}
