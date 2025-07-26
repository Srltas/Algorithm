import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] array = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(st.nextToken());
            }

            long money = 0;
            for (int i = 0; i < m; i++) {
                money += array[i];
            }

            long maxMoney = money;
            for (int i = m; i < n; i++) {
                money += array[i] - array[i - m];
                maxMoney = Math.max(maxMoney, money);
            }
            bw.write(String.valueOf(maxMoney));
            bw.flush();
        }
    }
}
