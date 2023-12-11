package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        // 源文件路径
        String filePath = "./test/java/resources/f1.txt";
        // 编码输出文件路径
        String targetFilePath="result.txt";
        // 频度集路径
        String fFilePath="getFrequency.txt";
        // 解码输出文件路径
        String outputFilePath="recode.txt";

        Map<Character, Integer> getFrequency = new HashMap<>();
        getFrequency = GetFrequency.getFrequency(filePath);

        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.Store(getFrequency);
        Map<Character, String> codeTable = huffmanTree.getCodeTable();

        while(true) {
            //用户界面：
            System.out.println("--------------------欢迎使用huffman编码器---------------------");
            System.out.println("操作命令说明：");
            System.out.println("1：统计输入文件的字符频度并堆字符集编码并输出至文件（基本要求）");
            System.out.println("2：对整个文件编码并保存编码后结果（中级要求）");
            System.out.println("3：文件解码（高级要求）");
            System.out.println("4：退出");

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

                    // 调用静态方法将频度集写入文件getFrequency.txt
                    GetFrequency.writeMapToFile(getFrequency, "getFrequency.txt");
                    // 调用静态方法将码字集写入文件getCode.txt
                    HuffmanTree.writeMapToFile(codeTable,"getCode.txt");

                    break;
                //中级要求:
                case 2:
                    FileCoder.encodeFile(filePath,targetFilePath,fFilePath,huffmanTree);
                    break;
                //高级要求
                case 3:
                    FileCoder.decodeFile(outputFilePath,targetFilePath,fFilePath,huffmanTree);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("无效命令，请重新输入！！！");
            }
        }
    }
}
