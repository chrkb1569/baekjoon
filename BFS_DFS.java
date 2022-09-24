import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;
import java.util.Stack;

class Node_1260 {
    private int number;
    private List<Integer> link;

    public Node_1260(int number) {
        this.number = number;
        this.link = new LinkedList<>();
    }

    public int getNumber() {
        return this.number;
    }

    public List<Integer> getLink() {
        return this.link;
    }
}

class Compare implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }
}

class CompareSort implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
}

public class BFS_DFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int nodeNumber = Integer.parseInt(st.nextToken());
        int lineNumber = Integer.parseInt(st.nextToken());
        int startNumber = Integer.parseInt(st.nextToken());

        Node_1260[] node = new Node_1260[nodeNumber+1];
        boolean[] visit = new boolean[nodeNumber + 1];

        for(int i = 1; i <= nodeNumber; i++) {
            node[i] = new Node_1260(i);
        }

        for(int i = 0; i < lineNumber; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            node[num1].getLink().add(num2);
            node[num2].getLink().add(num1);
        }

        getDFS(node, visit, startNumber);
        visit = new boolean[nodeNumber + 1];
        System.out.println();
        getBFS(node, visit, startNumber);

    }

    public static void getDFS(Node_1260[] node, boolean[] visit, int start) {
        Stack<Integer> stack = new Stack<>();

        stack.push(start);

        while(!stack.isEmpty()) {
            Node_1260 value = node[stack.pop()];

            if(visit[value.getNumber()]) {
                continue;
            }

            visit[value.getNumber()] = true;

            List<Integer> lst = value.getLink();
            Collections.sort(lst, new Compare());

            for(int s : lst) {
                stack.push(s);
            }

            System.out.print(value.getNumber() + " ");

        }
    }

    public static void getBFS(Node_1260[] node, boolean[] visit, int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);

        while(!queue.isEmpty()) {
            Node_1260 value = node[queue.poll()];

            if(visit[value.getNumber()]) continue;

            visit[value.getNumber()] = true;

            List<Integer> lst = value.getLink();

            Collections.sort(lst, new CompareSort());

            for(int s : value.getLink()) {
                queue.offer(s);
            }

            System.out.print(value.getNumber() + " ");
        }
    }
}
