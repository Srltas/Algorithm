/**
 *
 * URL : https://www.acmicpc.net/problem/17472
 *
 * 다리 만들기 2
 *
 * 문제
 * 섬으로 이루어진 나라가 있고, 모든 섬을 다리로 연결하려고 한다. 이 나라의 지도는 N×M 크기의 이차원 격자로 나타낼 수 있고, 격자의 각 칸은 땅이거나 바다이다.
 *
 * 섬은 연결된 땅이 상하좌우로 붙어있는 덩어리를 말하고, 아래 그림은 네 개의 섬으로 이루어진 나라이다. 색칠되어있는 칸은 땅이다.
 *
 *
 *
 * 다리는 바다에만 건설할 수 있고, 다리의 길이는 다리가 격자에서 차지하는 칸의 수이다. 다리를 연결해서 모든 섬을 연결하려고 한다. 섬 A에서 다리를 통해 섬 B로 갈 수 있을 때, 섬 A와 B를 연결되었다고 한다. 다리의 양 끝은 섬과 인접한 바다 위에 있어야 하고, 한 다리의 방향이 중간에 바뀌면 안된다. 또, 다리의 길이는 2 이상이어야 한다.
 *
 * 다리의 방향이 중간에 바뀌면 안되기 때문에, 다리의 방향은 가로 또는 세로가 될 수 밖에 없다. 방향이 가로인 다리는 다리의 양 끝이 가로 방향으로 섬과 인접해야 하고, 방향이 세로인 다리는 다리의 양 끝이 세로 방향으로 섬과 인접해야 한다.
 *
 * 섬 A와 B를 연결하는 다리가 중간에 섬 C와 인접한 바다를 지나가는 경우에 섬 C는 A, B와 연결되어있는 것이 아니다.
 *
 * 아래 그림은 섬을 모두 연결하는 올바른 2가지 방법이고, 다리는 회색으로 색칠되어 있다. 섬은 정수, 다리는 알파벳 대문자로 구분했다.
 *
 *
 * 다리의 총 길이: 13
 *
 * D는 2와 4를 연결하는 다리이고, 3과는 연결되어 있지 않다.
 *
 * 다리의 총 길이: 9 (최소)
 *
 * 다음은 올바르지 않은 3가지 방법이다
 *
 *
 * C의 방향이 중간에 바뀌었다	D의 길이가 1이다.	가로 다리인 A가 1과 가로로 연결되어 있지 않다.
 * 다리가 교차하는 경우가 있을 수도 있다. 교차하는 다리의 길이를 계산할 때는 각 칸이 각 다리의 길이에 모두 포함되어야 한다. 아래는 다리가 교차하는 경우와 기타 다른 경우에 대한 2가지 예시이다.
 *
 *
 * A의 길이는 4이고, B의 길이도 4이다.
 *
 * 총 다리의 총 길이: 4 + 4 + 2 = 10
 *
 * 다리 A: 2와 3을 연결 (길이 2)
 *
 * 다리 B: 3과 4를 연결 (길이 3)
 *
 * 다리 C: 2와 5를 연결 (길이 5)
 *
 * 다리 D: 1과 2를 연결 (길이 2)
 *
 * 총 길이: 12
 *
 * 나라의 정보가 주어졌을 때, 모든 섬을 연결하는 다리 길이의 최솟값을 구해보자.
 *
 * 입력
 * 첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. 둘째 줄부터 N개의 줄에 지도의 정보가 주어진다. 각 줄은 M개의 수로 이루어져 있으며, 수는 0 또는 1이다. 0은 바다, 1은 땅을 의미한다.
 *
 * 출력
 * 모든 섬을 연결하는 다리 길이의 최솟값을 출력한다. 모든 섬을 연결하는 것이 불가능하면 -1을 출력한다.
 *
 * 제한
 * 1 ≤ N, M ≤ 10
 * 3 ≤ N×M ≤ 100
 * 2 ≤ 섬의 개수 ≤ 6
 * 예제 입력 1
 * 7 8
 * 0 0 0 0 0 0 1 1
 * 1 1 0 0 0 0 1 1
 * 1 1 0 0 0 0 0 0
 * 1 1 0 0 0 1 1 0
 * 0 0 0 0 0 1 1 0
 * 0 0 0 0 0 0 0 0
 * 1 1 1 1 1 1 1 1
 * 예제 출력 1
 * 9
 * 예제 입력 2
 * 7 8
 * 0 0 0 1 1 0 0 0
 * 0 0 0 1 1 0 0 0
 * 1 1 0 0 0 0 1 1
 * 1 1 0 0 0 0 1 1
 * 1 1 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0
 * 1 1 1 1 1 1 1 1
 * 예제 출력 2
 * 10
 * 예제 입력 3
 * 7 8
 * 1 0 0 1 1 1 0 0
 * 0 0 1 0 0 0 1 1
 * 0 0 1 0 0 0 1 1
 * 0 0 1 1 1 0 0 0
 * 0 0 0 0 0 0 0 0
 * 0 1 1 1 0 0 0 0
 * 1 1 1 1 1 1 0 0
 * 예제 출력 3
 * 9
 * 예제 입력 4
 * 7 7
 * 1 1 1 0 1 1 1
 * 1 1 1 0 1 1 1
 * 1 1 1 0 1 1 1
 * 0 0 0 0 0 0 0
 * 1 1 1 0 1 1 1
 * 1 1 1 0 1 1 1
 * 1 1 1 0 1 1 1
 * 예제 출력 4
 * -1
 */

