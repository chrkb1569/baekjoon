import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Rectangle {
    private static Square first;
    private static Square second;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        for(int testCase = 0; testCase < 4; testCase++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int p1 = Integer.parseInt(st.nextToken());
            int q1 = Integer.parseInt(st.nextToken());

            first = new Square(x1, y1, p1, q1); // 첫 번째 직사각형

            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            int q2 = Integer.parseInt(st.nextToken());

            second = new Square(x2, y2, p2, q2); // 두 번째 직사각형

            sb.append(checkStatus()).append("\n");
        }

        System.out.println(sb);
    }

    private static char checkStatus() {
        if(first.getX() == second.getX() &&
                first.getY() == second.getY() &&
                first.getP() == second.getP() &&
                first.getQ() == second.getQ()) return 'a';
        if(isDCase()) return 'd';
        if(isBCase()) return 'b';
        if(isCCase()) return 'c';
        return 'a';
    }

    // 서로 만나지 않는 경우
    private static boolean isDCase() {
        int[] firstLeftUp = first.getLeftUp();
        int[] firstLeftDown = first.getLeftDown();
        int[] firstRightDown = first.getRightDown();

        int[] secondLeftUp = second.getLeftUp();
        int[] secondLeftDown = second.getLeftDown();
        int[] secondRightDown = second.getRightDown();

        if(firstRightDown[0] < secondLeftDown[0]) return true;
        if(secondRightDown[0] < firstLeftDown[0]) return true;
        if(firstLeftDown[1] > secondLeftUp[1]) return true;
        if(secondLeftDown[1] > firstLeftUp[1]) return true;

        return false;
    }

    // 서로 한 점에서 만나는 경우
    private static boolean isCCase() {
        int[] firstLeftUp = first.getLeftUp();
        int[] firstRightUp = first.getRightUp();
        int[] firstRightDown = first.getRightDown();
        int[] firstLeftDown = first.getLeftDown();

        int[] secondLeftUp = second.getLeftUp();
        int[] secondRightUp = second.getRightUp();
        int[] secondRightDown = second.getRightDown();
        int[] secondLeftDown = second.getLeftDown();

        if(firstLeftUp[0] == secondRightDown[0] && firstLeftUp[1] == secondRightDown[1]) return true;
        if(firstRightUp[0] == secondLeftDown[0] && firstRightUp[1] == secondLeftDown[1]) return true;
        if(firstRightDown[0] == secondLeftUp[0] && firstRightDown[1] == secondLeftUp[1]) return true;
        if(firstLeftDown[0] == secondRightUp[0] && firstLeftDown[1] == secondRightUp[1]) return true;

        return false;
    }

    // 서로 선분에서 만나는 경우
    private static boolean isBCase() {
        int[] firstLeftUp = first.getLeftUp();
        int[] firstRightUp = first.getRightUp();
        int[] firstRightDown = first.getRightDown();
        int[] firstLeftDown = first.getLeftDown();

        int[] secondLeftUp = second.getLeftUp();
        int[] secondRightUp = second.getRightUp();
        int[] secondRightDown = second.getRightDown();
        int[] secondLeftDown = second.getLeftDown();

        if(secondLeftUp[0] == firstRightUp[0] &&
                secondLeftUp[1] < firstRightUp[1] &&
                secondLeftUp[1] > firstRightDown[1]) return true;
        if(secondLeftDown[0] == firstRightUp[0] &&
                secondLeftDown[1] > firstRightDown[1] &&
                secondLeftDown[1] < firstRightUp[1]) return true;
        if(secondLeftDown[1] == firstLeftUp[1] &&
                secondLeftDown[0] > firstLeftUp[0] &&
                secondLeftDown[0] < firstRightUp[0]) return true;
        if(secondRightDown[1] == firstLeftUp[1] &&
                secondRightDown[0] > firstLeftUp[0] &&
                secondRightDown[0] < firstRightUp[0]) return true;
        if(secondRightUp[0] == firstLeftUp[0] &&
                secondRightUp[1] > firstLeftDown[1] &&
                secondRightUp[1] < firstLeftUp[1]) return true;
        if(secondRightDown[0] == firstLeftUp[0] &&
                secondRightDown[1] > firstLeftDown[1] &&
                secondRightDown[1] < firstLeftUp[1]) return true;
        if(secondLeftUp[1] == firstLeftDown[1] &&
                secondLeftUp[0] > firstLeftDown[0] &&
                secondLeftUp[0] < firstRightDown[0]) return true;
        if(secondRightUp[1] == firstLeftDown[1] &&
                secondRightUp[0] > firstLeftDown[0] &&
                secondRightUp[0] < firstRightDown[0]) return true;
        if(firstLeftUp[0] == secondRightUp[0] &&
                firstLeftUp[1] > secondRightDown[1] &&
                firstLeftUp[1] < secondRightUp[1]) return true;
        if(firstLeftDown[0] == secondRightUp[0] &&
                firstLeftDown[1] > secondRightDown[1] &&
                firstLeftDown[1] < secondRightUp[1]) return true;
        if(firstLeftDown[1] == secondLeftUp[1] &&
                firstLeftDown[0] > secondLeftUp[0] &&
                firstLeftDown[0] < secondRightUp[0]) return true;
        if(firstRightDown[1] == secondLeftUp[1] &&
                firstRightDown[0] > secondLeftUp[0] &&
                firstRightDown[0] < secondRightUp[0]) return true;
        if(firstRightUp[0] == secondLeftDown[0] &&
                firstRightUp[1] > secondLeftDown[1] &&
                firstRightUp[1] < secondLeftUp[1]) return true;
        if(firstRightDown[0] == secondLeftDown[0] &&
                firstRightDown[1] > secondLeftDown[1] &&
                firstRightDown[1] < secondLeftUp[1]) return true;
        if(firstLeftUp[1] == secondLeftDown[1] &&
                firstLeftUp[0] > secondLeftDown[0] &&
                firstLeftUp[0] < secondRightDown[0]) return true;
        if(firstRightUp[1] == secondLeftDown[1] &&
                firstRightUp[0] > secondLeftDown[0] &&
                firstRightUp[0] < secondRightDown[0]) return true;
        if(firstLeftUp[0] == secondLeftDown[0] &&
                firstLeftUp[1] == secondLeftDown[1] &&
                firstRightUp[0] == secondRightDown[0] &&
                firstRightUp[1] == secondRightDown[1]) return true;
        if(firstRightUp[0] == secondLeftUp[0] &&
                firstRightUp[1] == secondLeftUp[1] &&
                firstRightDown[0] == secondLeftDown[0] &&
                firstRightDown[1] == secondLeftDown[1]) return true;
        if(firstLeftDown[0] == secondLeftUp[0] &&
                firstLeftDown[1] == secondLeftUp[1] &&
                firstRightDown[0] == secondRightUp[0] &&
                firstRightDown[1] == secondRightUp[1]) return true;
        if(firstLeftUp[0] == secondRightUp[0] &&
                firstLeftUp[1] == secondRightUp[1] &&
                firstLeftDown[0] == secondRightDown[0] &&
                firstLeftDown[1] == secondRightDown[1]) return true;

        return false;
    }

    private static class Square {
        private int x;
        private int y;
        private int p;
        private int q;

        public Square(int x, int y, int p, int q) {
            this.x = x;
            this.y = y;
            this.p = p;
            this.q = q;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getP() {
            return this.p;
        }

        public int getQ() {
            return this.q;
        }

        public int[] getLeftDown() {
            return new int[]{this.x, this.y};
        }

        public int[] getRightDown() {
            return new int[]{this.p, this.y};
        }

        public int[] getLeftUp() {
            return new int[]{this.x, this.q};
        }

        public int[] getRightUp() {
            return new int[]{this.p, this.q};
        }
    }
}
