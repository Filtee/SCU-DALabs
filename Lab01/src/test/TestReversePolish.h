#ifndef TEST_TESTREVERSEPOLISH_H
#define TEST_TESTREVERSEPOLISH_H

#include "ReversePolish.h"
#include "doctest/doctest.h"
#include "ADT/Queue.h"

/*
 * 逆波兰式的转换单元测试
 */
TEST_CASE("Test ReversePolish.h") {
    SUBCASE("Test1") {
        string a = "-1 + (6 + 2) * 3";
        ReversePolish *rp = new ReversePolish();
        string actual = rp->toString(a);
        string expect;
        expect.append("-162+3*+");

        CHECK_EQ(expect, actual);
    }

    SUBCASE("Test2") {
        string a = "-12/ (-23) ^ (-6 + 2)*3";
        ReversePolish *rp = new ReversePolish();
        string actual = rp->toString(a);
        string expect;
        expect.append("-12-23-62+^/3*");

        CHECK_EQ(expect, actual);
    }

    SUBCASE("Test3") {
        string a = "-1948/784-112+(-65 + 32) /45";
        ReversePolish *rp = new ReversePolish();
        string actual = rp->toString(a);
        string expect;
        expect.append("-1948784/112--6532+45/+");

        CHECK_EQ(expect, actual);
    }
}

#endif //TEST_TESTREVERSEPOLISH_H
