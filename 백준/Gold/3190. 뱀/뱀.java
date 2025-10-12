import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int APPLE = 1;
    static final int SNAKE_BODY = -1;
    static final int EMPTY = 0;

    static int N;
    static int[][] board;
    static Map<Integer, Character> rotationMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());

            board = new int[N + 1][N + 1];

            int K = Integer.parseInt(br.readLine());
            StringTokenizer st;
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                board[x][y] = APPLE;
            }

            int L = Integer.parseInt(br.readLine());
            for (int i = 0; i < L; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                char b = st.nextToken().charAt(0);
                rotationMap.put(a, b);
            }

            Snake snake = new Snake(1, 1);
            int time = 1;
            while (snake.move()) {
                if (rotationMap.containsKey(time)) snake.rotation(rotationMap.get(time));
                time++;
            }
            System.out.println(time);
        }
    }

    static class Snake {
        final int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int currDirect = 0;
        Deque<int[]> snakeBody = new ArrayDeque<>();

        public Snake(int x, int y) {
            snakeBody.add(new int[]{x, y});
            board[x][y] = -1;
        }

        public boolean move() {
            int[] head = newHead(snakeBody.getLast(), d[currDirect]);
            if (isDie(head)) return false;
            if (board[head[0]][head[1]] == EMPTY) {
                int[] tail = snakeBody.pollFirst();
                board[tail[0]][tail[1]] = EMPTY;
            }
            snakeBody.add(head);
            board[head[0]][head[1]] = SNAKE_BODY;
            return true;
        }

        public void rotation(char c) {
            if (c == 'D') {
                currDirect = (currDirect + 1) % 4;
            } else if (c == 'L') {
                currDirect = (currDirect + 3) % 4;
            }
        }

        private int[] newHead(int[] head, int[] currDir) {
            return new int[] {head[0] + currDir[0], head[1] + currDir[1]};
        }

        private boolean isDie(int[] head) {
            return (head[0] <= 0 || head[0] > N || head[1] <= 0 || head[1] > N)
                || board[head[0]][head[1]] == SNAKE_BODY;
        }
    }
}
