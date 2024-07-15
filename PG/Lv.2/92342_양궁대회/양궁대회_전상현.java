/**
 * 재귀
 */

class Solution {
    //라이언이 (10 - i) 점수에 쏜 화살의 개수
    private static int[] ryon = new int[11];
    //현재 result 값
    private static int[] result = new int[11];
    //가장 큰 점수차
    private static int max = Integer.MIN_VALUE;

    public int[] solution(int n, int[] info) {
        rec(0, n, info,0);

        if (max == -1) {
            result = new int[1];
            result[0] = -1;
        }

        return result;
    }

    private static void rec(int L, int n, int[] info, int start) {
        if (L == n) {
            int diff = score(info);

            if (max < diff) {
                max = diff;
                result = ryon.clone();
            } else if (max == diff) {
                //최고 점수차랑 같을 경우 총 합을 더해서 현재 최고 점수차 조합보다 작을 경우 업데이트 해줌
                int o = 0;
                int p = 0;
                for (int i = 0; i <= 10; i++) {
                    o += ((10 - i) * result[i]);
                    p += ((10 - i) * ryon[i]);
                }
                if (p < o) {
                    result = ryon.clone();
                }

            }

            return;
        }
        int i;
        for (i = start; i <= 10; i++) {
            //라이언이 어피치보다 많이 쐈다면 다음 점수로 넘어감
            if (ryon[i] > info[i]) continue;
            ryon[i]++;
            rec(L + 1, n, info, i);
            ryon[i]--;
        }
    }

    private static int score(int[] info) {
        int pSum = 0;
        int rSum = 0;
        int i;
        for (i = 0; i < 11; i++) {
            if (ryon[i] == 0 && info[i] == 0) continue;
            if (ryon[i] > info[i]) {
                rSum += (10 - i);
            } else {
                pSum += (10 - i);
            }
        }

        if (pSum >= rSum) {
            return -1;
        }
        return (rSum - pSum);
    }
}