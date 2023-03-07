import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Deque_Implement {

    public static class DequeImpl {
        private List<Integer> list;

        public DequeImpl() {
            this.list = new LinkedList<>();
        }

        public void push_front(int value) {
            list.add(0, value);
        }

        public void push_back(int value) {
            list.add(value);
        }

        public int pop_front() {
            if(this.list.isEmpty()) return -1;
            return list.remove(0);
        }

        public int pop_back() {
            if(this.list.isEmpty()) return -1;
            return list.remove(list.size() - 1);
        }

        public int size() {
            return this.list.size();
        }

        public int empty() {
            return (this.list.isEmpty())? 1 : 0;
        }

        public int front() {
            if(list.isEmpty()) return -1;
            return this.list.get(0);
        }

        public int back() {
            if(list.isEmpty()) return -1;
            return this.list.get(list.size() - 1);
        }
    }

    public static StringBuilder sb = new StringBuilder();
    public static DequeImpl deque = new DequeImpl();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int cases = Integer.parseInt(br.readLine());

        for(int i = 0; i < cases; i++) {
            String command = br.readLine();

            if(command.contains(" ")) {
                st = new StringTokenizer(command, " ");
                processWithBlank(st);
                continue;
            }

            processWithoutBlank(command);
        }

        System.out.println(sb.toString());
    }

    public static void processWithBlank(StringTokenizer st) {
        String command = st.nextToken();
        int value = Integer.parseInt(st.nextToken());

        switch(command) {
            case "push_front":
                deque.push_front(value);
                break;

            case "push_back":
                deque.push_back(value);
                break;
        }
    }

    public static void processWithoutBlank(String command) {
        switch(command) {
            case "front":
                sb.append(deque.front() + "\n");
                break;

            case "back":
                sb.append(deque.back() + "\n");
                break;

            case "size":
                sb.append(deque.size() + "\n");
                break;

            case "empty":
                sb.append(deque.empty() + "\n");
                break;

            case "pop_front":
                sb.append(deque.pop_front() + "\n");
                break;

            case "pop_back":
                sb.append(deque.pop_back() + "\n");
                break;
        }
    }
}
