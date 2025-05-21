import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCW {
    private static int[][] pointArr = new int[3][2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int point = 0; point < 3; point++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pointArr[point] = new int[]{x, y};
        }

        int vectorValue = getVectorValue();

        printResult(vectorValue);
    }

    private static int getVectorValue() {
        int aX = pointArr[0][0];
        int aY = pointArr[0][1];

        int bX = pointArr[1][0];
        int bY = pointArr[1][1];

        int cX = pointArr[2][0];
        int cY = pointArr[2][1];

        return (bX - aX) * (cY - aY) - (cX - aX) * (bY - aY);
    }

    private static void printResult(int vectorValue) {
        if(vectorValue == 0) {
            System.out.println("0");
            return;
        }

        if(vectorValue > 0) {
            System.out.println("1");
            return;
        }

        System.out.println("-1");
    }
}
