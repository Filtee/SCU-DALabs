#ifndef TEST_FORMULA_H
#define TEST_FORMULA_H

#include "string"
#include "iostream"
using namespace std;

#include "ADT/Stack.h"
#include "calc/Calc.h"
#include "ReversePolish.h"

/*
 * TODO: 公式计算类
 *  实例化: 调用 {Formula()} / {Formula(str)} 以生成实例
 *  工作原理:
 *  1. 调用 {formula->store(str)} 来存储输入的公式
 *      1> 输入的字符串将先被转换成逆波兰式(以Stack的形式返回)后, 存储为私有空间中的 {element}
 *          若{element}原先不为null, 则先销毁先前存储的{ADT}, 再将得到的逆波兰式栈存入
 *      2> 存入后进行计算, 运算过程交给{calc.h}或{IntelliCalc.h}头文件中包含的计算公式, 调
 *          用语法如{calc::Add(strA, strB)}; 输入的两个参数均为{string}类型, 返回的结果也
 *          为{string}类型
 *  2. 调用 {formula->result()} 来获取公式计算结果
 */
class Formula {
private:
    // 存储的逆波兰式
    Stack<string> *formula;
    // 计算得到的结果
    string result = "";

    /*
     * TODO: 调用逆波兰式进行计算
     *  运算过程交给{calc.h}或{IntelliCalc.h}头文件中包含的计算公式,
     *  调用语法如{calc::Add(strA, strB)}; 输入的两个参数均为{string}
     *  类型, 返回的结果也为{string}类型
     */
    void proceed() {
        //定义一个用来存储运算过程的栈
        Stack<string> *tempResult = new Stack<string>;
        while (formula->length() != 0) {
            string str = formula->pop();
            //判断该字符串为数字还是操作符并进行操作
            switch (NorO(str)) {
                case 1:
                    str.erase(0, 1);
                    tempResult->push(str);
                    break;
                default:
                    //遇到符号，就从栈中提数
                    //由于逆波兰式中前两个数（对应这里的字符串）一定是数，故不用担心提不出两个数的问题
                    string num1 = tempResult->pop();
                    string num2 = tempResult->pop();
                    str.erase(0, 1);
                    if (str == "+") {
                        tempResult->push(Calc::add(num1, num2));
                    } else if (str == "-") {
                        tempResult->push(Calc::sub(num2, num1));
                    } else if (str == "*") {
                        tempResult->push(Calc::mul(num1, num2));
                    } else if (str == "/") {
                        //除法运算这里暂时选用精度更高的dev()，保证后续不出错
                        tempResult->push(Calc::dev(num2, num1));
                    } else if (str == "%") {
                        tempResult->push(Calc::mod(num2, num1));
                    } else if (str == "&") {
                        //虽然Calc中定义的power方法不仅限于开方，但由于更高阶的开次方键盘上暂时没有对应的符号，故此处只做了开方
                        tempResult->push(Calc::power(num1, "2"));
                    } else if (str == "^") {
                        tempResult->push(Calc::power(num2, num1));
                    } else if (str == "!") {
                        tempResult->push(Calc::fac(num1));
                    } else {
                    }
            }
        }
        //弹出栈里最后仅剩的string，即运算结果
        result.append(tempResult->pop());
    }

    //如果是n，返回1，说明接下来的部分是数字；
    //如果不是n,是o，返回0，说明接下来的部分是运算符
    int NorO(string str) {
        return str[0] == 'n';
    };

public:
    // Constructor
    Formula(string str = nullptr) {
        store(str);
        formula = new Stack<string>();
    }

    // Destructor
    ~Formula() {
        clear();
    }

    void clear() {
        formula->clear();
        result = nullptr;
    }

    /*
     * 存储转换后得到的逆波兰式
     */
    void store(string str) {
        delete formula;
        formula = ReversePolish::convert(str);
        // 运算
        proceed();
    }

    /*
     * 返回结果
     */
    string getResult() {
        return result;
    }
};

#endif //TEST_FORMULA_H
