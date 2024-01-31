import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dice_Game {
    private static int[][] resultArr;
    private static int[] diceArr = {0, 0, 0, 0, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {1, -1, 0, 0};
    private static StringBuilder sb = new StringBuilder();
    private static int x;
    private static int y;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        initResultArr(height, width);

        for(int i = 0; i< height; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        st = new StringTokenizer(br.readLine(), " ");

        for(;st.hasMoreTokens();) {
            int command = Integer.parseInt(st.nextToken()) - 1;

            x += dx[command];
            y += dy[command];

            if(x < 0 || x >= resultArr.length || y < 0 || y >= resultArr[0].length) {
                x -= dx[command];
                y -= dy[command];
                continue;
            }

            processCommand(command);
        }

        System.out.println(sb);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void processCommand(int command) {
        if(command == 0) moveEast();
        if(command == 1) moveWest();
        if(command == 2) moveNorth();
        if(command == 3) moveSouth();

        if(resultArr[x][y] == 0) {
            resultArr[x][y] = diceArr[1];
        }
        else {
            diceArr[1] = resultArr[x][y];
            resultArr[x][y] = 0;
        }

        sb.append(diceArr[0]).append("\n");
    }

    public static void moveEast() {
        int tmp = diceArr[0];

        diceArr[0] = diceArr[4];
        diceArr[4] = diceArr[1];
        diceArr[1] = diceArr[5];
        diceArr[5] = tmp;
    }

    public static void moveWest() {
        int tmp = diceArr[0];

        diceArr[0] = diceArr[5];
        diceArr[5] = diceArr[1];
        diceArr[1] = diceArr[4];
        diceArr[4] = tmp;
    }

    public static void moveNorth() {
        int tmp = diceArr[0];

        diceArr[0] = diceArr[2];
        diceArr[2] = diceArr[1];
        diceArr[1] = diceArr[3];
        diceArr[3] = tmp;
    }

    public static void moveSouth() {
        int tmp = diceArr[0];

        diceArr[0] = diceArr[3];
        diceArr[3] = diceArr[1];
        diceArr[1] = diceArr[2];
        diceArr[2] = tmp;
    }
}