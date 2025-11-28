import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            int[] switches = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                switches[i] = Integer.parseInt(st.nextToken());
            }

            int student = Integer.parseInt(br.readLine());
            for (int i = 0; i < student; i++) {
                st = new StringTokenizer(br.readLine());
                int gender = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());

                if (gender == 1) {
                    for (int j = num; j <= N; j+= num) {
                        switches[j] = switches[j] == 0 ? 1 : 0;
                    }
                } else {
                    switches[num] = switches[num] == 0 ? 1 : 0;
                    int left = num - 1;
                    int right = num + 1;
                    while (left >= 1 && right <= N) {
                        if (switches[left] == switches[right]) {
                            switches[left] = switches[left] == 0 ? 1 : 0;
                            switches[right] = switches[right] == 0 ? 1 : 0;
                            left--;
                            right++;
                        } else {
                            break;
                        }
                    }
                }
            }

            for (int i = 1; i <= N; i++) {
                System.out.print(switches[i]);
                if (i % 20 == 0) {
                    System.out.println();
                } else if (i < N) {
                    System.out.print(" ");
                }
            }
        }
    }
}
