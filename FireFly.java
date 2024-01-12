import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FireFly {
    private static int[] upperArr;
    private static int[] lowerArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int minObstacle = width;
        int count = 0;

        upperArr = getNLengthArr(width/2);
        lowerArr = getNLengthArr(width/2);

        for(int i = 0; i < width/2; i++) {
            lowerArr[i] = Integer.parseInt(br.readLine());
            upperArr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(upperArr);
        Arrays.sort(lowerArr);

        for(int i = 1; i <= height; i++) {
            int obstacleCount = binarySearch_Lower(i) + binarySearch_Upper(height - i + 1);

            if(obstacleCount == minObstacle) {
                count++;
                continue;
            }

            if(obstacleCount < minObstacle) {
                minObstacle = obstacleCount;
                count = 1;
            }
        }

        System.out.println(minObstacle + " " + count);
    }

    private static int[] getNLengthArr(int N) {
        return new int[N];
    }

    private static int binarySearch_Upper(int key) {
        int low = 0;
        int high = upperArr.length;

        while(low < high) {
            int mid = (low + high)/2;

            if(key <= upperArr[mid]) {
                high = mid;
                continue;
            }

            low = mid + 1;
        }

        return upperArr.length - high;
    }

    private static int binarySearch_Lower(int key) {
        int low = 0;
        int high = lowerArr.length;

        while(low < high) {
            int mid = (low + high)/2;

            if(key <= lowerArr[mid]) {
                high = mid;
                continue;
            }

            low = mid + 1;
        }

        return lowerArr.length - high;
    }
}
