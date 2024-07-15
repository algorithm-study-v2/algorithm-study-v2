import java.util.*;

/**
 * 구현?...문자열?...
 */
class Solution {
    public int[] solution(int[] fees, String[] records) {
        //0~9999번 까지의 차 번호 배열 생성
        boolean[] car = new boolean[10000];
        //i번째 차의 총합 저장
        int[] total = new int[10000];
        //키 : 차 번호, 입차 시간 저장
        HashMap<Integer, Integer> timeRecord = new HashMap<>();
        int defaultTime = fees[0], defaultPrice = fees[1], unitTime = fees[2], unitPrice = fees[3];
        int maxTime = (23 * 60) + 59;
        for (String log : records) {
            String[] record = log.split(" ");
            String[] time = record[0].split(":");
            //시간 계산
            int totalTime = (Integer.parseInt(time[0]) * 60) + Integer.parseInt(time[1]);
            int num = Integer.parseInt(record[1]);
            //입차일 경우 car 배열에 true 처리 후 timeRecord Map에 입차 시간 저장
            //출차일 경우 timeRecord에서 입차 시간 가져온 후 빼준 후 total에 저장
            if (record[2].equals("IN")) {
                car[num] = true;
                timeRecord.put(num, totalTime);
            } else if (record[2].equals("OUT")) {
                car[num] = false;
                total[num] += (totalTime - timeRecord.get(num));
                timeRecord.put(num,0);
            }
        }

        int[] sort = new int[timeRecord.size()];
        int[] answer = new int[timeRecord.size()];
        int i = 0;

        for (Integer carNum : timeRecord.keySet()) {
            sort[i++] = carNum;
            //만약 입차 상태라면 23:59분에서 입차 시간을 빼준 값을 더해줌
            if (car[carNum]) {
                total[carNum] += (maxTime - timeRecord.get(carNum));
            }
            //전체 시간이 기본 시간보다 작다면 기본 요금으로 처리
            if (total[carNum] <= defaultTime) {
                timeRecord.put(carNum, defaultPrice);
            } else {
                int price = (int) (defaultPrice + (Math.ceil(((double) (total[carNum] - defaultTime) / unitTime)) * unitPrice));
                timeRecord.put(carNum, price);
            }
        }

        Arrays.sort(sort);

        i = 0;
        for (int c : sort) {
            answer[i++] = timeRecord.get(c);
        }

        return answer;
    }
}