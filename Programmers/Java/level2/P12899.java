package level2;

import java.util.Scanner;

public class P12899 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Solution solution = new Solution();
        System.out.println(solution.solution(scanner.nextInt()));
    }

    static class Solution {
        public String solution(int n) {
            String answer = "";
            String[] num = {"4", "1", "2"};

            while (n > 0) {
                answer = num[n % 3] + answer;
                n = (n - 1) / 3;
            }

            return answer;
        }
    }
}