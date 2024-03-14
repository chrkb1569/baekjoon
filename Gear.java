import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gear {
    private static char[][] gearArr = new char[4][8];
    private static int FINAL_SCORE = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N -> 0, S -> 1
        for(int gearInex = 0; gearInex < 4; gearInex++) {
            char[] gearInfo = br.readLine().toCharArray();
            mkGearArr(gearInex, gearInfo);
        }

        // 회전의 수
        int K = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < K; testCase++) {
            st = new StringTokenizer(br.readLine(), " ");

            int gearNumber = Integer.parseInt(st.nextToken());
            int commandType = Integer.parseInt(st.nextToken());

            processCommand(gearNumber, commandType);
        }

        addPoint();

        System.out.println(FINAL_SCORE);
    }

    private static void mkGearArr(int gearIndex, char[] gearInfo) {
        gearArr[gearIndex] = gearInfo;
    }

    private static void processCommand(int gearNumber, int type) {
        char firstGearRightPoint = gearArr[0][2];

        char secondGearLeftPoint = gearArr[1][6];
        char secondGearRightPoint = gearArr[1][2];

        char thirdGearLeftPoint = gearArr[2][6];
        char thirdGearRightPoint = gearArr[2][2];

        char forthGearLeftPoint = gearArr[3][6];

        int revType = (type == 1)? -1 : 1;

        if(gearNumber == 1) {
            circulateGear(gearNumber, type);

            boolean second = false;
            boolean third = false;

            if(firstGearRightPoint != secondGearLeftPoint) {
                second = true;
                circulateGear(2, revType);
            }

            if(second && secondGearRightPoint != thirdGearLeftPoint) {
                third = true;
                circulateGear(3, type);
            }

            if(third && thirdGearRightPoint != forthGearLeftPoint) {
                circulateGear(4, revType);
            }

            return;
        }

        if(gearNumber == 2) {
            circulateGear(gearNumber, type);

            boolean third = false;

            if(secondGearLeftPoint != firstGearRightPoint) {
                circulateGear(1, revType);
            }

            if(secondGearRightPoint != thirdGearLeftPoint) {
                third = true;
                circulateGear(3, revType);
            }

            if(third && thirdGearRightPoint != forthGearLeftPoint) {
                circulateGear(4, type);
            }

            return;
        }

        if(gearNumber == 3) {
            circulateGear(gearNumber, type);

            boolean second = false;

            if(thirdGearLeftPoint != secondGearRightPoint) {
                second = true;
                circulateGear(2, revType);
            }

            if(thirdGearRightPoint != forthGearLeftPoint) {
                circulateGear(4, revType);
            }

            if(second && firstGearRightPoint != secondGearLeftPoint) {
                circulateGear(1, type);
            }

            return;
        }

        if(gearNumber == 4) {
            circulateGear(gearNumber, type);

            boolean second = false;
            boolean third = false;

            if(forthGearLeftPoint != thirdGearRightPoint) {
                third = true;
                circulateGear(3, revType);
            }

            if(third && thirdGearLeftPoint != secondGearRightPoint) {
                second = true;
                circulateGear(2, type);
            }

            if(second && secondGearLeftPoint != firstGearRightPoint) {
                circulateGear(1, revType);
            }
        }
    }

    private static void circulateGear(int gearNumber, int type) {
        if(type == 1) {
            circulateRight(gearNumber);
            return;
        }

        circulateLeft(gearNumber);
    }

    // 시계 방향, type == 1
    private static void circulateRight(int gearNumber) {
        char[] gearInfo = gearArr[gearNumber-1];

        char tmp = gearInfo[7];

        for(int index = 7; index > 0; index--) {
            gearInfo[index] = gearInfo[index-1];
        }

        gearInfo[0] = tmp;
    }

    // 반시계 방향, type == -1
    private static void circulateLeft(int gearNumber) {
        char[] gearInfo = gearArr[gearNumber-1];

        char tmp = gearInfo[0];

        for(int index = 0; index < gearInfo.length-1; index++) {
            gearInfo[index] = gearInfo[index+1];
        }

        gearInfo[7] = tmp;
    }

    private static void addPoint() {
        if(gearArr[0][0] == '1') FINAL_SCORE += 1;
        if(gearArr[1][0] == '1') FINAL_SCORE += 2;
        if(gearArr[2][0] == '1') FINAL_SCORE += 4;
        if(gearArr[3][0] == '1') FINAL_SCORE += 8;
    }
}