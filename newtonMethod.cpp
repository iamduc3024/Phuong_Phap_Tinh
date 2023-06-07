#include <iostream>
#include <cmath>

using namespace std;

//x(n) = x(n - 1) + f(x(n - 1)) / f'(x(n - 1))

pair<double, double> v[100]; //v.first để lưu hệ số; v.second để lưu số mũ
double result[100];

void inputEquation(int n) {
    for (int i = 1; i <= n; i++) {
        cin >> v[i].first >> v[i].second;
    }
}

// Hàm để tính f(x)
double function(int n, double x) {
    double resultOfFunction = 0;
    for (int i = 1; i <= n; i++) {
        resultOfFunction += (v[i].first * pow(x, v[i].second));
    }
    return resultOfFunction;
}

// Hàm để tính f'(x)
double derivFunction(int n, double x) {
    double resultOfDerivFunction = 0;
    for (int i = 1; i <= n; i++) {
        resultOfDerivFunction += (v[i].first * v[i].second * pow(x, v[i].second - 1));
    }
    return resultOfDerivFunction;
}

void newtonMethod(int n) {
    for (int i = 1; i < 100; i++) {
        if (derivFunction(n, result[i - 1]) != 0) {
            result[i] = result[i - 1] - function(n, result[i - 1]) / derivFunction(n, result[i - 1]);
        }
    }
}

int main() {
    int n;
    cin >> n;
    inputEquation(n);
    cin >> result[0];
    newtonMethod(n);
    cout << result[4];
    return 0;
}