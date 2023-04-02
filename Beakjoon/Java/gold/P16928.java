/**
 *
 * URL : https://www.acmicpc.net/problem/16928
 *
 * 뱀과 사다리 게임
 *
 * 문제
 * 뱀과 사다리 게임을 즐겨 하는 큐브러버는 어느 날 궁금한 점이 생겼다.
 *
 * 주사위를 조작해 내가 원하는 수가 나오게 만들 수 있다면, 최소 몇 번만에 도착점에 도착할 수 있을까?
 *
 * 게임은 정육면체 주사위를 사용하며, 주사위의 각 면에는 1부터 6까지 수가 하나씩 적혀있다. 게임은 크기가 10×10이고, 총 100개의 칸으로 나누어져 있는 보드판에서 진행된다. 보드판에는 1부터 100까지 수가 하나씩 순서대로 적혀져 있다.
 *
 * 플레이어는 주사위를 굴려 나온 수만큼 이동해야 한다. 예를 들어, 플레이어가 i번 칸에 있고, 주사위를 굴려 나온 수가 4라면, i+4번 칸으로 이동해야 한다. 만약 주사위를 굴린 결과가 100번 칸을 넘어간다면 이동할 수 없다. 도착한 칸이 사다리면, 사다리를 타고 위로 올라간다. 뱀이 있는 칸에 도착하면, 뱀을 따라서 내려가게 된다. 즉, 사다리를 이용해 이동한 칸의 번호는 원래 있던 칸의 번호보다 크고, 뱀을 이용해 이동한 칸의 번호는 원래 있던 칸의 번호보다 작아진다.
 *
 * 게임의 목표는 1번 칸에서 시작해서 100번 칸에 도착하는 것이다.
 *
 * 게임판의 상태가 주어졌을 때, 100번 칸에 도착하기 위해 주사위를 굴려야 하는 횟수의 최솟값을 구해보자.
 *
 * 입력
 * 첫째 줄에 게임판에 있는 사다리의 수 N(1 ≤ N ≤ 15)과 뱀의 수 M(1 ≤ M ≤ 15)이 주어진다.
 *
 * 둘째 줄부터 N개의 줄에는 사다리의 정보를 의미하는 x, y (x < y)가 주어진다. x번 칸에 도착하면, y번 칸으로 이동한다는 의미이다.
 *
 * 다음 M개의 줄에는 뱀의 정보를 의미하는 u, v (u > v)가 주어진다. u번 칸에 도착하면, v번 칸으로 이동한다는 의미이다.
 *
 * 1번 칸과 100번 칸은 뱀과 사다리의 시작 또는 끝이 아니다. 모든 칸은 최대 하나의 사다리 또는 뱀을 가지고 있으며, 동시에 두 가지를 모두 가지고 있는 경우는 없다. 항상 100번 칸에 도착할 수 있는 입력만 주어진다.
 *
 * 출력
 * 100번 칸에 도착하기 위해 주사위를 최소 몇 번 굴려야 하는지 출력한다.
 *
 * 예제 입력 1
 * 3 7
 * 32 62
 * 42 68
 * 12 98
 * 95 13
 * 97 25
 * 93 37
 * 79 27
 * 75 19
 * 49 47
 * 67 17
 * 예제 출력 1
 * 3
 * 5를 굴려 6으로 이동한다.
 * 6을 굴려 12로 이동한다. 이 곳은 98로 이동하는 사다리가 있기 때문에, 98로 이동한다.
 * 2를 굴려 100으로 이동한다.
 * 예제 입력 2
 * 4 9
 * 8 52
 * 6 80
 * 26 42
 * 2 72
 * 51 19
 * 39 11
 * 37 29
 * 81 3
 * 59 5
 * 79 23
 * 53 7
 * 43 33
 * 77 21
 * 예제 출력 2
 * 5
 */

package gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P16928 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[][] array = new int[11][11];
    boolean[][] visited = new boolean[11][11];

    public static void main(String[] args) throws IOException {
        new P16928().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            Node node = changePositionToNode(start);
            array[node.n][node.m] = end;
        }

        bw.write(BFS() + System.lineSeparator());
        bw.flush();


        bw.close();
        br.close();
    }

    private int BFS() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 1));
        visited[0][1] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;

            while (size-- > 0) {
                Node nowNode = queue.poll();
                for (int i = 1; i <= 6; i++) {
                    int now = changeNodeToPosition(nowNode);
                    Node node = changePositionToNode(now + i);
                    int next = array[node.n][node.m] != 0 ? array[node.n][node.m] : now + i;
                    if (next == 100) {
                        return count;
                    }

                    Node nextNode = changePositionToNode(next);
                    if (!visited[nextNode.n][nextNode.m]) {
                        queue.offer(nextNode);
                        visited[nextNode.n][nextNode.m] = true;
                    }
                }
            }
        }

        return 0;
    }

    private int changeNodeToPosition(Node node) {
        int n = node.n * 10;
        int m = node.m;

        return n + m;
    }

    private Node changePositionToNode(int num) {
        int n = num / 10;
        int m = num % 10;

        return new Node(n, m);
    }

    static class Node{
        int n;
        int m;

        public Node(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }
}
