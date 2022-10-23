/**
 *
 * URL : https://www.acmicpc.net/problem/11505
 *
 * 구간 곱 구하기
 *
 * 문제
 * 어떤 N개의 수가 주어져 있다. 그런데 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 곱을 구하려 한다. 만약에 1, 2, 3, 4, 5 라는 수가 있고, 3번째 수를 6으로 바꾸고 2번째부터 5번째까지 곱을 구하라고 한다면 240을 출력하면 되는 것이다. 그리고 그 상태에서 다섯 번째 수를 2로 바꾸고 3번째부터 5번째까지 곱을 구하라고 한다면 48이 될 것이다.
 *
 * 입력
 * 첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다. M은 수의 변경이 일어나는 횟수이고, K는 구간의 곱을 구하는 횟수이다. 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다. 그리고 N+2번째 줄부터 N+M+K+1 번째 줄까지 세 개의 정수 a,b,c가 주어지는데, a가 1인 경우 b번째 수를 c로 바꾸고 a가 2인 경우에는 b부터 c까지의 곱을 구하여 출력하면 된다.
 *
 * 입력으로 주어지는 모든 수는 0보다 크거나 같고, 1,000,000보다 작거나 같은 정수이다.
 *
 * 출력
 * 첫째 줄부터 K줄에 걸쳐 구한 구간의 곱을 1,000,000,007로 나눈 나머지를 출력한다.
 *
 * 예제 입력 1
 * 5 2 2
 * 1
 * 2
 * 3
 * 4
 * 5
 * 1 3 6
 * 2 2 5
 * 1 5 2
 * 2 3 5
 * 예제 출력 1
 * 240
 * 48
 * 예제 입력 2
 * 5 2 2
 * 1
 * 2
 * 3
 * 4
 * 5
 * 1 3 0
 * 2 2 5
 * 1 3 6
 * 2 2 5
 * 예제 출력 2
 * 0
 * 240
 */

package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11505 {

    private static long[] tree;
    private static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int treeHeight = 0;
        int length = N;
        while (length != 0) {
            length /= 2;
            treeHeight++;
        }

        int leafNodeIndex = (int) Math.pow(2, treeHeight);
        tree = new long[leafNodeIndex * 2 + 1];

        // 곱셈 연산을 해야해서 1로 초기화
        for (int i = 0; i < tree.length; i++) {
            tree[i] = 1;
        }

        // 리프노드 데이터 입력
        for (int i = leafNodeIndex; i <= leafNodeIndex + N - 1; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        // 트리 구성
        setTree(tree.length - 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            long e = Long.parseLong(st.nextToken());

            if (n == 1) {
                changeValue(s + leafNodeIndex - 1, e);
            } else if (n == 2) {
                s = s + leafNodeIndex - 1;
                e = e + leafNodeIndex - 1;
                System.out.println(getMul(s, (int) e));
            } else {
                return;
            }
        }
        br.close();
    }

    private static long getMul(int s, int e) {
        long partMul = 1;

        while (s <= e) {
            if (s % 2 == 1) {
                partMul = tree[s] * partMul % MOD;
                s++;
            }

            if (e % 2 == 0) {
                partMul = tree[e] * partMul % MOD;
                e--;
            }

            s /= 2;
            e /= 2;
        }
        return partMul;
    }

    private static void changeValue(int index, long value) {
        tree[index] = value;
        while (index > 1) {
            index = index / 2;
            tree[index] = tree[index * 2] % MOD * tree[index * 2 + 1] % MOD;
        }
    }

    private static void setTree(int i) {
        while (i != 1) {
            tree[i / 2] = tree[i / 2] * tree[i] % MOD;
            i--;
        }
    }
}
