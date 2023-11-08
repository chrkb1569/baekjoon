import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AddArr {
    public static long[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] lengthArr = br.readLine().split(" ");

        int lengthA = Integer.parseInt(lengthArr[0]);
        int lengthB = Integer.parseInt(lengthArr[1]);

        initResultArr(lengthA + lengthB);

        String[] arrA = br.readLine().split(" ");
        String[] arrB = br.readLine().split(" ");

        mkResultArr(arrA, arrB);

        for(long value : resultArr) {
            sb.append(value).append(" ");
        }
        System.out.println(sb);
    }

    public static void initResultArr(int length) {
        resultArr = new long[length];
    }

    public static void mkResultArr(String[] arrA, String[] arrB) {
        int indexA = 0;
        int indexB = 0;

        for(int i = 0; i < resultArr.length; i++) {
            if(indexA == arrA.length) {
                resultArr[i] = Long.parseLong(arrB[indexB]);
                indexB++;
                continue;
            }

            if(indexB == arrB.length) {
                resultArr[i] = Long.parseLong(arrA[indexA]);
                indexA++;
                continue;
            }

            long valueA = Long.parseLong(arrA[indexA]);
            long valueB = Long.parseLong(arrB[indexB]);

            if(valueA < valueB) {
                resultArr[i] = valueA;
                indexA++;
                continue;
            }
            resultArr[i] = valueB;
            indexB++;
        }
    }
}
