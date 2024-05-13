import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N -> 0 S -> 1
// 1-> right -1 -> left
public class Gear_2 {
    private static Gear[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // gear

        initResultArr(T);

        for(int gear = 0; gear < T; gear++) {
            String gearStatus = br.readLine();
            resultArr[gear] = new Gear(gearStatus);
        }

        int K = Integer.parseInt(br.readLine()); // testCase

        for(int testCase = 0; testCase < K; testCase++) {
            st = new StringTokenizer(br.readLine(), " ");

            int gearNumber = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken()); // 1 -> right, 0 -> left

            processCommand(gearNumber, type);
        }

        printGearCount();
    }

    private static void initResultArr(int gear) {
        resultArr = new Gear[gear];
    }

    private static void processCommand(int gearNumber, int type) {
        Gear gear = resultArr[gearNumber - 1];

        String[] gearArr = gear.getGearArr();

        String stdEast = gearArr[2];
        String stdWest = gearArr[6];
        int flag = type;

        // type == 1 시계 방향으로 회전
        if(type == 1) {
            gear.circulateRight();
            flag = -1;
        }

        // type == -1 반시계 방향으로 회전
        if(type == -1) {
            gear.circulateLeft();
            flag = 1;
        }

        int flagForRight = flag;
        int flagForLeft = flag;

        String eastPoint = stdEast;
        String westPoint = stdWest;

        // 기어의 상태를 먼저 판단하고, 회전을 시킬지 결정하는 로직으로 설계해야함
        // 회전하는 기어의 오른쪽 상태 확인
        for(int index = gearNumber; index < resultArr.length; index++) {
            Gear nextGear = resultArr[index];

            String[] arr = nextGear.getGearArr();

            String nextEastPoint = arr[2];
            String nextWestPoint = arr[6];

            if(eastPoint.equals(nextWestPoint)) break;

            eastPoint = nextEastPoint;

            if(flagForRight == 1) {
                nextGear.circulateRight();
                flagForRight = -1;
                continue;
            }

            if(flagForRight == -1) {
                nextGear.circulateLeft();
                flagForRight = 1;
            }
        }

        // 회전하는 기어의 왼쪽 상태 확인
        for(int index = gearNumber-2; index >= 0; index--) {
            Gear nextGear = resultArr[index];

            String[] arr = nextGear.getGearArr();

            String nextEastPoint = arr[2];
            String nextWestPoint = arr[6];

            if(westPoint.equals(nextEastPoint)) break;

            westPoint = nextWestPoint;

            if(flagForLeft == 1) {
                flagForLeft = -1;
                nextGear.circulateRight();
                continue;
            }

            if(flagForLeft == -1) {
                flagForLeft = 1;
                nextGear.circulateLeft();
            }
        }
    }

    private static void printGearCount() {
        int gearCount = 0;

        for(int index = 0; index < resultArr.length; index++) {
            Gear gear = resultArr[index];

            String northPoint = gear.getGearArr()[0];

            if(northPoint.equals("1")) gearCount++;
        }

        System.out.println(gearCount);
    }

    private static class Gear {
        private String[] gearArr = new String[8];

        public Gear(String gearStatus) {
            String[] statusArr = gearStatus.split("");

            for(int index = 0; index < 8; index++) {
                gearArr[index] = statusArr[index];
            }
        }

        public String[] getGearArr() {
            return this.gearArr;
        }

        // 시계 방향 회전
        public void circulateRight() {
            String bin = gearArr[7];

            for(int index = gearArr.length-1; index > 0; index--) {
                gearArr[index] = gearArr[index-1];
            }

            gearArr[0] = bin;
        }

        // 반시계 방향 회전
        public void circulateLeft() {
            String bin = gearArr[0];

            for(int index = 0; index < gearArr.length - 1; index++) {
                gearArr[index] = gearArr[index+1];
            }

            gearArr[7] = bin;
        }

    }
}
