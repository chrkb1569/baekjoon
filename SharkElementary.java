import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SharkElementary {
    private static int[][] resultArr;
    private static int[][] personArr;
    private static List<Integer> resultList = new ArrayList<>();
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);
        initPersonArr(N);

        for(int person = 0; person < N * N; person++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultList(st);
        }

        mkResultArr();

        int point = addPoint();

        System.out.println(point);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length][length];
    }

    private static void initPersonArr(int length) {
        personArr = new int[length * length][4];
    }

    private static void mkResultList(StringTokenizer st) {
        int personNumber = Integer.parseInt(st.nextToken());
        int index = 0;

        for(;st.hasMoreTokens();) {
            personArr[personNumber-1][index++] = Integer.parseInt(st.nextToken());
        }

        resultList.add(personNumber);
    }

    private static void mkResultArr() {
        for(int personNumber : resultList) {
            int[] indexArr = findIndex(personNumber);

            int y = indexArr[0];
            int x = indexArr[1];

            resultArr[y][x] = personNumber;
        }
    }

    private static int[] findIndex(int personNumber) {
        int friend1 = personArr[personNumber-1][0];
        int friend2 = personArr[personNumber-1][1];
        int friend3 = personArr[personNumber-1][2];
        int friend4 = personArr[personNumber-1][3];
        int maxFriend = -1;
        int maxEmpty = -1;
        int xValue = 0;
        int yValue = 0;

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr.length; width++) {
                if(resultArr[height][width] != 0) continue;

                int friendCount = 0;
                int emptyCount = 0;

                for(int i = 0; i < 4; i++) {
                    int y = height + dy[i];
                    int x = width + dx[i];

                    if(x < 0 || y < 0 || x >= resultArr.length || y >= resultArr.length) continue;
                    int value = resultArr[y][x];
                    if(value == 0) {
                        emptyCount++;
                        continue;
                    }
                    if(value == friend1 || value == friend2 || value == friend3 || value == friend4) {
                        friendCount++;
                    }
                }

                if(friendCount > maxFriend) {
                    maxFriend = friendCount;
                    maxEmpty = emptyCount;
                    xValue = width;
                    yValue = height;
                    continue;
                }

                if(friendCount == maxFriend && emptyCount > maxEmpty) {
                    maxEmpty = emptyCount;
                    xValue = width;
                    yValue = height;
                }
            }
        }

        return new int[]{yValue, xValue};
    }

    private static int addPoint() {
        int point = 0;

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr.length; width++) {
                int personNumber = resultArr[height][width] - 1;

                int friend1 = personArr[personNumber][0];
                int friend2 = personArr[personNumber][1];
                int friend3 = personArr[personNumber][2];
                int friend4 = personArr[personNumber][3];

                int personCount = 0;

                for(int i = 0; i < 4; i++) {
                    int x = width + dx[i];
                    int y = height + dy[i];

                    if(x < 0 || y < 0 || x >= resultArr.length || y >= resultArr.length) continue;

                    int value = resultArr[y][x];

                    if(value == friend1 || value == friend2 || value == friend3 || value == friend4) personCount++;
                }

                if(personCount <= 0) continue;

                point += Math.pow(10, personCount-1);
            }
        }

        return point;
    }
}