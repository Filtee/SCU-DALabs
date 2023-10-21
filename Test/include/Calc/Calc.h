//
// Created by Christopher on 2023/10/21.
//

#ifndef TEST_CALC_H
#define TEST_CALC_H


#include "string"
using namespace std;

class Calc {
public:
    static string add(const string &strA, const string &strB) {
        double numA = stod(strA);
        double numB = stod(strB);
        return to_string(numA + numB);
    }

    static string sub(const string &strA, const string &strB) {
        double numA = stod(strA);
        double numB = stod(strB);
        return to_string(numA - numB);
    }

    static string mul(const string &strA, const string &strB) {
        double numA = stod(strA);
        double numB = stod(strB);
        return to_string(numA * numB);
    }

    static string dev(const string &strA, const string &strB) {
        double numA = stod(strA);
        double numB = stod(strB);
        return to_string(numA / numB);
    }

    static string fDev(const string &strA, const string &strB) {
        int numA = stoi(strA);
        int numB = stoi(strB);
        return to_string((int) numA / numB);
    }

    static string mod(const string &strA, const string &strB) {
        int numA = stoi(strA);
        int numB = stoi(strB);
        return to_string(numA % numB);
    }
};

#endif //TEST_CALC_H
