import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            int[][] arr = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int k = 0; k < Math.min(N, M) / 2; k++) {
                int endRow = N-k-1;
                int endCol = M-k-1;

                List<Integer> list = new ArrayList<>();
                // L -> R
                for (int i = k; i <= endCol; i++) {
                    list.add(arr[k][i]);
                }

                // T -> B
                for (int i = k + 1; i <= endRow; i++) {
                    list.add(arr[i][endCol]);
                }

                // R -> L
                for (int i = endCol - 1; i >= k; i--) {
                    list.add(arr[endRow][i]);
                }

                // B -> T
                for (int i = endRow - 1; i >= k + 1; i--) {
                    list.add(arr[i][k]);
                }

                int size = list.size();
                int rollCount = R % size;

                Collections.rotate(list, -rollCount);

                int index = 0;
                for (int i = k; i <= endCol; i++) {
                    arr[k][i] = list.get(index++);
                }

                for (int i = k + 1; i <= endRow; i++) {
                    arr[i][endCol] = list.get(index++);
                }

                for (int i = endCol - 1; i >= k; i--) {
                    arr[endRow][i] = list.get(index++);
                }

                for (int i = endRow - 1; i >= k + 1; i--) {
                    arr[i][k] = list.get(index++);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    sb.append(arr[i][j]).append(' ');
                }
                sb.append('\n');
            }
            System.out.println(sb);
        }
    }
}
