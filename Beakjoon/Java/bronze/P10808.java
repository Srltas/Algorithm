/**
 *
 * URL : https://www.acmicpc.net/problem/10808
 *
 * 알파벳 개수
 *
 * 문제
 * 알파벳 소문자로만 이루어진 단어 S가 주어진다. 각 알파벳이 단어에 몇 개가 포함되어 있는지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 단어 S가 주어진다. 단어의 길이는 100을 넘지 않으며, 알파벳 소문자로만 이루어져 있다.
 *
 * 출력
 * 단어에 포함되어 있는 a의 개수, b의 개수, …, z의 개수를 공백으로 구분해서 출력한다.
 *
 * 예제 입력 1
 * baekjoon
 * 예제 출력 1
 * 1 1 0 0 1 0 0 0 0 1 1 0 0 1 2 0 0 0 0 0 0 0 0 0 0 0
 */

package bronze;

import java.io.*;

public class P10808 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
      new P10808().solution();
  }

  public void solution() throws IOException {
    String str = br.readLine();
    int[] alphabet = new int[26];
    for (int i = 0; i < str.length(); i++) {
      alphabet[str.charAt(i) - 'a']++;
    }

    for (int a : alphabet) {
      bw.write(a + " ");
    }
    bw.flush();

    bw.close();
    br.close();
  }
}
