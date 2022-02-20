import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Counting_Sort {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int count = Integer.parseInt(br.readLine());
        int[] arr = new int[count];
        int[] result = new int[count];
        int[] count_sort;

        for(int i = 0; i < count; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        count_sort = Count_Sort(arr);

        result = result_sort(count_sort, arr);

        for(int s : result) {
            bw.write(s + "\n");
        }
        bw.flush();
        bw.close();

    }

    public static int[] Count_Sort(int[] arr) {
        int max = arr[0];

        for(int i = 0 ; i < arr.length; i++) {
            if(arr[i] >=max) {
                max = arr[i];
            }
        }

        int[] count_sort = new int[max+1];

        for(int j = 0; j < arr.length; j++) {
            count_sort[arr[j]]++;
        }

        count_sort = total_sum(count_sort);

        return count_sort;
    }

    public static int[] total_sum(int[] arr) {
        for(int i = 0; i < arr.length-1; i++) {
            arr[i+1] = arr[i+1] + arr[i];
        }

        return arr;
    }

    public static int[] result_sort(int[] sort, int[] origin) {
        int[] result = new int[origin.length];
        int value;

        for(int i = origin.length-1; i >=0; i--) {
            value = origin[i];
            sort[value]--;
            result[sort[value]] = value;
        }

        return result;
    }
}
