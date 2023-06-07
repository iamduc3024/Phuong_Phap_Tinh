#include <iostream>
#include <cmath>

using namespace std;

void input(int n, double** a, double* b) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> a[i][j];
        }
        cin >> b[i];
    }
}

void gaussJordan(int n, double** a, double * b) {
    for (int k = 0; k < n; k++) {
        for (int i = k + 1; i < n; i++) {
            if (a[i][k] > a[k][k]) {
                for (int j = 0; j < n; j++) {
                    double temp = a[i][j];
                    a[i][j] = a[k][j];
                    a[k][j] = temp;
                }
                swap(b[i], b[k]);
                break;
            }
        }
        double pivot = a[k][k];
        for (int j = k; j < n; j++) {
            a[k][j] /= pivot;
        }
        b[k] /= pivot;
        for (int i = 0; i < n; i++) {
            if (i == k || a[i][k] == 0) {
                continue;
            }
            double factor = a[i][k];
            for (int j = k; j < n; j++) {
                a[i][j] -= factor * a[k][j];
            }
            b[i] -= factor * b[k];
        }
    }
}

int main() {
    int n;
    cin >> n;
    double** a = new double*[n];
    for (int i = 0; i < n; i++) {
        a[i] = new double[n];
    }
    double* b = new double[n];
    input(n, a, b);
    gaussJordan(n, a, b);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cout << a[i][j] << " ";
        }
        cout << b[i] << endl;
    }
    return 0;
}