import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static final int[] opposite = {5, 3, 4, 1, 2, 0};

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            Dice[] dices = new Dice[N];
            for (int i = 0; i < N; i++) {
                dices[i] = new Dice(new StringTokenizer(br.readLine()));
            }

            int maxResult = 0;
            for (int i = 0; i < 6; i++) {
                Dice firstDice = dices[0];
                int currentBottomIndex = i;
                int currentTopIndex = opposite[currentBottomIndex];
                int currentTopValue = firstDice.num[currentTopIndex];

                int currentSum = firstDice.getMaxSideValue(currentBottomIndex);

                for (int d = 1; d < N; d++) {
                    Dice nextDice = dices[d];

                    currentBottomIndex = nextDice.findFaceIndex(currentTopValue);
                    currentTopIndex = opposite[currentBottomIndex];
                    currentTopValue = nextDice.num[currentTopIndex];

                    currentSum += nextDice.getMaxSideValue(currentBottomIndex);
                }
                maxResult = Math.max(currentSum, maxResult);
            }
            System.out.println(maxResult);
        }
    }

    static class Dice {
        int[] num = new int[6];

        public Dice(StringTokenizer st) {
            for (int i = 0; i < 6; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }
        }

        public int findFaceIndex(int value) {
            for (int i = 0; i < 6; i++) {
                if (num[i] == value) {
                    return i;
                }
            }
            return -1;
        }

        public int getMaxSideValue(int bottomIndex) {
            int topIndex = opposite[bottomIndex];
            int max = 0;
            for (int i = 0; i < 6; i++) {
                if (i != bottomIndex && i != topIndex) {
                    max = Math.max(max, num[i]);
                }
            }
            return max;
        }
    }
}
