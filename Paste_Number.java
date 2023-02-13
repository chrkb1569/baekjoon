import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Paste_Number {

    // 지점 하나의 정보를 저장하기 위한 Number 클래스 생성
    public static class Number {
        private int x; // 해당 지점의 x좌표
        private int y; // 해당 지점의 y좌표
        private int number; // 해당 지점의 수
        private boolean visit; // 해당 지점 방문 여부

        public Number(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
            this.visit = false;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getNumber() {
            return this.number;
        }

        public boolean isVisit() {
            return this.visit;
        }

        public void visit() {
            this.visit = true;
        }
    }

    public static Number[][] resultArr; // 사용자로부터 입력 받은 지점들을 저장하기 위한 배열 resultArr
    public static List<Integer> resultList; // 단지의 수와 단지에 속하는 집의 수를 저장하기 위한 resultList
    public static Stack<Number> resultQueue; // DFS를 수행하기 위한 resultQueue

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initResultArr(N); // 배열 초기화
        initResultList(); // List 초기화
        initResultQueue(); // Stack 초기화

        for(int i = 0; i < N; i++) {
            String value = br.readLine();
            mkResultArr(i, value);
        }

        findPasterNumber(); // DFS 수행
        printResult(); // List에 속한 결과값 출력
    }

    public static void findPasterNumber() {
        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr.length; j++) {
                if(resultArr[i][j].getNumber() == 0) continue; // 해당 지점의 숫자가 0인 경우 생략
                if(resultArr[i][j].isVisit()) continue; // 해당 지점을 방문하였을 경우 생략

                int count = 0;
                resultQueue.push(resultArr[i][j]);

                // DFS 수행
                while(!resultQueue.isEmpty()) {
                    Number value = resultQueue.pop();

                    if(value.isVisit()) continue;
                    if(value.getNumber() == 0) continue;

                    value.visit();
                    count++; // 단지에 속한 세대 수 더하기

                    // 각 지점의 상하좌우를 스택에 대입하여 DFS 수행
                    if(value.getX() > 0) resultQueue.push(resultArr[value.getY()][value.getX()-1]);
                    if(value.getX() < resultArr.length-1) resultQueue.push(resultArr[value.getY()][value.getX()+1]);
                    if(value.getY() > 0) resultQueue.push(resultArr[value.getY()-1][value.getX()]);
                    if(value.getY() < resultArr.length-1) resultQueue.push(resultArr[value.getY()+1][value.getX()]);
                }

                resultList.add(count); // 단지에 속하는 세대 수 더하기
            }
        }
    }

    public static void printResult() {
        System.out.println(resultList.size()); // 단지의 수 출력

        Collections.sort(resultList); // 오름차순으로 출력하기 위하여 정렬

        for (int value : resultList) {
            System.out.println(value); // 단지에 속하는 세대 수 출력력
        }
    }

    public static void mkResultArr(int height, String value) {
        for(int i = 0; i < value.length(); i++) {
            resultArr[height][i] = new Number(i, height, value.charAt(i) - 48);
        }
    }

    public static void initResultArr(int value) {
        resultArr = new Number[value][value];
    }

    public static void initResultList() {
        resultList = new LinkedList<>();
    }

    public static void initResultQueue() {
        resultQueue = new Stack<>();
    }
}
