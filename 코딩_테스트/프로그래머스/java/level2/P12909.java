package level2;

import java.util.Scanner;
import java.util.Stack;

public class P12909 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] cArray = sc.nextLine().toCharArray();

        Stack<Character> stack = new Stack<Character>();

        for (char c : cArray) {
            if (!stack.isEmpty()) {
                if (c == ')' && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            } else {
                stack.push(c);
            }
        }

        if (stack.isEmpty()) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
