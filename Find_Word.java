import java.io.*;

public class Find_Word {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        String answer = br.readLine();
        int count = 0;

        for(int i = 0; i < str.length();) {
            if(i <= str.length() - answer.length()) {
                String match = str.substring(i, answer.length() + i);
                if(match.equals(answer)) {
                    count++;
                    i += answer.length();
                }
                else i++;
            }
            else i++;
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}