import java.util.Scanner;

public class binh_phuong_toi_thieu {
    public static double[] solve(double[][] coefficients, double[] constants) {
        int rowCount = coefficients.length;
        int columnCount = coefficients[0].length;

        // Tạo ma trận mở rộng gồm ma trận hệ số và ma trận hằng số
        double[][] augmentedMatrix = new double[rowCount][columnCount + 1];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                augmentedMatrix[i][j] = coefficients[i][j];
            }
            augmentedMatrix[i][columnCount] = constants[i];
        }

        // Áp dụng phương pháp Gauss-Jordan
        for (int pivot = 0; pivot < rowCount; pivot++) {
            // Tìm hàng có giá trị tuyệt đối lớn nhất trong cột pivot
            int maxRowIndex = pivot;
            double maxRowValue = Math.abs(augmentedMatrix[pivot][pivot]);
            for (int i = pivot + 1; i < rowCount; i++) {
                double currentRowValue = Math.abs(augmentedMatrix[i][pivot]);
                if (currentRowValue > maxRowValue) {
                    maxRowValue = currentRowValue;
                    maxRowIndex = i;
                }
            }

            // Hoán đổi hàng có giá trị tuyệt đối lớn nhất lên đầu cột pivot
            double[] temp = augmentedMatrix[pivot];
            augmentedMatrix[pivot] = augmentedMatrix[maxRowIndex];
            augmentedMatrix[maxRowIndex] = temp;

            // Chia hàng pivot cho phần tử pivot để đưa phần tử pivot về 1
            double pivotValue = augmentedMatrix[pivot][pivot];
            for (int j = pivot; j < columnCount + 1; j++) {
                augmentedMatrix[pivot][j] /= pivotValue;
            }

            // Loại bỏ các phần tử trong cột pivot bên dưới phần tử pivot
            for (int i = 0; i < rowCount; i++) {
                if (i != pivot) {
                    double factor = augmentedMatrix[i][pivot];
                    for (int j = pivot; j < columnCount + 1; j++) {
                        augmentedMatrix[i][j] -= factor * augmentedMatrix[pivot][j];
                    }
                }
            }
        }

        // Trích xuất nghiệm từ ma trận mở rộng
        double[] solution = new double[rowCount];
        for (int i = 0; i < rowCount; i++) {
            solution[i] = augmentedMatrix[i][columnCount];
        }

        return solution;
    }
    public static double calculateFunction(double x) {
//        return Math.pow(Math.E, - x * x) / Math.pow(2 * Math.PI, -0.5);
        return Math.cos(x) * Math.pow(Math.E, 2 * x);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = 0;
        double b = Math.PI / 2;
        int n = 50; // n la so diem moc
        double h = (b - a) / n;
        double[] x = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            x[i] = a + i * h;
        }
        int lv = 10; // lv la bac cua phuong trinh
        for (int p = 1; p <= lv; p++) {
            double[][] matrix = new double[p + 1][p + 1];
            for (int i = 0; i <= p; i++) {
                for (int j = 0; j <= p; j++) {
                    double temp = 0;
                    for (int k = 0; k <= n; k++) {
                        temp += Math.pow(x[k], i + j);
                    }
                    matrix[i][j] = temp;
                }
            }
            double[] y = new double[lv + 1];
            for (int i = 0; i <= p; i++) {
                double temp = 0;
                for (int j = 0; j <= n; j++) {
                    temp += Math.pow(x[j], i) * calculateFunction(x[j]);
                }
                y[i] = temp;
            }
            double[] result = solve(matrix, y);
            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i]);
            }
            System.out.println();
            double temp = 0;
            for (int i = result.length - 1; i >= 0; i--) {
                temp += result[i] * Math.pow(Math.PI / 16, i);
            }
            System.out.println(temp - calculateFunction(Math.PI / 16));
        }
//        double[][] matrix = new double[lv + 1][lv + 1];
//        for (int i = 0; i <= lv; i++) {
//            for (int j = 0; j <= lv; j++) {
//                double temp = 0;
//                for (int k = 0; k <= n; k++) {
//                    temp += Math.pow(x[k], i + j);
//                }
//                matrix[i][j] = temp;
//            }
//        }
//        double[] y = new double[lv + 1];
//        for (int i = 0; i <= lv; i++) {
//            double temp = 0;
//            for (int j = 0; j <= n; j++) {
//                temp += Math.pow(x[j], i) * calculateFunction(x[j]);
//            }
//            y[i] = temp;
//        }
//        double[] result = solve(matrix, y);
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(result[i]);
//        }
//        System.out.println();
//        double temp = 0;
//        for (int i = result.length - 1; i >= 0; i--) {
//            temp += result[i] * Math.pow(Math.PI / 16, i);
//        }
//        System.out.println(temp - calculateFunction(Math.PI / 16));
    }
}
