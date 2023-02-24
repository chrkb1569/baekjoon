import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Alphabet {

    public static class Point {
        private int x;
        private int y;
        private int count;
        private char alphabet;
        private boolean visit;

        public Point(int x, int y, char alphabet) {
            this.x = x;
            this.y =  y;
            this.count = 0;
            this.alphabet = alphabet;
            this.visit = false;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getCount() {
            return this.count;
        }

        public char getAlphabet() {
            return this.alphabet;
        }

        public boolean isVisit() {
            return this.visit;
        }

        public void visit() {
            this.visit = true;
        }

        public void initVisit() {
            this.visit = false;
        }

        public void setCount(int value) {
            count = Math.max(this.count, value);
        }
    }

    public static Point[][] resultArr;
    public static boolean[] alphaArr;
    public static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        initResultArr(height, width);
        initAlphaArr();

        for(int i = 0; i < height; i++) {
            String value = br.readLine();
            mkResultArr(i, value);
        }

        getMaxValue(0, 0, 0);

        System.out.println(maxValue);
    }

    public static void getMaxValue(int height, int width, int count) {
        Point value = resultArr[height][width];
        char alphabet = value.getAlphabet();

        if(value.isVisit()) return;
        if(alphaArr[alphabet - 65]) return;

        int x = value.getX();
        int y = value.getY();

        value.visit();
        alphaArr[alphabet - 65] = true;
        value.setCount(count+1);

        maxValue = Math.max(maxValue, count+1);

        if(x > 0) {
            getMaxValue(height, width-1, count + 1);
        }
        if(x < resultArr[0].length - 1) {
            getMaxValue(height, width + 1, count + 1);
        }
        if(y > 0) {
            getMaxValue(height-1, width, count + 1);
        }
        if(y < resultArr.length - 1) {
            getMaxValue(height + 1, width, count + 1);
        }

        alphaArr[alphabet - 65] = false;
        value.initVisit();
    }

    public static void mkResultArr(int height, String value) {
        int numValue = 0;

        for(int i = 0; i < value.length(); i++) {
            resultArr[height][numValue] = new Point(numValue, height, value.charAt(i));
            numValue++;
        }
    }

    public static void initAlphaArr() {
        alphaArr = new boolean[26];
    }

    public static void initResultArr(int height, int width) {
        resultArr = new Point[height][width];
    }
}
