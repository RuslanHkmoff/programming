//import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;

public class ReverseTranspose {
    public static void main(String[] args) throws IOException, NumberFormatException {
        int rows = 0, columns = 0, maxColumns = 0;
        int it = 0;
        Scanner scStrings = new Scanner(System.in);
        int[][] matrix = new int[10000][2];
        String str = scStrings.nextLine();
        while (str != null) {
            Scanner scNumbers = new Scanner(str);
            String currNumber = scNumbers.next();
            while (currNumber != null) {
                if (columns == matrix[it].length) {
                    matrix[it] = Arrays.copyOf(matrix[it], matrix[it].length * 2);

                }
              //  huinyaCheck = Integer.parseInt(currNumber);
                int input = Integer.parseInt(currNumber);
                if (input == 0) {
                    matrix[it][columns] = 1000001;
                } else {
                    matrix[it][columns] = input;
                }
                columns++;
                currNumber = scNumbers.next();
            }
            str = scStrings.nextLine();
            if (columns > maxColumns) {
                maxColumns = columns;
            }
            columns = 0;
            rows++;
            it++;
        }
        int[][] ans = new int[maxColumns][rows];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                if (matrix[x][y] == 0) {
                    break;
                } else {
                    ans[y][x] = matrix[x][y];
                }
            }
        }
        for (int p = 0; p < maxColumns; p++) {
            for (int q = 0; q < rows; q++) {
                int temp = ans[p][q];
                if (temp != 0 && temp != 1000001) {
                    System.out.print(temp + " ");
                } else if (temp == 1000001) {
                    temp = 0;
                    System.out.print(temp + " ");
                }
            }
            System.out.println();
        }
    }
}