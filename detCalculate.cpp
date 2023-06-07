#include <iostream>
#include <cmath>

using namespace std;

void inputMatrix(int** matrix, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> matrix[i][j];
        }
    }
}

double det(int** matrix, int n) {
    double result = 0;
    int** submatrix = new int*[n - 1];
    for (int i = 0; i < n - 1; i++) {
        submatrix[i] = new int[n - 1];
    }
    if (n == 2) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    } else {
        for (int x = 0; x < n; x++) {
            int subi = 0;
            for (int i = 1; i < n; i++) {
                int subj = 0;
                for (int j = 0; j < n; j++) {
                    if (j == x) {
                        continue;
                    }
                    submatrix[subi][subj] = matrix[i][j];
                    subj++;
                }
                subi++;
            }
            result += pow(-1, x) * matrix[0][x] * det(submatrix, n - 1);
        }
    }
    return result;
}

int main() {
    int n;
    cin >> n;
    int** matrix = new int*[n];
    for (int i = 0; i < n; i++) {
        matrix[i] = new int[n];
    }
    inputMatrix(matrix, n);
    cout << det(matrix, n);
    return 0;
}