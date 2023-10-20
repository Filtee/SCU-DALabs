#include <gtest/gtest.h>
#include "LStack.h"

TEST(addCase1, should) {
    LStack<int> stack;
    int nums[]{1, 2, 3, 4, 5, 6, 7, 2, 9, 3, 5};
    for (int num: nums) {
        stack.push(num);
    }

    for (int i = 10; i != -1; --i) {
        ASSERT_EQ(nums[i], stack.pop());
    }
}