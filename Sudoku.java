import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Sudoku {
    private static int[][] resultArr = new int[9][9];
    private static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 9; i++) {
            String[] infoArr = br.readLine().split("");
            mkResultArr(infoArr, i);
        }

        mkSudoku(0, 0);
    }

    private static void mkResultArr(String[] infoArr, int height) {
        for(int i = 0; i < infoArr.length; i++) {
            resultArr[height][i] = Integer.parseInt(infoArr[i]);
        }
    }

    private static void mkSudoku(int height, int width) {
        if(flag) return;

        if(height == 9) {
            flag = true;

            for(int[] arr : resultArr) {
                for(int value : arr) {
                    System.out.print(value);
                }
                System.out.println();
            }

            return;
        }

        if(width == 9) {
            mkSudoku(height + 1, 0);
            return;
        }

        if(resultArr[height][width] == 0) {
            for(int i = 1; i<= 9; i++) {
                if(!checkValidation(height, width, i)) continue;

                resultArr[height][width] = i;
                mkSudoku(height, width + 1);
            }

            resultArr[height][width] = 0;

            return;
        }

        mkSudoku(height, width + 1);
    }

    private static boolean checkValidation(int height, int width, int value) {
        for(int i = 0; i < 9; i++) {
            if(i == width) continue;
            if(resultArr[height][i] == value) return false;
        }

        for(int i = 0; i < 9; i++) {
            if(i == height) continue;
            if(resultArr[i][width] == value) return false;
        }

        int dx = width / 3;
        int dy = height / 3;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                int x = 3 * dx + j;
                int y = 3 * dy + i;

                if(x == width && y == height) continue;
                if(resultArr[y][x] == value) return false;
            }
        }

        return true;
    }
}
