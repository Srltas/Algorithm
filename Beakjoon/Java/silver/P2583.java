/**
 *
 * URL : https://www.acmicpc.net/problem/2583
 *
 * 영역 구하기
 *
 * 문제
 * 눈금의 간격이 1인 M×N(M,N≤100)크기의 모눈종이가 있다. 이 모눈종이 위에 눈금에 맞추어 K개의 직사각형을 그릴 때, 이들 K개의 직사각형의 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어진다.
 *
 * 예를 들어 M=5, N=7 인 모눈종이 위에 <그림 1>과 같이 직사각형 3개를 그렸다면, 그 나머지 영역은 <그림 2>와 같이 3개의 분리된 영역으로 나누어지게 된다.
 *
 *
 *
 * <그림 2>와 같이 분리된 세 영역의 넓이는 각각 1, 7, 13이 된다.
 *
 * M, N과 K 그리고 K개의 직사각형의 좌표가 주어질 때, K개의 직사각형 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어지는지, 그리고 분리된 각 영역의 넓이가 얼마인지를 구하여 이를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 M과 N, 그리고 K가 빈칸을 사이에 두고 차례로 주어진다. M, N, K는 모두 100 이하의 자연수이다. 둘째 줄부터 K개의 줄에는 한 줄에 하나씩 직사각형의 왼쪽 아래 꼭짓점의 x, y좌표값과 오른쪽 위 꼭짓점의 x, y좌표값이 빈칸을 사이에 두고 차례로 주어진다. 모눈종이의 왼쪽 아래 꼭짓점의 좌표는 (0,0)이고, 오른쪽 위 꼭짓점의 좌표는(N,M)이다. 입력되는 K개의 직사각형들이 모눈종이 전체를 채우는 경우는 없다.
 *
 * 출력
 * 첫째 줄에 분리되어 나누어지는 영역의 개수를 출력한다. 둘째 줄에는 각 영역의 넓이를 오름차순으로 정렬하여 빈칸을 사이에 두고 출력한다.
 *
 * 예제 입력 1
 * 5 7 3
 * 0 2 4 4
 * 1 1 2 5
 * 4 0 6 2
 * 예제 출력 1
 * 3
 * 1 7 13
 */

package silver;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class P2583 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};

    int M, N, K;
    boolean[][] array;

    public static void main(String[] args) throws IOException {
        new P2583().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        array = new boolean[N][M];
        while (K-- > 0) {
            int[] points = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int i = points[0]; i < points[2]; i++) {
                for (int j = points[1]; j < points[3]; j++) {
                    array[i][j] = true;
                }
            }
        }

        int totalCount = 0;
        List<Integer> countList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!array[i][j]) {
                    totalCount++;
                    countList.add(BFS(new Node(i, j)));
                }
            }
        }

        bw.write(totalCount + System.lineSeparator());
        Collections.sort(countList);
        for (int i : countList) {
            bw.write(i + " ");
        }
        bw.flush();

        bw.close();
        br.close();
    }

    public int BFS(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        array[node.x][node.y] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            Node nextNode = queue.poll();
            count++;

            for (int i = 0; i < 4; i++) {
                int x = nextNode.x + dx[i];
                int y = nextNode.y + dy[i];

                if (x >= 0 && y >= 0 && x < N && y < M) {
                    if (!array[x][y]) {
                        queue.offer(new Node(x, y));
                        array[x][y] = true;
                    }
                }
            }
        }

        return count;
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
