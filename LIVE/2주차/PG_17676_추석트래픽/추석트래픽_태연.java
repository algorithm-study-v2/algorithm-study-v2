import java.util.PriorityQueue;

class 추석트래픽_태연 {
    
    static final int HOUR = 60*60*1000;
    static final int MINUTE = 60*1000;
    static final int SECOND = 1000;
    
    static String[] data;
    static String[] timeLog;
    
    static class Log implements Comparable<Log>{
        int time;
        int isEnd;
        
        Log(int time, int isEnd){
            this.time=time;
            this.isEnd=isEnd;
        }
        
        public int compareTo(Log l){
            if(this.time==l.time){
                return this.isEnd - l.isEnd;
            } else {
                return this.time - l.time;
            }
        }
    }
    
    static int getEndTime(String str){
        timeLog = str.split(":");
        
        return Integer.parseInt(timeLog[0])*HOUR
            + Integer.parseInt(timeLog[1])*MINUTE
            + (int) (Double.parseDouble(timeLog[2]) * SECOND);
    }
    
    static int getDuration(String str){
        return (int) (Double.parseDouble(str.split("s")[0]) * SECOND);
    }
    
    public int solution(String[] lines) {
        
        int nLines = lines.length;
        PriorityQueue<Log> logQueue = new PriorityQueue<>();
        
        int endTime;
        int duration;
        
        for(String line : lines){
            data = line.split(" ");
                        
            endTime = getEndTime(data[1]);
            duration = getDuration(data[2]);
                        
            // 시작시간
            logQueue.add(new Log(endTime - duration + 1, 0));
            
            // 끝나는시간 + 0.999s 간격
            logQueue.add(new Log(endTime+999, 1));
        }
        
        int maxCnt = 0;
        int curCnt = 0;
        int startCnt = 0;
        Log log;
        
        while(!logQueue.isEmpty() && startCnt < nLines){
            log = logQueue.poll();
            
            if(log.isEnd == 0){
                if(++curCnt > maxCnt) {
                    maxCnt = curCnt;
                }
                startCnt++;
            } else {
                curCnt--;
            }
        }
        
        return maxCnt;
    }
}