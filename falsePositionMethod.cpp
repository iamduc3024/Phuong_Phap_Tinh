#include <iostream>
#include <cmath>

using namespace std;

// Phương pháp này giống secantMethod, nhưng cải tiến hơn, do khi f(a) * f(b) < 0 thì giữa a và b sẽ có ít nhất 1 nghiệm nên thay vì xét 2 số liền ngay sau thì t cần chọn 2 số trái dấu liền ngay sau xi để áp dụng CT

pair<double, double> v[100];
double result[100];

void inputEquation(int n) {
    for (int i = 1; i <= n; i++) {
        cin >> v[i].first >> v[i].second;
    }
}

double function(int n, double x) {
    double resultOfFunction = 0;
    for (int i = 1; i <= n; i++) {
        resultOfFunction += (v[i].first * pow(x, v[i].second));
    }
    return resultOfFunction;
}

void falsePositionMethod(int n) {
    for (int i = 2; i < 100; i++) {
        if (function(n, result[i - 1]) < 0) {
            for (int j = i - 2; j >= 0; j--) {
                if (function(n, result[j]) < 0) {
                    continue;
                } else {
                    result[i] = (result[i - 1] * function(n, result[j]) - result[j] * function(n, result[i - 1])) / (function(n, result[j]) - function(n, result[i - 1]));
                    break;
                }
            }
        } else {
            for (int j = i - 2; j >= 0; j--) {
                if (function(n, result[j]) > 0) {
                    continue;
                } else {
                    result[i] = (result[i - 1] * function(n, result[j]) - result[j] * function(n, result[i - 1])) / (function(n, result[j]) - function(n, result[i - 1]));
                    break;
                }
            }
        }
    }
}

int main() {
    int n;
    cin >> n;
    inputEquation(n);
    cin >> result[0] >> result[1];
    falsePositionMethod(n);
    cout << result[5];
    return 0;
}