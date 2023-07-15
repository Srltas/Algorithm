/**
 *
 * URL : https://www.acmicpc.net/problem/5639
 *
 * 이진 검색 트리
 *
 * 문제
 * 이진 검색 트리는 다음과 같은 세 가지 조건을 만족하는 이진 트리이다.
 *
 * 노드의 왼쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 작다.
 * 노드의 오른쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 크다.
 * 왼쪽, 오른쪽 서브트리도 이진 검색 트리이다.
 *
 *
 * 전위 순회 (루트-왼쪽-오른쪽)은 루트를 방문하고, 왼쪽 서브트리, 오른쪽 서브 트리를 순서대로 방문하면서 노드의 키를 출력한다. 후위 순회 (왼쪽-오른쪽-루트)는 왼쪽 서브트리, 오른쪽 서브트리, 루트 노드 순서대로 키를 출력한다. 예를 들어, 위의 이진 검색 트리의 전위 순회 결과는 50 30 24 5 28 45 98 52 60 이고, 후위 순회 결과는 5 28 24 45 30 60 52 98 50 이다.
 *
 * 이진 검색 트리를 전위 순회한 결과가 주어졌을 때, 이 트리를 후위 순회한 결과를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 트리를 전위 순회한 결과가 주어진다. 노드에 들어있는 키의 값은 106보다 작은 양의 정수이다. 모든 값은 한 줄에 하나씩 주어지며, 노드의 수는 10,000개 이하이다. 같은 키를 가지는 노드는 없다.
 *
 * 출력
 * 입력으로 주어진 이진 검색 트리를 후위 순회한 결과를 한 줄에 하나씩 출력한다.
 *
 * 예제 입력 1
 * 50
 * 30
 * 24
 * 5
 * 28
 * 45
 * 98
 * 52
 * 60
 * 예제 출력 1
 * 5
 * 28
 * 24
 * 45
 * 30
 * 60
 * 52
 * 98
 * 50
 */

package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class P5639 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
      new P5639().solution();
  }

  public void solution() throws IOException {
    BinarySearchTree binarySearchTree = new BinarySearchTree();

    while (true) {
      String str = br.readLine();
      if (str == null || str.equals("")) {
        break;
      }
      binarySearchTree.insert(Integer.parseInt(str));
    }

    List<Integer> list = binarySearchTree.postOrder();
    for (int n : list) {
      bw.write(n + System.lineSeparator());
    }
    bw.flush();

    bw.close();
    br.close();
  }



  static class BinarySearchTree {
    private Node root;
    private List<Integer> resultList = new ArrayList<>();

    public List<Integer> postOrder() {
      postOrder(root);
      return this.resultList;
    }

    private void postOrder(Node node) {
      if (node != null) {
        postOrder(node.left);
        postOrder(node.right);
        resultList.add(node.value);
      }
    }
    public boolean insert(int value) {
      Node newNode = new Node(value);
      if (find(value)) {
        return false;
      }

      if (root == null) {
        root = newNode;
        return true;
      }

      Node currentNode = root;
      Node parent;
      while (true) {
        parent = currentNode;
        if (value < currentNode.getValue()) {
          currentNode = currentNode.getLeft();
          if (currentNode == null) {
            parent.setLeft(newNode);
            return true;
          }
        } else {
          currentNode = currentNode.getRight();
          if (currentNode == null) {
            parent.setRight(newNode);
            return true;
          }
        }
      }
    }

    private boolean find(int value) {
      Node currentNode = root;
      while (currentNode != null) {
        if (currentNode.getValue() == value) {
          return true;
        } else if (currentNode.getValue() > value) {
          currentNode = currentNode.getLeft();
        } else {
          currentNode = currentNode.getRight();
        }
      }
      return false;
    }
  }

  static class Node {
    Node left;
    Node right;
    int value;

    public Node(int value) {
      this.value = value;
    }

    public Node getLeft() {
      return left;
    }

    public void setLeft(Node left) {
      this.left = left;
    }

    public Node getRight() {
      return right;
    }

    public void setRight(Node right) {
      this.right = right;
    }

    public int getValue() {
      return value;
    }

    public void setValue(int value) {
      this.value = value;
    }
  }
}
