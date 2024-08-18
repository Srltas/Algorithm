/**
 * URL : https://school.programmers.co.kr/learn/courses/30/lessons/67256?language=java
 */

package kakao_internship_2020;

public class P67256 {
  public static void main(String[] args) {
    String result1 = new P67256().solution(new int[] {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right");
    String result2 = new P67256().solution(new int[] {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "left");
    String result3 = new P67256().solution(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, "right");

    System.out.println(result1);
    System.out.println(result2);
    System.out.println(result3);
  }

  int[][] keypad = {
      {3, 1}, // 0
      {0, 0}, // 1
      {0, 1}, // 2
      {0, 2}, // 3
      {1, 0}, // 4
      {1, 1}, // 5
      {1, 2}, // 6
      {2, 0}, // 7
      {2, 1}, // 8
      {2, 2}};// 9
  int[] rightHand = {3, 0};
  int[] leftHand = {3, 2};
  String mainHand;

  public String solution(int[] numbers, String hand) {
    mainHand = hand.equals("right") ? "R" : "L";

    StringBuilder answer = new StringBuilder();
    for (int number : numbers) {
      String clickHand = clickKeypad(number);
      answer.append(clickHand);

      if (clickHand.equals("R")) {
        rightHand = keypad[number];
      } else {
        leftHand = keypad[number];
      }
    }
    return answer.toString();
  }

  public String clickKeypad(int number) {
    if (number==1 || number==4 || number==7) return "L";
    if (number==3 || number==6 || number==9) return "R";

    int leftDistance = getDistance(leftHand, number);
    int rightDistance = getDistance(rightHand, number);

    if (leftDistance > rightDistance) return "R";
    if (leftDistance < rightDistance) return "L";

    return mainHand;
  }

  public int getDistance(int[] hand, int number) {
    return Math.abs(hand[0] - keypad[number][0]) + Math.abs(hand[1] - keypad[number][1]);
  }
}
