import java.util.Scanner;

public class tim_tri_rieng_troi_nhat_va_vecto_rieng_troi_nhat_bang_pp_luy_thua {
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

    public static double[][] positionTransformMatrix(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] newMatrix = new double[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                newMatrix[i][j] = matrix[j][i];
            }
        }
        return newMatrix;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = scanner.nextDouble();
            }
        }
        int k = scanner.nextInt();
        double[][][] x = new double[k + 1][n][1];
        for (int i = 0; i < n; i++) {
            x[0][i][0] = scanner.nextDouble();
        }
        for (int i = 1; i <= k; i++) {
            double[][] aMulXi = multiplyMatrix(a, x[i - 1]);
            double temp = Double.MIN_VALUE;
            for (int j = 0; j < aMulXi.length; j++) {
                for (int p = 0; p < aMulXi[0].length; p++) {
                    if (aMulXi[j][p] > temp) {
                        temp = aMulXi[j][p];
                    }
                }
            }
            for (int j = 0; j < n; j++) {
                x[i][j][0] = aMulXi[j][0] / temp;
            }
        }
//        for (int i = 0; i < n; i++) {
//            System.out.println(x[2][i][0]);
//        }
        double[] lamda = new double[k];
        for (int i = 1; i <= k; i++) {
            double[][] aMulXi = multiplyMatrix(a, x[i]);
            double[][] aMulXiTransform = positionTransformMatrix(aMulXi);
            double[][] temp1 = multiplyMatrix(aMulXiTransform, x[i]);
            double[][] temp2 = multiplyMatrix(positionTransformMatrix(x[i]), x[i]);
            lamda[i - 1] = temp1[0][0] / temp2[0][0];
        }
        for (int i = 0; i < k; i++) {
            System.out.println(lamda[i]);
        }
    }
}
