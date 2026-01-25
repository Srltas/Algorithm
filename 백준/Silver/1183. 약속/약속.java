import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i] = b - a;
        }

        if (arr.length % 2 != 0) {
            System.out.println(1);
            return;
        }

        int mid = arr.length / 2;
        Arrays.sort(arr);
        System.out.println(Math.abs(arr[mid] - arr[mid - 1] + 1));
    }
}
