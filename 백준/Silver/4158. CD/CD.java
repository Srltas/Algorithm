import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String[] str = br.readLine().split(" ");
            int N = Integer.parseInt(str[0]);
            int M = Integer.parseInt(str[1]);

            if (N == 0 && M == 0) {
                break;
            }

            int[] array1 = new int[N];
            int[] array2 = new int[M];

            for (int i = 0; i < N; i++) {
                array1[i] = Integer.parseInt(br.readLine());
            }

            for (int i = 0; i < M; i++) {
                array2[i] = Integer.parseInt(br.readLine());
            }

            int count = 0;
            int i = 0, j = 0;

            while (i < N && j < M) {
                if (array1[i] == array2[j]) {
                    count++;
                    i++;
                    j++;
                } else if (array1[i] < array2[j]) {
                    i++;
                } else {
                    j++;
                }
            }
            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}