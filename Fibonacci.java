import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Fibonacci {

    static Integer[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int num = Integer.parseInt(br.readLine());

        for(int i = 0; i < num; i++) {
            int number = Integer.parseInt(br.readLine());
            arr = new Integer[41][2];

            Integer[] value = getResult(number);

            sb.append(value[0]).append(" ").append(value[1]).append("\n");
        }

        bw.write(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static Integer[] getResult(int number) {
        if(number == 0) {
            arr[0][0] = 1;
            arr[0][1] = 0;

            return arr[0];
        }

        else if(number == 1) {
            arr[1][0] = 0;
            arr[1][1] = 1;

            return arr[1];
        }

        if(arr[number][0] == null || arr[number][1] == null) {
            Integer[] value1 = getResult(number - 1);
            Integer[] value2 = getResult(number - 2);

            arr[number][0] = value1[0] + value2[0];
            arr[number][1] = value1[1] + value2[1];
        }

        return arr[number];
    }
}
