import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumberSquare {
    private static int[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // height
        int M = Integer.parseInt(st.nextToken()); // width

        initResultArr(N, M);

        for(int height = 0; height < N; height++) {
            String[] infoArr = br.readLine().split("");
            mkResultArr(infoArr, height);
        }

        int maxArea = getMaxArea();

        System.out.println(maxArea);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    private static void mkResultArr(String[] infoArr, int height) {
        for(int index = 0; index < infoArr.length; index++) {
            resultArr[height][index] = Integer.parseInt(infoArr[index]);
        }
    }

    private static int getMaxArea() {
        int maxArea = 1;

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr[0].length; width++) {
                int length = 0;

                while(height + length < resultArr.length && width + length < resultArr[0].length) {
                    int leftUp = resultArr[height][width];
                    int rightUp = resultArr[height][width + length];
                    int leftDown = resultArr[height + length][width];
                    int rightDown = resultArr[height + length][width + length];

                    length++;

                    if(leftUp != rightUp || leftUp != leftDown || leftUp != rightDown) continue;
                    maxArea = Math.max(maxArea, length * length);
                }
            }
        }

        return maxArea;
    }
}