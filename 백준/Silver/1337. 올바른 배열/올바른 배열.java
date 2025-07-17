import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);  // 정렬

        int maxCount = 0;
        int left = 0;

        for (int right = 0; right < N; right++) {
            while (arr[right] - arr[left] > 4) {
                left++;
            }
            int count = right - left + 1;
            maxCount = Math.max(maxCount, count);
        }

        System.out.println(5 - maxCount);
    }
}