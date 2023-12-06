package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int choice = 0;

        String filePath = "./test/java/resources/f3.txt";

        Map<Character, Integer> getFrequency = new HashMap<>();
        getFrequency = GetFrequency.getFrequency(filePath);

        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.Store(getFrequency);
        Map<Character, String> codeTable = huffmanTree.getCodeTable();

        while(true) {
            //用户界面：
            System.out.println("--------------------欢迎使用huffman编码器---------------------");
            System.out.println("操作命令说明：");
            System.out.println("统计输入文件的字符频度并堆字符集编码并输出（基本要求）：1");
            System.out.println("           对整个文件编码并保存编码后结果（中级要求）：2");
            System.out.println("                               文件解码（高级要求）：3");
            System.out.println("                                              退出：4");

            //获取用户输入的指令
            System.out.println("请输入命令：");
            choice = sc.nextInt();

            switch(choice) {
                //初级要求：
                case 1:
                    //把频度集打印在终端上
                    for(Map.Entry<Character, Integer> entry : getFrequency.entrySet()) {
                        System.out.println("Character:" + entry.getKey() + ", Frequency:" + entry.getValue());
                    }

                    System.out.println("-------------------------------");
                    // 把codeTable打印在终端上
                    for(Map.Entry<Character, String> entry : codeTable.entrySet()) {
                        System.out.println("Character:" + entry.getKey() + ", Code:" + entry.getValue());
                    }
                    break;
                //中级要求:
                case 2:

                    break;
                //高级要求
                case 3:

                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("无效命令，请重新输入！");
            }
        }
    }
}
