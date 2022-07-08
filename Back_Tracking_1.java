import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Back_Tracking_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken()); // 전체 수의 개수
        int N = Integer.parseInt(st.nextToken()); // 중복 없이 뽑아야 할 데이터의 개수
        int[] values = new int[M];

        for(int i = 0; i < M; i++) {
            values[i] = i+1;
        }

        boolean[] arr = new boolean[M];
        LinkedList<Integer> output = new LinkedList<>();

        permutation(values, arr, output,0, M, N);

    }

    public static void permutation(int[] values, boolean[] arr, LinkedList<Integer> output, int count, int total, int r) {
        if(count == r) {
            print(output);
            return;
        }

        for(int i = 0; i < total; i++) {
            if(arr[i] != true) {
                arr[i] = true;
                output.addLast(values[i]);
                permutation(values, arr, output,count+1, total, r);
                arr[i] = false;
                output.removeLast();
            }
        }
    }

    public static void print(LinkedList<Integer> output) {

        for(Iterator<Integer> itr = output.iterator();itr.hasNext();) {
            System.out.print(itr.next() + " ");
        }

        System.out.println();
    }
}