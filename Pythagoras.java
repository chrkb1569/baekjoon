import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pythagoras {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r, height, floor;
        double result;

        int num1, num2, num3;

        num1 = Integer.parseInt(st.nextToken());
        num2 = Integer.parseInt(st.nextToken());
        num3 = Integer.parseInt(st.nextToken());

        if (num1 > num2) {
            if (num1 > num3) {
                r = num1;
                height = num2;
                floor = num3;
            } else {
                r = num3;
                height = num1;
                floor = num2;
            }
        } else {
            if (num2 > num3) {
                r = num2;
                floor = num1;
                height = num3;
            } else {
                r = num3;
                floor = num1;
                height = num2;
            }
        }

        while (true) {
            if (r == 0 && height == 0 && floor == 0) {
                break;
            } else {
                result = Math.pow(height, 2) + Math.pow(floor, 2);

                if (result == Math.pow(r, 2)) {
                    System.out.println("right");
                } else {
                    System.out.println("wrong");
                }
            }

            StringTokenizer sn = new StringTokenizer(br.readLine());
            num1 = Integer.parseInt(sn.nextToken());
            num2 = Integer.parseInt(sn.nextToken());
            num3 = Integer.parseInt(sn.nextToken());

            if (num1 > num2) {
                if (num1 > num3) {
                    r = num1;
                    height = num2;
                    floor = num3;
                } else {
                    r = num3;
                    height = num1;
                    floor = num2;
                }
            } else {
                if (num2 > num3) {
                    r = num2;
                    floor = num1;
                    height = num3;
                } else {
                    r = num3;
                    floor = num1;
                    height = num2;
                }
            }
        }
    }
}
