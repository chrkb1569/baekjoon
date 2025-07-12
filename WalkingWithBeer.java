import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class WalkingWithBeer {

    private static int[][] HOME;
    private static int[][] INDEX;
    private static int[][] FESTIVAL;

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // testCase 1 <= T <= 50

        for(int testCase = 0; testCase < T; testCase++) {
            int n = Integer.parseInt(br.readLine()); // 편의점의 수 0 <= n <= 100

            initHome();
            initIndex(n);
            initFestival();

            st = new StringTokenizer(br.readLine(), " "); // 집의 좌표

            int homeX = Integer.parseInt(st.nextToken());
            int homeY = Integer.parseInt(st.nextToken());

            setHome(homeX, homeY);

            for(int convenientStore = 0; convenientStore < n; convenientStore++) {
                st = new StringTokenizer(br.readLine(), " ");

                int convenientX = Integer.parseInt(st.nextToken());
                int convenientY = Integer.parseInt(st.nextToken());

                setIndex(convenientStore, convenientX, convenientY);
            }

            st = new StringTokenizer(br.readLine(), " "); // 축제 장소의 좌표

            int festivalX = Integer.parseInt(st.nextToken());
            int festivalY = Integer.parseInt(st.nextToken());

            setFestival(festivalX, festivalY);

            String result = checkResult();
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

    private static void initHome() {
        HOME = new int[1][2];
    }

    private static void initIndex(int length) {
        INDEX = new int[length + 1][2];
    }

    private static void initFestival() {
        FESTIVAL = new int[1][2];
    }

    private static void setHome(int x, int y) {
        HOME[0][0] = y;
        HOME[0][1] = x;
    }

    private static void setIndex(int index, int x, int y) {
        INDEX[index][0] = y;
        INDEX[index][1] = x;
    }

    private static void setFestival(int x, int y) {
        FESTIVAL[0][0] = y;
        FESTIVAL[0][1] = x;

        INDEX[INDEX.length - 1][0] = y;
        INDEX[INDEX.length - 1][1] = x;
    }

    private static String checkResult() {
        if(checkValidation()) return "happy";
        return "sad";
    }

    private static boolean checkValidation() {
        Queue<int[]> resultQueue = new LinkedList<>();
        boolean[] visitArr = new boolean[INDEX.length];

        int startY = HOME[0][0];
        int startX = HOME[0][1];

        int targetY = FESTIVAL[0][0];
        int targetX = FESTIVAL[0][1];

        int initialDistance = getDistance(startX, startY, targetX, targetY);
        if(initialDistance <= 1_000) return true;

        resultQueue.add(new int[]{startY, startX}); // y, x

        while(!resultQueue.isEmpty()) {
            int[] current = resultQueue.poll();

            int y = current[0];
            int x = current[1];

            if(y == targetY && x == targetX) return true;

            for(int index = 0; index < INDEX.length; index++) {
                int[] store = INDEX[index];

                int storeY = store[0];
                int storeX = store[1];

                if(visitArr[index]) continue;

                int distance = getDistance(x, y, storeX, storeY);
                if(distance > 1_000) continue;

                visitArr[index] = true;
                resultQueue.add(new int[]{storeY, storeX});
            }
        }

        return false;
    }

    private static int getDistance(int fromX, int fromY, int toX, int toY) {
        return Math.abs(toX - fromX) + Math.abs(toY - fromY);
    }
}