package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P17472 {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[] parent;
    static int N, M, sNum;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<ArrayList<int[]>> sumList;
    static ArrayList<int[]> mlist;
    static PriorityQueue<bEdge> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 섬 번호를 지정해줄 변수
        sNum = 1;
        // 모든 섬 정보 저장
        sumList  = new ArrayList<>();

        // 각 자리에서 BFS 탐색으로 섬들을 분리
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    BFS(i, j);
                    sNum++;
                    sumList.add(mlist);
                }
            }
        }

        queue = new PriorityQueue<>();

        // 섬의 각 지점에서 만들 수 있는 모든 에지를 지정
        for (int i = 0; i < sumList.size(); i++) {
            ArrayList<int[]> now = sumList.get(i);
            for (int j = 0; j < now.size(); j++) {
                int r = now.get(j)[0];
                int c = now.get(j)[1];
                int now_S = map[r][c];

                for (int d = 0; d < 4; d++) {
                    int tempR = dr[d];
                    int tempC = dc[d];
                    int blenght = 0;

                    while (r + tempR >= 0 && r + tempR < N && c + tempC >= 0 && c + tempC < M) {
                        if (map[r + tempR][c + tempC] == now_S) {   // 같은 섬이면 에지를 만들 수 없음
                            break;
                        } else if (map[r + tempR][c + tempC] != 0) { // 같은 섬도 아니고 바다도 아니면
                            // 다른 섬 -> 길이가 1 이상일 때 에지로 더함
                            if (blenght > 1) {
                                queue.offer(new bEdge(now_S, map[r + tempR][c + tempC], blenght));
                            }
                            break;
                        } else {
                            blenght++;
                        }

                        if (tempR < 0) {
                            tempR--;
                        } else if (tempR > 0) {
                            tempR++;
                        } else if (tempC < 0) {
                            tempC--;
                        } else if (tempC > 0) {
                            tempC++;
                        }
                    }
                }
            }
        }

        // 섬의 개수 만큼 만들기
        parent = new int[sNum];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        int useEdge = 0;
        int result = 0;
        while (!queue.isEmpty()) {
            bEdge now = queue.poll();
            if (find(now.s) != find(now.e)) {
                union(now.s, now.e);
                result += now.v;
                useEdge++;
            }
        }

        if (useEdge == sNum - 2) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int n) {
        if (parent[n] == n) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }

    // BFS를 이용해 연결된 섬을 찾아줌
    private static void BFS(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        mlist = new ArrayList<>();
        int[] start = { i, j };

        q.offer(start);
        mlist.add(start);
        visited[i][j] = true;
        map[i][j] = sNum;

        while (!q.isEmpty()) {
            int now[] = q.poll();
            int r = now[0];
            int c = now[1];

            for (int d = 0; d < 4; d++) {
                int tempR = dr[d];
                int tempC = dc[d];
                while (r + tempR >= 0 && r + tempR < N && c + tempC >= 0 && c + tempC < M) {
                    // 현재 방문한 적이 없고 바다가 아니면 같은 섬으로 취급
                    if (!visited[r + tempR][c + tempC] && map[r + tempR][c + tempC] != 0) {
                        addNode(r + tempR, c + tempC, q);
                    } else {
                        break;
                    }

                    if (tempR < 0) {
                        tempR--;
                    } else if (tempR > 0) {
                        tempR++;
                    } else if (tempC < 0) {
                        tempC--;
                    } else if (tempC > 0) {
                        tempC++;
                    }
                }
            }
        }
    }

    private static void addNode(int i, int j, Queue<int[]> q) {
        map[i][j] = sNum;
        visited[i][j] = true;
        int[] temp = { i, j };
        mlist.add(temp);
        q.offer(temp);
    }
}

class bEdge implements Comparable<bEdge> {

    int s;
    int e;
    int v;

    public bEdge(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }

    @Override
    public int compareTo(bEdge o) {
        return this.v - o.v;
    }
}
