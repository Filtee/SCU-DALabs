#ifndef TEST_FORMULA_H
#define TEST_FORMULA_H

#include "assert.h"

#include "string"
using namespace std;

#include "Stack/LStack.h"
#include "Calc/Calc.h"

class Formula {
private:
    LStack<string> *elements;

    bool isNum(char ch) {
        return '0' < ch && ch < '9';
    }


public:
    Formula() {
        elements = new LStack<string>();
    }

    void clear() {
        elements->clear();
    }

    void store(string str) {

    }

};

#endif //TEST_FORMULA_H
