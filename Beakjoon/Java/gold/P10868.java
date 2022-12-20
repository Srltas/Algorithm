/**
 *
 * URL : https://www.acmicpc.net/problem/10868
 *
 * 최솟값
 *
 * 문제
 * N(1 ≤ N ≤ 100,000)개의 정수들이 있을 때, a번째 정수부터 b번째 정수까지 중에서 제일 작은 정수를 찾는 것은 어려운 일이 아니다. 하지만 이와 같은 a, b의 쌍이 M(1 ≤ M ≤ 100,000)개 주어졌을 때는 어려운 문제가 된다. 이 문제를 해결해 보자.
 *
 * 여기서 a번째라는 것은 입력되는 순서로 a번째라는 이야기이다. 예를 들어 a=1, b=3이라면 입력된 순서대로 1번, 2번, 3번 정수 중에서 최솟값을 찾아야 한다. 각각의 정수들은 1이상 1,000,000,000이하의 값을 갖는다.
 *
 * 입력
 * 첫째 줄에 N, M이 주어진다. 다음 N개의 줄에는 N개의 정수가 주어진다. 다음 M개의 줄에는 a, b의 쌍이 주어진다.
 *
 * 출력
 * M개의 줄에 입력받은 순서대로 각 a, b에 대한 답을 출력한다.
 *
 * 예제 입력 1
 * 10 4
 * 75
 * 30
 * 100
 * 38
 * 50
 * 51
 * 52
 * 20
 * 81
 * 5
 * 1 10
 * 3 5
 * 6 9
 * 8 10
 * 예제 출력 1
 * 5
 * 38
 * 20
 * 5
 */

package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10868 {

    private static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int treeHeight = 0;
        int length = N;
        while (length != 0) {
            length /= 2;
            treeHeight++;
        }

        int leafNodeIndex = (int) Math.pow(2, treeHeight);
        tree = new long[leafNodeIndex * 2 + 1];
        // MAX 값으로 tree 초기화
        for (int i = 1; i < tree.length; i++) {
            tree[i] = Long.MAX_VALUE;
        }

        // 데이터 입력 받기
        for (int i = leafNodeIndex; i < leafNodeIndex + N ; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        //최솟값으로 트리 다시 구성하기
        setTree(tree.length - 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            // 실제 트리의 리프 노드의 위치로 변환
            s = s + leafNodeIndex - 1;
            e = e + leafNodeIndex - 1;

            System.out.println(getMin(s, e));
        }
    }

    private static long getMin(int s, int e) {
        long minValue = Long.MAX_VALUE;

        while (s <= e) {
            if (s % 2 == 1) {
                minValue = Math.min(minValue, tree[s]);
                s++;
            }
            s /= 2;

            if (e % 2 == 0) {
                minValue = Math.min(minValue, tree[e]);
                e--;
            }
            e /= 2;
        }

        return minValue;
    }

    private static void setTree(int i) {
        while (i != 1) {
            if (tree[i / 2] > tree[i]) {
                tree[i / 2] = tree[i];
            }
            i--;
        }
    }
}
