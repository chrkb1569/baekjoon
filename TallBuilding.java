import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TallBuilding {

    private static int[] resultArr;

    private static int MAX_COUNT = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 0 <= N <= 50
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultArr(st);

        for(int startNumber = 1; startNumber < resultArr.length; startNumber++) {
            int count = 0;

            for(int endNumber = 1; endNumber < resultArr.length; endNumber++) {
                if(!isReachable(startNumber, endNumber)) continue;

                count++;
            }

            MAX_COUNT = Math.max(MAX_COUNT, count);
        }

        System.out.println(MAX_COUNT);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length + 1];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 1;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static boolean isReachable(int startX, int endX) {
        if(startX == endX) return false;

        if(startX > endX) {
            int tmp = startX;
            startX = endX;
            endX = tmp;
        }

        int startY = resultArr[startX];
        int endY = resultArr[endX];

        double incline = (double)(endY - startY) / (double)(endX - startX);

        for(int index = startX + 1; index < endX; index++) {
            double currentY = resultArr[index];

            if(currentY >= (incline * (index - startX) + startY)) return false;
        }

        return true;
    }
}
