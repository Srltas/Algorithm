package level2;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P42587 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(", ");
        int[] priorities = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            priorities[i] = Integer.parseInt(str[i]);
        }

        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        int answer = 0;

        for (int i = 0; i < priorities.length; i++) {
            q.add(priorities[i]);
        }

        while (!q.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (priorities[i] == q.peek()) {
                    if (i == 0) {
                        answer++;
                        System.out.println(answer);
                        break;
                    }
                    answer++;
                    q.poll();
                }
            }
            break;
        }
        System.out.println(answer);
    }
}