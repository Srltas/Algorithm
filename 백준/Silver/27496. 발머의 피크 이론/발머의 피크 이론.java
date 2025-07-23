import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] array = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int alcohol = 0;
        int time = 0;
        for (int i = 0; i < L; i++) {
            alcohol += array[i];
            if (isAlcoholCheck(alcohol)) {
                time++;
            }
        }

        for (int i = L; i < N; i++) {
            alcohol += array[i] - array[i - L];
            if (isAlcoholCheck(alcohol)) {
                time++;
            }
        }
        System.out.println(time);
    }

    private static boolean isAlcoholCheck(int alcohol) {
        return 129 <= alcohol && alcohol <= 138;
    }
}