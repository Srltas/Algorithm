package level2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class P42586 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(",");
        int[] progresses = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            progresses[i] = Integer.parseInt(str[i]);
        }

        str = sc.nextLine().split(",");
        int[] speeds = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            speeds[i] = Integer.parseInt(str[i]);
        }


        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            q.add((100 - progresses[i]) / speeds[i]);
        }

        List<Integer> l = new ArrayList<Integer>();
        while (!q.isEmpty()) {
            int day = q.poll();
            int count = 1;

            while (!q.isEmpty() && day >= q.peek()) {
                count++;
                q.poll();
            }

            l.add(count);
        }
        
        System.out.println(l);
    }
}
