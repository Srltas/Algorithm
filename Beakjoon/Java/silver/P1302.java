/**
 *
 * URL : https://www.acmicpc.net/problem/1302
 *
 * 베스트셀러
 *
 * 문제
 * 김형택은 탑문고의 직원이다. 김형택은 계산대에서 계산을 하는 직원이다. 김형택은 그날 근무가 끝난 후에, 오늘 판매한 책의 제목을 보면서 가장 많이 팔린 책의 제목을 칠판에 써놓는 일도 같이 하고 있다.
 *
 * 오늘 하루 동안 팔린 책의 제목이 입력으로 들어왔을 때, 가장 많이 팔린 책의 제목을 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 오늘 하루 동안 팔린 책의 개수 N이 주어진다. 이 값은 1,000보다 작거나 같은 자연수이다. 둘째부터 N개의 줄에 책의 제목이 입력으로 들어온다. 책의 제목의 길이는 50보다 작거나 같고, 알파벳 소문자로만 이루어져 있다.
 *
 * 출력
 * 첫째 줄에 가장 많이 팔린 책의 제목을 출력한다. 만약 가장 많이 팔린 책이 여러 개일 경우에는 사전 순으로 가장 앞서는 제목을 출력한다.
 *
 * 예제 입력 1
 * 5
 * top
 * top
 * top
 * top
 * kimtop
 * 예제 출력 1
 * top
 * 예제 입력 2
 * 9
 * table
 * chair
 * table
 * table
 * lamp
 * door
 * lamp
 * table
 * chair
 * 예제 출력 2
 * table
 * 예제 입력 3
 * 6
 * a
 * a
 * a
 * b
 * b
 * b
 * 예제 출력 3
 * a
 * 예제 입력 4
 * 8
 * icecream
 * peanuts
 * peanuts
 * chocolate
 * candy
 * chocolate
 * icecream
 * apple
 * 예제 출력 4
 * chocolate
 * 예제 입력 5
 * 1
 * soul
 * 예제 출력 5
 * soul
 */

package silver;

import java.io.*;
import java.util.*;

public class P1302 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        new P1302().solution();
    }

    public void solution() throws IOException {
        HashMap<String, Integer> books = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            String book = br.readLine() ;
            books.put(book, books.getOrDefault(book, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list = new LinkedList<>(books.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() == o2.getValue()) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return o2.getValue() - o1.getValue();
            }
        });

        bw.write(list.get(0).getKey());
        bw.flush();

        bw.close();
        br.close();
    }
}
