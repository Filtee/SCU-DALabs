#ifndef TEST_TESTSTACK_H
#define TEST_TESTSTACK_H

#include "doctest/doctest.h"
#include "Stack/LStack.h"

TEST_CASE("Testing stack.h") {
    LStack<int> stack;
    int nums[]{1, 2, 3, 4, 5, 6, 7, 2, 9, 3, 5};
    for (int num: nums) {
        stack.push(num);
    }

    for (int i = 10; i != -1; --i) {
        CHECK_EQ(nums[i], stack.pop());
    }
}

#endif //TEST_TESTSTACK_H
