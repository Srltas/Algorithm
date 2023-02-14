/**
 *
 * URL : https://www.acmicpc.net/problem/11652
 *
 * 카드
 *
 * 문제
 * 준규는 숫자 카드 N장을 가지고 있다. 숫자 카드에는 정수가 하나 적혀있는데, 적혀있는 수는 -262보다 크거나 같고, 262보다 작거나 같다.
 *
 * 준규가 가지고 있는 카드가 주어졌을 때, 가장 많이 가지고 있는 정수를 구하는 프로그램을 작성하시오. 만약, 가장 많이 가지고 있는 정수가 여러 가지라면, 작은 것을 출력한다.
 *
 * 입력
 * 첫째 줄에 준규가 가지고 있는 숫자 카드의 개수 N (1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N개 줄에는 숫자 카드에 적혀있는 정수가 주어진다.
 *
 * 출력
 * 첫째 줄에 준규가 가장 많이 가지고 있는 정수를 출력한다.
 *
 * 예제 입력 1
 * 5
 * 1
 * 2
 * 1
 * 2
 * 1
 * 예제 출력 1
 * 1
 * 예제 입력 2
 * 6
 * 1
 * 2
 * 1
 * 2
 * 1
 * 2
 * 예제 출력 2
 * 1
 */

package silver;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class P11652 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        new P11652().solution();
    }

    public void solution() throws IOException {
        HashMap<Long, Integer> cards = new HashMap<>();

        int N = parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            long card = Long.parseLong(br.readLine());
            cards.put(card, cards.getOrDefault(card, 0) + 1);
        }

        List<Map.Entry<Long, Integer>> list = cards.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        int maxValue = 0;
        long result = 0;
        for (Map.Entry<Long, Integer> entry : list) {
            if (maxValue < entry.getValue()) {
                maxValue = entry.getValue();
                result = entry.getKey();
            } else if (maxValue == entry.getValue()) {
                result = result > entry.getKey() ? entry.getKey() : result;
            }
        }
        bw.write(result + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }
}
