import java.io.*;

public class Hive {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        Find_Space(num);

        br.close();
    }

    public static void Find_Space(int num) throws IOException{
        if(num == 1) {
            System.out.println(num);
        }
        else {
            int start = 2;
            int count = 2;
            int val = 6;
            int end;

            while(true) {
                end = start + val;

                if(num < end) {
                    break;
                }

                else {
                    count++;
                    start = end;
                    val += 6;
                }
            }

            System.out.println(count);
        }
    }
}
