import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Longest_SubSet {

    public static int[] valueArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        initResultArr(length);
        mkResultArr(st);

        System.out.println(getMaxCount());
    }

    public static int getMaxCount() {
        int length = valueArr.length;
        int[] resultArr = new int[length];

        int index = 1;

        resultArr[0] = valueArr[0];

        for(int i = 1; i < length; i++) {
            int value = valueArr[i];

            if(value > resultArr[index - 1]) {
                index++;
                resultArr[index - 1] = value;
                continue;
            }

            int low = 0;
            int high = index - 1;

            while(low < high) {
                int middle = (low + high) / 2;

                if(resultArr[middle] < value) {
                    low = middle + 1;
                    continue;
                }

                high = middle;
            }

            resultArr[low] = value;
        }

        return index;
    }

    public static void mkResultArr(StringTokenizer st) {
        int numValue = 0;

        for(;st.hasMoreTokens();) {
            valueArr[numValue++] = Integer.parseInt(st.nextToken());
        }
    }

    public static void initResultArr(int length) {
        valueArr = new int[length];
    }
}