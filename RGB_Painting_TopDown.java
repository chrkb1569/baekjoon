import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB_Painting_TopDown {

    static int[][] cost;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int length = Integer.parseInt(br.readLine());

        cost = new int[length][3];
        arr = new int[length][3];

        for(int i = 0; i < length; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(Math.min(Math.min(getResult(length - 1, 0), getResult(length - 1, 1)),
                getResult(length - 1, 2)));
    }

    public static int getResult(int length, int index) {
        if(length == 0) {
            return cost[length][index];
        }

        if(arr[length][index] == 0) {
            if(index == 0) {
                arr[length][index] += (Math.min(getResult(length - 1, 1), getResult(length - 1, 2))
                        + cost[length][index]);
            }

            else if(index == 1) {
                arr[length][index] += (Math.min(getResult(length - 1, 0), getResult(length - 1 , 2))
                        + cost[length][index]);
            }

            else if(index == 2) {
                arr[length][index] += (Math.min(getResult(length - 1, 0), getResult(length - 1, 1))
                        + cost[length][index]);
            }
        }

        return arr[length][index];
    }
}
