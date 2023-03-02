/**
 *
 * URL : https://www.acmicpc.net/problem/5904
 *
 * Moo 게임
 *
 * 문제
 * Moo는 술자리에서 즐겁게 할 수 있는 게임이다. 이 게임은 Moo수열을 각 사람이 하나씩 순서대로 외치면 되는 게임이다.
 *
 * Moo 수열은 길이가 무한대이며, 다음과 같이 생겼다.
 *
 * m o o m o o o m o o m o o o o m o o m o o o m o o m o o o o o
 * Moo 수열은 다음과 같은 방법으로 재귀적으로 만들 수 있다. 먼저, S(0)을 길이가 3인 수열 "m o o"이라고 하자. 1보다 크거나 같은 모든 k에 대해서, S(k)는 S(k-1)과 o가 k+2개인 수열 "m o ... o" 와 S(k-1)을 합쳐서 만들 수 있다.
 *
 * S(0) = "m o o"
 * S(1) = "m o o m o o o m o o"
 * S(2) = "m o o m o o o m o o m o o o o m o o m o o o m o o"
 * 위와 같은 식으로 만들면, 길이가 무한대인 문자열을 만들 수 있으며, 그 수열을 Moo 수열이라고 한다.
 *
 * N이 주어졌을 때, Moo 수열의 N번째 글자를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N (1 ≤ N ≤ 109)이 주어진다.
 *
 * 출력
 * N번째 글자를 출력한다.
 *
 * 예제 입력 1
 * 11
 * 예제 출력 1
 * m
 */

package gold;

import java.io.*;

public class P5904 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N;
    char answer;

    public static void main(String[] args) throws IOException {
        new P5904().solution();
    }

    public void solution() throws IOException {
        N = Integer.parseInt(br.readLine());
        int S = 0;
        int size = 3;

        while (N > size) {
            S++;
            size = (S + 3) + (size * 2);
        }

        divide(S,1, size + 1);

        bw.write(answer + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    public void divide(int S, int left, int right) {
        int size = right - left;

        if (size == 3) {
            if (left <= N && right >= N) {
                answer = mooCheck(left);
            }
            return;
        }

        int middleLeft = ((size - (S + 3)) / 2) + left;
        int middleRight = middleLeft + (S + 3);

        if (middleLeft <= N && middleRight > N) {
            answer = mooCheck(middleLeft);
        } else if (middleLeft > N) {
            divide(--S, left, middleLeft);
        } else {
            divide(--S, middleRight, right);
        }
    }

    public char mooCheck(int left) {
        if (left == N) {
            return 'm';
        }
        return 'o';
    }
}
