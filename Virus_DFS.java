import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

class Computer {
    private int number;

    private List<Integer> link;

    public Computer(int number) {
        this.number = number;
        this.link = new LinkedList<>();
    }

    public int getNumber() {
        return this.number;
    }

    public List<Integer> getList() {
        return this.link;
    }
}

public class Virus_DFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Computer> stack = new Stack<>();

        StringTokenizer st;

        int com = Integer.parseInt(br.readLine());

        Computer[] arr = new Computer[com];
        boolean[] visit = new boolean[com];

        for(int i = 0; i < com; i++) {
            arr[i] = new Computer(i+1);
        }

        int links = Integer.parseInt(br.readLine());

        for(int i = 0; i < links; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            arr[from-1].getList().add(to);
            arr[to - 1].getList().add(from);
        }

        stack.push(arr[0]);
        int count = 0;

        while(!stack.isEmpty()) {
            Computer value = stack.pop();

            if(visit[value.getNumber()-1]) {
                continue;
            }

            visit[value.getNumber()-1] = true;
            count++;

            for(int s : value.getList()) {
                stack.push(arr[s-1]);
            }
        }

        System.out.println(count - 1);
    }
}
