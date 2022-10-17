/**
 *
 * URL : https://www.acmicpc.net/problem/14425
 *
 * 문자열 집합
 *
 * 문제
 * 총 N개의 문자열로 이루어진 집합 S가 주어진다.
 *
 * 입력으로 주어지는 M개의 문자열 중에서 집합 S에 포함되어 있는 것이 총 몇 개인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 문자열의 개수 N과 M (1 ≤ N ≤ 10,000, 1 ≤ M ≤ 10,000)이 주어진다.
 *
 * 다음 N개의 줄에는 집합 S에 포함되어 있는 문자열들이 주어진다.
 *
 * 다음 M개의 줄에는 검사해야 하는 문자열들이 주어진다.
 *
 * 입력으로 주어지는 문자열은 알파벳 소문자로만 이루어져 있으며, 길이는 500을 넘지 않는다. 집합 S에 같은 문자열이 여러 번 주어지는 경우는 없다.
 *
 * 출력
 * 첫째 줄에 M개의 문자열 중에 총 몇 개가 집합 S에 포함되어 있는지 출력한다.
 *
 * 예제 입력 1
 * 5 11
 * baekjoononlinejudge
 * startlink
 * codeplus
 * sundaycoding
 * codingsh
 * baekjoon
 * codeplus
 * codeminus
 * startlink
 * starlink
 * sundaycoding
 * codingsh
 * codinghs
 * sondaycoding
 * startrink
 * icerink
 * 예제 출력 1
 * 4
 */

package silver;

import java.util.Scanner;

public class P14425 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        tNode root = new tNode();
        for (int i = 0; i < N; i++) {
            String text = sc.next();
            tNode now = root;

            for (int j = 0; j < text.length(); j++) {
                char c = text.charAt(j);
                // 26개 알파벳의 위치를 배열 index로 나타내기 위해 -'a' 수행
                if (now.next[c - 'a'] == null) {
                    now.next[c - 'a'] = new tNode();
                }
                // 해당 알파벳의 인스턴스 주소를 넘김
                now = now.next[c - 'a'];
                if (j == text.length() - 1) {
                    now.isEnd = true;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < M; i++) {
            String text = sc.next();
            tNode now = root;

            for (int j = 0; j < text.length(); j++) {
                char c = text.charAt(j);
                if (now.next[c - 'a'] == null) {
                    break;
                }

                now = now.next[c - 'a'];
                if (j == text.length() - 1 && now.isEnd) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}

class tNode {
    // 알파벳 개수
    tNode[] next = new tNode[26];
    boolean isEnd;
}
