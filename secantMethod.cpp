#include <iostream>
#include <cmath>

using namespace std;

// x(n) = {[x(n - 1) * f(x(n - 2))] - [x(n - 2) * f(x(n - 1))]} / [f(x(n - 2)) - f(x(n - 1))]

pair<double, double> v[100]; // v.first để lưu hệ số, v.second đẻ lưu số mũ
double result[100];

void inputEquation(int n) {
    for (int i = 1; i <= n; i++) {
        cin >> v[i].first >> v[i].second;
    }
}

// Hàm đê tính f(x)
double function(int n, double x) {
    double resultOfFunction = 0;
    for (int i = 1; i <= n; i++) {
        resultOfFunction += (v[i].first * pow(x, v[i].second));
    }
    return resultOfFunction;
}

// Hàm để tính các giá trị xi
void prepareSecantMethod(int n) {
    for (int i = 2; i < 100; i++) {
        result[i] = (result[i - 2] * function(n, result[i - 1]) - result[i - 1] * function(n, result[i - 2])) / (function(n, result[i - 1]) - function(n, result[i - 2]));
    }
}

double secantMethod(int n, double epsilon) {
    double finalResult;
    for (int i = 1; i < 100; i++) {
        if (abs(result[i] - result[i - 1]) < epsilon) {
            finalResult = result[i];
        }
    }
    return finalResult;
}

int main() {
    int n;
    cin >> n;
    inputEquation(n);
    double epsilon;
    cin >> result[0] >> result[1] >> epsilon;
    prepareSecantMethod(n);
    cout << result[5];
    return 0;
}