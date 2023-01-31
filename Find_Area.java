import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Find_Area {

    // 정사각형 하나의 정보를 저장하기 위한 Point 클래스 생성
    public static class Point {
        private int leftDownX; // 왼쪽 하단의 X 좌표
        private int leftDownY; // 왼쪽 하단의 Y 좌표
        private int rightUpX; // 오른쪽 상단의 X 좌표
        private int rightUpY; // 오른쪽 상단의 Y 좌표
        private boolean visit; // 해당 지점이 색칠되었는지 확인하기 위한 논리값

        public Point(int leftDownX, int leftDownY, int rightUpX, int rightUpY) {
            this.leftDownX = leftDownX;
            this.leftDownY = leftDownY;
            this.rightUpX = rightUpX;
            this.rightUpY = rightUpY;
            this.visit = false;
        }

        public int getLeftDownX() {
            return this.leftDownX;
        }

        public int getLeftDownY() {
            return this.leftDownY;
        }

        public boolean isVisit() {
            return this.visit;
        }

        public void visit() {
            this.visit = true;
        }
    }

    public static Point[][] resultArr; // 전체적인 모눈 종이의 상태를 저장하기 위한 배열 resultArr 선언
    public static Queue<Point> resultQueue; // BFS를 수행하기 위한 resultQueue 선언
    public static List<Integer> resultList; // BFS를 통하여 얻은 넓이의 값을 저장하기 위한 resultList 선언

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int height = Integer.parseInt(st.nextToken()); // 전체 모눈 종이의 높이
        int width = Integer.parseInt(st.nextToken()); // 전체 모눈 종이의 너비
        int color = Integer.parseInt(st.nextToken()); // 사용자로부터 입력 받은 색칠된 모눈 종이의 범위

        initResultArr(height, width); // 모눈 종이 배열 초기화
        initResultQueue(); // Queue 초기화
        initResultList(); // List 초기화

        for(int i = 0; i < color; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st); // 사용자로부터 색칠된 모눈 종이의 범위를 입력 받아서 visit를 통하여 색칠해줌
        }

        System.out.println(getAreaCount()); // BFS를 통하여 색칠되지않은 지점의 개수 구하여 출력

        Collections.sort(resultList); // 면적의 값을 오름차순으로 출력하기 위하여 정렬 수행

        for(int value : resultList) {
            System.out.print(value + " ");
        }
    }

    // BFS를 통하여 색칠되지 않은 면적의 개수를 구하기 위한 함수
    public static int getAreaCount() {
        int areaCount = 0; // 색칠되지 않은 면적의 개수를 저장하기 위한 변수 areaCount 선언 및 초기화

        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[0].length; j++) {
                Point point = resultArr[i][j];

                if(point.isVisit()) continue; // 해당 지점이 색칠되어있는 경우, 다음으로 넘어감

                int wide = 0;

                resultQueue.offer(point);

                while(!resultQueue.isEmpty()) {
                    Point value = resultQueue.poll();

                    if(value.isVisit()) continue;
                    value.visit();

                    wide++;

                    int leftDownX = value.getLeftDownX();
                    int leftDownY = value.getLeftDownY();

                    // 조건문을 통하여 다음 지역을 Queue에 넣어줌
                    if(leftDownX > 0) resultQueue.offer(resultArr[leftDownY][leftDownX - 1]);
                    if(leftDownY > 0) resultQueue.offer(resultArr[leftDownY - 1][leftDownX]);
                    if(leftDownX < resultArr[0].length - 1) resultQueue.offer(resultArr[leftDownY][leftDownX + 1]);
                    if(leftDownY < resultArr.length - 1) resultQueue.offer(resultArr[leftDownY + 1][leftDownX]);
                }

                areaCount++; // BFS를 정상적으로 끝마쳤을 경우, 색칠되지 않은 면적의 개수인 areaCount를 증가시켜줌
                resultList.add(wide); // 색칠되지 않은 면적의 넓이를 리스트에 저장
            }
        }

        return areaCount;
    }

    public static void mkResultArr(StringTokenizer st) {
        int leftDownX = Integer.parseInt(st.nextToken());
        int leftDownY = Integer.parseInt(st.nextToken());
        int rightUpX = Integer.parseInt(st.nextToken());
        int rightUpY = Integer.parseInt(st.nextToken());

        for(int i = leftDownY; i < rightUpY; i++) {
            for(int j = leftDownX; j < rightUpX; j++) {
                Point value = resultArr[i][j];
                value.visit(); // 사용자로부터 색칠된 모눈종이의 범위를 입력받아서 색칠해주는 작업을 수행
            }
        }
    }

    public static void initResultArr(int height, int width) {
        resultArr = new Point[height][width];

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                resultArr[i][j] = new Point(j, i, j+1, i+1);
            }
        }
    }

    public static void initResultQueue() {
        resultQueue = new LinkedList<>();
    }

    public static void initResultList() {
        resultList = new LinkedList<>();
    }
}
