package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "./test/java/resources/f1.txt";

        Map<Character,Integer> getFrequency = new HashMap<>();
        getFrequency = GetFrequency.getFrequency(filePath);

        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.Store(getFrequency);
        Map<Character,String> codeTable = huffmanTree.getCodeTable();

        //初级要求：把codeTable打印在终端上
        for(Map.Entry<Character,String> entry : codeTable.entrySet()){
            System.out.println("Character:" + entry.getKey() + ", Code:" + entry.getValue());
        }

        //中级要求

        //高级要求

    }
}
