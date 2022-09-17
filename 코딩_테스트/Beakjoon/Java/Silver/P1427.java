package silver;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class P1427 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        
        Integer[] A = new Integer[str.length()];

        for (int i = 0; i < str.length(); i++) {
            A[i] = str.charAt(i) - 48;
        }

        Arrays.sort(A, Collections.reverseOrder());

        for (int a : A) {
            System.out.print(a);
        }
    }
}
