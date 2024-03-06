import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class ABSHeap {
    private static PriorityQueue<Element> resultQueue = new PriorityQueue<>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int commands = Integer.parseInt(br.readLine());

        // userInput != 0 -> 배열에 값을 넣어라
        // userInput == 0 -> 절댓값이 가장 작은 값을 출력하고, 제거하라
        // 배열이 비어있는 경우, 0을 출력
        for(int command = 0; command < commands; command++) {
            int userInput = Integer.parseInt(br.readLine());

            processCommand(userInput);
        }

        System.out.println(sb);
    }

    private static void processCommand(int command) {
        if(command != 0) {
            resultQueue.add(new Element(command));
            return;
        }

        if(resultQueue.isEmpty()) {
            sb.append(0).append("\n");
            return;
        }

        Element element = resultQueue.poll();
        sb.append(element.getNumber()).append("\n");
    }

    private static class Element implements Comparable<Element> {
        private final int number;

        public Element(int number) {
            this.number = number;
        }

        public int getNumber() {
            return this.number;
        }

        @Override
        public int compareTo(Element element) {
            if(Math.abs(this.number) == Math.abs(element.getNumber())) return this.number - element.getNumber();
            return Math.abs(this.number) - Math.abs(element.getNumber());
        }
    }
}
