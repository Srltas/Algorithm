package bronze;

import java.util.Scanner;
import java.util.StringTokenizer;

public class P2475 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        int[] A = new int[5];
        for (int i = 0; i < 5; i++) {
            A[i] = (int) Math.pow(Integer.parseInt(st.nextToken()),2);
        }

        int sum = 0;
        for (int a : A) {
            sum += a;
        }
        System.out.println(sum % 10);
    }
}
