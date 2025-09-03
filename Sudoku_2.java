import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Sudoku_2 {

    private static int[][] resultArr = new int[9][9];

    private static List<int[]> resultList = new LinkedList<>();

    private static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int height = 0; height < 9; height++) {
            st = new StringTokenizer(br.readLine(), " ");

            mkResultArr(height, st);
        }

        mkSudoku(0);
    }

    private static void mkResultArr(int height, StringTokenizer st) {
        int width = 0;

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());

            if(value == 0) resultList.add(new int[]{height, width});

            resultArr[height][width++] = value;
        }
    }

    private static void mkSudoku(int index) {
        if(flag) return;
        if(index == resultList.size()) {
            flag = true;
            printResult();
            return;
        }

        int[] infoArr = resultList.get(index);

        int height = infoArr[0];
        int width = infoArr[1];

        for(int nextValue = 1; nextValue <= 9; nextValue++) {
            if(flag) continue;
            if(!checkValidation(height, width, nextValue)) continue;

            resultArr[height][width] = nextValue;
            mkSudoku(index + 1);
            resultArr[height][width] = 0;
        }
    }

    private static boolean checkValidation(int height, int width, int value) {
        for(int w = 0; w < 9; w++) {
            if(resultArr[height][w] == value) return false;
        }

        for(int h = 0; h < 9; h++) {
            if(resultArr[h][width] == value) return false;
        }

        int boxHeight = height / 3 * 3;
        int boxWidth = width / 3 * 3;

        for(int h = boxHeight; h < boxHeight  + 3; h++) {
            for(int w = boxWidth; w < boxWidth + 3; w++) {
                if(resultArr[h][w] == value) return false;
            }
        }

        return true;
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();

        for(int[] arr : resultArr) {
            for(int value : arr) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}