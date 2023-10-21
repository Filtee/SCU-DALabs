#ifndef TEST_CALC_H
#define TEST_CALC_H

#include "string"
using namespace std;

/*
 * TODO:愚蠢算法
 *  无需实例化
 *  工作原理：
 *      将输入的两个{string}类型的变量, 将它们转换为double类型进行非常
 *      愚蠢简单的加、减、乘、除、整除、模、开根号(未完成)、幂乘(未完成)、
 *      阶乘(未完成)运算
 *      返回值均为{string}类型
 */

class Calc {
private:
    // 所有的 helper functions 在这里

public:
    // 加法运算
    static string add(const string &strA, const string &strB) {
        double numA = stod(strA);
        double numB = stod(strB);
        return to_string(numA + numB);
    }

    // 减法运算
    static string sub(const string &strA, const string &strB) {
        double numA = stod(strA);
        double numB = stod(strB);
        return to_string(numA - numB);
    }

    // 乘法运算
    static string mul(const string &strA, const string &strB) {
        double numA = stod(strA);
        double numB = stod(strB);
        return to_string(numA * numB);
    }

    // 除法运算
    static string dev(const string &strA, const string &strB) {
        double numA = stod(strA);
        double numB = stod(strB);
        return to_string(numA / numB);
    }

    // 整除运算
    static string fDev(const string &strA, const string &strB) {
        int numA = stoi(strA);
        int numB = stoi(strB);
        return to_string((int) numA / numB);
    }

    // 模运算
    static string mod(const string &strA, const string &strB) {
        int numA = stoi(strA);
        int numB = stoi(strB);
        return to_string(numA % numB);
    }

    // TODO: 开根号(二阶/三阶/...)
    static string root(const string &strA, const string &strB) {
        return nullptr;
    }

    // TODO: 幂乘运算
    static string pow(const string &strA, const string &strB) {
        return nullptr;
    }

    // TODO: 阶乘运算
    static string fac(const string &strA) {
        return nullptr;
    }
};

#endif //TEST_CALC_H
