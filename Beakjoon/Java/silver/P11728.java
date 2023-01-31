/**
 *
 * URL : https://www.acmicpc.net/problem/11728
 *
 * 배열 합치기
 *
 * 문제
 * 정렬되어있는 두 배열 A와 B가 주어진다. 두 배열을 합친 다음 정렬해서 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 배열 A의 크기 N, 배열 B의 크기 M이 주어진다. (1 ≤ N, M ≤ 1,000,000)
 *
 * 둘째 줄에는 배열 A의 내용이, 셋째 줄에는 배열 B의 내용이 주어진다. 배열에 들어있는 수는 절댓값이 109보다 작거나 같은 정수이다.
 *
 * 출력
 * 첫째 줄에 두 배열을 합친 후 정렬한 결과를 출력한다.
 *
 * 예제 입력 1
 * 2 2
 * 3 5
 * 2 9
 * 예제 출력 1
 * 2 3 5 9
 * 예제 입력 2
 * 2 1
 * 4 7
 * 1
 * 예제 출력 2
 * 1 4 7
 * 예제 입력 3
 * 4 3
 * 2 3 5 9
 * 1 4 7
 * 예제 출력 3
 * 1 2 3 4 5 7 9
 */

package silver;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P11728 {
    public static void main(String[] args) throws IOException {
        new P11728().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);
        for (Integer integer : list) {
            bw.write(integer + " ");
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
