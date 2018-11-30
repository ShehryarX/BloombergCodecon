import java.util.Scanner;

public class BitFlip {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] s = sc.nextLine().toCharArray();
        int[] nums = new int[32];

        for (int i = 0; i < s.length; i++) {
            nums[i] = Integer.parseInt(s[i] + "");
        }

        int max = 0;
        boolean checked = false;
        // flip ith bit
        for (int i = 0; i < 32; i++) {
            if (nums[i] == 0) {
                checked = true;
                int[] flip = flip(nums, i);
                max = Math.max(max, countOnes(flip));
            }
        }

        if (!checked) {
            max = 32;
        }


        System.out.println(max);
    }

    public static int[] flip(int[] orig, int flip) {
        int[] arr = new int[32];
        for (int i = 0; i < 32; i++) arr[i] = orig[i];
        arr[flip] = 1;
        return arr;
    }

    public static int countOnes(int[] arr) {
        int seq = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                ++seq;
                max = Math.max(max, seq);
            } else {
                seq = 0;
            }
        }

        return max;
    }
}
