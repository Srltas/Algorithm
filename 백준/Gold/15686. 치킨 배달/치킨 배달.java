import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static int[][] home;
  static int[][] chicken;
  static int[][] chickenHouse;
  static int cityMinChicken = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      List<int[]> tempHome = new ArrayList<>();
      List<int[]> tempChicken = new ArrayList<>();

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
           int n = Integer.parseInt(st.nextToken());
           if (n == 1) {
             tempHome.add(new int[]{i ,j});
           } else if (n == 2) {
             tempChicken.add(new int[]{i, j});
           }
        }
      }

      home = new int[tempHome.size()][2];
      chicken = new int[tempChicken.size()][2];
      for (int i = 0; i < tempHome.size(); i++) home[i] = tempHome.get(i);
      for (int i = 0; i < tempChicken.size(); i++) chicken[i] = tempChicken.get(i);

      chickenHouse = new int[M][2];
      dfs(0, 0);
      System.out.println(cityMinChicken);
    }
  }

  static void dfs(int index, int start) {
    if (index == M) {
      cityMinChicken = Math.min(cityMinChicken, minPath());
      return;
    }

    for (int i = start; i < chicken.length; i++) {
      chickenHouse[index] = chicken[i];
      dfs(index + 1, i + 1);
    }
  }

  static int minPath() {
    int totalCityDistance = 0;
    for (int i = 0; i < home.length; i++) {
      int minForOneHome = Integer.MAX_VALUE;
      for (int[] cPoint : chickenHouse) {
        int dist = Math.abs(home[i][0] - cPoint[0]) + Math.abs(home[i][1] - cPoint[1]);
        minForOneHome = Math.min(minForOneHome, dist);
      }
      totalCityDistance += minForOneHome;
   }
    return totalCityDistance;
  }
}
