/**
 *
 * URL : https://www.acmicpc.net/problem/2752
 *
 * 세수정렬
 *
 * 문제
 * 동규는 세수를 하다가 정렬이 하고싶어졌다.
 *
 * 숫자 세 개를 생각한 뒤에, 이를 오름차순으로 정렬하고 싶어 졌다.
 *
 * 숫자 세 개가 주어졌을 때, 가장 작은 수, 그 다음 수, 가장 큰 수를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 숫자 세 개가 주어진다. 이 숫자는 1보다 크거나 같고, 1,000,000보다 작거나 같다. 이 숫자는 모두 다르다.
 *
 * 출력
 * 제일 작은 수, 그 다음 수, 제일 큰 수를 차례대로 출력한다.
 *
 * 예제 입력 1
 * 3 1 2
 * 예제 출력 1
 * 1 2 3
 */

package bronze;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class P2752 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        IntStream.range(0, st.countTokens())
                .forEach(i -> list.add(Integer.parseInt(st.nextToken())));

        list.stream().sorted().forEach(l -> {
            try {
                bw.write(l + " ");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        bw.flush();

        bw.close();
        br.close();
    }
}
