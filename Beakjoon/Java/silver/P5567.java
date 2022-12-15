/**
 *
 * URL : https://www.acmicpc.net/problem/5567
 *
 * 결혼식
 *
 * 문제
 * 상근이는 자신의 결혼식에 학교 동기 중 자신의 친구와 친구의 친구를 초대하기로 했다. 상근이의 동기는 모두 N명이고, 이 학생들의 학번은 모두 1부터 N까지이다. 상근이의 학번은 1이다.
 *
 * 상근이는 동기들의 친구 관계를 모두 조사한 리스트를 가지고 있다. 이 리스트를 바탕으로 결혼식에 초대할 사람의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 상근이의 동기의 수 n (2 ≤ n ≤ 500)이 주어진다. 둘째 줄에는 리스트의 길이 m (1 ≤ m ≤ 10000)이 주어진다. 다음 줄부터 m개 줄에는 친구 관계 ai bi가 주어진다. (1 ≤ ai < bi ≤ n) ai와 bi가 친구라는 뜻이며, bi와 ai도 친구관계이다.
 *
 * 출력
 * 첫째 줄에 상근이의 결혼식에 초대하는 동기의 수를 출력한다.
 *
 * 예제 입력 1
 * 6
 * 5
 * 1 2
 * 1 3
 * 3 4
 * 2 3
 * 4 5
 * 예제 출력 1
 * 3
 * 예제 입력 2
 * 6
 * 5
 * 2 3
 * 3 4
 * 4 5
 * 5 6
 * 2 5
 * 예제 출력 2
 * 0
 */

package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P5567 {

    static ArrayList<Integer>[] list;
    static boolean[] friends;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];
        friends = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        DFS(1, 0);

        int count = 0;
        for (boolean b : friends) {
            if (b) {
                count++;
            }
        }
        System.out.println(count == 0 ? 0 : count - 1);
    }

    static void DFS(int n, int depth) {
        if (depth == 2) {
            return;
        }

        for (int friend : list[n]) {
            friends[friend] = true;
            DFS(friend, depth + 1);
        }
    }
}
