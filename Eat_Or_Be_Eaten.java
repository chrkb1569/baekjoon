import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Eat_Or_Be_Eaten {
    public static StringBuilder sb = new StringBuilder();
    public static int[] A;
    public static int[] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            String[] arr = br.readLine().split(" ");

            initA(Integer.parseInt(arr[0]));
            initB(Integer.parseInt(arr[1]));

            String[] arrA = br.readLine().split(" ");
            String[] arrB = br.readLine().split(" ");

            mkA(arrA);
            mkB(arrB);

            mkResultString();
        }

        System.out.println(sb);
    }

    public static void mkResultString() {
        Arrays.sort(B);
        int result = 0;

        for(int i = 0; i < A.length; i++) {
            int start = 0, mid = 0, index = 0;
            int end = B.length - 1;

            while(start <= end) {
                mid = (start + end) / 2;

                if(B[mid] < A[i]) {
                    start = mid + 1;
                    index = start;
                    continue;
                }
                end = mid - 1;
            }

            result += index;
        }

        sb.append(result).append("\n");
    }

    public static void mkA(String[] values) {
        for(int i = 0; i < values.length; i++) {
            A[i] = Integer.parseInt(values[i]);
        }
    }

    public static void mkB(String[] values) {
        for(int i = 0; i < values.length; i++) {
            B[i] = Integer.parseInt(values[i]);
        }
    }

    public static void initA(int length) {
        A = new int[length];
    }

    public static void initB(int length) {
        B = new int[length];
    }
}