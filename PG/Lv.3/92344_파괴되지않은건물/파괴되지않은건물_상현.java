/**
 * IMOS 
 * 누적합
 */
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] prefixSum = new int[board.length + 1][board[0].length + 1];

        for(int[] info : skill) {
            calcPrefix(info[0], info[1], info[2], info[3], info[4], info[5], prefixSum);
        }
        int i = 0;
        int j = 0;
        calcRow(prefixSum);
        calcCol(prefixSum);

        for(i = 0; i < board.length; i++) {
            for(j = 0; j < board[0].length; j++) {
                board[i][j] += prefixSum[i][j];
                if(board[i][j] > 0) answer++;
            }
        }
        return answer;
    }

    private static void calcRow(int[][] prefixSum) {
        int i = 0;
        int j = 0;

        for(i = 0; i < prefixSum.length; i++) {
            for(j = 0; j < prefixSum[0].length; j++) {
                if(j != 0) prefixSum[i][j] += prefixSum[i][j -1];
            }
        }
    }

    private static void calcCol(int[][] prefixSum) {
        int i = 0;
        int j = 0;

        for(i = 0; i < prefixSum[0].length; i++) {
            for(j = 0; j < prefixSum.length; j++) {
                if(j != 0) prefixSum[j][i] += prefixSum[j - 1][i];
            }
        }
    }
    private static void calcPrefix(int active, int r1, int c1, int r2, int c2, int degree, int[][] prefixSum) {
        if(active == 1) {
            prefixSum[r1][c1] += (-degree);
            prefixSum[r1][c2 + 1] += degree;
            prefixSum[r2 + 1][c1] += degree;
            prefixSum[r2 + 1][c2 + 1] += (-degree);
        } else if(active == 2) {
            prefixSum[r1][c1] += degree;
            prefixSum[r1][c2 + 1] += (-degree);
            prefixSum[r2 + 1][c1] += (-degree);
            prefixSum[r2 + 1][c2 + 1] += degree;
        }
    }
}
