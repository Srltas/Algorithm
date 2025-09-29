import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static long[] tree;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            Runner[] runners = new Runner[N];
            for (int i = 0; i < N; i++) {
                runners[i] = new Runner(Integer.parseInt(br.readLine()), i);
            }

            int[] skills = new int[N];
            for (int i = 0; i < N; i++) {
                skills[i] = runners[i].skill;
            }

            Arrays.sort(skills);

            Map<Integer, Integer> skillMap = new HashMap<>();
            for (int i = 0; i < N; i++) {
                skillMap.put(skills[i], i);
            }

            for (int i = 0; i < N; i++) {
                runners[i].skill = skillMap.get(runners[i].skill);
            }

            tree = new long[4 * N];

            int[] results = new int[N];
            for (int i = 0; i < N; i++) {
                Runner currentRunner = runners[i];
                int compressedSkill = currentRunner.skill;

                long strongerAhead = 0;
                if (compressedSkill + 1 < N) {
                    strongerAhead = query(1, 0, N - 1, compressedSkill + 1, N - 1);
                }

                results[currentRunner.id] = (int) strongerAhead + 1;
                update(1, 0, N - 1, compressedSkill);
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(results[i]).append('\n');
            }
            System.out.println(sb);
        }
    }

    static void update(int node, int start, int end, int index) {
        if (index < start || index > end) {
            return;
        }

        tree[node]++;

        if (start != end) {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, index);
            update(node * 2 + 1, mid + 1, end, index);
        }
    }

    static long query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return 0;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        long leftSum = query(node * 2, start, mid, left, right);
        long rightSum = query(node * 2 + 1, mid + 1, end, left, right);
        return leftSum + rightSum;
    }

    static class Runner {
        int skill;
        int id;

        public Runner(int skill, int id) {
            this.skill = skill;
            this.id = id;
        }
    }
}
