//Problem        : A Very Odd Problem
//Language       : Java
//Compiled Using : javac
//Version        : Java 1.7.0_75
//Input for your program will be provided from STDIN
//Print out all output from your program to STDOUT

import java.util.Scanner;
//Your submission should *ONLY* use the following class name
public class AVeryOddProblem
{
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (sc.nextInt() % 2 == 0) ++cnt;
        }

        if (cnt < 2) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }

}
