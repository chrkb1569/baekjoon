import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Permutation_2 {
    private static boolean[][] beforeArr;
    private static boolean[][] afterArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // height
        int M = Integer.parseInt(st.nextToken()); // width

        initArr(N, M);

        for(int height = 0; height < N; height++) {
            String userInput = br.readLine();
            mkBeforeArr(userInput, height);
        }

        for(int height = 0; height < N; height++) {
            String userInput = br.readLine();
            mkAfterArr(userInput, height);
        }

        int cost = getChangeCost();

        if(checkValidation()) {
            System.out.println(cost);
            return;
        }

        System.out.println(-1);
    }

    private static void initArr(int height, int width) {
        beforeArr = new boolean[height][width];
        afterArr = new boolean[height][width];
    }

    private static void mkBeforeArr(String userInput, int height) {
        String trueValue = "1";
        int index = 0;

        for(String value : userInput.split("")) {
            if(value.equals(trueValue)) {
                beforeArr[height][index++] = true;
                continue;
            }

            beforeArr[height][index++] = false;
        }
    }

    private static void mkAfterArr(String userInput, int height) {
        String trueValue = "1";
        int index = 0;

        for(String value : userInput.split("")) {
            if(value.equals(trueValue)) {
                afterArr[height][index++] = true;
                continue;
            }

            afterArr[height][index++] = false;
        }
    }

    private static int getChangeCost() {
        int cost = 0;

        for(int height = 0; height < beforeArr.length - 2; height++) {
            for(int width = 0; width < beforeArr[0].length - 2; width++) {
                if(beforeArr[height][width] == afterArr[height][width]) continue;

                changeStatus(height, width);
                cost++;
            }
        }

        return cost;
    }

    private static void changeStatus(int height, int width) {
        for(int h = height; h <= height + 2; h++) {
            for(int w = width; w <= width + 2; w++) {
                beforeArr[h][w] = !beforeArr[h][w];
            }
        }
    }

    private static boolean checkValidation() {
        for(int height = 0; height < beforeArr.length; height++) {
            for(int width = 0; width < beforeArr[0].length; width++) {
                if(beforeArr[height][width] != afterArr[height][width]) return false;
            }
        }

        return true;
    }
}
