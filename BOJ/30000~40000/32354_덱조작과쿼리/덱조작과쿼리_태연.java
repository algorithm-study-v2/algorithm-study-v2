import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 덱조작과쿼리_태연 {
    
    /*
     * - 570ms
     *
     * - 링크드리스트
     */

    static final String PRINT = "print";
    static final String PUSH = "push";
    static final String POP = "pop";
    static final String RESTORE = "restore";

    static class Node{
        long value;
        Node prev;

        Node(long value){
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Node[] log = new Node[n+1];
        log[0] = new Node(0L);
        log[0].prev = log[0];
        Node ptr = log[0];
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String op = st.nextToken();
            if(op.equals(PRINT)){
                sb.append(ptr.value).append('\n');
            } else if(op.equals(PUSH)){
                Node newLog = new Node(ptr.value + Long.parseLong(st.nextToken()));
                newLog.prev = ptr;
                ptr = newLog;
            } else if(op.equals(POP)){
                ptr = ptr.prev;
            } else if(op.equals(RESTORE)){
                ptr = log[Integer.parseInt(st.nextToken())];
            }

            log[i+1] = ptr;
        }

        System.out.print(sb);
    }
}
