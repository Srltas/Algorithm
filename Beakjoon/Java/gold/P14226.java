/**
 *
 * URL : https://www.acmicpc.net/problem/14226
 *
 * 이모티콘
 *
 * 문제
 * 영선이는 매우 기쁘기 때문에, 효빈이에게 스마일 이모티콘을 S개 보내려고 한다.
 *
 * 영선이는 이미 화면에 이모티콘 1개를 입력했다. 이제, 다음과 같은 3가지 연산만 사용해서 이모티콘을 S개 만들어 보려고 한다.
 *
 * 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
 * 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
 * 화면에 있는 이모티콘 중 하나를 삭제한다.
 * 모든 연산은 1초가 걸린다. 또, 클립보드에 이모티콘을 복사하면 이전에 클립보드에 있던 내용은 덮어쓰기가 된다. 클립보드가 비어있는 상태에는 붙여넣기를 할 수 없으며, 일부만 클립보드에 복사할 수는 없다. 또한, 클립보드에 있는 이모티콘 중 일부를 삭제할 수 없다. 화면에 이모티콘을 붙여넣기 하면, 클립보드에 있는 이모티콘의 개수가 화면에 추가된다.
 *
 * 영선이가 S개의 이모티콘을 화면에 만드는데 걸리는 시간의 최솟값을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 S (2 ≤ S ≤ 1000) 가 주어진다.
 *
 * 출력
 * 첫째 줄에 이모티콘을 S개 만들기 위해 필요한 시간의 최솟값을 출력한다.
 *
 * 예제 입력 1
 * 2
 * 예제 출력 1
 * 2
 * 예제 입력 2
 * 4
 * 예제 출력 2
 * 4
 * 예제 입력 3
 * 6
 * 예제 출력 3
 * 5
 * 예제 입력 4
 * 18
 * 예제 출력 4
 * 8
 */

package gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P14226 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new P14226().solution();
    }

    public void solution() throws IOException {
        int S = Integer.parseInt(br.readLine());

        bw.write(BFS(S) + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    private int BFS(int S) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(1, 0, 0));
        boolean[][] visited = new boolean[2001][1001];
        visited[0][1] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (now.total == S) {
                return now.time;
            }

            // 1. 화면에 있는 이모티콘 클립보드에 저장
            queue.offer(new Node(now.total, now.total, now.time + 1));

            // 2. 클립보드에 있는 이모티콘 붙여넣기
            // 클립보드가 비어있지 않고, 붙여넣은 후 개수가 총 개수보다 적어야 하며, 이전에 방문한적 없어야함
            if (now.clipboard != 0 && now.total + now.clipboard <= S
                    && !visited[now.clipboard][now.total + now.clipboard]) {
                queue.offer(new Node(now.total + now.clipboard, now.clipboard, now.time + 1));
                visited[now.clipboard][now.total + now.clipboard] = true;
            }

            // 3. 화면에 있는 이모티콘 중 하나 삭제
            if (now.total >= 1 && !visited[now.clipboard][now.total - 1]) {
                queue.offer(new Node(now.total - 1, now.clipboard, now.time + 1));
                visited[now.clipboard][now.total - 1] = true;
            }
        }
        return 0;
    }

    static class Node {
        int total;
        int clipboard;
        int time;

        public Node (int total, int clipboard, int time) {
            this.total = total;
            this.clipboard = clipboard;
            this.time = time;
        }
    }
}
