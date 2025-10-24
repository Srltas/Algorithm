import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            int[][] arr = new int[R][C];
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int upRow = 0;
            for (int i = 0; i < R; i++) {
                if (arr[i][0] == -1) {
                    upRow = i;
                    break;
                }
            }
            int downRow = upRow + 1;

            while (T-- > 0) {
                int[][] tempArr = new int[R][C];
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (arr[i][j] == -1) {
                            tempArr[i][j] = -1;
                            continue;
                        }

                        tempArr[i][j] += arr[i][j];

                        if (arr[i][j] > 0) {
                            int count = 0;
                            int n = arr[i][j] / 5;
                            for (int k = 0; k < 4; k++) {
                                int nx = i + dx[k];
                                int ny = j + dy[k];
                                if (nx < 0 || nx >= R || ny < 0 || ny >= C || arr[nx][ny] == -1) continue;
                                count++;
                                tempArr[nx][ny] += n;
                            }
                            tempArr[i][j] -= (n * count);
                        }
                    }
                }

                for (int i = 0; i < R; i++) {
                    System.arraycopy(tempArr[i], 0, arr[i], 0, C);
                }

                // upRow
                int upPrev = arr[upRow][1];
                arr[upRow][1] = 0;
                for (int i = 2; i < C; i++) {
                    int temp = arr[upRow][i];
                    arr[upRow][i] = upPrev;
                    upPrev = temp;
                }

                for (int i = upRow - 1; i >= 0; i--) {
                    int temp = arr[i][C - 1];
                    arr[i][C - 1] = upPrev;
                    upPrev = temp;
                }

                for (int i = C - 2; i >= 0; i--) {
                    int temp = arr[0][i];
                    arr[0][i] = upPrev;
                    upPrev = temp;
                }

                for (int i = 1; i < upRow; i++) {
                    int temp = arr[i][0];
                    arr[i][0] = upPrev;
                    upPrev = temp;
                }


                int downPrev = arr[downRow][1];
                arr[downRow][1] = 0;
                for (int i = 2; i < C; i++) {
                    int temp = arr[downRow][i];
                    arr[downRow][i] = downPrev;
                    downPrev = temp;
                }

                for (int i = downRow + 1; i < R; i++) {
                    int temp = arr[i][C - 1];
                    arr[i][C - 1] = downPrev;
                    downPrev = temp;
                }

                for (int i = C - 2; i >= 0; i--) {
                    int temp = arr[R - 1][i];
                    arr[R - 1][i] = downPrev;
                    downPrev = temp;
                }

                for (int i = R - 2; i > downRow; i--) {
                    int temp = arr[i][0];
                    arr[i][0] = downPrev;
                    downPrev = temp;
                }
            }

            int sum = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (arr[i][j] > 0) {
                        sum += arr[i][j];
                    }
                }
            }

            System.out.println(sum);
        }
    }
}
