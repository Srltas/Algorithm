/**
 *
 * URL : https://www.acmicpc.net/problem/25497
 *
 * 기술 연계마스터 임스
 *
 * 문제
 * 임스는 연계 기술을 사용하는 게임을 플레이 중에 있다. 연계 기술은 사전 기술과 본 기술의 두 개의 개별 기술을 순서대로 사용해야만 정상적으로 사용 가능한 기술을 말한다.
 *
 * 하나의 사전 기술은 하나의 본 기술과만 연계해서 사용할 수 있으며, 연계할 사전 기술 없이 본 기술을 사용했을 경우에는 게임의 스크립트가 꼬여서 이후 사용하는 기술들이 정상적으로 발동되지 않는다. 그렇지만 반드시 사전 기술을 사용한 직후에 본 기술을 사용할 필요는 없으며, 중간에 다른 기술을 사용하여도 연계는 정상적으로 이루어진다.
 *
 * 임스가 사용할 수 있는 기술에는 $1$~$9$, $L$, $R$, $S$, $K$가 있다. $1$~$9$는 연계 없이 사용할 수 있는 기술이고, $L$은 $R$의 사전 기술, $S$은 $K$의 사전 기술이다.
 *
 * 임스가 정해진 순서대로 $N$개의 기술을 사용할 때, 기술이 몇 번이나 정상적으로 발동하는지를 구해보자.
 *
 * 단, 연계 기술은 사전 기술과 본 기술 모두 정상적으로 발동되었을 때만 하나의 기술이 발동된 것으로 친다.
 *
 * 입력
 * 첫 번째 줄에는 총 기술 사용 횟수 $N$이 주어진다. ($1 \le N \le 200\,000$)
 *
 * 두 번째 줄에는 임스가 사용할 $N$개의 기술이 공백 없이 주어진다.
 *
 * 출력
 * 임스가 정상적으로 기술을 사용한 총 횟수를 출력한다.
 *
 * 예제 입력 1
 * 5
 * S12K2
 * 예제 출력 1
 * 4
 *
 * 예제 입력 2
 * 4
 * 1LKR
 * 예제 출력 2
 * 1
 *
 * 예제 입력 3
 * 4
 * SSKK
 * 예제 출력 3
 * 2
 */

package silver;

import java.io.*;
import java.util.Stack;

public class P25497 {
    public static void main(String[] args) throws IOException {
        new P25497().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        String skill = br.readLine();

        Stack<Character> LRStack = new Stack<>();
        Stack<Character> SKStack = new Stack<>();

        int count = 0;
        for (int i = 0; i < N; i++) {
            char c = skill.charAt(i);

            if (c == 'L') {
                LRStack.push(c);
            } else if (c == 'S') {
                SKStack.push(c);
            } else if (c == 'R') {
                if (!LRStack.isEmpty() && LRStack.peek() == 'L') {
                    count++;
                    LRStack.pop();
                } else {
                    break;
                }
            } else if (c == 'K') {
                if (!SKStack.isEmpty() && SKStack.peek() == 'S') {
                    count++;
                    SKStack.pop();
                } else {
                    break;
                }
            } else {
                count++;
            }
        }
        bw.write(count + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }
}
