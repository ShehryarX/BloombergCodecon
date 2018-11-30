import java.util.*;

public class FastestFlight {
    static ArrayList<Flight[]> permutations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();

        Flight[] flights = new Flight[R];

        for (int i = 0; i < R; i++) {
            String cityCode = sc.next();
            String destination = sc.next();
            int duration = sc.nextInt();
            int N = sc.nextInt();
            int[] deperatures = new int[N];
            for (int x = 0; x < N; x++) {
                deperatures[x] = sc.nextInt();
            }

            flights[i] = new Flight(cityCode, destination, duration, deperatures);
//            System.out.println(flights[i]);
        }

        String startingLocation = sc.next();
        String destination = sc.next();
        int intialDepartureTime = sc.nextInt();

        generatePermutations(flights.length, flights);
        int min = Integer.MAX_VALUE;

        for (Flight[] perm : permutations) {
            TreeSet<Flight> path = new TreeSet<>();

            boolean flag = true;
            for (int i = 0; i < perm.length; i++) {
                if (perm[0].cityCode.equals(startingLocation)) {
                    path.add(perm[i]);
                } else {
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                break;
            }
            if (path.size() == 0 || !path.last().cityCode.equals(destination)) {
                break;
            }

            int total = 0;

            for (Flight p : path) {
                total += p.duration;
            }

            min = Math.min(min, total);

//            System.out.println(total);
            // simulate flight journey
        }

        if (1440 - min - intialDepartureTime < 0) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(min);
        }

    }

    static void generatePermutations(int n, Flight[] array) {
        if (n == 1) {
            Flight[] fff = Arrays.copyOf(array, array.length);
            permutations.add(fff);
            return;
        }
        for (int i = 0; i < n; i++) {
            generatePermutations(n - 1, array);
            if ((n & 1) == 0) {
                Flight tmp = array[i];
                array[i] = array[n - 1];
                array[n - 1] = tmp;
            } else {
                Flight tmp = array[0];
                array[0] = array[n - 1];
                array[n - 1] = tmp;

            }
        }
    }

    public static boolean contains(Flight[] f, Flight g, int k) {
//        System.out.println("checking if " + g + " is in " + Arrays.toString(f));
        for (int i = 0; i < k; i++) {
            if (f[i].cityCode == g.cityCode && f[i].departures == g.departures) {
                return true;
            }
        }

        return false;
    }

    public static class Flight implements Comparable {
        String cityCode;
        String destination;
        int duration;
        int[] departures;

        public Flight(String cityCode, String destination, int duration, int[] departures) {
            this.cityCode = cityCode;
            this.destination = destination;
            this.duration = duration;
            this.departures = departures;
        }

        @Override
        public String toString() {
            return cityCode;
        }


        @Override
        public int compareTo(Object o) {
            Flight fff = (Flight) o;

            if (fff.cityCode.equals(this.cityCode)) {
                return 0;
            } else return 1;
        }
    }
}
