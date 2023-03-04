import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class BomberMan {

    public static class Point {
        private int x;
        private int y;
        private char value;

        public Point(int x, int y, char value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public char getValue() {
            return this.value;
        }

        public void setValue(char value) {
            this.value = value;
        }
    }

    public static Point[][] resultArr;
    public static Queue<Point> resultQueue;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());

        initResultArr(height, width);
        initResultQueue();

        for(int i = 0; i < height; i++) {
            String status = br.readLine();
            mkResultArr(i, status);
        }

        afterSecond(second);
        printStatus();
    }

    public static void afterSecond(int value) {

        int second = getSecond(value);

        switch(second) {
            case 1: // 초기상태 그냥 출력
                break;

            case 2: // 모든 판이 가득 찬 상태 출력
                fillStatus();
                break;

            case 3: // 설치된 폭탄 기준 상하좌우 터짐
                fillStatus();
                explodeBomb();
                break;

            case 4: // 폭발 - 채우기 - 폭발 연쇄 작용
                fillStatus();
                explodeBomb();
                fillStatusAfterBomb();
                explodeBomb();
                break;
        }
    }

    public static int getSecond(int second) {
        if(second <= 3) return second;
        if(second % 2 == 0) return 2;
        if(second % 4 == 3) return 3;
        return 4;
    }

    // 폭탄이 없는 자리에 폭탄을 설치하는 로직
    public static void fillStatus() {
        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[i].length; j++) {
                if(resultArr[i][j].getValue() != '.') continue;
                resultArr[i][j].setValue('O');
            }
        }
    }

    public static void fillStatusAfterBomb() {
        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[i].length; j++) {
                if(resultArr[i][j].getValue() != '.') {
                    resultQueue.offer(resultArr[i][j]);
                    continue;
                }
                resultArr[i][j].setValue('O');
            }
        }
    }

    // 설치된 폭탄의 상하좌우칸 모두 터짐
    public static void explodeBomb() {
        while(!resultQueue.isEmpty()) {
            Point point = resultQueue.poll();

            int x = point.getX();
            int y = point.getY();

            resultArr[y][x].setValue('.');
            if(x > 0) resultArr[y][x-1].setValue('.');
            if(x < resultArr[0].length - 1) resultArr[y][x+1].setValue('.');
            if(y > 0) resultArr[y-1][x].setValue('.');
            if(y < resultArr.length - 1) resultArr[y+1][x].setValue('.');
        }

    }

    // 상태 출력
    public static void printStatus() {
        for(Point[] arr : resultArr) {
            for(Point value : arr) {
                sb.append(value.getValue());
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void mkResultArr(int height, String status) {
        for(int i = 0; i < status.length(); i++) {
            char value = status.charAt(i);
            resultArr[height][i] = new Point(i, height, value);

            if(value == 'O') resultQueue.offer(resultArr[height][i]);
        }
    }

    public static void initResultArr(int height, int width) {
        resultArr = new Point[height][width];
    }

    public static void initResultQueue() {
        resultQueue = new LinkedList<>();
    }
}
