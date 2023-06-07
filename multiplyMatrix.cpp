#include <iostream>
#include <cmath>
#include <cstring>

using namespace std;

void inputMatrix(double** matrix, int n, int m) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> matrix[i][j];
        }
    }
}

double** multiplyMatrix(double** matrixA, double** matrixB, int n, int m, int k) {
    double** multiplyOfTwoMatrix = new double*[n];
    for (int i = 0; i < n; i++) {
        multiplyOfTwoMatrix[i] = new double[k];
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < k; j++) {
            multiplyOfTwoMatrix[i][j] = 0;
            for (int p = 0; p < m; p++) {
                multiplyOfTwoMatrix[i][j] += (matrixA[i][p] * matrixB[p][j]);
            }
        }
    }
    return multiplyOfTwoMatrix;
}

int main() {
    int n, m, k;
    cin >> n >> m >> k;
    double** matrixA = new double*[n];
    for (int i = 0; i < n; i++) {
        matrixA[i] = new double[m];
    }
    inputMatrix(matrixA, n, m);
    double** matrixB = new double*[m];
    for (int i = 0; i < m; i++) {
        matrixB[i] = new double[k];
    }
    inputMatrix(matrixB, m, k);
    double** multiplyOfTwoMatrix = multiplyMatrix(matrixA, matrixB, n, m, k);
    cout << endl;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < k; j++) {
            cout << multiplyOfTwoMatrix[i][j] << " ";
        }
        cout << endl;
    }
    return 0;
}