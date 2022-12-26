/**
 *
 * URL : https://www.acmicpc.net/problem/10817
 *
 * 세 수
 *
 * 문제
 * 세 정수 A, B, C가 주어진다. 이때, 두 번째로 큰 정수를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 세 정수 A, B, C가 공백으로 구분되어 주어진다. (1 ≤ A, B, C ≤ 100)
 *
 * 출력
 * 두 번째로 큰 정수를 출력한다.
 *
 * 예제 입력 1
 * 20 30 10
 * 예제 출력 1
 * 20
 * 예제 입력 2
 * 30 30 10
 * 예제 출력 2
 * 30
 * 예제 입력 3
 * 40 40 40
 * 예제 출력 3
 * 40
 * 예제 입력 4
 * 20 10 10
 * 예제 출력 4
 * 10
 */

package bronze;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class P10817 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>();
        while (st.hasMoreTokens()) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        list = list.stream().sorted().collect(Collectors.toList());
        bw.write(list.get(list.size() - 2) + "\n");
        bw.flush();

        bw.close();
        br.close();
    }
}
