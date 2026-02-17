import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        String type = st.nextToken();

        int perGame = (type.equals("Y")) ? 1 : (type.equals("F") ? 2 : 3);

        Set<String> players = new HashSet<>();
        for (int i = 0; i < N; i++) players.add(br.readLine());
        System.out.println(players.size() / perGame);
    }
}
