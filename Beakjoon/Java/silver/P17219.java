package silver;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P17219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, String> sites = new HashMap<String, String>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String key = st.nextToken();
            String value = st.nextToken();
            sites.put(key, value);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < M; i++) {
            bw.write(sites.get(br.readLine()));
            bw.newLine();
        }

        bw.flush();
        br.close();
        bw.close();

    }
}
