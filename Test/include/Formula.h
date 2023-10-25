#ifndef TEST_FORMULA_H
#define TEST_FORMULA_H

#include "string"
using namespace std;

#include "ADT/Stack.h"
#include "Calc/Calc.h"
#include "ReversePolish.h"

/*
 * TODO: 公式计算类
 *  实例化: 调用 {Formula()} / {Formula(str)} 以生成实例
 *  工作原理:
 *  1. 调用 {formula->store(str)} 来存储输入的公式
 *      1> 输入的字符串将先被转换成逆波兰式(以Stack的形式返回)后, 存储为私有空间中的 {element}
 *          若{element}原先不为null, 则先销毁先前存储的{ADT}, 再将得到的逆波兰式栈存入
 *      2> 存入后进行计算, 运算过程交给{Calc.h}或{IntelliCalc.h}头文件中包含的计算公式, 调
 *          用语法如{Calc::Add(strA, strB)}; 输入的两个参数均为{string}类型, 返回的结果也
 *          为{string}类型
 *  2. 调用 {formula->result()} 来获取公式计算结果
 */
class Formula {
private:
    // 存储的逆波兰式
    Stack<string> *elements;
    // 计算得到的结果
    string result;

    bool isNum(char ch) {
        return '0' < ch && ch < '9';
    }

    /*
     * TODO: 调用逆波兰式进行计算
     *  运算过程交给{Calc.h}或{IntelliCalc.h}头文件中包含的计算公式,
     *  调用语法如{Calc::Add(strA, strB)}; 输入的两个参数均为{string}
     *  类型, 返回的结果也为{string}类型
     */
    void proceed() {

    }

public:
    // Constructor
    Formula(string str = nullptr) {
        store(str);
        elements = new Stack<string>();
    }

    // Destructor
    ~Formula() {
        clear();
    }

    void clear() {
        elements->clear();
        result = nullptr;
    }

    /*
     * 存储转换后得到的逆波兰式
     */
    void store(string str) {
        // 将输入的str转换成逆波兰式存储
        if (elements != nullptr) {
            delete elements;
        }
    }

    /*
     * 返回结果
     */
    string getResult() {
        return result;
    }
};

#endif //TEST_FORMULA_H
