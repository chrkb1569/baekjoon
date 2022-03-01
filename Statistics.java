import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;

public class Statistics {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(br.readLine());
        int[] arr = new int[num];

        for(int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        //bw.write(Arithmetic_Mean(arr) + "\n");
        Arithmetic_Mean(arr);
        bw.write(Median(arr) + "\n");
        bw.write(Mode(arr) + "\n");
        bw.write(Range(arr) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void Arithmetic_Mean(int[] arr){
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int sum = 0;
        for(int i : arr) {
            sum += i;
        }

        double result = (double)sum / arr.length;

        if(result < 0 && result > -1) {
            result = 0;
        }

        System.out.printf("%.0f%n", result);
    }

    public static int Median(int[] arr){
        Arrays.sort(arr);
        int result = 0;

        for(int i = 0; i < arr.length; i++) {
            if(i == (arr.length / 2)) {
                result = arr[i];
            }
        }

        return result;
    }

    //-4000 <= x <= 4000  ---> 0<= x + 4000 <= 8000

    public static int Mode(int[] arr){
        int[] arr_result = new int[8001];
        int max = 0;
        int result_index = 0;
        int result_count = 0;
        int result;

        for(int i = 0; i < arr.length; i++) {
            arr_result[arr[i] + 4000]++;
        }

        for(int j = 0; j < arr_result.length; j++) {
            if(arr_result[j] > max) {
                max = arr_result[j];
                result_index = j-4000;
            }
        }

        if(max > (arr.length / 2)) {
            return result_index;
        }

        else {
            for(int j = 0; j < arr_result.length; j++) {
                if(arr_result[j] == max) {
                    result_count++;
                    if(result_count == 2) {
                        result_index = j - 4000;
                        break;
                    }
                }

            }
            return result_index;
        }
    }

    public static int Range(int[] arr){
        int max, min;
        max = arr[0];
        min = arr[0];

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] >= max) {
                max = arr[i];
            }

            else if(arr[i] <= min) {
                min = arr[i];
            }
        }
        return (max - min);
    }
}
