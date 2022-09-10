package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1260 {

    private static int node;
    private static int edge;
    private static int startPoint;
    private static ArrayList<Integer>[] A;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        int startPoint = Integer.parseInt(st.nextToken());

        A = new ArrayList[edge + 1];
        for (int i = 1; i < node + 1; i++) {
            A[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            A[S].add(E);
            A[E].add(S);
        }

        for (int i = 1; i <= node; i++) {
            Collections.sort(A[i]);
        }

        visited = new boolean[edge + 1];
        DFS(startPoint);
        System.out.println();

        visited = new boolean[edge + 1];
        BFS(startPoint);
        System.out.println();
    }

    private static void DFS(int node) {
        if (visited[node]) {
            return;
        }

        System.out.print(node + " ");
        visited[node] = true;
        for (int a : A[node]) {
            if (!visited[a]) {
                DFS(a);
            }
        }
    }

    private static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int front = queue.poll();
            System.out.print(front + " ");

            for (int i : A[front]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
