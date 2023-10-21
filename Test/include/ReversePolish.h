#ifndef TEST_REVERSEPOLISH_H
#define TEST_REVERSEPOLISH_H

#include "string"
using namespace std;

/*
 * TODO: 逆波兰式转换类
 *  不需要实例化
 *  工作原理:
 *      通过调用{ReversePolish::convert(str)}获得由输入的字符串{str}
 *      转换而来的存储式子逆波兰式的栈;
 *      其中逆波兰式中所有元素均为{string}类型, 仅含一个字符的运算符, 诸
 *      如:+ - * / 等, 均存放为{string}类型
 */
class ReversePolish {
public:
    /*
     * TODO: 将输入的字符串转换成逆波兰式
     *  返回值为{LStack<string>*}类型, 即存储逆波兰式的栈的指针
     */
    static LStack<string>* convert(const string &str) {
        return nullptr;
    }
};

#endif //TEST_REVERSEPOLISH_H
