/**
 *
 * URL : https://www.acmicpc.net/problem/12605
 *
 * 단어순서 뒤집기
 *
 * 문제
 * 스페이스로 띄어쓰기 된 단어들의 리스트가 주어질때, 단어들을 반대 순서로 뒤집어라. 각 라인은 w개의 영단어로 이루어져 있으며, 총 L개의 알파벳을 가진다. 각 행은 알파벳과 스페이스로만 이루어져 있다. 단어 사이에는 하나의 스페이스만 들어간다.
 *
 * 입력
 * 첫 행은 N이며, 전체 케이스의 개수이다.
 *
 * N개의 케이스들이 이어지는데, 각 케이스는 스페이스로 띄어진 단어들이다. 스페이스는 라인의 처음과 끝에는 나타나지 않는다. N과 L은 다음 범위를 가진다.
 *
 * N = 5
 * 1 ≤ L ≤ 25
 * 출력
 * 각 케이스에 대해서, 케이스 번호가 x일때  "Case #x: " 를 출력한 후 그 후에 이어서 단어들을 반대 순서로 출력한다.
 *
 * 예제 입력 1
 * 3
 * this is a test
 * foobar
 * all your base
 * 예제 출력 1
 * Case #1: test a is this
 * Case #2: foobar
 * Case #3: base your all
 */

package bronze;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class P12605 {
    public static void main(String[] args) throws IOException {
        new P12605().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            bw.write("Case #" + i + ": " + reverseSentence(new StringTokenizer(br.readLine())) + System.lineSeparator());
        }
        bw.flush();

        bw.close();
        br.close();
    }

    private String reverseSentence(StringTokenizer st) {
        Stack<String> stack = new Stack<>();
        while (st.hasMoreTokens()) {
            stack.push(st.nextToken());
        }

        String sentence = "";
        while (!stack.isEmpty()) {
            sentence += stack.pop() + " ";
        }
        
        return sentence;
    }
}
