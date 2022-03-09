import java.util.Scanner;
import java.math.BigInteger;

public class Break_Even_Point {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long cost_first, cost_produce, cost_real, result;

        cost_first = in.nextLong();
        cost_produce = in.nextLong();
        cost_real = in.nextLong();

        if(cost_produce > cost_real || cost_produce == cost_real) {
            result = -1;
        }
        else {
            result = cost_first / (cost_real - cost_produce) + 1;
        }

        System.out.println(result);

    }
}
