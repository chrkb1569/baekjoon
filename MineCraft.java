import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MineCraft {
    public static int[][] resultArr;
    public static int inventory;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infoArr = br.readLine().split(" ");

        int height = Integer.parseInt(infoArr[0]);
        int width = Integer.parseInt(infoArr[1]);
        inventory = Integer.parseInt(infoArr[2]);

        initResultArr(height, width);

        for(int i = 0; i < height; i++) {
            infoArr = br.readLine().split(" ");
            mkResultArr(infoArr, i);
        }

        printResult();
    }

    public static void printResult() {
        int minValue = getMinValue();
        int maxValue = getMaxValue();
        int minCost = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;

        for(int height = minValue; height <= maxValue; height++) {
            int block = inventory;
            int cost = 0;

            for(int i = 0; i < resultArr.length; i++) {
                for(int j = 0; j < resultArr[i].length; j++) {
                    int value = resultArr[i][j];

                    if(value < height) {
                        block -= (height - value);
                        cost += (height - value);
                    }
                    else {
                        block += (value - height);
                        cost += (value - height) * 2;
                    }
                }
            }

            if(block < 0) break;

            if(cost <= minCost) {
                minCost = cost;
                maxHeight = Math.max(maxHeight, height);
            }
        }

        System.out.println(minCost + " " + maxHeight);
    }

    public static int getMinValue() {
        int minValue = Integer.MAX_VALUE;

        for(int i = 0; i < resultArr.length; i++) {
            minValue = Math.min(minValue, Arrays.stream(resultArr[i]).min().getAsInt());
        }

        return minValue;
    }

    public static int getMaxValue() {
        int maxValue = Integer.MIN_VALUE;

        for(int i = 0; i < resultArr.length; i++) {
            maxValue = Math.max(maxValue, Arrays.stream(resultArr[i]).max().getAsInt());
        }

        return maxValue;
    }

    public static void mkResultArr(String[] infoArr, int height) {
        for(int i = 0; i < infoArr.length; i++) {
            resultArr[height][i] = Integer.parseInt(infoArr[i]);
        }
    }

    public static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }
}
