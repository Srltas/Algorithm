package silver;

import java.util.Arrays;
import java.util.Scanner;

public class P11399 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        int[] S = new int[N];
        for (int i = 1; i < N; i++) {
            int point = i;
            int value = A[i];
            for (int j = i - 1; j >= 0; j--) {
                if (A[j] < A[i]) {
                    point = j + 1;
                    break;
                }
                if (j == 0) {
                    point = 0;
                }
            }

            for (int j = i; j > point; j--) {
                A[j] = A[j - 1];
            }
            A[point] = value;
        }

        S[0] = A[0];
        for (int i = 1; i < N; i++) {
            S[i] = S[i - 1] + A[i];
        }

        System.out.println(Arrays.stream(S).sum());
    }
}
