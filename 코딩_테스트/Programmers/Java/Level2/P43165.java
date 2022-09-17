package level2;

import java.util.Scanner;

public class P43165 {

    private static boolean[] visited;
    private static int[] numbers;
    private static int target;
    private static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(", ");
        numbers = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            numbers[i] = Integer.parseInt(str[i]);
        }

        target = sc.nextInt();
        visited = new boolean[numbers.length];

        DFS(0, 0);

        System.out.println(count);
    }

    private static void DFS(int num, int total) {
        if (num == numbers.length) {
            if (total == target) {
                count++;
            }
            return;
        }

        DFS(num + 1,total + numbers[num]);
        DFS(num + 1, total - numbers[num]);
    }
}