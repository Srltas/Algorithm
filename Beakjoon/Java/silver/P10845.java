package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class P10845 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        Deque<Integer> q = new LinkedList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");

            if (str[0].equals("push")) {
                q.offer(Integer.parseInt(str[1]));
            } else if (str[0].equals("pop")) {
                if (!q.isEmpty()) {
                    System.out.println(q.poll());
                } else {
                    System.out.println(-1);
                }
            } else if (str[0].equals("size")) {
                System.out.println(q.size());
            } else if (str[0].equals("empty")) {
                if (q.isEmpty()) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else if (str[0].equals("front")) {
                if (!q.isEmpty()) {
                    System.out.println(q.peek());
                } else {
                    System.out.println(-1);
                }
            } else if (str[0].equals("back")) {
                if (!q.isEmpty()) {
                    System.out.println(q.peekLast());
                } else {
                    System.out.println(-1);
                }
            }
        }
    }
}
