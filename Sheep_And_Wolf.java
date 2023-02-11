import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sheep_And_Wolf {

    public static char[][] resultArr;
    public static boolean able = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        initResultArr(height, width);

        for(int i = 0; i < height; i++) {
            String status = br.readLine();
            mkResultArr(i, status);
        }

        mkFence();

        printResult();
    }

    public static void mkFence() {
        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[i].length; j++) {
                if(resultArr[i][j] == '.' || resultArr[i][j] == 'W' || resultArr[i][j] == 'D') continue;

                if(j > 0 && validateFence(i, j-1)) resultArr[i][j - 1] = 'D';
                if(j < resultArr[i].length-1 && validateFence(i, j+1)) resultArr[i][j + 1] = 'D';
                if(i > 0 && validateFence(i-1, j)) resultArr[i - 1][j] = 'D';
                if(i < resultArr.length - 1 && validateFence(i + 1, j)) resultArr[i + 1][j] = 'D';
            }
        }
    }

    public static boolean validateFence(int height, int width) {
        if(resultArr[height][width] == 'W') {
            able = false;
            return false;
        }

        if(resultArr[height][width] == 'S') {
            return false;
        }
        return true;
    }

    public static void printResult() {
        if(able) {
            System.out.println("1");

            for(char[] result : resultArr) {
                for(char value : result) {
                    System.out.print(value);
                }
                System.out.println();
            }

            return;
        }
        System.out.println("0");
    }

    public static void initResultArr(int height, int width) {
        resultArr = new char[height][width];
    }

    public static void mkResultArr(int height, String status) {
        for(int i = 0; i < status.length(); i++) {
            resultArr[height][i] = status.charAt(i);
        }
    }
}
