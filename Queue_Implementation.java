import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Queue {
    private List<Integer> queue = new LinkedList<>();

    public void push(Integer s) {
        queue.add(s);
    }
    
    public int pop() {
        if(queue.isEmpty()) {
            return -1;
        }
        else {
            return queue.remove(0);
        }
    }

    public int size() {
        return queue.size();
    }

    public int empty() {
        if(queue.isEmpty()) {
            return 1;
        }
        else return 0;
    }

    public int front() {
        if(queue.isEmpty()) {
            return -1;
        }
        else return queue.get(0);
    }

    public int back() {
        if(queue.isEmpty()) {
            return -1;
        }
        else return queue.get(queue.size()-1);
    }
}

public class Queue_Implementation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue queue = new Queue();
        int result = 0;

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            String command = br.readLine();

            if(command.contains(" ")) {
                StringTokenizer st = new StringTokenizer(command, " ");
                String str = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                if(str.equals("push")) {
                    queue.push(value);
                }
            }
            
            else {
                switch(command) {
                    case "pop" :
                        result = queue.pop();
                        System.out.println(result);
                        break;
                        
                    case "size" :
                        result = queue.size();
                        System.out.println(result);
                        break;
                        
                    case "empty":
                        result = queue.empty();
                        System.out.println(result);
                        break;
                        
                    case "front" :
                        result = queue.front();
                        System.out.println(result);
                        break;
                        
                    case "back" :
                        result = queue.back();
                        System.out.println(result);
                        break;
                }
            }

        }
    }
}
