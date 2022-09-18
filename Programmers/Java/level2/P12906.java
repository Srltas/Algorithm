package level2;

import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class P12906 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(",");
        int[] arr = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        Stack<Integer> stack = new Stack<Integer>();
        for (int a : arr) {
            if (!stack.isEmpty()) {
                if (stack.peek() != a) {
                    stack.push(a);
                }
            } else {
                stack.push(a);
            }
        }

        Collections.reverse(stack);
        System.out.println(stack);
        for (int s : stack) {
            System.out.println(s);
        }
    }
}
