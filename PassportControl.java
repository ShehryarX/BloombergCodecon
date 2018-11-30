import java.util.Arrays;
import java.util.Scanner;

public class PassportControl {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 1 passenger = 1 minute;
        // each group = 1 agent
        // assigned to lowest # agent
        // after checking 10 groups, agent takes 5 mins time off
        // how many mans go through each booth?

        int n = sc.nextInt();
        Booth[] booths = new Booth[n];
        for (int i = 0; i < n; i++) {
            booths[i] = new Booth(false, false, 0);
        }

        int m = sc.nextInt();
        int[] groups = new int[m];

        for (int i = 0; i < m; i++) {
            groups[i] = sc.nextInt();
        }

        int currentIndex = 0;

        while (currentIndex < m || boothIsWorking(booths)) {
            int next = findNextAvailableBooth(booths);
            if (currentIndex >= m) {
                next = -1;
            }
//            System.out.println("Assigned " + groups[currentIndex] + " to booth " + next);
//            for (int i = 0; i < n; i++) {
//                System.out.println("BOOTH " + i + " " + booths[i]);
//            }
            if (next == -1) {
//                System.out.println("working...");
                // work

                for (int i = 0; i < booths.length; i++) {
                    if (booths[i].isWorking) {
                        booths[i].customers--;
                        if (booths[i].customers == 0) {
                            booths[i].handled++;
                            if (booths[i].handled == 10) {
                                booths[i].isWorking = false;
                                booths[i].isTakingBreak = true;
                                booths[i].breakTime = 5;
                            } else {
                                booths[i].isWorking = false;
                            }
                        }
                    } else if (booths[i].isTakingBreak) {
                        booths[i].breakTime--;
                        if (booths[i].breakTime <= 0) {
                            booths[i].isTakingBreak = false;
                        }
                    }
                }
            } else {
                // assign new load
                booths[next].isWorking = true;
                booths[next].customers = groups[currentIndex++];
//                booths[next].customers--;
//                if (booths[next].customers == 0) {
//                    booths[next].handled++;
//                    if (booths[next].handled == 10) {
//                        booths[next].isWorking = false;
//                        booths[next].isTakingBreak = true;
//                        booths[next].breakTime = 5;
//                    } else {
//                        booths[next].isWorking = false;
//                    }
//                }
            }
        }

        // print status
        for (int i = 0; i < n; i++) {
            System.out.print(booths[i].handled + " ");
        }

    }

    public static int findNextAvailableBooth(Booth[] booths) {
        for (int i = 0; i < booths.length; i++) {
            if (!booths[i].isTakingBreak && !booths[i].isWorking && booths[i].customers == 0) {
                return i;
            }
        }

        return -1;
    }

    public static boolean boothIsWorking(Booth[] booths) {
        for (int i = 0; i < booths.length; i++) {
            if (booths[i].isWorking) return true;
        }
        return false;
    }

    public static class Booth {
        boolean isTakingBreak;
        boolean isWorking;
        int customers;
        int breakTime;
        int handled;

        public Booth(boolean isTakingBreak, boolean isWorking, int customers) {
            this.isTakingBreak = isTakingBreak;
            this.isWorking = isWorking;
            this.customers = customers;
            this.breakTime = 0;
            this.handled = 0;
        }

        @Override
        public String toString() {
            return "Booth{" +
                    "isTakingBreak=" + isTakingBreak +
                    ", isWorking=" + isWorking +
                    ", customers=" + customers +
                    '}';
        }
    }
}
