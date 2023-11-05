#include "string"

using namespace std;

/*
 * TODO:主函数写在这里
 *  注：运行测试时请在 {main} 前面加上 {_} 即：修改为 {_main}
 *      否则 doctest 将无法运行
 * */
int _main() {
    bool continue = true;
    string inputStr;
    char judge ;

    while(continue){

    cout << "支持的运算符：+ - * / ^ & ( )" << endl;
    cout << "请输入算式(以'='结束)：" << endl;
    getline(cin,inputStr);
        while(inputStr[inputStr.length() - 1] != '='){
            cout << "格式有误，请重新输入" << endl;
            getline(cin,inputStr);
        }
    Formula* formula1 = new Formula(inputStr);

    cout << formula1->getResult()<<endl;

    cout<<"是否继续（Y/N）?"<<endl;
    cin >> judge;
    continue = judge == 'Y'? true : false;
    }
    
    return 0;
}
