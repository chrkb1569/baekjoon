import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MagicianShark_RainDance {
    private static int[][] resultArr;
    private static Queue<int[]> cloudQueue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 격자의 크기 2 <= N <= 50
        int M = Integer.parseInt(st.nextToken()); // 명령의 횟수 1 <= M <= 100

        initResultArr(N);
        initCloudQueue();

        // 초기 상태 형성
        for(int height = 0; height < resultArr.length; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, height);
        }

        // 사용자로부터 d, s 입력받음
        for(int command = 0; command < M; command++) {
            st = new StringTokenizer(br.readLine(), " ");

            int d = Integer.parseInt(st.nextToken()); // 구름이 이동해야하는 방향
            int s = Integer.parseInt(st.nextToken()); // 구름이 이동하는 거리

            processCommand(d, s);
        }

        // 전체 배열에 저장된 물의 양을 합함
        int totalSum = getTotalSum();

        // 결과값 출력
        System.out.println(totalSum);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length][length];
    }

    private static void initCloudQueue() {
        int N = resultArr.length;

        cloudQueue.add(new int[]{N - 1, 0});
        cloudQueue.add(new int[]{N - 1, 1});
        cloudQueue.add(new int[]{N - 2, 0});
        cloudQueue.add(new int[]{N - 2, 1});
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    // 사용자로부터 입력된 구름이 이동하는 방향, 거리를 통하여 프로세스 처리
    private static void processCommand(int direction, int distance) {
        moveCloud(direction, distance); // 구름 이동
        startRain(); // 바구니 물 증가 (비)
        increaseBucketWater(); // 바구니 물 증가 (주변 바구니 상태 체크)
        makeClouds(); // 구름 생성
    }

    private static void moveCloud(int direction, int distance) {
        int cloudCount = cloudQueue.size();

        for(int count = 0; count < cloudCount; count++) {
            int[] cloud = cloudQueue.poll();

            int[] movedCloud = moveCloudByDirection(cloud, direction, distance);

            cloudQueue.add(movedCloud);
        }
    }

    private static int[] moveCloudByDirection(int[] cloudArr, int direction, int distance) {
        if(direction == 1) return moveDirection1(cloudArr, distance);
        if(direction == 2) return moveDirection2(cloudArr, distance);
        if(direction == 3) return moveDirection3(cloudArr, distance);
        if(direction == 4) return moveDirection4(cloudArr, distance);
        if(direction == 5) return moveDirection5(cloudArr, distance);
        if(direction == 6) return moveDirection6(cloudArr, distance);
        if(direction == 7) return moveDirection7(cloudArr, distance);
        return moveDirection8(cloudArr, distance);
    }

    // 서쪽
    private static int[] moveDirection1(int[] cloudArr, int distance) {
        distance %= resultArr.length;

        int x = cloudArr[1];

        x -= distance;

        if(x < 0) x += resultArr.length;

        cloudArr[1] = x;

        return cloudArr;
    }

    // 북서쪽
    private static int[] moveDirection2(int[] cloudArr, int distance) {
        int x = cloudArr[1];
        int y = cloudArr[0];

        for(int currentDistance = 0; currentDistance < distance; currentDistance++) {
            x--;
            y--;

            if(x < 0) x += resultArr.length;
            if(y < 0) y += resultArr.length;
        }

        cloudArr[0] = y;
        cloudArr[1] = x;

        return cloudArr;
    }

    // 북쪽
    private static int[] moveDirection3(int[] cloudArr, int distance) {
        distance %= resultArr.length;

        int y = cloudArr[0];

        y -= distance;

        if(y < 0) y += resultArr.length;

        cloudArr[0] = y;

        return cloudArr;
    }

    // 북동쪽
    private static int[] moveDirection4(int[] cloudArr, int distance) {
        int x = cloudArr[1];
        int y = cloudArr[0];

        for(int currentDistance = 0; currentDistance < distance; currentDistance++) {
            x++;
            y--;

            if(x >= resultArr.length) x -= resultArr.length;
            if(y < 0) y += resultArr.length;
        }

        cloudArr[0] = y;
        cloudArr[1] = x;

        return cloudArr;
    }

    // 동쪽
    private static int[] moveDirection5(int[] cloudArr, int distance) {
        distance %= resultArr.length;

        int x = cloudArr[1];

        x += distance;

        if(x >= resultArr.length) x -= resultArr.length;

        cloudArr[1] = x;

        return cloudArr;
    }

    // 남동쪽
    private static int[] moveDirection6(int[] cloudArr, int distance) {
        int x = cloudArr[1];
        int y = cloudArr[0];

        for(int currentDistance = 0; currentDistance < distance; currentDistance++) {
            x++;
            y++;

            if(x >= resultArr.length) x -= resultArr.length;
            if(y >= resultArr.length) y -= resultArr.length;
        }

        cloudArr[0] = y;
        cloudArr[1] = x;

        return cloudArr;
    }

    // 남쪽
    private static int[] moveDirection7(int[] cloudArr, int distance) {
        distance %= resultArr.length;

        int y = cloudArr[0];

        y += distance;

        if(y >= resultArr.length) y -= resultArr.length;

        cloudArr[0] = y;

        return cloudArr;
    }

    // 남서쪽
    private static int[] moveDirection8(int[] cloudArr, int distance) {
        int x = cloudArr[1];
        int y = cloudArr[0];

        for(int currentDistance = 0; currentDistance < distance; currentDistance++) {
            x--;
            y++;

            if(y >= resultArr.length) y -= resultArr.length;
            if(x < 0) x += resultArr.length;
        }

        cloudArr[0] = y;
        cloudArr[1] = x;

        return cloudArr;
    }

    // 구름이 위치한 지역은 비가 내림, 양동이의 물 1 증가
    private static void startRain() {
        for(int[] arr : cloudQueue) {
            int y = arr[0];
            int x = arr[1];

            resultArr[y][x]++;
        }
    }

    // Queue에 저장된 모든 구름 정보 삭제
    private static void removeClouds() {
        cloudQueue.clear();
    }

    // 대각선 방향에 위치한 양동이 중 물의 양이 1이 넘는 양동이의 수 만큼 물 증가
    private static void increaseBucketWater() {
        for(int[] arr : cloudQueue) {
            int y = arr[0];
            int x = arr[1];

            if(x-1 >= 0 && y-1 >= 0 && resultArr[y-1][x-1] >= 1) resultArr[y][x]++;
            if(x+1 < resultArr.length && y-1 >= 0 && resultArr[y-1][x+1] >= 1) resultArr[y][x]++;
            if(x-1 >= 0 && y+1 < resultArr.length && resultArr[y+1][x-1] >= 1) resultArr[y][x]++;
            if(x+1 < resultArr.length && y+1 < resultArr.length && resultArr[y+1][x+1] >= 1) resultArr[y][x]++;
        }
    }

    // 기존에 구름이 생성된 지점을 제외하고 구름 생성
    private static void makeClouds() {
        int initialSize = cloudQueue.size();

        for(int[] arr : cloudQueue) {
            int y = arr[0];
            int x = arr[1];

            resultArr[y][x] -= 1_000_000;
        }

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr.length; width++) {
                int currentValue = resultArr[height][width];

                if(currentValue < 2) continue;

                resultArr[height][width] -= 2;

                cloudQueue.add(new int[]{height, width});
            }
        }

        for(int size = 0; size < initialSize; size++) {
            int[] cloudArr = cloudQueue.poll();

            int y = cloudArr[0];
            int x = cloudArr[1];

            resultArr[y][x] += 1_000_000;
        }
    }

    // 최종합 계산
    private static int getTotalSum() {
        int sum = 0;

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr.length; width++) {
                sum += resultArr[height][width];
            }
        }

        return sum;
    }
}
