import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] tree;
    static int[] size;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());

            tree = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                tree[u].add(v);
                tree[v].add(u);
            }

            size = new int[N + 1];
            makeTree(R, -1);

            for (int q = 0; q < Q; q++) {
                int u = Integer.parseInt(br.readLine());
                System.out.println(size[u]);
            }
        }
    }

    static void makeTree(int currentNode, int parent) {
        size[currentNode] = 1;
        for (int nextNode : tree[currentNode]) {
            if (nextNode != parent) {
                makeTree(nextNode, currentNode);
                size[currentNode] += size[nextNode];
            }
        }
    }
}
