import java.util.Scanner;

public class gaussSeidel {
    public static double[][] inverseMatrix(double[][] matrix) {
        int n = matrix.length;

        // Tạo ma trận nhận kết quả
        double[][] result = new double[n][n*2];

        // Sao chép ma trận đầu vào vào ma trận kết quả và thêm ma trận đơn vị vào bên phải
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, result[i], 0, n);
            result[i][i+n] = 1;
        }

        // Áp dụng phương pháp Gauss-Jordan để chuyển ma trận về ma trận đơn vị
        for (int i = 0; i < n; i++) {
            // Đặt giá trị chéo chính tại vị trí (i, i) về 1 bằng cách chia hàng i cho giá trị tại (i, i)
            double pivot = result[i][i];
            for (int j = 0; j < 2*n; j++) {
                result[i][j] /= pivot;
            }

            // Loại bỏ giá trị khác 0 ở cột i bằng cách trừ các hàng khác với hàng i nhân với một hệ số thích hợp
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double coefficient = result[k][i];
                    for (int j = 0; j < 2*n; j++) {
                        result[k][j] -= coefficient * result[i][j];
                    }
                }
            }
        }

        // Lấy ma trận nghịch đảo từ ma trận kết quả
        double[][] inverse = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(result[i], n, inverse[i], 0, n);
        }

        return inverse;
    }

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
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(a[i][j] + " ");
//            }
//            System.out.println();
//        }
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
        double[][] l = new double[n][n];
        double[][] u = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    l[i][j] = a[i][j];
                    u[i][j] = 0;
                } else if (i < j) {
                    u[i][j] = a[i][j];
                    l[i][j] = 0;
                } else {
                    l[i][j] = 0;
                    u[i][j] = 0;
                }
            }
        }
        double[][] iPlusL = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                iPlusL[i][j] = iMatrix[i][j] + l[i][j];
            }
        }
        double[][] iPlusLInverse = inverseMatrix(iPlusL);
        double[][] negativeIPlusLInverse = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                negativeIPlusLInverse[i][j] = iPlusLInverse[i][j] * -1;
                if (negativeIPlusLInverse[i][j] == -0) {
                    negativeIPlusLInverse[i][j] = Math.abs(negativeIPlusLInverse[i][j]);
                }
            }
        }
        double[][] c = multiplyMatrix(negativeIPlusLInverse, u);
        double[][] d = multiplyMatrix(iPlusLInverse, b);
//        for (int i = 0; i < n; i++) {
//            System.out.println(d[i][0]);
//        }
        int k = scanner.nextInt();
        double[][][] x = new double[k][n][1];
        for (int i = 0; i < n; i++) {
            x[0][i][0] = scanner.nextDouble();
        }
        for (int i = 1; i < k; i++) {
            x[i] = multiplyMatrix(c, x[i - 1]);
            for (int j = 0; j < n; j++) {
                x[i][j][0] += d[j][0];
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(x[2][i][0]);
        }
    }
}
