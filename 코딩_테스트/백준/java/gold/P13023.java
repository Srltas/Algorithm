import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P13023 {

    static ArrayList<Integer>[] A;
    static boolean visited[];
    static boolean arrive;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        A = new ArrayList[N] ;
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            A[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            A[s].add(e);
            A[e].add(s);
        }

        for (int i = 0; i < N; i++) {
            DFS(i, 1);

            if (arrive) {
                break;
            }
        }

        if (arrive) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static void DFS(int n, int depth) {
        if (depth == 5 || arrive) {
            arrive = true;
            return;
        }

        visited[n] = true;
        for (int i : A[n]) {
            if (!visited[i]) {
                DFS(i, depth + 1);
            }
        }

        visited[n] = false;
    }
}