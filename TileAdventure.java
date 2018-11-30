import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class TileAdventure {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int iterations = sc.nextInt();

        char[][] grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = sc.next().charAt(0);
            }
        }
        int currentRow = 0;
        int currentCol = 0;
        System.out.print(grid[0][0]);
        for (int i = 0; i < iterations; i++) {
            Option[] options = new Option[4];
            for (int cnt = 0; cnt < 4; cnt++) {
                options[cnt] = new Option(-1, 'z');
            }
            // look around
            // check left
            if (currentRow != 0) {
                options[0] = new Option(0, grid[currentRow - 1][currentCol]);
            }
            // up
            if (currentCol != 0) {
                options[1] = new Option(1, grid[currentRow][currentCol - 1]);
            }
            // down
            if (currentCol != cols - 1) {
                options[2] = new Option(2, grid[currentRow][currentCol + 1]);
            }
            // right
            if (currentRow != rows - 1) {
                options[3] = new Option(3, grid[currentRow + 1][currentCol]);
            }


            Arrays.sort(options);

            boolean flag = false;
            // check if first is less than all
            for (int j = 1; j < 4; j++) {
                if (options[j].val == options[0].val) {
                    flag = true;
                }
            }

            if (flag) {
                // check rows
                if (currentRow - 1 >= 0) {
                    // go left
                    System.out.print(grid[--currentRow][currentCol]);
                } else if (currentCol - 1 >= 0) {
                    System.out.print(grid[currentRow][--currentCol]);
                    // go up
                } else {
                    // go right
                    System.out.print(grid[++currentRow][currentCol]);
                }

            } else {
                int direction = options[0].pos;

                for (int x = 0; x < 4; x++) {
                    if (options[x].val != grid[currentRow][currentCol]) {
                        direction = options[x].pos;
                        break;
                    }
                }
                if (direction == 0) {
                    // go left
                    System.out.print(grid[--currentRow][currentCol]);
                } else if (direction == 1) {
                    // go up
                    System.out.print(grid[currentRow][--currentCol]);
                } else if (direction == 2) {
                    // go down
                    System.out.print(grid[currentRow][++currentCol]);
                } else {
                    // go right
                    System.out.print(grid[++currentRow][currentCol]);
                }
            }
        }
    }

    static class Option implements Comparable {
        int pos;
        char val;

        public Option(int pos, char val) {
            this.pos = pos;
            this.val = val;
        }

        @Override
        public int compareTo(Object o) {
            Option op = (Option) o;
            if (op.val > this.val) return -1;
            else if (op.val < this.val) return 1;
            return 0;
        }

        @Override
        public String toString() {
            return "Option{" +
                    "pos=" + pos +
                    ", val=" + val +
                    '}';
        }
    }
}
