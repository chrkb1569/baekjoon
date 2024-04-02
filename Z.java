import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Z {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 2^N
        int r = Integer.parseInt(st.nextToken()); // height
        int c = Integer.parseInt(st.nextToken()); // width

        if(r == 0 && c == 0) {
            System.out.println(0);
            return;
        }

        System.out.println(getTargetNumber((int)Math.pow(2, N), 0, 0, c, r, 0));
    }

    private static int getTargetNumber(int length, int currentX, int currentY, int targetX, int targetY, int value) {
        if(length == 2) {
            if(currentX == targetX && currentY == targetY) return value;
            if(currentX + 1 == targetX && currentY == targetY) return value + 1;
            if(currentX == targetX && currentY + 1 == targetY) return value + 2;
            if(currentX + 1 == targetX && currentY + 1 == targetY) return value + 3;
        }

        length /= 2;
        int addedValue = length * length;

        if(targetX < currentX + length && targetY < currentY + length) return getTargetNumber(length, currentX, currentY, targetX, targetY, value);
        if(targetX >= currentX + length && targetY < currentY + length) return getTargetNumber(length, currentX + length, currentY, targetX, targetY, value + addedValue);
        if(targetX < currentX + length && targetY >= currentY + length) return getTargetNumber(length, currentX, currentY + length, targetX, targetY, value + 2 * addedValue);
        return getTargetNumber(length, currentX + length, currentY + length, targetX, targetY, value + 3 * addedValue);
    }
}