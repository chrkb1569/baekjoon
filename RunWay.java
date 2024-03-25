import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RunWay {
    private static int[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        initResultArr(N);

        for(int height = 0; height < N; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, height);
        }

        int pathCount = getPathCount(L);

        System.out.println(pathCount);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length][length];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static int getPathCount(int pathLength) {
        int pathCount = 0;

        for(int width = 0; width < resultArr.length; width++) {
            if(checkHeightValidation(width, pathLength)) pathCount++;
        }

        for(int height = 0; height < resultArr[0].length; height++) {
            if(checkWidthValidation(height, pathLength)) pathCount++;
        }

        return pathCount;
    }

    private static boolean checkHeightValidation(int width, int pathLength) {
        int number = resultArr[0][width];
        int sameNumber = 0;

        for(int index = 0; index < resultArr[0].length; ) {
            int value = resultArr[index][width];

            if(value == number) {
                sameNumber++;
                index++;
                continue;
            }

            if(Math.abs(number - value) > 1) return false;
            if(number > value) {
                if(index + pathLength - 1 >= resultArr.length) return false;

                for(int length = index; length < index + pathLength; length++) {
                    if(value != resultArr[length][width]) return false;
                }

                sameNumber = 0;
                number = resultArr[index + pathLength - 1][width];
                index += pathLength;

                continue;
            }

            if(sameNumber < pathLength) return false;
            number = value;
            sameNumber = 1;
            index++;
        }

        return true;
    }

    private static boolean checkWidthValidation(int height, int pathLength) {
        int number = resultArr[height][0];
        int sameNumber = 0;

        for(int index = 0; index < resultArr[0].length; ) {
            int value = resultArr[height][index];

            if(value == number) {
                sameNumber++;
                index++;
                continue;
            }

            if(Math.abs(number - value) > 1) return false;
            if(number > value) {
                if(index + pathLength - 1 >= resultArr[0].length) return false;

                for(int length = index; length < index + pathLength; length++) {
                    if(value != resultArr[height][length]) return false;
                }


                sameNumber = 0;
                number = resultArr[height][index + pathLength - 1];
                index += pathLength;

                continue;
            }

            if(sameNumber < pathLength) return false;
            number = value;
            sameNumber = 1;
            index++;
        }

        return true;
    }
}