import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.IOException;

public class Merge_Sort {

    static int[] result = new int[1000000];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int number = Integer.parseInt(br.readLine());

        int[] arr = new int[number];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Merge(arr, 0, arr.length-1);

        for(int i = 0; i < arr.length; i++) {
            bw.write(arr[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void Process_Merge(int[] arr, int start, int mid, int end) {
        int i, j, k, l, m;

        i = start;
        j = mid+1;
        k = start;
        l = end;

        while(i <= mid && j <= l) {
            if(arr[i] >= arr[j]) {
                result[k++] = arr[j++];
            }

            else if(arr[i] <= arr[j]) {
                result[k++] = arr[i++];
            }
        }

        if(i>mid){
            for(m=j; m<=end; m++)
                result[k++] = arr[m];
        }

        else{
            for(m=i; m<=mid; m++)
                result[k++] = arr[m];
        }

        for(m=start; m<=end; m++){
            arr[m] = result[m];
        }
    }

    public static void Merge(int[] arr, int start, int end) {
        int mid;

        if(start < end) {
            mid = (start + end) / 2;

            Merge(arr, start, mid);
            Merge(arr, mid+1, end);
            Process_Merge(arr, start, mid, end);
        }
    }
}
