import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());
            while (T-- > 0) {
                int[] array = new int[10];
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int i = 0; i < 10; i++) {
                    array[i] = Integer.parseInt(st.nextToken());
                }
                Arrays.sort(array);
                System.out.println(array[7]);
            }
        }
    }
}
