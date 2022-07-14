import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Stack{
    private List<Integer> stack = new LinkedList<>();

    public int Top() {
        if(stack.isEmpty()) {
            return -1;
        }

        else return stack.get(stack.size()-1);
    }

    public int Size() {
        return stack.size();
    }

    public int Pop() {
        if(stack.isEmpty()) {
            return -1;
        }

        else {
            int pop = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            return pop;
        }

    }

    public int Empty() {
        if(stack.isEmpty()) {
            return 1;
        }
        else return 0;
    }

    public void Push(int item) {
        stack.add(item);
    }
}

public class Stack_Implementation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack stack = new Stack();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int i = 0; i < N; i++) {
            String command = br.readLine();

            if(command.contains(" ")) {
                st = new StringTokenizer(command, " ");
                String operation = st.nextToken();
                int number = Integer.parseInt(st.nextToken());

                if(operation.equals("push")) {
                    stack.Push(number);
                }
            }
            else {
                switch(command) {
                    case "top":
                        System.out.println(stack.Top());
                        break;

                    case "size":
                        System.out.println(stack.Size());
                        break;

                    case "pop":
                        System.out.println(stack.Pop());
                        break;

                    case "empty":
                        System.out.println(stack.Empty());
                        break;
                }
            }
        }
    }
}
