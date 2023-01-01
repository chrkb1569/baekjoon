import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Array_Rotation {

    static int[][] resultArr;
    static int[] commandArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int command = Integer.parseInt(st.nextToken());

        initResultArr(height, width);
        initCommandArr(command);

        for(int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        st = new StringTokenizer(br.readLine(), " ");

        mkCommandArr(st);
        operateCmd();
        printResultArr();
    }

    public static void operateCmd() {
        for(int command : commandArr) {
            switch(command) {
                case 1:
                    cmd1();
                    break;

                case 2:
                    cmd2();
                    break;

                case 3:
                    cmd3();
                    break;

                case 4:
                    cmd4();
                    break;

                case 5:
                    cmd5();
                    break;

                case 6:
                    cmd6();
                    break;
            }
        }
    }
    // 배열 상하 반전
    public static void cmd1() {
        int height = resultArr.length;
        int width = resultArr[0].length;

        int[][] arr = new int[height][width];

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                arr[height-1-i][j] = resultArr[i][j];
            }
        }

        resultArr = arr;
    }
    // 배열 좌우 반전
    public static void cmd2() {
        int height = resultArr.length;
        int width = resultArr[0].length;

        int[][] arr = new int[height][width];

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                arr[i][width-1-j] = resultArr[i][j];
            }
        }

        resultArr = arr;
    }
    // 배열 오른쪽으로 90도 회전
    public static void cmd3() {
        int height = resultArr.length;
        int width = resultArr[0].length;

        int[][] arr = new int[width][height];

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                arr[i][height - 1 - j] = resultArr[j][i];
            }
        }

        resultArr = arr;
    }
    // 배열 왼쪽으로 90도 회전
    public static void cmd4() {
        int height = resultArr.length;
        int width = resultArr[0].length;

        int[][] arr = new int[width][height];

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                arr[width - 1 - i][j] = resultArr[j][i];
            }
        }

        resultArr = arr;
    }
    // 부분 수열 왼쪽으로 회전
    public static void cmd5() {
        int height = resultArr.length;
        int width = resultArr[0].length;

        int[][] arr = new int[height][width];

        for(int i = 0; i < height/2; i++) {
            for(int j = 0; j < width/2; j++) {
                arr[i][j + width/2] = resultArr[i][j]; // 1 -> 2
            }

            for(int j = width/2; j < width; j++) {
                arr[i + height/2][j] = resultArr[i][j]; // 2 -> 3
            }
        }

        for(int i = height/2; i < height; i++) {
            for(int j = 0; j < width/2; j++) {
                arr[i - height/2][j] = resultArr[i][j]; // 4 -> 1
            }

            for(int j = width/2; j < width; j++) {
                arr[i][j - width/2] = resultArr[i][j]; // 3 -> 4
            }
        }

        resultArr = arr;
    }
    // 부분 수열 이동
    public static void cmd6() {
        int height = resultArr.length;
        int width = resultArr[0].length;

        int[][] arr = new int[height][width];

        for(int i = 0; i < height/2; i++) {
            for(int j = 0; j < width/2; j++) {
                arr[i + height/2][j] = resultArr[i][j]; // 1 -> 4
            }

            for(int j = width/2; j < width; j++) {
                arr[i][j - width/2] = resultArr[i][j]; // 2 -> 1
            }
        }

        for(int i = height/2; i < height; i++) {
            for(int j = 0; j < width/2; j++) {
                arr[i][j + width/2] = resultArr[i][j]; // 4 -> 3
            }

            for(int j = width/2; j < width; j++) {
                arr[i - height/2][j] = resultArr[i][j]; // 3 -> 2
            }
        }

        resultArr = arr;
    }

    public static void printResultArr() {
        for(int[] arr : resultArr) {
            for(int value : arr) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static void mkResultArr(StringTokenizer st, int height) {
        int numValue = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][numValue++] = Integer.parseInt(st.nextToken());
        }
    }

    public static void mkCommandArr(StringTokenizer st) {
        int numValue = 0;

        for(;st.hasMoreTokens();) {
            commandArr[numValue++] = Integer.parseInt(st.nextToken());
        }
    }

    public static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    public static void initCommandArr(int command) {
        commandArr = new int[command];
    }
}
