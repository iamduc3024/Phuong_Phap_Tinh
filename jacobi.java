import java.util.Scanner;

public class jacobi {
    public static double[][] multiplyMatrix(double[][] matrix1, double[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        // Kiểm tra số cột của ma trận 1 phải bằng số hàng của ma trận 2 để có thể nhân ma trận
        if (cols1 != rows2) {
            throw new IllegalArgumentException("Số cột của ma trận 1 phải bằng số hàng của ma trận 2");
        }

        // Tạo ma trận kết quả có kích thước phù hợp
        double[][] result = new double[rows1][cols2];

        // Thực hiện phép nhân ma trận
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[][] a = new double[n][n];
        double[][] b = new double[n][1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = scanner.nextDouble();
            }
            b[i][0] = scanner.nextDouble();
        }
        for (int i = 0; i < n; i++) {
            double temp = a[i][i];
            for (int j = 0; j < n; j++) {
                a[i][j] /= temp;
            }
            b[i][0] /= temp;
        }
        double[][] iMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    iMatrix[i][j] = 1;
                } else {
                    iMatrix[i][j] = 0;
                }
            }
        }
        double[][] iSubA = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                iSubA[i][j] = iMatrix[i][j] - a[i][j];
            }
        }
        int k = scanner.nextInt();
        double[][][] x = new double[k][n][1];
        for (int i = 0; i < n; i++) {
            x[0][i][0] = scanner.nextDouble();
        }
        for (int i = 1; i < k; i++) {
            x[i] = multiplyMatrix(iSubA, x[i - 1]);
            for (int j = 0; j < n; j++) {
                x[i][j][0] += b[j][0];
            }
        }
    }
}
