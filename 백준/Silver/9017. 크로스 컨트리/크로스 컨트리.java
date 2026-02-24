import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] rank = new int[N];
            int[] teamCount = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                rank[i] = Integer.parseInt(st.nextToken());
                teamCount[rank[i]]++;
            }

            int[] totalScore = new int[N];
            int[] runnerCount = new int[N];
            int[] fiveRank = new int[N];

            int currentScore = 1;
            for (int i = 0; i < N; i++) {
                int teamNum = rank[i];
                if (teamCount[teamNum] >= 6) {
                    runnerCount[teamNum]++;
                    if (runnerCount[teamNum] <= 4) {
                        totalScore[teamNum] += currentScore;
                    } else if (runnerCount[teamNum] == 5) {
                        fiveRank[teamNum] = currentScore;
                    }
                    currentScore++;
                }
            }

            int winner = -1;
            int minScore = Integer.MAX_VALUE;
            for (int i = 1; i < N; i++) {
                if (teamCount[i] >= 6) {
                    if (totalScore[i] < minScore) {
                        minScore = totalScore[i];
                        winner = i;
                    } else if (totalScore[i] == minScore) {
                        if (fiveRank[i] < fiveRank[winner]) {
                            winner = i;
                        }
                    }
                }
            }
            sb.append(winner).append('\n');
        }
        System.out.println(sb);
    }
}
