import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Hanoi_Top{

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(in.readLine());
        int result = (int)(Math.pow(2, number))-1;
        bw.write(String.valueOf(result) + "\n");
        top_sol(number, '1', '2', '3');
        bw.flush();
        bw.close();
    }

    public static void top_sol(int num, char start, char via, char end) throws IOException{
        if(num == 1) {
            bw.write(start + " " + end + "\n");
        }

        else {
            top_sol(num-1, start, end, via);
            bw.write(start + " " + end + "\n");
            top_sol(num-1, via, start, end);
        }
    }
}