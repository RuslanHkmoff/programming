import java.io.IOException;
import java.util.Arrays;

public class ReverseAbc {
    public static void main(String[] args) throws IOException, NullPointerException {
        Scanner scStrings = new Scanner(System.in);
        String[][] matrix = new String[3][3];
        int rows = 0, columns = 0;
        try {
            String line = scStrings.nextLine();
            while (line != null) {
                Scanner scNumbers = new Scanner(line);
                String st = scNumbers.next();
                while (st != null) {
                    if (columns == matrix[rows].length) {
                        matrix[rows] = Arrays.copyOf(matrix[rows], matrix[rows].length * 2);
                    }
                    matrix[rows][columns] = st;
                    columns++;
                    st = scNumbers.next();
                    scNumbers.close();
                }
                columns = 0;
                rows++;
                if (rows == matrix.length) {
                    matrix = Arrays.copyOf(matrix, 2 * matrix.length);
                }
                if (matrix[rows] == null) {
                    matrix[rows] = new String[10];
                }
                line = scStrings.nextLine();
            }
        } finally {
            scStrings.close();
        }
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = matrix[i].length - 1; j >= 0; j--) {
                if (matrix[i][j] != null) {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}





