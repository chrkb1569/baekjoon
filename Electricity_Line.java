import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Electricity_Line {

    // 전깃줄과 관련된 정보를 저장하기 위한 클래스 Line 선언, 정렬을 위하여 Comparable 인터페이스 구현
    public static class Line implements Comparable<Line> {
        private int startNumber; // A 전봇대의 숫자를 나타내는 startNumber
        private int endNumber; // B 전봇대의 숫자를 나타내는 endNumber

        public Line(int startNumber, int endNumber) {
            this.startNumber = startNumber;
            this.endNumber = endNumber;
        }

        public int getStartNumber() {
            return this.startNumber;
        }

        public int getEndNumber() {
            return this.endNumber;
        }

        @Override // 정렬시, 시작 번호를 기준으로 오름차순 정렬을 하기 위하여 compareTo 메소드 선언
        public int compareTo(Line line) {
            return this.getStartNumber() - line.getStartNumber();
        }
    }

    public static Line[] resultArr; // 사용자로부터 입력 받은 값을 저장하기 위한 resultArr 배열 선언
    public static int[] dpArr; // 연결된 최대한의 전깃줄의 개수를 저장하기 위한 배열 dpArr 선언

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int line = Integer.parseInt(br.readLine());

        initResultArr(line);
        initDpArr(line);

        for(int i = 0;  i < line; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        Arrays.sort(resultArr); // 시작 번호를 기준으로 오름차순 정렬 수행

        getRmCount(); // dpArr값을 채워 넣는 작업 수행
    }

    public static void getRmCount() {
        for (int i = 0; i < resultArr.length; i++) {
            dpArr[i] = 1;

            for (int j = 0; j < i; j++) {
                if(resultArr[i].getEndNumber() > resultArr[j].getEndNumber()) {
                    dpArr[i] = Math.max(dpArr[i], dpArr[j] + 1);
                }
            }
        }

        int maxValue = Integer.MIN_VALUE;

        for(int value : dpArr) {
            if(value > maxValue) {
                maxValue = value;
            }
        }

        System.out.println(resultArr.length - maxValue);
    }

    public static void mkResultArr(StringTokenizer st, int index) {
        int startNumber = Integer.parseInt(st.nextToken());
        int endNumber = Integer.parseInt(st.nextToken());

        resultArr[index] = new Line(startNumber, endNumber);
    }

    public static void initResultArr(int value) {
        resultArr = new Line[value];
    }

    public static void initDpArr(int value) {
        dpArr = new int[value];
    }
}
