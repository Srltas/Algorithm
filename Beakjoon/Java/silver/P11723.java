/**
URL : https://www.acmicpc.net/problem/11723

문제
비어있는 공집합 S가 주어졌을 때, 아래 연산을 수행하는 프로그램을 작성하시오.

add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.
remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.
check x: S에 x가 있으면 1을, 없으면 0을 출력한다. (1 ≤ x ≤ 20)
toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)
all: S를 {1, 2, ..., 20} 으로 바꾼다.
empty: S를 공집합으로 바꾼다. 
입력
첫째 줄에 수행해야 하는 연산의 수 M (1 ≤ M ≤ 3,000,000)이 주어진다.

둘째 줄부터 M개의 줄에 수행해야 하는 연산이 한 줄에 하나씩 주어진다.

출력
check 연산이 주어질때마다, 결과를 출력한다.

예제 입력 1  복사
26
add 1
add 2
check 1
check 2
check 3
remove 2
check 1
check 2
toggle 3
check 1
check 2
check 3
check 4
all
check 10
check 20
toggle 10
remove 20
check 10
check 20
empty
check 1
toggle 1
check 1
toggle 1
check 1
예제 출력 1  복사
1
1
0
1
0
1
0
1
0
1
1
0
0
0
1
0
 */
 
package silver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

 public class P11723 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        Set<Integer> S = new HashSet<Integer>();
        for (int i = 0; i < N; i++) {
            String[] st = br.readLine().split(" ");

            if (st[0].equals("add")) {
                S.add(Integer.parseInt(st[1]));
            } else if (st[0].equals("remove")) {
                S.remove(Integer.parseInt(st[1]));
            } else if (st[0].equals("check")) {
                if (S.contains(Integer.parseInt(st[1]))) {
                    bw.write(1 + "\n");
                } else {
                    bw.write(0 + "\n");
                }
            } else if (st[0].equals("toggle")) {
                if (S.contains(Integer.parseInt(st[1]))) {
                    S.remove(Integer.parseInt(st[1]));
                } else {
                    S.add(Integer.parseInt(st[1]));
                }
            } else if (st[0].equals("all")) {
                for (int j = 1; j < 21; j++) {
                    S.add(j);
                }
            } else if (st[0].equals("empty")) {
                S.clear();
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }
 }