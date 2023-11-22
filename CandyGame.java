import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CandyGame {
    public static String[][] resultArr;
    public static int maxLength = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);

        for(int i = 0; i < N; i++) {
            String[] infoArr = br.readLine().split("");
            mkResultArr(infoArr, i);
        }

        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[i].length; j++) {
                String current = resultArr[i][j];

                if(j + 1 < resultArr[i].length && !resultArr[i][j+1].equals(current)) {
                    String[][] cpyArr = changeWithRight(j, i);
                    checkMaxDownLength(cpyArr);
                    checkMaxRightLength(cpyArr);
                }

                if(i + 1 < resultArr.length && !resultArr[i+1][j].equals(current)) {
                    String[][] cpyArr = changeWithDown(j, i);
                    checkMaxDownLength(cpyArr);
                    checkMaxRightLength(cpyArr);
                }
            }
        }

        System.out.println(maxLength);
    }

    public static void initResultArr(int length) {
        resultArr = new String[length][length];
    }

    public static void mkResultArr(String[] infoArr, int height) {
        for(int i = 0; i < infoArr.length; i++) {
            resultArr[height][i] = infoArr[i];
        }
    }

    public static String[][] cpyArr() {
        String[][] cpyArr = new String[resultArr.length][resultArr.length];

        for(int i = 0; i < resultArr.length; i++) {
            cpyArr[i] = resultArr[i].clone();
        }

        return cpyArr;
    }

    public static String[][] changeWithRight(int currentX, int currentY) {
        String[][] arr = cpyArr();
        String box;

        box = arr[currentY][currentX];
        arr[currentY][currentX] = arr[currentY][currentX+1];
        arr[currentY][currentX+1] = box;

        return arr;
    }

    public static String[][] changeWithDown(int currentX, int currentY) {
        String[][] arr = cpyArr();
        String box;

        box = arr[currentY][currentX];
        arr[currentY][currentX] = arr[currentY+1][currentX];
        arr[currentY+1][currentX] = box;

        return arr;
    }

    public static void checkMaxRightLength(String[][] arr) {
        String color;

        for(int i = 0; i < resultArr.length; i++) {
            int length = 1;
            color = arr[i][0];

            for(int j = 1; j < resultArr[i].length; j++) {
                String value = arr[i][j];

                if(color.equals(value)) {
                    length++;
                    continue;
                }

                maxLength = Math.max(maxLength, length);
                color = value;
                length = 1;
            }

            maxLength = Math.max(maxLength, length);
        }
    }

    public static void checkMaxDownLength(String[][] arr) {
        String color;

        for(int i = 0; i < resultArr[0].length; i++) {
            int length = 1;
            color = arr[0][i];

            for(int j = 1; j < resultArr.length; j++) {
                String value = arr[j][i];

                if(color.equals(value)) {
                    length++;
                    continue;
                }

                maxLength = Math.max(maxLength, length);
                color = value;
                length = 1;
            }

            maxLength = Math.max(maxLength, length);
        }
    }
}
