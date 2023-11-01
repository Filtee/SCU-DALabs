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

    // 式子队列 保存转换出来的逆波兰式 {string}
    Queue<string> *formulaQueue;

    // 字符栈 将以{string}类型输入的字符串的每个字符放入栈中
    Stack<char> *charStack;

    // 运算符栈 保存所有{charStack}中的运算符
    // {Operator}结构体形式 {char}成员保存运算符 {int}成员保存优先级
    Stack<Operator> *opStack;

    // 保存上一个{opStack}中弹出来的上一个{char}
    char lastChar;

    /* 初始化所有的栈和队列 */
    void initialize(const string &str) {
        formulaQueue = new Queue<string>();
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

    /*
     * 解决数字
     * 如果下一个字符是数字 则将其之后的所有数字字符放入str中
     */
    void dealWithNum(char op = 0) {
        string str;

        // 如果数字前有正负号 将其保存到{str中}
        if (op) {
            str.push_back(op);
        }
        // 将本次弹出来的字符保存到{lastChar}中
        lastChar = charStack->topValue();
        // 将接下来的数字符号都保存到{str}中
        while (charStack->length() != 0
               && isNum(charStack->topValue())) {
            str.push_back(charStack->pop());
        }
        formulaQueue->add(str);
    }

    /*
     * 解决运算符
     * 如果下一个字符是运算符 判断运算符的类型
     * 并以此判断需要使用的对应的处理方式
     */
    void dealWithOperator() {
        // 将弹出字符栈中的下一个字符保存到结构体中
        Operator opStruct;
        opStruct.op = charStack->pop();

        if (opStruct.op == '(') {
            // 如果为前括号 将前括号保存到运算符栈中 优先级为0
            opStruct.priority = 0;
            opStack->push(opStruct);

        } else if (opStruct.op == ')') {
            // 如果为后括号 调用{dealWithBracket}函数
            // 将运算符栈中的所有运算符放入式子队列 直至遇到前括号
            dealWithBracket();

        } else {
            /*
             * TODO: 填充更多的运算符 && 完成对于负号的处理
             *  如果为运算符 根据运算符类型为其附加运算优先级
             */
            switch (opStruct.op) {
                case '+':
                case '-':
                    if (isNum(lastChar)) {
                        // 上一个处理的字符为数字 则不为正负号 而是加减二元运算符
                        opStruct.priority = 1;
                    } else {
                        // 上一个处理的字符不为数字 则为正负号 与接下来的数字一同保存
                        dealWithNum(opStruct.op);
                        return;
                    }
                    break;
                case '*':
                case '/':
                    opStruct.priority = 2;
                    break;
                case '^':
                    opStruct.priority = 3;
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
                formulaQueue->add(str);
            }

            // 将生成的结构体压入运算符栈中
            opStack->push(opStruct);
            // 将本次弹出来的字符保存到{lastChar}中
            lastChar = opStruct.op;
        }
    }

    // 操作符
    void clearOperator() {
        string str;
        while (opStack->length() != 0) {
            str = "";
            str.push_back(opStack->pop().op);
            formulaQueue->add(str);
        }
    }

    // 解决括号
    // 将运算符栈中的所有运算符放入式子队列 直至遇到前括号
    void dealWithBracket() {
        string str;
        // 如果没有遇到前括号 将遇到的运算符放入式子队列
        while (opStack->topValue().op != '(') {
            str = "";
            str.push_back(opStack->pop().op);
            formulaQueue->add(str);
        }
        // pop掉运算符栈中的前括号
        opStack->pop();
    }

    // 将式子队列转换为包含逆波兰式的栈返回
    Stack<string> *toStack() {
        Stack<string> *ret = new Stack<string>();
        while (formulaQueue->length() != 0) {
            ret->push(formulaQueue->removeFirst());
        }
        return ret;
    }

public:
    /*
     * 将输入的字符串转换成逆波兰式
     *  返回值为{LStack<string>*}类型, 即存储逆波兰式的栈的指针
     */
    Stack<string> *convert(const string &str) {
        initialize(str);

        while (charStack->length() != 0) {
            // 移除空格
            if (charStack->topValue() == ' ') {
                charStack->pop();
            }
            // 判断是数字还是运算符 采取相应的方法解决
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
        // 转换为{string}类型
        string ret;
        while (stack->length() != 0) {
            ret.append(stack->pop());
        }
        return ret;
    }
};

#endif //TEST_REVERSEPOLISH_H
