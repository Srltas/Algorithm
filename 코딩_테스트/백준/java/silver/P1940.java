package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1940 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int array[] = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);

        int startIndex = 0;
        int endIndex = N-1;
        int count = 0;
        while (startIndex < endIndex) {
            if (array[startIndex] + array[endIndex] == M) {
                count++;
                startIndex++;
                endIndex--;
            } else if (array[startIndex] + array[endIndex] > M) {
                endIndex--;
            } else {
                startIndex++;
            }
        }
        System.out.println(count);
    }
}
