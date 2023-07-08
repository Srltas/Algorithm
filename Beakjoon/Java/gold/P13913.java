/**
 *
 * URL : https://www.acmicpc.net/problem/13913
 *
 * 숨바꼭질 4
 *
 * 문제
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
 *
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 *
 * 출력
 * 첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 *
 * 둘째 줄에 어떻게 이동해야 하는지 공백으로 구분해 출력한다.
 *
 * 예제 입력 1
 * 5 17
 * 예제 출력 1
 * 4
 * 5 10 9 18 17
 * 예제 입력 2
 * 5 17
 * 예제 출력 2
 * 4
 * 5 4 8 16 17
 */

package gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class P13913 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  final int MAX_RANGE = 100_001;

  int N, K;
  boolean[] visited = new boolean[MAX_RANGE];

  public static void main(String[] args) throws IOException {
      new P13913().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    Stack stack = BFS(N, K);
    bw.write(stack.size() - 1 + System.lineSeparator());
    while (!stack.isEmpty()) {
      bw.write(stack.pop() + " ");
    }
    bw.flush();

    bw.close();
    br.close();
  }

  private Stack BFS(int start, int end) {
    Stack stack = new Stack();
    Queue<Node> queue = new LinkedList<>();
    queue.offer(new Node(start,0));
    visited[start] = true;

    while (!queue.isEmpty()) {
      Node currNode = queue.poll();

      if (currNode.x == end) {
        stack = getMovePath(currNode);
      }

      if (checkRange(currNode.x + 1)) {
        queue.offer(new Node(currNode.x + 1, currNode, currNode.count + 1));
        visited[currNode.x + 1] = true;
      }

      if (checkRange(currNode.x - 1)) {
        queue.offer(new Node(currNode.x - 1, currNode, currNode.count));
        visited[currNode.x - 1] = true;
      }

      if (checkRange(currNode.x * 2)) {
        queue.offer(new Node(currNode.x * 2, currNode, currNode.count));
        visited[currNode.x * 2] = true;
      }
    }
    return stack;
  }

  private Stack getMovePath(Node node) {
    Stack<Integer> stack = new Stack<>();
    while (node.x != node.preNode.x) {
      stack.push(node.x);
      node = node.preNode;
    }
    stack.push(node.x);

    return stack;
  }

  private boolean checkRange(int x) {
    return x >= 0 && x < MAX_RANGE && !visited[x];
  }

  static class Node {
    int x;
    Node preNode;
    int count;

    public Node(int x, int count) {
      this.x = x;
      this.count = count;
      this.preNode = this;
    }

    public Node(int x, Node preNode, int count) {
      this.x = x;
      this.preNode = preNode;
      this.count = count;
    }
  }
}
