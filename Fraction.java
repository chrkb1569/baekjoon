import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fraction {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        Find_Fraction(num);
    }

    public static void Find_Fraction(int num) {
       int sum = 2, start = 1, val = 2;
       int down, up;

       if(num == 1) {
       }
       else {
           while(true) {
               start += val;
               if(num <= start) {
                   sum++;
                   break;
               }
               else {
                   val++;
                   sum++;
               }
           }
       }

       if(sum % 2 == 0) {
           up = sum -1;
           down = 1;
           for(int i = start - sum + 2; i <= start; i++) {
               if(i == num) {
                   System.out.printf("%d/%d", up, down);
               }
               up--;
               down++;
           }
       }
       else {
           up = sum -1;
           down = 1;
           for(int i = start - sum + 2; i <= start; i++) {
               if(i == num) {
                   System.out.printf("%d/%d", down, up);
               }
               up--;
               down++;
           }
       }

    }
}