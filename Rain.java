import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Rain {
    private static int[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int area = 0;

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        initResultArr(height, width);

        st = new StringTokenizer(br.readLine(), " ");
        mkResultArr(st);

        for(int[] arr : resultArr) {
            area += getArea(arr);
        }

        System.out.println(area);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());

            for(int i = 0; i < value; i++) {
                resultArr[i][index] = 1;
            }

            index++;
        }
    }

    private static int getArea(int[] arr) {
        boolean flag = false;
        int area = 0;
        int count = 0;

        for(int i = 1; i < arr.length; i++) {
            int currentValue = arr[i];
            int preValue = arr[i-1];

            if(preValue == 0 && currentValue == 0 && flag) {
                count++;
                continue;
            }

            if(preValue == 0 && currentValue == 1 && flag) {
                area += count;
                count = 0;
                flag = false;
                continue;
            }

            if(preValue == 0 && currentValue == 1 && !flag) {
                flag = true;
                continue;
            }

            if(preValue == 1 && currentValue == 0) {
                flag = true;
                count++;
            }
        }

        return area;
    }
}
