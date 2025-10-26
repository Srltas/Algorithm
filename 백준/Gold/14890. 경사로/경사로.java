import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, L, count;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 행
            for (int i = 0; i < N; i++) {
                boolean[] used = new boolean[N];
                boolean canPass = true;
                for (int j = 0; j < N - 1; j++) {
                    int diff = map[i][j] - map[i][j + 1];

                    if (Math.abs(diff) > 1) {
                        canPass = false;
                        break;
                    } else if (diff == 1) {
                        if (!downRow(i, j + 1, used)) {
                            canPass = false;
                            break;
                        }
                    } else if (diff == -1) {
                        if (!upRow(i, j, used)) {
                            canPass = false;
                            break;
                        }
                    }
                }
                if (canPass) count++;
            }

            // 열
            for (int j = 0; j < N; j++) {
                boolean[] used = new boolean[N];
                boolean canPass = true;
                for (int i = 0; i < N - 1; i++) {
                    int diff = map[i][j] - map[i + 1][j];

                    if (Math.abs(diff) > 1) {
                        canPass = false;
                        break;
                    } else if (diff == 1) {
                        if (!downCol(i + 1, j, used)) {
                            canPass = false;
                            break;
                        }
                    } else if (diff == -1) {
                        if (!upCol(i, j, used)) {
                            canPass = false;
                            break;
                        }
                    }
                }
                if (canPass) count++;
            }

            System.out.println(count);
        }
    }

    static boolean downRow(int i, int j, boolean[] used) {
        int value = map[i][j];
        for (int l = 0; l < L; l++) {
            int next = j + l;
            if (outRange(next) || used[next] || map[i][next] != value) {
                return false;
            }
        }

        for (int l = 0; l < L; l++) {
            used[j + l] = true;
        }
        return true;
    }

    static boolean upRow(int i, int j, boolean[] used) {
        int value = map[i][j];
        for (int l = 0; l < L; l++) {
            int next = j - l;
            if (outRange(next) || used[next] || map[i][next] != value) {
                return false;
            }
        }

        for (int l = 0; l < L; l++) {
            used[j - l] = true;
        }
        return true;
    }

    static boolean downCol(int i , int j, boolean[] used) {
        int value = map[i][j];
        for (int l = 0; l < L; l++) {
            int next = i + l;
            if (outRange(next) || used[next] || map[next][j] != value) {
                return false;
            }
        }

        for (int l = 0; l < L; l++) {
            used[i + l] = true;
        }
        return true;
    }

    static boolean upCol(int i, int j, boolean[] used) {
        int value = map[i][j];
        for (int l = 0; l < L; l++) {
            int next = i - l;
            if (outRange(next) || used[next] || map[next][j] != value) {
                return false;
            }
        }

        for (int l = 0; l < L; l++) {
            used[i - l] = true;
        }
        return true;
    }

    static boolean outRange(int x) {
        return x < 0 || x >= N;
    }
}
