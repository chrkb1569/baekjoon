import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    private int number;

    private List<Integer> link;

    public Node(int number) {
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

public class Virus_BFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Node> queue = new LinkedList<>();
        StringTokenizer st;

        int com = Integer.parseInt(br.readLine());

        Node[] arr = new Node[com];
        boolean[] visit = new boolean[com];

        for(int i = 0; i < com; i++) {
            arr[i] = new Node(i+1);
        }

        int links = Integer.parseInt(br.readLine());

        for(int i = 0; i < links; i++) {
            String link = br.readLine();
            st = new StringTokenizer(link, " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            arr[from-1].getList().add(to);
            arr[to-1].getList().add(from);
        }

        int count = 0;

        queue.offer(arr[0]);

        while(!queue.isEmpty()) {
            Node value = queue.poll();

            if(visit[value.getNumber()-1]) {
                continue;
            }

            count++;
            visit[value.getNumber()-1] = true;

            for(int s : value.getList()) {
                queue.offer(arr[s-1]);
            }
        }

        System.out.println(count - 1);
    }
}