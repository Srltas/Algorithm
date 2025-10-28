import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};

    static int N;
    static int[][] room;
    static Student[] students;
    static Map<Integer, Set<Integer>> friendsMap;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            int studentCount = N * N;
            students = new Student[studentCount];
            friendsMap = new HashMap<>();

            for (int i = 0; i < studentCount; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                Set<Integer> fSet = new HashSet<>();
                for (int j = 0; j < 4; j++) {
                    fSet.add(Integer.parseInt(st.nextToken()));
                }
                students[i] = new Student(n, fSet);
                friendsMap.put(n, fSet);
            }

            room = new int[N+1][N+1];
            for (Student s : students) {
                Candidate bestSeat = findBestSeat(s.friendsSet);
                room[bestSeat.r][bestSeat.c] = s.num;
            }

            System.out.println(satisfaction());
        }
    }

    static Candidate findBestSeat(Set<Integer> friends) {
        List<Candidate> candidates = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (room[i][j] != 0) continue;

                int friendCount = 0;
                int emptyCount = 0;

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (outRoom(nx, ny)) continue;

                    if (room[nx][ny] == 0) {
                        emptyCount++;
                    } else if (friends.contains(room[nx][ny])) {
                        friendCount++;
                    }
                }
                candidates.add(new Candidate(i, j, friendCount, emptyCount));
            }
        }
        Collections.sort(candidates);
        return candidates.get(0);
    }

    static int satisfaction() {
        int total = 0;
        int[] scores = {0, 1, 10, 100, 1000};

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int sId = room[i][j];
                Set<Integer> myFriends = friendsMap.get(sId);

                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (outRoom(nx, ny)) continue;
                    if (myFriends.contains(room[nx][ny])) count++;
                }
                total += scores[count];
            }
        }
        return total;
    }

    static class Candidate implements Comparable<Candidate> {
        int r, c;
        int friendCount;
        int emptyCount;

        public Candidate(int r, int c, int friendCount, int emptyCount) {
            this.r = r;
            this.c = c;
            this.friendCount = friendCount;
            this.emptyCount = emptyCount;
        }

        @Override
        public int compareTo(Candidate other) {
            // 1. 좋아하는 학생 수 (내림차순)
            if (this.friendCount != other.friendCount) {
                return Integer.compare(other.friendCount, this.friendCount);
            }
            // 2. 비어있는 칸 수 (내림차순)
            if (this.emptyCount != other.emptyCount) {
                return Integer.compare(other.emptyCount, this.emptyCount);
            }
            // 3. 행 번호 (오름차순)
            if (this.r != other.r) {
                return Integer.compare(this.r, other.r);
            }
            // 4. 열 번호 (오름차순)
            return Integer.compare(this.c, other.c);
        }
    }

    static boolean outRoom(int x, int y) {
        return x < 1 || y < 1 || x > N || y > N;
    }

    static class Student {
        int num;
        Set<Integer> friendsSet;

        public Student(int num, Set<Integer> friendsSet) {
            this.num = num;
            this.friendsSet = friendsSet;
        }
    }
}
