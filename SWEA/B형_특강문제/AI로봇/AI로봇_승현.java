import java.util.*;

public class AI로봇_승현 {
    private static Robot[] robots;
    private static int[][] work;
    private static RobotPQ robotPQ;

    static class Robot implements Comparable<Robot> {
        public int id;
        public int wbTime;
        public int wID;
        public boolean isBroken;
        public boolean isWorking;
        public int jobStartTime;

        public Robot(int id, int wbTime, int wID, boolean isBroken, boolean isWorking) {
            this.id = id;
            this.wbTime = wbTime;
            this.wID = wID;
            this.isBroken = isBroken;
            this.isWorking = isWorking;
        }

        public Robot(int id, int wbTime) {
            this.id = id;
            this.wbTime = wbTime;
        }

        @Override
        public int compareTo(Robot o) {
            if (this.wbTime == o.wbTime) {
                return this.id - o.id;
            }
            else {
                return o.wbTime - this.wbTime;
            }
        }
    }

    static class RobotPQ {
        TreeSet<Robot> robotList;

        public RobotPQ() {
            robotList = new TreeSet<>();
        }

        public void add(Robot r) {
            robotList.add(r);
        }

        public void remove(Robot r) {
            robotList.remove(r);
        }

        public Robot peekFirst() {
            return robotList.first();
        }

        public Robot peekLast() {
            return robotList.last();
        }

        public TreeSet<Robot> subset(int mOpt) {
            if (mOpt == 0) {
                return (TreeSet<Robot>) robotList.tailSet(new Robot(100000, peekLast().wbTime + 1), false);
            }
            else {
                return (TreeSet<Robot>) robotList.headSet(new Robot(100000, peekFirst().wbTime - 1), false);
            }
        }
    }

    public static void init(int n) {
        robots = new Robot[n+1];
        work = new int[50001][];
        robotPQ = new RobotPQ();

        for (int i = 1; i <= n; i++) {
            robots[i] = new Robot(i, 0, 0, false, false);
            robotPQ.add(robots[i]);
        }
    }

    public static int callJob(int cTime, int wID, int mNum, int mOpt) {
        work[wID] = new int[mNum];

        int idSum = 0;
        int idx = 0;
        TreeSet<Robot> subset = robotPQ.subset(mOpt);
        while (idx < mNum) {
            if (subset.isEmpty()) {
                subset = robotPQ.subset(mOpt);
            }
            Robot r = subset.pollFirst();
            if (r.isBroken || r.isWorking) {
                continue;
            }
            robotPQ.remove(r);
            r.jobStartTime = cTime;
            r.isWorking = true;
            r.wID = wID;
            idSum += r.id;
            work[wID][idx++] = r.id;
        }

        return idSum;
    }

    public static void returnJob(int cTime, int wID) {
        for (int i = 0; i < work[wID].length; i++) {
            Robot r = robots[work[wID][i]];
            if (r.wID != wID) {
                continue;
            }
            if (r.isWorking) {
                r.wbTime += cTime - r.jobStartTime;
                r.isWorking = false;
                robotPQ.add(r);
            }
        }
        work[wID] = null;
    }

    public static void broken(int cTime, int rID) {
        Robot r = robots[rID];
        if (r.isBroken || !r.isWorking) {
            return;
        }
        robotPQ.remove(r);
        r.isBroken = true;
        r.isWorking = false;
        r.wID = 0;
    }

    public static void repair(int cTime, int rID) {
        Robot r = robots[rID];
        if (!r.isBroken) {
            return;
        }
        r.isBroken = false;
        r.wbTime = cTime;
        robotPQ.add(r);
    }

    public static int check(int cTime, int rID) {
        Robot r = robots[rID];
        if (r.isBroken) {
            return 0;
        }
        else if (r.isWorking) {
            return -r.wID;
        }
        else {
            return cTime - r.wbTime;
        }
    }
}