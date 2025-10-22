import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            Cogwheel[] g = new Cogwheel[4];
            for (int i = 0; i < 4; i++) {
                g[i] = new Cogwheel(br.readLine().toCharArray());
            }

            int K = Integer.parseInt(br.readLine());
            while (K-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int index = Integer.parseInt(st.nextToken()) - 1;
                int dir = Integer.parseInt(st.nextToken());

                boolean[] diff = new boolean[3];
                for (int i = 0; i < 3; i++) {
                    diff[i] = g[i].right() != g[i + 1].left();
                }

                int[] rot = new int[4];
                rot[index] = dir;

                for (int i = index; i < 3; i++) {
                    if (!diff[i]) break;
                    rot[i + 1] = -rot[i];
                }

                for (int i = index - 1; i >= 0; i--) {
                    if (!diff[i]) break;
                    rot[i] = -rot[i + 1];
                }

                for (int i = 0; i < 4; i++) {
                    g[i].spin(rot[i]);
                }
            }

            int total = 0;
            if (g[0].top() == 1) total += 1;
            if (g[1].top() == 1) total += 2;
            if (g[2].top() == 1) total += 4;
            if (g[3].top() == 1) total += 8;
            System.out.println(total);
        }
    }

    static class Cogwheel {
        int[] arr = new int[8];

        public Cogwheel(char[] s) {
            for (int i = 0; i < 8; i++) {
                arr[i] = s[i] == '0' ? 0 : 1;
            }
        }

        public int left() { return arr[6]; }
        public int right() { return arr[2]; }
        public int top() { return arr[0]; }

        public void spin(int dir) {
            if (dir == 0) return;
            if (dir == 1) clockwise();
            else counterclockwise();
        }

        private void clockwise() {
            int last = arr[arr.length - 1];
            System.arraycopy(arr, 0, arr, 1, 7);
            arr[0] = last;
        }

        private void counterclockwise() {
            int first = arr[0];
            System.arraycopy(arr, 1, arr, 0, 7);
            arr[7] = first;
        }
    }
}
