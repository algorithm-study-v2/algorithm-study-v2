/**
 * 조합, 재귀
 *
 */

class Solution {
    //할인율 조합 배열
    private static int[][] price;
    private static int[][] user;
    //판매액 최대값
    private static int maxPrice = Integer.MIN_VALUE;
    //이모티콘 플러스 가입자 최대값
    private static int maxPeople = Integer.MIN_VALUE;

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        price = new int[5][emoticons.length];
        user = users;

        //각 이모티콘에 대해서 10.20.30.40% 할인했을 때의 금액 계산
        setSaleMoney(emoticons);

        rec(0, emoticons.length, new int[emoticons.length]);

        answer[0] = maxPeople;
        answer[1] = maxPrice;
        return answer;
    }

    private static void rec(int l, int n, int[] discount) {
        if (l == n) {
            comparsion(discount);
            return;
        }

        for (int i = 1; i <= 4; i++) {
            discount[l] = i;
            rec(l + 1, n, discount);
        }

    }

    private static void comparsion(int[] discount) {
        int peopleCnt = 0;
        int totalPrice = 0;
        for (int[] u : user) {
            int userMoney = u[1];
            int buy = 0;
            for (int j = 0; j < discount.length; j++) {
                //이모티콘 구매했을 경우
                if (u[0] <= (discount[j] * 10)) {
                    buy += price[discount[j]][j];
                }
                //구매한 금액이 기준 금액을 넘었을 경우 플러스 가입 증가
                if (buy >= userMoney) {
                    peopleCnt++;
                    buy = -1;
                    break;
                }
            }
            if (buy != -1) totalPrice += buy;
        }

        if (peopleCnt < maxPeople) return;

        compare(peopleCnt, totalPrice);
    }

    private static void setSaleMoney(int[] emoticons) {
        double percent = 0.1;

        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < emoticons.length; j++) {
                price[i][j] = (int) (emoticons[j] - (percent * emoticons[j]));
            }
            percent += 0.1;
        }
    }
    //이모티콘 가입자, 판매액 비교
    private static void compare(int people, int price) {
        if (maxPeople < people) {
            maxPeople = people;
            maxPrice = price;
        } else if (maxPeople == people) {
            if (maxPrice < price) {
                maxPrice = price;
            }
        }
    }
}
