import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  StringTokenizer st;

  int N, K;
  List<Nation> nations = new ArrayList<>();
  Nation selectNation;

  public static void main(String[] args) throws IOException {
    new Main().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int code = Integer.parseInt(st.nextToken());
      int gold = Integer.parseInt(st.nextToken());
      int silver = Integer.parseInt(st.nextToken());
      int bronze = Integer.parseInt(st.nextToken());

      Nation nation = new Nation(code, gold, silver, bronze);
      if (code == K) {
        selectNation = nation;
      }
      nations.add(nation);
    }

    nations.sort(null);

    for (int i = 0; i < N; i++) {
      if (sameCountMedal(nations.get(i), selectNation)) {
        System.out.println(i + 1);
        break;
      }
    }
    br.close();
  }

  private boolean sameCountMedal(Nation beforeNation, Nation selectNation) {
    return beforeNation.gold == selectNation.gold
        && beforeNation.silver == selectNation.silver
        && beforeNation.bronze == selectNation.bronze;
  }

  static class Nation implements Comparable<Nation> {
    int code;
    int gold;
    int silver;
    int bronze;

    public Nation(int code, int gold, int silver, int bronze) {
      this.code = code;
      this.gold = gold;
      this.silver = silver;
      this.bronze = bronze;
    }

    public int compareTo(Nation o) {
      if (this.gold == o.gold) {
        if (this.silver == o.silver) {
          return o.bronze - this.bronze;
        }
        return o.silver - this.silver;
      }
      return o.gold - this.gold;
    }
  }
}
