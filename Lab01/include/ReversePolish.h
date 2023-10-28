#ifndef TEST_REVERSEPOLISH_H
#define TEST_REVERSEPOLISH_H

#include "assert.h"
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
private:
    typedef struct {
        char op;
        int priority;
    } Operator;

    Queue<string> *numQueue;

    Stack<char> *charStack;

    Stack<Operator> *opStack;


    /* 初始化 */
    void initialize(const string &str) {
        numQueue = new Queue<string>();
        opStack = new Stack<Operator>();
        charStack = new Stack<char>();
        for (int i = str.length() - 1; i != -1; --i) {
            char ch = str.at(i);
            charStack->push(ch);
        }
    }

    // 判断是否为数字
    int isNum(char ch) {
        return ch >= '0' && ch <= '9';
    }

    // 解决数字
    void dealWithNum() {
        string str;
        while (charStack->length() != 0
               && isNum(charStack->topValue())) {
            str.push_back(charStack->pop());
        }
        numQueue->add(str);
    }

    // 解决运算符
    void dealWithOperator() {
        Operator opStruct;
        opStruct.op = charStack->pop();

        if (opStruct.op == '(') {
            opStruct.priority = 0;
            opStack->push(opStruct);

        } else if (opStruct.op == ')') {
            dealWithBracket();

        } else {
            switch (opStruct.op) {
                case '+':
                case '-':
                    opStruct.priority = 1;
                    break;
                case '*':
                case '/':
                    opStruct.priority = 2;
                    break;
                default:
                    assert("非法输入!");
            };

            // 运算符优先级高于栈内的运算符 => 入栈 (或栈空)
            // 否则 从堆栈中pop所有优先级更高或一样的运算符(或直到括号) 再让当前运算符入栈
            string str;
            while (opStack->length() != 0 &&
                   opStruct.priority <= opStack->topValue().priority) {
                str = "";
                str.push_back(opStack->pop().op);
                numQueue->add(str);
            }
            opStack->push(opStruct);
        }
    }

    void clearOperator() {
        string str;
        while (opStack->length() != 0) {
            str = "";
            str.push_back(opStack->pop().op);
            numQueue->add(str);
        }
    }

    // 解决括号
    void dealWithBracket() {
        string str;
        while (opStack->topValue().op != '(') {
            str = "";
            str.push_back(opStack->pop().op);
            numQueue->add(str);
        }
        opStack->pop();
    }

    // 转换结果为栈
    Stack<string> *toStack() {
        Stack<string> *ret = new Stack<string>();
        while (numQueue->length() != 0) {
            ret->push(numQueue->removeFirst());
        }
        return ret;
    }

public:
    /*
     * TODO: 将输入的字符串转换成逆波兰式
     *  返回值为{LStack<string>*}类型, 即存储逆波兰式的栈的指针
     */
    Stack<string> *convert(const string &str) {
        initialize(str);

        while (charStack->length() != 0) {
            if (isNum(charStack->topValue())) {
                dealWithNum();
            } else {
                dealWithOperator();
            }
        }
        clearOperator();
        return toStack();
    }

    string toString(const string &str) {
        Stack<string> *stack = convert(str);
        string ret;
        while (stack->length() != 0) {
            ret.append(stack->pop());
        }
        return ret;
    }
};

#endif //TEST_REVERSEPOLISH_H
