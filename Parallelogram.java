import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Parallelogram {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String coordinateInfo = br.readLine();
        String[] coordinateArr = coordinateInfo.split(" ");

        Point A = new Point(Integer.parseInt(coordinateArr[0]), Integer.parseInt(coordinateArr[1]));
        Point B = new Point(Integer.parseInt(coordinateArr[2]), Integer.parseInt(coordinateArr[3]));
        Point C = new Point(Integer.parseInt(coordinateArr[4]), Integer.parseInt(coordinateArr[5]));

        if(isParallel(A, B, C)) {
            System.out.println("-1.0");
            return;
        }

        double ab = getLength(A, B);
        double ac = getLength(A, C);
        double bc = getLength(B, C);

        double maxLength = Math.max(ab, Math.max(ac, bc));
        double minLength = Math.min(ab, Math.min(ac, bc));

        System.out.println(2 * (maxLength - minLength));
    }

    private static boolean isParallel(Point a, Point b, Point c) {
        int aX = a.getX();
        int aY = a.getY();

        int bX = b.getX();
        int bY = b.getY();

        int cX = c.getX();
        int cY = c.getY();

        if(aX == bX && aX == cX) return true;

        double inclinationAB = (double)(bY - aY) / (bX - aX);
        double inclinationAC = (double)(cY - aY) / (cX - aX);

        return inclinationAB == inclinationAC;
    }

    private static double getLength(Point a, Point b) {
        int aX = a.getX();
        int aY = a.getY();

        int bX = b.getX();
        int bY = b.getY();

        return Math.sqrt(Math.pow(aX - bX, 2) + Math.pow(aY - bY, 2));
    }

    private static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }
}
