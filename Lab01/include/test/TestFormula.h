#ifndef TEST_TESTFORMULA_H
#define TEST_TESTFORMULA_H

#include "doctest/doctest.h"
#include "calc/Calc.h"
#include "formula/Formula.h"

/*
 * TODO: 整个式子计算结果单元测试
 */
TEST_CASE("Test Formula.h") {
    SUBCASE("Test1") {
        string a = "-1 + (6 + 2) * 3";

        Formula *formula = new Formula(a);
        string actual = formula->getResult();
        string expect = Calc::convert("23");

        CHECK_EQ(expect, actual);
    }

    SUBCASE("Test2") {
        string a = "-12/ (-23) ^ (4 + -2)*3";

        Formula *formula = new Formula(a);
        string actual = formula->getResult();
        string expect = Calc::convert("-0.068052");

        CHECK_EQ(expect, actual);
    }
}

#endif //TEST_TESTFORMULA_H
