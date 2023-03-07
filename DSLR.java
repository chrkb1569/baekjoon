import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

// D 값을 2배로 설정, 9999보다 클 경우에는 10000으로 나눈 나머지를 반환
// S 1을 뺀 결과 출력, 음수일 경우에는 9999반환
// L 가장 왼쪽의 값을 오른쪽에 넣어줌
// R 가장 오른쪽의 값을 왼쪽에 넣어줌

public class DSLR {

    public static StringBuilder sb = new StringBuilder();
    public static Queue<Integer> resultQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int test = Integer.parseInt(br.readLine());

        for(int i = 0; i < test; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            boolean[] visit = new boolean[10000];
            String[] command = new String[10000];
            initResultQueue();

            getMinCommand(visit, command, start, end);
        }

        System.out.print(sb.toString());
    }

    public static void getMinCommand(boolean[] visit, String[] command, int start, int end) {
        command[start] = "";
        resultQueue.offer(start);

        while(!resultQueue.isEmpty()) {
            int value = resultQueue.poll();

            if(value == end) {
                sb.append(command[value] + "\n");
                break;
            }

            if(visit[value]) continue;
            visit[value] = true;

            int D = calculateD(value);
            int S = calculateS(value);
            int L = calculateL(value);
            int R = calculateR(value);

            if(!visit[D]) {
                if(command[D] == null) command[D] = command[value] + "D";
                else {
                    int originLength = command[D].length();
                    int newLength = command[value].length();

                    if(originLength > newLength) command[D] = command[value] + "D";
                }

                resultQueue.offer(D);
            }

            if(!visit[S]) {
                if(command[S] == null) command[S] = command[value] + "S";
                else {
                    int originLength = command[S].length();
                    int newLength = command[value].length();

                    if(originLength > newLength) command[S] = command[value] + "S";
                }
                resultQueue.offer(S);
            }

            if(!visit[L]) {
                if(command[L] == null) command[L] = command[value] + "L";
                else {
                    int originLength = command[L].length();
                    int newLength = command[value].length();

                    if(originLength > newLength) command[L] = command[value] + "L";
                }
                resultQueue.offer(L);
            }

            if(!visit[R]) {
                if(command[R] == null) command[R] = command[value] + "R";
                else {
                    int originLength = command[R].length();
                    int newLength = command[value].length();

                    if(originLength > newLength) command[R] = command[value] + "R";
                }
                resultQueue.offer(R);
            }
        }
    }

    public static int calculateD(int value) {
        return (2 * value) % 10000;
    }

    public static int calculateS(int value) {
        return (value == 0)? 9999 : value - 1;
    }

    public static int calculateL(int value) {
        return (value % 1000) * 10 + (value / 1000);
    }

    public static int calculateR(int value) {
        return (value % 10) * 1000 + (value / 10);
    }

    public static void initResultQueue() {
        resultQueue = new LinkedList<>();
    }
}

//public class DSLR {
//
//    public static class Command {
//        private String command;
//        private boolean visit;
//
//        public Command(String command) {
//            this.command = command;
//            this.visit = false;
//        }
//
//        public String getCommand() {
//            return this.command;
//        }
//
//        public boolean isVisit() {
//            return this.visit;
//        }
//
//        public void setCommand(String command) {
//            if(this.command.equals("")) {
//                this.command = command;
//                return;
//            }
//
//            this.command = (this.command.length() < command.length())? this.command : command;
//        }
//
//        public void visit() {
//            this.visit = true;
//        }
//    }
//
//    public static Command[] resultArr;
//    public static Queue<Integer> resultQueue;
//    public static StringBuilder sb = new StringBuilder();
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        int test = Integer.parseInt(br.readLine());
//
//        for(int i = 0; i < test; i++) {
//            st = new StringTokenizer(br.readLine(), " ");
//            initResultQueue();
//            initResultArr();
//
//            int now = Integer.parseInt(st.nextToken());
//            int target = Integer.parseInt(st.nextToken());
//
//            getMinCommand(now, target);
//        }
//
//        System.out.println(sb.toString());
//    }
//
//    public static void getMinCommand(int now, int target) {
//        resultQueue.offer(now);
//
//        while(!resultQueue.isEmpty()) {
//            int value = resultQueue.poll();
//
//            if(value == target) break;
//            if(resultArr[value].isVisit()) continue;
//
//            resultArr[value].visit();
//            String command = resultArr[value].getCommand();
//
//            int D = calculateD(value);
//            int S = calculateS(value);
//            int L = calculateL(value);
//            int R = calculateR(value);
//
//            if(!resultArr[D].isVisit()) {
//                resultQueue.offer(D);
//                resultArr[D].setCommand(command + "D");
//            }
//
//            if(!resultArr[S].isVisit()) {
//                resultQueue.offer(S);
//                resultArr[S].setCommand(command + "S");
//            }
//
//            if(!resultArr[L].isVisit()) {
//                resultQueue.offer(L);
//                resultArr[L].setCommand(command + "L");
//            }
//
//            if(!resultArr[R].isVisit()) {
//                resultQueue.offer(R);
//                resultArr[R].setCommand(command + "R");
//            }
//        }
//
//        sb.append(resultArr[target].getCommand() + "\n");
//    }
//    // D 값을 2배로 설정, 9999보다 클 경우에는 10000으로 나눈 나머지를 반환
//    public static int calculateD(int value) {
//        return (2 * value) % 10000;
//    }
//
//    // S 1을 뺀 결과 출력, 음수일 경우에는 9999반환
//    public static int calculateS(int value) {
//        return (value == 0)? 9999 : value - 1;
//    }
//
//    // L 가장 왼쪽의 값을 오른쪽에 넣어줌
//    public static int calculateL(int value) {
//        int first = value / 1000;
//        int second = value % 1000 / 100;
//        int third = value % 100 / 10;
//        int forth = value % 10;
//
//       return 1000 * second + 100 * third + 10 * forth + first;
//    }
//
//    // R 가장 오른쪽의 값을 왼쪽에 넣어줌
//    public static int calculateR(int value) {
//        int first = value / 1000;
//        int second = value % 1000 / 100;
//        int third = value % 100 / 10;
//        int forth = value % 10;
//
//        return 1000 * forth + 100 * first + 10 * second + third;
//    }
//
//    public static void initResultQueue() {
//        resultQueue = new LinkedList<>();
//    }
//
//    public static void initResultArr() {
//        resultArr = new Command[10000];
//
//        for(int i = 0; i < 10000; i++) {
//            resultArr[i] = new Command("");
//        }
//    }
//}