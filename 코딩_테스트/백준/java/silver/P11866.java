package silver;

import java.util.*;

// public class P11866 {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         String[] str = sc.nextLine().split(" ");
//         int N = Integer.parseInt(str[0]);
//         int K = Integer.parseInt(str[1]);

//         Queue<Integer> q = new LinkedList<Integer>();
//         for (int i = 1; i <= N; i++) {
//             q.add(i);
//         }

//         StringBuilder sb = new StringBuilder();
//         sb.append("<");
//
//         int count = 1;
//         while (q.size() > 1) {
//             if (count == K) {
//                 sb.append(q.poll()).append(", ");
//                 count = 1;
//             } else {
//                 q.offer(q.poll());
//                 count++;
//             }
//         }

//         sb.append(q.poll()).append(">");
//         System.out.println(sb);
//     }
// }

public class P11866 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);

        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        int index = 0;
        while (N > 1) {
            index = (index + (K - 1)) % N;

            sb.append(list.remove(index)).append(", ");
            N--;
        }

        sb.append(list.remove()).append(">");
        System.out.println(sb);
    }
}