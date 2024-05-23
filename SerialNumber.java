import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SerialNumber {
    private static PriorityQueue<Component> resultQueue = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int number = 0; number < N; number++) {
            resultQueue.add(new Component(br.readLine()));
        }

        printResult();
    }

    private static void printResult() {
        StringBuffer sb = new StringBuffer();

        while(!resultQueue.isEmpty()) {
            Component component = resultQueue.poll();

            sb.append(component.getOriginValue()).append("\n");
        }

        System.out.println(sb);
    }

    private static class Component implements Comparable<Component> {
        private String originValue;
        private int length;
        private int sum;

        public Component(String originValue) {
            this.originValue = originValue;
            this.length = originValue.length();

            String trimValue = originValue.replaceAll("[^0-9]", "");

            if(trimValue.isEmpty() || trimValue.isBlank()) {
                this.sum = 0;
                return;
            }

            this.sum = Arrays.stream(trimValue.split(""))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .sum();
        }

        public String getOriginValue() {
            return this.originValue;
        }

        public int getLength() {
            return this.length;
        }

        public int getSum() {
            return this.sum;
        }

        @Override
        public int compareTo(Component component) {
            if(this.getLength() != component.getLength()) return this.length - component.getLength();
            if(this.getSum() == component.getSum()) return this.originValue.compareTo(component.getOriginValue());
            return this.getSum() - component.getSum();
        }
    }
}
