package main;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// 这个类用来通过读取文件来获取其中各个字符出现的频率
public class GetFrequency {

    // 空参构造方法
    public GetFrequency(){}

    // 用来获取字符出现的频率的方法
    //静态方法不用实例化即可调用
    public static Map<Character,Integer> getFrequency(String filePath) throws IOException {

        Map<Character,Integer> charFrequency = new HashMap<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            int curChar ;

            // 逐字符读取文件内容
            while((curChar = reader.read()) != -1){
                char character = (char) curChar;

                // 更新字符频率
                charFrequency.put(character,charFrequency.getOrDefault(character,0) + 1);
            }
        }

        return charFrequency;
    }

    //定义了将Map写入文件的静态方法
    public static void writeMapToFile(Map<Character, Integer> map, String fileName) {
        try (BufferedWriter writer1 = new BufferedWriter(new FileWriter(fileName))) {
            // 遍历Map的键值对，将其写入文件
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                writer1.write(entry.getKey() + ": " + entry.getValue());
                writer1.newLine();  // 换行
            }
            System.out.println("频度集写入文件成功！");
        } catch (IOException e) {
            System.err.println("写入文件时发生错误：" + e.getMessage());
        }
    }
}