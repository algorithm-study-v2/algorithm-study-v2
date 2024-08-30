import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 하이퍼토마토_태연 {

    /*
     * - 2272ms
     *
     * - 11차원 bfs
     */

    static int[][] movement = new int[][] { { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 } };

    static int[] dim = new int[11];
    static Queue<int[]> queue = new LinkedList<>();
    static int totalTomatoes = 0;
    static int ripeTomatoes = 0;

    static boolean inRange(int[] pos) {
        for (int i = 0; i < 11; i++) {
            if (pos[i] < 0 || pos[i] >= dim[10-i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 10; i >= 0; i--) {
            dim[i] = Integer.parseInt(st.nextToken());
        }

        int[][][][][][][][][][][] arr = new int[dim[10]][dim[9]][dim[8]][dim[7]][dim[6]][dim[5]][dim[4]][dim[3]][dim[2]][dim[1]][dim[0]];

        for (int m = 0; m < dim[0]; m++) {
            for (int n = 0; n < dim[1]; n++) {
                for (int o = 0; o < dim[2]; o++) {
                    for (int p = 0; p < dim[3]; p++) {
                        for (int q = 0; q < dim[4]; q++) {
                            for (int r = 0; r < dim[5]; r++) {
                                for (int s = 0; s < dim[6]; s++) {
                                    for (int t = 0; t < dim[7]; t++) {
                                        for (int u = 0; u < dim[8]; u++) {
                                            for (int v = 0; v < dim[9]; v++) {
                                                st = new StringTokenizer(br.readLine());
                                                for (int w = 0; w < dim[10]; w++) {
                                                    int value = Integer.parseInt(st.nextToken());
                                                    arr[w][v][u][t][s][r][q][p][o][n][m] = value;
                                                    if (value == 1) {
                                                        queue.add(new int[] {w, v, u, t, s, r, q, p, o, n, m});
                                                        ripeTomatoes++;
                                                    }
                                                    if (value != -1) {
                                                        totalTomatoes++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (ripeTomatoes == totalTomatoes) {
            System.out.println(0);
            return;
        }

        int days = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean ripened = false;

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                for (int[] move : movement) {
                    int[] newPos = new int[11];
                    for (int j = 0; j < 11; j++) {
                        newPos[j] = current[j] + move[j];
                    }

                    if (inRange(newPos) && arr[newPos[0]][newPos[1]][newPos[2]][newPos[3]][newPos[4]][newPos[5]][newPos[6]][newPos[7]][newPos[8]][newPos[9]][newPos[10]] == 0) {
                        arr[newPos[0]][newPos[1]][newPos[2]][newPos[3]][newPos[4]][newPos[5]][newPos[6]][newPos[7]][newPos[8]][newPos[9]][newPos[10]] = 1;
                        queue.add(newPos);
                        ripeTomatoes++;
                        ripened = true;
                    }
                }
            }

            if (ripened) {
                days++;
            }
        }

        if (ripeTomatoes == totalTomatoes) {
            System.out.println(days);
        } else {
            System.out.println(-1);
        }
    }
}
